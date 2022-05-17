package it.polimi.ingsw.Client.CLI;

import java.io.IOException;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private Socket server;
    private CLI cli;
    private boolean exit;

    public ServerConnection(Socket socket, CLI cli) {
        this.server = socket;
        this.cli = cli;
        exit = false;
    }

    @Override
    public void run() {
        try {
            while (!exit) {

                String type = "";
                String text = "";
                String str;

                do {
                    str = cli.getFromServerInput().readLine();
                } while (str == null);

                type = str.toUpperCase();

                while (!(str = cli.getFromServerInput().readLine()).equals("END OF MESSAGE"))
                    text += str;

                this.cli.elaborateMessage(type, text);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void exit() {
        exit = true;

    }
}
