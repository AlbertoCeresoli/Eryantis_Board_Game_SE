package it.polimi.ingsw.Client.CLI;

import java.io.IOException;
import java.net.Socket;

/**
 * The task of the class is to read what comes from server and prepare the message to be elaborated from CLI.
 * ServerConnection runs on its own thread
 */
public class ServerConnection implements Runnable {
    private final Socket server;
    private final CLI cli;
    private boolean exit;

    public ServerConnection(Socket socket, CLI cli) {
        this.server = socket;
        this.cli = cli;
        exit = false;
    }

    /**
     * The method keep running until player decides to quit the game. Its job is to read what comes from server and
     * prepare two strings: type of the message and text of it.
     * Once they're ready, CLI's elaborateMessage method is called
     */
    @Override
    public void run() {
        try {
            while (!exit) {

                String type = "";
                String text = "";
                String str;

                str = elaborateType();

                type = str.toUpperCase();

                text = elaborateText();

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

    /**
     * When a new message is being received from server, elaborateType reads the first line of it, saving it into type.
     *
     * @return is a string containing the type of the message is being received from server.
     */
    private String elaborateType() throws IOException {
        String type;

        do {
            type = cli.getFromServerInput().readLine();
        } while (type == null);

        return type;
    }

    /**
     * The method elaborates the string that will be the text of the message coming from the server.
     * In order to not add a \n after reading last line of the message, is used another String variable: previousLine.
     * The boolean firstIteration is used to initialize previousLine variable
     *
     * @return text of the message received from server
     */
    private String elaborateText() throws IOException {
        String text = "";
        String line = "";
        String previousLine = "";
        boolean firstIteration = true;

        while (true) {
            if (firstIteration) {
                if (!(line = cli.getFromServerInput().readLine()).equals("END OF MESSAGE")) {
                    previousLine = line;
                    firstIteration = false;
                }
                else {
                    text = "";
                    break;
                }
            } else {
                if (!(line = cli.getFromServerInput().readLine()).equals("END OF MESSAGE")) {
                    text += previousLine + "\n";
                    previousLine = line;
                }
                else {
                    text += previousLine;
                    break;
                }
            }
        }
        return text;
    }

    /**
     * Method used to close the thread after player exit the game
     */
    public void exit() {
        exit = true;
    }
}
