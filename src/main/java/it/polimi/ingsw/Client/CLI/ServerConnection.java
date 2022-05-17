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

                String type = "";
                String text = "";
                String str;

                do {
                    str = cli.getFromServerInput().readLine();
                } while (str == null);

                type = str.toUpperCase();
                System.out.println("type is " + type);

                while (!(str = cli.getFromServerInput().readLine()).equals("END OF MESSAGE"))
                    text += str;

                System.out.println("text is " + text);

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


}
