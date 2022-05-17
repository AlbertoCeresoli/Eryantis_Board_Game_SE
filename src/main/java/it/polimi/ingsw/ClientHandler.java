package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;
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
    private String nickName;
    private boolean isActive;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        this.isActive = true;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
        clients.add(this);
    }


    @Override
    public void run() {
        try {
            try {
                out.println(MessageType.EASY_MESSAGE.getType() + "\n[SERVER] Welcome! You are the player " + clients.size() + "\nEND OF MESSAGE");

                setUp();

                int i = 0;

                while (isActive) {

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
                    if (request.startsWith("/quit")) {
                        isActive = false;
                        out.println(MessageType.EASY_MESSAGE.getType() + "\nDisconnected!\nEND OF MESSAGE");
                        out.close();
                        in.close();
                        client.close();
                        System.out.println("Client " + clients.indexOf(this) + " disconnected!");
                        clients.remove(this);

                    }


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

        out.println(MessageType.EASY_MESSAGE.getType() + "\nInsert your nickname: \nEND OF MESSAGE");
        request = in.readLine();
        nickName=request;

        if (clients.size() == 1) {
            do {
                out.println(MessageType.EASY_MESSAGE.getType() + "\nSelect Game Mode: 0 = easy/ 1 = hard\nEND OF MESSAGE");
                request = in.readLine();
            } while (!request.equals("0") && !request.equals("1"));
            if (request.equals("0")) {
                Constants.setGameMode(false);
            } else {
                Constants.setGameMode(true);
            }
            do {
                out.println(MessageType.EASY_MESSAGE.getType() + "\nSelect Number of Players: 2 / 3\nEND OF MESSAGE");
                request = in.readLine();
            } while (!request.equals("2") && !request.equals("3"));
            if (request.equals("2")) {
                Constants.setNumPlayers(2);
            } else {
                Constants.setNumPlayers(3);
            }
            out.println(MessageType.EASY_MESSAGE.getType() + "\n[SERVER] Waiting for other players to join...\nEND OF MESSAGE");
        }
    }

    private void outToAll(String substring) throws IOException {
        for (ClientHandler client : clients) {
            client.out.println(MessageType.EASY_MESSAGE.getType() + "\n" + substring);
        }
    }

    public void sendMessage(String msg) {
        out.println(MessageType.EASY_MESSAGE.getType() + "\n" + msg + "\nEND OF MESSAGE");
    }

    public String getInformation() throws IOException {
        return in.readLine();
    }

    public String getNickName() {
        return nickName;
    }
}
