package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private static final ArrayList<ClientHandler> clients = new ArrayList<>();
    private final int id;
    private String nickName;
    private boolean connected;
    public boolean informationIsNeeded;
    private String latestMessage;
    private boolean latestMessageValid;
    private boolean yourTurn;
    public final Object lock;

    /**
     *  Constructor which instantiates channel dedicated to the handling of each client
     * @param clientSocket to connect to the specific client
     * @throws IOException
     */
    public ClientHandler(Socket clientSocket) throws IOException {
        this.id = clients.size();
        this.client = clientSocket;
        this.connected = true;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
        clients.add(this);

        latestMessage = "";

        lock = new Object();

        latestMessageValid = false;
        yourTurn = false;
    }

    /**
     *  The run-method first asks for general info about the client and keep listening on the client's channel elaborating his request depending on what info the controller needs.
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
        if (request.startsWith("/quit")) {
            quit();
            return true;
        }

        if (request.startsWith("/say")) {
            say(request);
            return true;
        }

        return false;
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
            outToAll(request.substring(firstSpace + 1) + "\nEND OF MESSAGE");
        }
    }

    /**
     * SetUp is the first method used to map all the clients in indexToNick e nickToIndex
     * @throws IOException
     * @throws InterruptedException
     */
    public void selectNickName() throws IOException, InterruptedException {
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

    private void outToAll(String substring) throws IOException {
        for (ClientHandler client : clients) {
            client.out.println(MessageType.EASY_MESSAGE.getType() + "\n" + substring);
        }
    }

    public void sendMessage(String msg) {
        out.println(MessageType.EASY_MESSAGE.getType() + "\n" + msg + "\nEND OF MESSAGE");
    }

    public String getNickName() {
        return nickName;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessageValid(boolean latestMessageValid) {
        this.latestMessageValid = latestMessageValid;
    }

    public boolean isLatestMessageValid() {
        return latestMessageValid;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    public int getId() {
        return id;
    }

    public PrintWriter getOut() {
        return out;
    }

    public Socket getClient() {
        return client;
    }

    public void setInformationIsNeeded(boolean informationIsNeeded) {
        this.informationIsNeeded = informationIsNeeded;
    }
}
