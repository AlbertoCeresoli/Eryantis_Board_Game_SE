package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Client.FromServerMessagesReader;
import it.polimi.ingsw.Client.UI;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Messages.EasyMessage;
import it.polimi.ingsw.Messages.ErrorMessages.ErrorMessage;
import it.polimi.ingsw.Messages.GameStartsMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.PlayersNModeMessage;
import it.polimi.ingsw.Messages.SelectionMessages.*;
import it.polimi.ingsw.Messages.UpdateMessages.*;
import javafx.application.Platform;


import java.io.*;
import java.net.Socket;

public class GUINetworkConnection implements UI {
    private final GUI gui;
    private final Socket socket;
    private final ObjectInputStream fromServerInput;
    private final ObjectOutputStream toServerOutput;
    private final FromServerMessagesReader fromServerMessagesReader;

    /**
     * constructor of the class GUINetworkConnection
     */
    public GUINetworkConnection(GUI gui, String IPAddress, int serverPort) throws IOException {
        this.gui = gui;
        this.socket = new Socket(IPAddress, serverPort);
        this.toServerOutput = new ObjectOutputStream(this.socket.getOutputStream());
        this.toServerOutput.flush();
        this.fromServerInput = new ObjectInputStream(this.socket.getInputStream());

        this.fromServerMessagesReader = new FromServerMessagesReader(this.socket, this);
        new Thread(this.fromServerMessagesReader).start();
    }

    /**
     * sorts the messages sent from the server based on the type of the message
     * @param message message received from the server
     */
    @Override
    public void elaborateMessage(Message message) {
        if (message instanceof NicknameSelectionMessage) {
            this.gui.readAndSend("Insert your nickname: ");
        } else if (message instanceof NumberOfPlayersSelectionMessage) {
            this.gui.readAndSend("Select Number of Players: 2 / 3");
        } else if (message instanceof GameModeSelectionMessage) {
            this.gui.readAndSend("Select Game Mode: 0 = easy/ 1 = hard");
        } else if (message instanceof PlayersNModeMessage) {
            Constants.setGameMode(((PlayersNModeMessage) message).isGameMode());
            Constants.setNumPlayers(((PlayersNModeMessage) message).getNumberOfPlayers());
        } else if (message instanceof GameStartsMessage) {
            synchronized (this.gui.getLock()) {
                this.gui.setOk(true);
                this.gui.getLock().notifyAll();
            }
        } else {
            {
                runLater(message);
            }
        }
    }

    /**
     * it launches in a new thread the elaboration of the message received
     * @param message message sent from the server
     */
    private void runLater(Message message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                elaboration(message);
            }
        });
    }

    /**
     * sorts the messages sent from the server based on the type of the message (except the ones of the start of the game that are elaborated in elaborateMessage)
     * @param message message from the server
     */
    private void elaboration(Message message) {
        if (message instanceof EasyMessage) {
            try {
                gui.getController().printEasyMessage(((EasyMessage) message).getText());
            } catch (NullPointerException ignored) {
                System.out.println(((EasyMessage) message).getText());
            }
        }
        if (message instanceof UpdateMessage) {
            gui.getController().updateGame((UpdateMessage) message);
        }
        if (message instanceof SelectionMessage) {
            elaborateSelectionMessage((SelectionMessage) message);
        }
        if (message instanceof ErrorMessage) {
            elaborateErrorMessage((ErrorMessage) message);
        }
        if (message instanceof PlayersNModeMessage) {
            Constants.setNumPlayers(((PlayersNModeMessage) message).getNumberOfPlayers());
            Constants.setGameMode(((PlayersNModeMessage) message).isGameMode());
        }
    }

    /**
     * it elaborates the messages of selection
     * @param message selection message used to request an information to the client
     */
    private void elaborateSelectionMessage(SelectionMessage message) {
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
            gui.getSelection().selectIsland(((IslandSelectionMessage) message).getMaximumIndex(), ((IslandSelectionMessage) message).getMaximumIndex());
        }
        if (message instanceof MNStepsSelectionMessage) {
            gui.getSelection().selectSteps(((MNStepsSelectionMessage) message).getMaxSteps());
        }
        if (message instanceof CloudSelectionMessage) {
            gui.getSelection().selectCloud();
        }
        if (message instanceof CharacterCardSelectionMessage) {
            gui.getSelection().selectCCIndex();
        }
    }

    /**
     * elaborates all the messages of error
     * @param message Error message sent from the server
     */
    private void elaborateErrorMessage(ErrorMessage message) {
        gui.getController().printEasyMessage(message.getText());
    }

    /**
     * method used to send a response to the server
     * @param text String sent to the server
     */
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

    /**
     * closes the connection with the server
     */
    @Override
    public void exit() {

    }

    /**
     * set and get methods
     */
    public GUI getGui() {
        return gui;
    }


    @Override
    public ObjectInputStream getFromServerInput() {
        return fromServerInput;
    }
}