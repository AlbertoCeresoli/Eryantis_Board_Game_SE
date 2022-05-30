package it.polimi.ingsw;

import it.polimi.ingsw.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameHandler implements Runnable {
    private final ArrayList<ClientHandler> clientHandlers;
    private final Controller controller;
    private final MessageGenerator messageGenerator;
    Map<Integer, String> indexToNick;
    Map<String, Integer> nickToIndex;

    public GameHandler(ArrayList<ClientHandler> clients) {
        clientHandlers = clients;
        controller = new Controller(this);
        indexToNick = new HashMap<>();
        nickToIndex = new HashMap<>();
        messageGenerator = new MessageGenerator();

        for (int i = 0; i < clients.size(); i++) {
            indexToNick.put(i, clients.get(i).getNickName());
            nickToIndex.put(clients.get(i).getNickName(), i);
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

    /**
     * Calls methods to print "S" to the selected player
     * @param playerIndex   that has to receive the message
     * @param s the message itself
     */
     public void newMessage(int playerIndex, String s){
        clientHandlers.get(playerIndex).sendMessage(s);
    }

    /**
     * As above but MessageType is a parameter required for the elaboration of certain prints
     * @param playerIndex   that has to receive the message
     * @param type the type of the message
     * @param text the message itself
     */
    public void newMessage(int playerIndex, MessageType type, String text) {
        clientHandlers.get(playerIndex).sendMessage(type, text);

    }

    public void messageToAll(String s) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendMessage(s);
        }
    }

    public void messageToAll(MessageType type, String s) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendMessage(type, s);
        }
    }

    /**
     * This method scans every input from the player checking whether the player used a specific keyword for information printing. In this case it prints what the player needs; then it continues the round tasks giving the input to the controller
     *
     * @param selection what the controller needs to continue the game
     * @param player    the actual player
     * @return iterates until the last input is the one which satisfies the controller
     * @throws InterruptedException
     */
    public String requestInformation(ObjectsToSelect selection, int player) throws InterruptedException {
        String result = getLatestMessageFromPlayer(player);

        Map<MessageType, String> message;

        //command card
        int cardNumber;

        //Play a character card
        if (result.equalsIgnoreCase("/play character card")) {
            if (Constants.isGameMode()) {
                newMessage(player, "Character cards are");
                printCharacterCards(player);

                newMessage(player, "Insert the index of the card you want to play:");

                String temp;
                do {
                    temp = requestInformation(ObjectsToSelect.CHARACTER_CARD, player);
                } while (!temp.equals("false"));

                cardNumber = Integer.parseInt(temp);
                controller.playCard(cardNumber, player);
            } else {
                newMessage(player, "You are playing a game in easy mode, there are no character cards");
            }

            return "false";
        }

        //Show character cards
        if (result.equalsIgnoreCase("/print character cards")) {
            if (controller.getModel().gameRules[4] == 1) {
                printCharacterCards(player);
            } else {
                newMessage(player, "You are playing a game in easy mode, there are no character cards");
            }

            return "false";
        }

        //extra commands
        //print all the students of the player
        if (result.equalsIgnoreCase("/print board")) {
            int index;
            newMessage(player, "Select the player:");
            String temp;

            do {
                temp = requestInformation(ObjectsToSelect.PLAYER, player);
            } while (!temp.equals("false"));

            index = Integer.parseInt(temp);
            printBoard(player, index);

            return "false";
        }

        //Show islands
        if (result.equalsIgnoreCase("/print islands")) {
            printIslands(player);

            return "false";
        }

        if (result.equalsIgnoreCase("/print boards")) {
            for (int i = 0; i < clientHandlers.size(); i++) {
                printBoard(player, i);
            }

            return "false";
        }

        //Show clouds
        if (result.equalsIgnoreCase("/print clouds")) {
            printClouds(player);

            return "false";
        }

        //Show teachers
        if (result.equalsIgnoreCase("/print teachers")) {
            printTeachers(player);

            return "false";
        }

        //Show assistant cards
        if (result.equalsIgnoreCase("/print assistant cards")) {
            printAssistantCards(player);

            return "false";
        }

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

        if (message.containsKey(MessageType.CORRECT_INPUT)) {
            System.out.println(message.get(MessageType.CORRECT_INPUT));
            newMessage(player, message.get(MessageType.CORRECT_INPUT));
            return result;
        }
        else {
            for (MessageType type : message.keySet()) {
                if (!type.equals(MessageType.CORRECT_INPUT)) {
                    newMessage(player, type, message.get(type));
                }
            }

            return "false";
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

    /**
     * prints methods TODO
     */
    public void printClouds(int player) {
        String s;
        s = PrintMessageGenerator.printClouds(controller.getModel().getBagNClouds().getClouds());
        newMessage(player, MessageType.PRINT_ALL_CLOUDS, s);
    }


    public void printBoard(int player, int playerIndex) {
        String s;
        s = PrintMessageGenerator.printBoard(controller.getModel().getPlayerInteraction().getPlayer(playerIndex).getBoard(), indexToNick.get(playerIndex), controller.getModel().getIslandInteraction().getTowersByPlayer()[playerIndex]);
        newMessage(player, MessageType.PRINT_BOARD, s);
    }

    public void printIslands(int player) {
        String s;
        s = PrintMessageGenerator.printAllIslands(controller.getModel().getIslandInteraction().getIslands(), controller.getModel().getIslandInteraction().getMotherNature(), indexToNick);
        //Mario, returns a message
        newMessage(player, MessageType.PRINT_ALL_ISLANDS, s);
    }

    public void printTeachers(int player) {
        String s;
        s = PrintMessageGenerator.printTeachers(controller.getModel().getIslandInteraction().getTeachers(), indexToNick);
        newMessage(player, MessageType.PRINT_TEACHERS, s);
    }

    public void printAssistantCards(int player) {
        String s = PrintMessageGenerator.printAssistantCards(controller.getModel().getPlayerInteraction().getPlayer(player).getAssistants());
        newMessage(player, MessageType.PRINT_ASSISTANT_CARDS, s);
    }

    public void printCharacterCards(int player) {
        String s = PrintMessageGenerator.printCharacterCards(controller.getModel().getCharacterCards());

        newMessage(player, MessageType.PRINT_CHARACTER_CARDS, s);
    }

    public ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

}
