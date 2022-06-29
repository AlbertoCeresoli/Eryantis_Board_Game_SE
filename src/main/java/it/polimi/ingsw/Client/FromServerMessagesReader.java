package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.CLI.ClientPrinter;
import it.polimi.ingsw.Messages.DisconnectionMessage;
import it.polimi.ingsw.Messages.GameAbortedMessage;
import it.polimi.ingsw.Messages.Message;
import javafx.application.Platform;

import java.io.IOException;
import java.net.Socket;

/**
 * The task of the class is to read what comes from server and prepare the message to be elaborated from CLI.
 * ServerConnection runs on its own thread
 */
public class FromServerMessagesReader implements Runnable {
	private final Socket server;
	private final UI ui;
	private boolean exit;

	public FromServerMessagesReader(Socket socket, UI ui) {
		this.server = socket;
		this.ui = ui;
		this.exit = false;
	}

	/**
	 * The method keep running until player decides to quit the game. Its job is to read what comes from server and
	 * prepare two strings: type of the message and text of it.
	 * Once they're ready, CLI's elaborateMessage method is called
	 */
	@Override
	public void run() {
		while (!exit) {
			try {
				Message message;
				do {
					message = (Message) ui.getFromServerInput().readObject();
				} while (message == null);
				if (message instanceof GameAbortedMessage) {
					ClientPrinter.easyPrint(((GameAbortedMessage) message).getText());
					exit();
					ui.exit();
				}
				if (message instanceof DisconnectionMessage) {
					synchronized (server) {
						exit();
						server.notifyAll();
					}
				} else {
					this.ui.elaborateMessage(message);
				}
			} catch (IOException | ClassNotFoundException e) {
				exit();
				ui.exit();
			}
		}
	}

	/**
	 * Method used to close the thread after player exit the game
	 */
	public void exit() {
		exit = true;
	}

	public boolean isExit() {
		return exit;
	}
}
