package it.polimi.ingsw.Client.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CLI implements Runnable{
    private Socket socket;
    //used to read what is written on the terminal
    private final BufferedReader keyboard;
    //used to get Message objects from server
    private final BufferedReader fromServerInput;
    //used to send to server what is read from keyboard
    private final PrintWriter toServerOutput;
    private final ServerConnection serverConnection;
    private final MessageParserClient parser;
    private final ClientPrinter clientPrinter;
    private final boolean activeGame;

    public static void main(String[] args) throws IOException {
        //connection to server
        System.out.println("[CLIENT] Waiting for server connection...");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("[CLIENT] Connected to server! [localhost, 1234]");

        CLI cli = new CLI(socket);

        cli.run();
    }

    public CLI(Socket socket) throws IOException {
        this.keyboard = new BufferedReader(new InputStreamReader(System.in));
        this.fromServerInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.toServerOutput = new PrintWriter(socket.getOutputStream(), true);
        this.parser = new MessageParserClient();
        this.clientPrinter = new ClientPrinter();
        this.activeGame = true;

        this.serverConnection = new ServerConnection(socket, this);
        new Thread(this.serverConnection).start();
    }

    @Override
    public void run() {
        while (isActiveGame()) {
            try {
                String command = keyboard.readLine();
                toServerOutput.println(command);
                if (command.equals("/quit")) {
                    serverConnection.exit();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void elaborateMessage(String type, String text) throws IOException {
        this.parser.getParseMessages().get(type).parse(text);

        if (text.equals("Disconnected")) {
            serverConnection.exit();
        }
    }

    public BufferedReader getFromServerInput() {
        return fromServerInput;
    }

    private boolean isActiveGame() {
        return activeGame;
    }

    public ClientPrinter getClientPrinter() {
        return clientPrinter;
    }
}
