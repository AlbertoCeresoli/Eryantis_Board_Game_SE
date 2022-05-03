package it.polimi.ingsw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CLI {
    public static void main(String[] args) throws IOException {

        //connetto al server
        System.out.println("[CLIENT] Waiting for server connection...");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("[CLIENT] Connected to server! [localhost, 1234]");

        ServerConnection serverConn = new ServerConnection(socket);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(serverConn).start();

        while (true) {

            String command = keyboard.readLine();
            if (command.equals("quit")) break;
            out.println(command);
        }

        socket.close();
    }
}
