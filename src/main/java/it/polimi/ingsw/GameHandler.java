package it.polimi.ingsw;

import it.polimi.ingsw.Constants.*;
import it.polimi.ingsw.Messages.EasyMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.PrintMessages.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameHandler extends Thread {
    //TODO pay attention to clientHandlers: it has to be synchronized everytime someone uses the arrayList
    private final ArrayList<ClientHandler> clientHandlers;
    private final Controller controller;
    private final MessageGenerator messageGenerator;
    Map<Integer, String> indexToNick;
    Map<String, Integer> nickToIndex;

    public GameHandler(ArrayList<ClientHandler> clients) {
        this.clientHandlers = clients;
        this.controller = new Controller(this);
        this.indexToNick = new HashMap<>();
        this.nickToIndex = new HashMap<>();
        this.messageGenerator = new MessageGenerator();

        for (int i = 0; i < clients.size(); i++) {
            this.indexToNick.put(i, clients.get(i).getNickName().toLowerCase());
            this.nickToIndex.put(clients.get(i).getNickName().toLowerCase(), i);
        }
    }

    @Override
    public void run() {
        try {
            controller.startGame();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO substitute easy messages

    //TODO synchronize
    /**
     * The method sends the string in input to the client putting it into an EasyMessage, so it has just to be printed
     * @param playerIndex   that has to receive the message
     * @param s the message itself
     */
    public void newMessage(int playerIndex, String s) {
        try {
            clientHandlers.get(playerIndex).sendMessage(new EasyMessage(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO synchronize
    public void newMessage(int playerIndex, Message message) {
        try {
            clientHandlers.get(playerIndex).sendMessage(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void messageToAll(String s) {
        synchronized (clientHandlers) {
            for (ClientHandler clientHandler : clientHandlers) {
                try {
                    clientHandler.sendMessage(new EasyMessage(s));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void messageToAll(Message message) {
        synchronized (clientHandlers) {
            for (ClientHandler clientHandler : clientHandlers) {
                try {
                    clientHandler.sendMessage(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * This method scans every input from the player checking whether the player
     * used a specific keyword for information printing.
     * In this case it prints what the player needs;
     * then it continues the round tasks giving the input to the controller
     *
     * @param selection what the controller needs to continue the game
     * @param player    the actual player
     * @return iterates until the last input is the one which satisfies the controller
     */
    public String requestInformation(ObjectsToSelect selection, int player) throws InterruptedException, IOException {
        String result = getLatestMessageFromPlayer(player).toLowerCase();

        Message message;

        //command card
        int cardNumber;

        //Play a character card
        if (result.equalsIgnoreCase("/play character card")) {
            if (Constants.isGameMode()) {
                newMessage(player, printCharacterCards());

                //TODO characterCardSelectionMessage
                newMessage(player, "Insert the index of the card you want to play:");

                String temp;
                do {
                    temp = requestInformation(ObjectsToSelect.CHARACTER_CARD, player);
                } while (!temp.equals("false"));

                cardNumber = Integer.parseInt(temp);
                controller.playCard(cardNumber, player);
                //TODO if necessary send an UpdateMessage
            } else {
                newMessage(player, "You are playing a game in easy mode, there are no character cards");
            }

            return "false";
        }

        //Show character cards
        if (result.equalsIgnoreCase("/print character cards")) {
            if (Constants.isGameMode()) {
                newMessage(player, printCharacterCards());
            } else {
                newMessage(player, "You are playing a game in easy mode, there are no character cards");
            }

            return "false";
        }

        //extra commands
        //print all the students of the player
        if (result.startsWith("/print board ")) {
            String nick = result.substring(13).toLowerCase();

            if (nickToIndex.containsKey(nick)) {
                int index = nickToIndex.get(nick);
                newMessage(player, printBoard(index));

            }
            else {
                newMessage(player, "There is not such player in this game");
            }

            return "false";
        }

        //prints an island of player choice
        if (result.startsWith("/print island ")) {
            try {
                int index = Integer.parseInt(result.substring(14));
                if (index > 0 && index < controller.getModel().getIslandInteraction().getIslands().size()){
                    newMessage(player, printIsland(index));
                }
                else {
                    newMessage(player, new NotValidIndexErrorMessage("Not valid index, the input must be between (0 - " +
                    (controller.getModel().getIslandInteraction().getIslands().size() - 1) + ")"));
                }
            } catch (NumberFormatException e) {
                newMessage(player, new NotValidInputErrorMessage("Not valid Input, the input must be an int between (0 - " +
                        (controller.getModel().getIslandInteraction().getIslands().size() - 1) + ")"));
            }

            return "false";
        }

        //Show islands
        if (result.equalsIgnoreCase("/print islands")) {
            newMessage(player, printIslands());

            return "false";
        }

        if (result.equalsIgnoreCase("/print boards")) {
            for (int i = 0; i < clientHandlers.size(); i++) {
                newMessage(player, printBoard(i));
            }

            return "false";
        }

        //Show clouds
        if (result.equalsIgnoreCase("/print clouds")) {
            newMessage(player, printClouds());

            return "false";
        }

        //Show teachers
        if (result.equalsIgnoreCase("/print teachers")) {
            newMessage(player, printTeachers());

            return "false";
        }

        //Show assistant cards
        if (result.equalsIgnoreCase("/print assistant cards")) {
            newMessage(player, printAssistantCards(player));

            return "false";
        }

        //TODO check methods of message generator for messages exchanged
        //creation of the message based on the object requested
        message = switch (selection) {
            case COLOR -> messageGenerator.colorSelection(result);
            case PLACE -> messageGenerator.placeSelection(result);
            case ISLAND ->
                    messageGenerator.islandSelection(result, controller.getModel().getIslandInteraction().getIslands().size());
            case STEPS ->
                    messageGenerator.stepsSelection(result, controller.getModel().getPlayerInteraction().getPlayer(player).getAssistants().get(controller.getLastAssistantCardsPlayed()[player]).getSteps());
            case PLAYER -> messageGenerator.playerIndexSelection(result, controller.getModel().gameRules[0]);
            case ASSISTANT_CARD ->
                    messageGenerator.assistantSelection(result, Constants.getNumPlayers(), controller.getLastAssistantCardsPlayed(), controller.getModel().getPlayerInteraction().getPlayers().get(player).getAssistants());
            case CLOUD ->
                    messageGenerator.cloudSelection(result, controller.getModel().gameRules[0], controller.getModel().getBagNClouds());
            case CHARACTER_CARD -> messageGenerator.characterCardSelection(result);
        };

        newMessage(player, message);

        if (message instanceof AlreadyPlayedErrorMessage || message instanceof AlreadyPlayedThisTurnErrorMessage ||
                message instanceof NotValidInputErrorMessage || message instanceof NotValidIndexErrorMessage) {
            return "false";
        } else {
            return result;
        }
    }

    /**
     * The method is called by requestInformation and is used to read the attribute latestMessage from turn's player.
     * <p>
     * When method starts, it initializes booleans of the client used to indicate if latestMessage has a valid content
     * and setting to true the need of a new message.
     * <p>
     * When the message will be inserted, and it is ready to be read, the method saves the string and returns it,
     * ready to be elaborated from request information
     *
     * @param player is the index of the player is playing now
     * @return the string inserted by the player
     */
    private String getLatestMessageFromPlayer(int player) throws InterruptedException {
        String result;

        clientHandlers.get(player).setLatestMessageValid(false);
        clientHandlers.get(player).setInformationIsNeeded(true);

        synchronized (clientHandlers.get(player).getLock()) {
            while (!clientHandlers.get(player).isLatestMessageValid()) {
                clientHandlers.get(player).getLock().wait();
            }
        }

        clientHandlers.get(player).setInformationIsNeeded(false);

        result = clientHandlers.get(player).getLatestMessage();
        clientHandlers.get(player).setLatestMessageValid(false);

        return result;
    }

    public PrintCloudsMessage printClouds() {
        PrintCloudsMessage message;
        message = new PrintCloudsMessage(controller.getModel().getBagNClouds().getClouds());
        return message;
    }


    public PrintBoardMessage printBoard(int playerIndex) {
        PrintBoardMessage message;
        message = new PrintBoardMessage(indexToNick.get(playerIndex),
                controller.getModel().getPlayerInteraction().getPlayer(playerIndex).getBoard(),
                controller.getModel().getIslandInteraction().getTowersByPlayer()[playerIndex]);
        return message;
    }

    private PrintIslandMessage printIsland(int index) {
        PrintIslandMessage message;
        message = new PrintIslandMessage(controller.getModel().getIslandInteraction().getIslands().get(index), index,
                controller.getModel().getIslandInteraction().getMotherNature() == index, indexToNick);
        return message;
    }

    public PrintIslandsMessage printIslands() {
        PrintIslandsMessage message;
        message = new PrintIslandsMessage(controller.getModel().getIslandInteraction().getIslands(),
                controller.getModel().getIslandInteraction().getMotherNature(), indexToNick);
        return message;
    }

    public PrintTeachersMessage printTeachers() {
        PrintTeachersMessage message;
        message = new PrintTeachersMessage(controller.getModel().getIslandInteraction().getTeachers(), indexToNick, nickToIndex);
        return message;
    }

    public PrintAssistantCardsMessage printAssistantCards(int player) {
        PrintAssistantCardsMessage message;
        message = new PrintAssistantCardsMessage(controller.getModel().getPlayerInteraction().getPlayer(player).getAssistants());
        return message;
    }

    public PrintCharacterCardsMessage printCharacterCards() {
        PrintCharacterCardsMessage message;
        message = new PrintCharacterCardsMessage(controller.getModel().getCharacterCards());
        return message;
    }

    public ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public Map<Integer, String> getIndexToNick() {
        return indexToNick;
    }
}
