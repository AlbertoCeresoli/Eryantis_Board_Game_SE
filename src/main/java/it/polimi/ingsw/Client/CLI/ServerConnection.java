package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Messages.Message;

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
		this.exit = false;
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
				Message message;
				do {
					message = (Message) cli.getFromServerInput().readObject();
				} while (message == null);

				this.cli.elaborateMessage(message);
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

	/**
	 * Method used to close the thread after player exit the game
	 */
	public void exit() {
		exit = true;
	}
}
