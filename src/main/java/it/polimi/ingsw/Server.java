package it.polimi.ingsw;

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
        int i = 0;
        while (true){
            i++;
            //wait for clients
            Socket client = server.accept();
            System.out.println("[SERVER] Client " + i + " connected!");

            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);
            pool.execute(clientThread);
        }

    }
}
