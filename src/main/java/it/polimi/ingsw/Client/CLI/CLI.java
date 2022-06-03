package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Messages.DisconnectionMessage;
import it.polimi.ingsw.Messages.EasyMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyPlayedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.ErrorMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.PrintMessages.*;

import java.io.*;
import java.net.Socket;

public class CLI implements Runnable {
	private final Socket socket;
	//used to read what is written on the terminal
	private final BufferedReader keyboard;
	//used to get Message objects from server
	private final ObjectInputStream fromServerInput;
	//used to send to server what is read from keyboard
	private final ObjectOutputStream toServerOutput;
	private final ServerConnection serverConnection;
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
		this.socket = socket;
		this.keyboard = new BufferedReader(new InputStreamReader(System.in));
		this.toServerOutput = new ObjectOutputStream(socket.getOutputStream());
		this.toServerOutput.flush();
		this.fromServerInput = new ObjectInputStream(socket.getInputStream());
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
				sendMessageToServer(new EasyMessage(command));
				if (command.equals("/quit")) {
					serverConnection.exit();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		try {
			this.socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void sendMessageToServer(Message message) throws IOException {
		toServerOutput.reset();
		toServerOutput.writeObject(message);
		toServerOutput.flush();
	}

	public void elaborateMessage(Message message) throws IOException {
		if (message instanceof EasyMessage) {
			ClientPrinter.easyPrint(((EasyMessage) message).getText());
		}
		if (message instanceof PrintMessage) {
			elaboratePrintMessage((PrintMessage) message);
		}
		if (message instanceof ErrorMessage) {
			elaborateErrorMessage((ErrorMessage) message);
		}
		if (message instanceof DisconnectionMessage) {
			activeGame = false;
			serverConnection.exit();
		}
	}

	public void elaboratePrintMessage(PrintMessage message) {
		if (message instanceof PrintAssistantCardsMessage) {
			ClientPrinter.printAssistantCards((PrintAssistantCardsMessage) message);
		}
		if (message instanceof PrintBoardMessage) {
			ClientPrinter.printBoard((PrintBoardMessage) message);
		}
		if (message instanceof PrintCharacterCardsMessage) {
			ClientPrinter.printCharacterCards((PrintCharacterCardsMessage) message);
		}
		if (message instanceof PrintCloudsMessage) {
			ClientPrinter.printClouds((PrintCloudsMessage) message);
		}
		if (message instanceof PrintIslandMessage) {
			ClientPrinter.printIsland((PrintIslandMessage) message);
		}
		if (message instanceof PrintIslandsMessage) {
			ClientPrinter.printIslands((PrintIslandsMessage) message);
		}
		if (message instanceof PrintTeachersMessage) {
			ClientPrinter.printTeachers((PrintTeachersMessage) message);
		}
	}

	public void elaborateErrorMessage(ErrorMessage message) {
		ClientPrinter.easyPrint(message.getText());
	}

	public ObjectInputStream getFromServerInput() {
		return fromServerInput;
	}

	private boolean isActiveGame() {
		return activeGame;
	}
}
