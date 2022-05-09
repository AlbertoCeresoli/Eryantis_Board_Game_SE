package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Message;

import java.io.IOException;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private Socket server;
    private CLI cli;

    public ServerConnection(Socket socket, CLI cli) {
        this.server = socket;
        this.cli = cli;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message serverResponse = (Message) cli.getFromServerInput().readObject();
                if (serverResponse == null) break;
                this.cli.elaborateMessage(serverResponse);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                this.server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
