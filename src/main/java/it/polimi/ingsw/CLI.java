package it.polimi.ingsw;

import java.io.*;
import java.net.Socket;

public class CLI {
    public static ClientPrinter clientPrinter = new ClientPrinter();
    public static void main(String[] args) throws IOException {
        //connetto al server
        System.out.println("[CLIENT] Waiting for server connection...");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("[CLIENT] Connected to server! [localhost, 1234]");

        ServerConnection serverConn = new ServerConnection(socket);

        //InputStream inputStream = socket.getInputStream();
        //ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        //OutputStream outputStream = socket.getOutputStream();
        //ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(serverConn).start();

        while (true) {
            String command = keyboard.readLine();
            if (command.equals("/quit")) break;
            out.println(command);
        }

        socket.close();
    }
}
