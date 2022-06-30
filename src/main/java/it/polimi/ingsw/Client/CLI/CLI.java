package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Client.FromServerMessagesReader;
import it.polimi.ingsw.Client.UI;
import it.polimi.ingsw.Messages.*;
import it.polimi.ingsw.Messages.ErrorMessages.ErrorMessage;
import it.polimi.ingsw.Messages.PrintMessages.*;
import it.polimi.ingsw.Messages.SelectionMessages.*;
import it.polimi.ingsw.Messages.UpdateMessages.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CLI implements Runnable, UI {
	private final Socket socket;
	//used to read what is written on the terminal
	private final BufferedReader keyboard;
	//used to get Message objects from server
	private final ObjectInputStream fromServerInput;
	//used to send to server what is read from keyboard
	private final ObjectOutputStream toServerOutput;
	private final FromServerMessagesReader fromServerMessagesReader;
	private final ClientPrinter clientPrinter;
	private boolean activeGame;

	public static void main(String[] args) throws IOException {
		//connection to server
		System.out.println("Connecting...");

		Scanner scanner = new Scanner(System.in);

		System.out.println("insert the IP address:");
		String IPAddress = scanner.nextLine();

		System.out.println("insert the server port:");
		int serverPort = scanner.nextInt();

		Socket socket = new Socket(IPAddress, serverPort);

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

		this.fromServerMessagesReader = new FromServerMessagesReader(socket, this);
		new Thread(this.fromServerMessagesReader).start();
	}

	@Override
	public void run() {
		while (activeGame) {
			try {
				String command = keyboard.readLine();
				if (command.equalsIgnoreCase("/quit")) {
					exit();
				}
				sendMessageToServer(new EasyMessage(command));
			} catch (IOException e) {
				System.out.println("Game over, you will be disconnected...");
				exit();
			}
		}

		synchronized (socket) {
			while (!fromServerMessagesReader.isExit()) {
				try {
					socket.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void sendMessageToServer(Message message) throws IOException {
		toServerOutput.reset();
		toServerOutput.writeObject(message);
		toServerOutput.flush();
	}

	public void elaborateMessage(Message message) {
		if (message instanceof EasyMessage) {
			ClientPrinter.easyPrint(((EasyMessage) message).getText());
		}
		if (message instanceof UpdateMessage) {
			elaborateUpdateMessage((UpdateMessage) message);
		}
		if (message instanceof SelectionMessage) {
			elaborateSelectionMessage((SelectionMessage) message);
		}
		if (message instanceof PrintMessage) {
			elaboratePrintMessage((PrintMessage) message);
		}
		if (message instanceof ErrorMessage) {
			elaborateErrorMessage((ErrorMessage) message);
		}
	}

	private void elaborateUpdateMessage(UpdateMessage message) {
		if (message instanceof CloudsUpdateMessage) {
			ClientPrinter.easyPrint("Students on the clouds have been changed. They are:");
			ClientPrinter.printClouds(((CloudsUpdateMessage) message).getPrintCloudsMessage());
		}
		if (message instanceof TeachersUpdateMessage) {
			ClientPrinter.easyPrint("Students have been moved from or to the hall.");
			ClientPrinter.printTeachers(((TeachersUpdateMessage) message).getPrintTeachersMessage());
		}
		if (message instanceof IslandsUpdateMessage) {
			ClientPrinter.easyPrint("Islands have changed");
		}
		if (message instanceof BoardUpdateMessage) {
			ClientPrinter.easyPrint("Board of " + ((BoardUpdateMessage) message).getNickname() + "has changed");
		}
	}

	private void elaborateSelectionMessage(SelectionMessage message) {
		if (message instanceof NicknameSelectionMessage) {
			ClientPrinter.easyPrint("Insert your nickname: ");
		}
		if (message instanceof NumberOfPlayersSelectionMessage) {
			ClientPrinter.easyPrint("Select Number of Players: 2 / 3");
		}
		if (message instanceof GameModeSelectionMessage) {
			ClientPrinter.easyPrint("Select Game Mode: 0 = easy/ 1 = hard");
		}
		if (message instanceof AssistantCardSelectionMessage) {
			ClientPrinter.easyPrint("It is your turn to play an Assistant Card");
			ClientPrinter.printAssistantCards(((AssistantCardSelectionMessage) message).getPrintAssistantCardsMessage());
		}
		if (message instanceof ColorSelectionMessage) {
			ClientPrinter.easyPrint("Select the color of the student you want to move");
			elaboratePrintMessage(((ColorSelectionMessage) message).getPrintMessage());
		}
		if (message instanceof StudentDestinationSelectionMessage) {
			ClientPrinter.easyPrint("Where do you want to put the student? Choose one between Hall or Island");
		}
		if (message instanceof IslandSelectionMessage) {
			ClientPrinter.easyPrint("Select an island between " +
					((IslandSelectionMessage) message).getMinimumIndex() + " and " +
					((IslandSelectionMessage) message).getMaximumIndex());
		}
		if (message instanceof MNStepsSelectionMessage) {
			int minSteps = ((MNStepsSelectionMessage) message).getMinSteps();
			int maxSteps = ((MNStepsSelectionMessage) message).getMaxSteps();
			ClientPrinter.easyPrint("It is time to move Mother Nature. How many steps you want her to do? " +
					"Choose a number from " + minSteps + " to " + maxSteps);
		}
		if (message instanceof CloudSelectionMessage) {
			ClientPrinter.printClouds(((CloudSelectionMessage) message).getPrintCloudsMessage());
			ClientPrinter.easyPrint("Insert the index of the cloud you want to choose");
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

	public void exit() {
		this.activeGame = false;
		try {
			this.keyboard.close();
		} catch (IOException ignored) {

		}
	}
}
