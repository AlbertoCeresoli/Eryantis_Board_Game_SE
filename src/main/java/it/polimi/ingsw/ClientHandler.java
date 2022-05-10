package it.polimi.ingsw;

import it.polimi.ingsw.Constants.MessageType;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private ObjectOutputStream objout;
    private ArrayList<ClientHandler> clients;
    private static int id;
    private static Controller controller;
    private static int numberOfPlayers;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        objout = new ObjectOutputStream(client.getOutputStream());    
    }

    @Override
    public void run() {
        try {
            try {
                objout.writeObject(new Message(MessageType.EASY_MESSAGE,("[SERVER] Welcome! You are the player " + clients.size())));
                String request;
                setUp();
                int i = 0;
                while (true) {

                    request = in.readLine();
                    if (request.startsWith("/say")) {
                        int firstSpace = request.indexOf(" ");
                        if (firstSpace != -1) {
                            outToAll(request.substring(firstSpace + 1));
                        }
                    }
                    if (id != i) {
                        objout.writeObject(new Message(MessageType.EASY_MESSAGE, "[SERVER] Non è il tuo turno, aspetta"));
                    }

                    if (id == i) {
                        objout.writeObject("[SERVER] " + request);
                        if(request.equalsIgnoreCase("pass")){
                        i = (i+1)%2;
                        notify();
                        }
                    } else{ wait();}
                }
            } catch (SocketException socketException) {
                System.out.println("[SERVER] Client disconnected");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                objout.writeObject(new Message(MessageType.EASY_MESSAGE,("Select Game Mode: 0 = easy/ 1 = hard")));
                request = in.readLine();
            } while (!request.equals("0") && !request.equals("1"));
            if (request.equals("0")) {
                gameMode = false;
            } else {
                gameMode = true;
            }
            do {
                objout.writeObject(new Message(MessageType.EASY_MESSAGE,("Select Number of Players: 2 / 3")));
                request = in.readLine();
            } while (!request.equals("2") && !request.equals("3"));
            if (request.equals("2")) {
                numberOfPlayers = 2;
            } else {
                numberOfPlayers = 3;
            }
            objout.writeObject(new Message(MessageType.EASY_MESSAGE,("[SERVER] Waiting for other players to join...")));
        }
        if (clients.size() == numberOfPlayers) {
            controller = new Controller(clients.size(), gameMode);
            outToAll("[SERVER] Controller created!! Game starting...");
            System.out.println("[SERVER] Controller created");
        }
    }

    private void outToAll(String substring) throws IOException {
        for (ClientHandler client : clients) {
            client.objout.writeObject(new Message(MessageType.EASY_MESSAGE,(substring)));
        }
    }

    private void sendMessage(Message request) throws IOException {
        objout.writeObject(new Message(MessageType.EASY_MESSAGE,(request.getText())));
    }

    private String getInformation() throws IOException {
        return in.readLine();
    }
}
