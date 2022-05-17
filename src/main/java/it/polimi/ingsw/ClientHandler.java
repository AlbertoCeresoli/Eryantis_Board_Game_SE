package it.polimi.ingsw;

import it.polimi.ingsw.Constants.MessageType;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private static final ArrayList<ClientHandler> clients = new ArrayList<>();
    private static int id;
    private static Controller controller;
    private static int numberOfPlayers;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
        clients.add(this);
    }

    public static Controller getController() {
        return controller;
    }

    @Override
    public void run() {
        try {
            try {
                out.println(MessageType.EASY_MESSAGE.getType() + "\n[SERVER] Welcome! You are the player " + clients.size() + "\nEND OF MESSAGE");

                setUp();

                int i = 0;

                while (true) {

                    String request;

                    do {
                        request = in.readLine();
                    } while (request == null);

                    if (request.startsWith("/say")) {
                        int firstSpace = request.indexOf(" ");
                        if (firstSpace != -1) {
                            outToAll(request.substring(firstSpace + 1) + "\nEND OF MESSAGE");
                        }
                    }

                    out.println(MessageType.EASY_MESSAGE.getType() + "\n" + "[SERVER] " + request + "\nEND OF MESSAGE");
                }
            } catch (SocketException socketException) {
                System.out.println("[SERVER] Client disconnected");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setUp() throws IOException, InterruptedException {
        String request;
        boolean gameMode = false;
        if (clients.size() == 1) {
            do {
                out.println(MessageType.EASY_MESSAGE.getType() + "\nSelect Game Mode: 0 = easy/ 1 = hard\nEND OF MESSAGE");
                request = in.readLine();
            } while (!request.equals("0") && !request.equals("1"));
            if (request.equals("0")) {
                gameMode = false;
            } else {
                gameMode = true;
            }
            do {
                out.println(MessageType.EASY_MESSAGE.getType() + "\nSelect Number of Players: 2 / 3\nEND OF MESSAGE");
                request = in.readLine();
            } while (!request.equals("2") && !request.equals("3"));
            if (request.equals("2")) {
                numberOfPlayers = 2;
            } else {
                numberOfPlayers = 3;
            }
            out.println(MessageType.EASY_MESSAGE.getType() + "\n[SERVER] Waiting for other players to join...\nEND OF MESSAGE");
        }
        if (clients.size() == numberOfPlayers) {
            controller = new Controller(clients.size(), gameMode);
            outToAll("[SERVER] Controller created!! Game starting...\nEND OF MESSAGE");
            System.out.println("[SERVER] Controller created");
        }
    }

    private void outToAll(String substring) throws IOException {
        for (ClientHandler client : clients) {
            System.out.println(substring);
            client.out.println(MessageType.EASY_MESSAGE.getType() + "\n" + substring);
        }
    }

    private void sendMessage(String msg) {
        out.println(MessageType.EASY_MESSAGE.getType() + "\n" + msg + "\nEND OF MESSAGE");
    }

    private String getInformation() throws IOException {
        return in.readLine();
    }
}
