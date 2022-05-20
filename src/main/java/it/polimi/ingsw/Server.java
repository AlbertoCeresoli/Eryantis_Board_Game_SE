package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(3);
    private static GameHandler gameHandler;



    public static void main(String[] args) throws IOException {
        //server creation
        ServerSocket server = new ServerSocket(1234);

        System.out.println("[SERVER] Waiting for client connection...");

        Socket firstClient = server.accept();
        System.out.println("[SERVER] Client " + 0 + " connected!");

        ClientHandler firstClientThread = new ClientHandler(firstClient);
        clients.add(firstClientThread);
        firstClientThread.gameRulesSelection();
        pool.execute(firstClientThread);

        int i = 1;
        while (i< Constants.getNumPlayers()){
            i++;
            //wait for clients
            Socket client = server.accept();
            System.out.println("[SERVER] Client " + i + " connected!");

            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);
            pool.execute(clientThread);
        }

        new GameHandler(Constants.getNumPlayers(), Constants.isGameMode(), clients);


    }
}
