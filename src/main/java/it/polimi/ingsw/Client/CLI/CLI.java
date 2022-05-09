package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Message;

import java.io.*;
import java.net.Socket;

public class CLI implements Runnable{
    private Socket socket;
    //used to read what is written on the terminal
    private final BufferedReader clientInput;
    //used to print on the terminal
    private final PrintStream clientOutput;
    //used to get Message objects from server
    private final ObjectInputStream fromServerInput;
    //used to send to server what is read from keyboard
    private final PrintWriter toServerOutput;
    //
    private final ServerConnection serverConnection;
    private final MessageParserClient parser;
    private final ClientPrinter clientPrinter;
    private boolean activeGame;

    public static void main(String[] args) throws IOException {
        //connection to server
        System.out.println("[CLIENT] Waiting for server connection...");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("[CLIENT] Connected to server! [localhost, 1234]");

        CLI cli = new CLI(socket);

        cli.run();
    }

    public CLI(Socket socket) throws IOException {
        this.clientInput = new BufferedReader(new InputStreamReader(System.in));
        this.clientOutput = new PrintStream(System.out);
        this.fromServerInput = new ObjectInputStream(socket.getInputStream());
        this.toServerOutput = new PrintWriter(socket.getOutputStream());
        this.serverConnection = new ServerConnection(socket, this);
        parser = new MessageParserClient();
        clientPrinter = new ClientPrinter();
        this.activeGame = true;

        new Thread(this.serverConnection).start();
    }

    @Override
    public void run() {
        while (isActiveGame()) {
            try {
                String command = clientInput.readLine();
                toServerOutput.println(command);
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

    public void elaborateMessage(Message message) {
        parser.parseMessages.get(message.getType()).parse(message.getText());
    }

    public ObjectInputStream getFromServerInput() {
        return fromServerInput;
    }

    private boolean isActiveGame() {
        return activeGame;
    }
}
