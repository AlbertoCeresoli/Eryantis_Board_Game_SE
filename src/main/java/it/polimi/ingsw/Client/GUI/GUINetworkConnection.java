package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Client.FromServerMessagesReader;
import it.polimi.ingsw.Client.UI;
import it.polimi.ingsw.Messages.EasyMessage;
import it.polimi.ingsw.Messages.ErrorMessages.ErrorMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.PrintMessages.*;
import it.polimi.ingsw.Messages.SelectionMessages.*;
import it.polimi.ingsw.Messages.UpdateMessages.*;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class GUINetworkConnection implements UI {
    private final GUI gui;
    private final Socket socket;
    private final ObjectInputStream fromServerInput;
    private final ObjectOutputStream toServerOutput;
    private final FromServerMessagesReader fromServerMessagesReader;
    private String IPaddress;
    private int serverPort;

    public GUINetworkConnection(GUI gui) throws IOException {
        this.gui = gui;
        Scanner scanner = new Scanner(System.in);

        System.out.println("insert the IP address:");
        IPaddress = scanner.nextLine();
        System.out.println("insert the server port:");
        serverPort = scanner.nextInt();

        this.socket = new Socket(IPaddress, serverPort);

        this.toServerOutput = new ObjectOutputStream(this.socket.getOutputStream());
        this.toServerOutput.flush();
        this.fromServerInput = new ObjectInputStream(this.socket.getInputStream());

        this.fromServerMessagesReader = new FromServerMessagesReader(this.socket, this);
        new Thread(this.fromServerMessagesReader).start();
    }

    @Override
    public void elaborateMessage(Message message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                elaboration(message);
            }
        });
    }

    private void elaboration(Message message) {
        if (message instanceof EasyMessage) {
            gui.getController().printEasyMessage(((EasyMessage) message).getText());
        }
        if (message instanceof UpdateMessage) {
            gui.getController().updateGame((UpdateMessage) message);
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

    private void elaborateSelectionMessage(SelectionMessage message) {
        if (message instanceof NicknameSelectionMessage) {
            gui.getSelection().selectNickname();
        }
        if (message instanceof NumberOfPlayersSelectionMessage) {
            gui.getSelection().selectNumPlayers();
        }
        if (message instanceof GameModeSelectionMessage) {
            gui.getSelection().selectGamemode();
        }
        if (message instanceof AssistantCardSelectionMessage) {
            gui.getSelection().selectAC();
        }
        if (message instanceof ColorSelectionMessage) {
            gui.getSelection().selectColor();
        }
        if (message instanceof StudentDestinationSelectionMessage) {
            gui.getSelection().selectPlace();
        }
        if (message instanceof IslandSelectionMessage) {
            gui.getSelection().selectIsland();
        }
        if (message instanceof MNStepsSelectionMessage) {
            gui.getSelection().selectSteps(((MNStepsSelectionMessage) message).getMaxSteps());
        }
        if (message instanceof CloudSelectionMessage) {
            gui.getSelection().selectCloud();
        }
    }

    private void elaboratePrintMessage(PrintMessage message) {
        if (message instanceof PrintAssistantCardsMessage) {
            gui.getController().onClickACP1();
        }
        if (message instanceof PrintBoardMessage) {
            gui.getController().onClickBoard(((PrintBoardMessage) message).getPlayerIndex());
        }
        if (message instanceof PrintCharacterCardsMessage) {
            gui.getController().onClickCharacterCards();
        }
        if (message instanceof PrintCloudsMessage) {
            gui.getController().onClickClouds();
        }
        if (message instanceof PrintIslandMessage) {
            gui.getController().zoomIsland(((PrintIslandMessage) message).getIslandIndex());
        }
        if (message instanceof PrintIslandsMessage) {
            gui.getController().onClickClouds();
        }
    }

    private void elaborateErrorMessage(ErrorMessage message) {
        gui.getController().printEasyMessage(message.getText());
    }

    public void sendMessageToServer(String text) {
        try {
            toServerOutput.reset();
            toServerOutput.writeObject(new EasyMessage(text));
            toServerOutput.flush();
        } catch (IOException e) {
            gui.quitGUI();
        }
    }
    //TODO

    @Override
    public void exit() {

    }

    public GUI getGui() {
        return gui;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public ObjectInputStream getFromServerInput() {
        return fromServerInput;
    }

    public ObjectOutputStream getToServerOutput() {
        return toServerOutput;
    }

    public FromServerMessagesReader getFromServerMessagesReader() {
        return fromServerMessagesReader;
    }

    public void setIPaddress(String IPaddress) {
        this.IPaddress = IPaddress;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
