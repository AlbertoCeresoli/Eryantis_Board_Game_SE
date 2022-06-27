package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Messages.DisconnectionMessage;
import it.polimi.ingsw.Messages.EasyMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private String nickName;
    private final int id;
    private final Socket client;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private static final ArrayList<ClientHandler> clients = new ArrayList<>();
    private boolean connected;
    private String latestMessage;
    private boolean latestMessageValid;
    public boolean informationIsNeeded;
    private boolean yourTurn;
    private final Object lock;

    /**
     * Constructor which instantiates channel dedicated to the handling of each client
     *
     * @param clientSocket to connect to the specific client
     */
    public ClientHandler(Socket clientSocket) throws IOException {
        this.id = clients.size();
        this.client = clientSocket;
        this.in = new ObjectInputStream(clientSocket.getInputStream());
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        this.connected = true;
        this.latestMessage = "";
        this.latestMessageValid = false;
        this.yourTurn = false;
        this.lock = new Object();
        clients.add(this);
    }

    /**
     * The run-method first asks for general info about the client and keep listening on the client's channel
     * elaborating his request depending on what info the controller needs.
     * <p>Requests which starts with "/" have an higher priority and are handled at server-level (these aren't passed to the game controller)</p>
     */
    @Override
    public void run() {
        try {
            while (connected) {
                EasyMessage request;

                do {
                    request = (EasyMessage) in.readObject();
                } while (request == null);

                boolean requestHasBeenManaged = manageRequest(request);

                if (!requestHasBeenManaged) {
                    if (yourTurn) {
                        if (informationIsNeeded && !latestMessageValid) {
                            synchronized (lock) {
                                latestMessage = request.getText();
                                informationIsNeeded = false;
                                latestMessageValid = true;
                                lock.notifyAll();
                            }
                        }
                    } else {
                        sendMessage(new EasyMessage("Not your turn now, please wait"));
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            try {
                in.close();
                out.close();
                client.close();
                System.out.println("Client " + clients.indexOf(this) +  " disconnected");
                clients.remove(this);
                sendMessageToAll(new EasyMessage("A client disconnected, game will be aborted..."));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method manages the high-priority request from the player calling the specific method.
     *
     * @param message string from keyboard
     * @return true if the request has been managed server-level
     */
    private boolean manageRequest(EasyMessage message) throws IOException {
        String request = message.getText().toLowerCase();

        if (request.equalsIgnoreCase("/quit")) {
            quit();
            return true;
        }

        if (request.equalsIgnoreCase("/help")) {
            help();
            return true;
        }

        if (request.startsWith("/say")) {
            say(request);
            return true;
        }

        return false;
    }

    /**
     * Prints on screen a default message containing the main requests
     */
    private void help() throws IOException {
        String helpMessage =
                "\nThese are common Eriantys commands used in various situations.\n" +
                        "Remember that commands are not case sensitive\n" +
                        "Commands are:\n\n" +
                        "to quit the game and disconnect from server\n" +
                        "   /quit\n\n" +
                        "to print something of the game\n" +
                        "   /print island x             to print a precise island. Insert island's index in place of 'x'\n" +
                        "   /print islands              to print all the islands\n" +
                        "   /print board nickname       to print the board of a player. Insert player's nickname in place of 'nickname'\n" +
                        "   /print boards               to print all the boards\n" +
                        "   /print assistant cards      to print all the assistant cards\n" +
                        "   /print teachers             to print who controls teachers now\n" +
                        "   /print clouds               to print all the clouds\n" +
                        "   /print character cards      to print character cards of the game\n\n" +
                        "to play a character card\n" +
                        "   /play name                  to play a character card. Insert the name of the card in place of 'name'\n";

        sendMessage(new EasyMessage(helpMessage));
    }

    /**
     * Closes all the streams and connection for a client
     */
    private void quit() throws IOException {
        connected = false;
        sendMessage(new DisconnectionMessage());
        out.close();
        in.close();
        client.close();
        System.out.println("Client " + id + " disconnected!");
        clients.remove(id);
    }

    /**
     * Implements a simple communication between all players
     *
     * @param request the message to send
     */
    private void say(String request) throws IOException {
        int firstSpace = request.indexOf(" ");
        if (firstSpace != -1) {
            sendMessageToAll(new EasyMessage(request.substring(firstSpace + 1)));
        }
    }

    /**
     * The method gets from connected client its nickname
     */
    public void selectNickName() throws IOException, ClassNotFoundException {
        EasyMessage request;

        sendMessage(new EasyMessage("Insert your nickname: "));
        do {
            request = (EasyMessage) in.readObject();
        } while (request == null);

        nickName = request.getText().toLowerCase();
    }

    /**
     * This method runs when the first client connects and asks for the settings he wants to play with
     */
    public void gameRulesSelection() throws IOException, ClassNotFoundException {
        EasyMessage request;

        do {
            sendMessage(new EasyMessage("Select Game Mode: 0 = easy/ 1 = hard"));
            do {
                request = (EasyMessage) in.readObject();
            } while (request == null);
        } while (!request.getText().equals("0") && !request.getText().equals("1"));

        Constants.setGameMode(!request.getText().equals("0"));

        do {
            sendMessage(new EasyMessage("Select Number of Players: 2 / 3"));
            do {
                request = (EasyMessage) in.readObject();
            } while (request == null);
        } while (!request.getText().equals("2") && !request.getText().equals("3"));

        if (request.getText().equals("2")) {
            Constants.setNumPlayers(2);
        } else {
            Constants.setNumPlayers(3);
        }

        sendMessage(new EasyMessage("Type of game has been chosen, wait for other clients to join"));
    }

    /**
     * Prints "text" to the player's screen
     *
     * @param message to be printed
     */
    public void sendMessage(Message message) throws IOException {
        out.reset();
        out.writeObject(message);
        out.flush();
    }

    /**
     * Print "text" to every players' screen
     *
     * @param message to be printed
     */
    private void sendMessageToAll(Message message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public String getNickName() {
        return nickName;
    }

    public Socket getClient() {
        return client;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public boolean isLatestMessageValid() {
        return latestMessageValid;
    }

    public Object getLock() {
        return lock;
    }

    public void setLatestMessageValid(boolean latestMessageValid) {
        this.latestMessageValid = latestMessageValid;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    public void setInformationIsNeeded(boolean informationIsNeeded) {
        this.informationIsNeeded = informationIsNeeded;
    }
}
