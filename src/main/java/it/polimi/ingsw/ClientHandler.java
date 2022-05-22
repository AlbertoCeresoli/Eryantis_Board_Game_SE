package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private String nickName;
    private final int id;
    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private static final ArrayList<ClientHandler> clients = new ArrayList<>();
    private boolean connected;
    private String latestMessage;
    private boolean latestMessageValid;
    public boolean informationIsNeeded;
    private boolean yourTurn;
    private final Object lock;

    /**
     * Constructor which instantiates channel dedicated to the handling of each client
     * @param clientSocket to connect to the specific client
     */
    public ClientHandler(Socket clientSocket) throws IOException {
        this.id = clients.size();
        this.client = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.connected = true;
        this.latestMessage = "";
        this.latestMessageValid = false;
        this.yourTurn = false;
        this.lock = new Object();

        clients.add(this);
    }

    /**
     *  The run-method first asks for general info about the client and keep listening on the client's channel
     *  elaborating his request depending on what info the controller needs.
     *  <p>Requests which starts with "/" have an higher priority and are handled at server-level (these aren't passed to the game controller)</p>
     */
    @Override
    public void run() {
        try {
            while (connected) {
                String request;

                do {
                    request = in.readLine();
                } while (request == null);

                boolean requestHasBeenManaged = manageRequest(request);

                if (!requestHasBeenManaged) {
                    if (yourTurn) {
                        if (informationIsNeeded && !latestMessageValid) {
                            synchronized (lock) {
                                latestMessage = request;
                                informationIsNeeded = false;
                                latestMessageValid = true;
                                lock.notifyAll();
                            }
                        }
                    }
                    else {
                        sendMessage("Not your turn now, please wait");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private boolean manageRequest(String request) throws IOException {
        if (request.equalsIgnoreCase("/quit")) {
            quit();
            return true;
        }

        if (request.equalsIgnoreCase("/help")) {
            help();
            return true;
        }

        if (request.equalsIgnoreCase("/print")) {
            print();
            return true;
        }

        if (request.startsWith("/say")) {
            say(request);
            return true;
        }

        return false;
    }

    private void help() {
        String helpMessage =
                "\nThese are common Eryantis commands used in various situations.\n" +
                "Remember that commands are not case sensitive\n" +
                "Commands are:\n\n" +
                "to quit the game and disconnect from server\n" +
                "   /quit\n\n" +
                "to print something of the game\n" +
                "   /print island x             to print a precise island. Insert island's index in place of 'x'\n" +
                "   /print islands              to print all the islands\n" +
                "   /print board nickname       to print the board of a player. Insert player's nickname in place of 'nickname'\n" +
                "   /print boards               to print all the boards\n" +
                "   /print teachers             to print who controls teachers now\n" +
                "   /print character cards      to print character cards of the game\n\n" +
                "to play a character card\n" +
                "   /play name                  to play a character card. Insert the name of the card in place of 'name'\n";

        sendMessage(helpMessage);
    }

    private void print() {

    }

    private void quit() throws IOException {
        connected = false;
        out.println(MessageType.EASY_MESSAGE.getType() + "\nDisconnected\nEND OF MESSAGE");
        out.close();
        in.close();
        client.close();
        System.out.println("Client " + id + " disconnected!");
        clients.remove(id);
    }

    private void say(String request) throws IOException {
        int firstSpace = request.indexOf(" ");
        if (firstSpace != -1) {
            sendMessageToAll(request.substring(firstSpace + 1) + "\nEND OF MESSAGE");
        }
    }

    /**
     * The method gets from connected client its nickname
     */
    public void selectNickName() throws IOException {
        String request;

        out.println(MessageType.EASY_MESSAGE.getType() + "\nInsert your nickname: \nEND OF MESSAGE");
        request = in.readLine();
        nickName = request;
    }

    public void gameRulesSelection() throws IOException {
        String request;

        do {
            out.println(MessageType.EASY_MESSAGE.getType() + "\nSelect Game Mode: 0 = easy/ 1 = hard\nEND OF MESSAGE");
            request = in.readLine();
        } while (!request.equals("0") && !request.equals("1"));

        Constants.setGameMode(!request.equals("0"));

        do {
            out.println(MessageType.EASY_MESSAGE.getType() + "\nSelect Number of Players: 2 / 3\nEND OF MESSAGE");
            request = in.readLine();
        } while (!request.equals("2") && !request.equals("3"));

        if (request.equals("2")) {
            Constants.setNumPlayers(2);
        } else {
            Constants.setNumPlayers(3);
        }

        sendMessage("Type of game has been chosen, wait for other clients to join");
    }

    public void sendMessage(String text) {
        out.println(MessageType.EASY_MESSAGE.getType() + "\n" + text + "\nEND OF MESSAGE");
    }

    private void sendMessageToAll(String text) {
        for (ClientHandler client : clients) {
            client.out.println(MessageType.EASY_MESSAGE.getType() + "\n" + text);
        }
    }

    public void sendMessage(MessageType type, String text) {
        out.println(type.getType() + "\n" + text + "\nEND OF MESSAGE");
    }

    public void sendMessageToAll(MessageType type, String text) {
        for (ClientHandler client : clients) {
            client.out.println(type + "\n" + text + "\nEND OF MESSAGE");
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
