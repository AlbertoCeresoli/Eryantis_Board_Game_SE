package it.polimi.ingsw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private static Controller controller;
    private static int numberOfPlayers;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            try {
                out.println("[SERVER] Welcome! You are the player " + clients.size());
                String request;
                setUp();

                while (true) {
                    request = in.readLine();

                    if (request.startsWith("/say")) {
                        int firstSpace = request.indexOf(" ");
                        if (firstSpace != -1) {
                            outToAll(request.substring(firstSpace + 1));
                        }
                    } else {
                        out.println("[SERVER]" + request);
                    }
                }
            } catch (SocketException socketException) {
                System.out.println("[SERVER] Client " + clients.indexOf(client) + " disconnected");
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
                out.println("Select Game Mode: 0 = easy/ 1 = hard");
                request = in.readLine();
            } while (!request.equals("0") && !request.equals("1"));
            if (request.equals("0")) {
                gameMode = false;
            } else {
                gameMode = true;
            }
            do {
                out.println("Select Number of Players: 2 / 3");
                request = in.readLine();
            } while (!request.equals("2") && !request.equals("3"));
            if (request.equals("2")) {
                numberOfPlayers = 2;
            } else {
                numberOfPlayers = 3;
            }
            out.println("[SERVER] Waiting for other players to join...");
        }
        if (clients.size() == numberOfPlayers) {
            controller = new Controller(clients.size(), gameMode);
            outToAll("[SERVER] Controller created!! Game starting...");
            System.out.println("[SERVER] Controller created");
        }
    }

    private void outToAll(String substring) {
        for (ClientHandler client : clients) {
            client.out.println(substring);
        }
    }
}
