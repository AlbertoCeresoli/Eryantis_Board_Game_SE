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

    public void newMessage(int playerIndex, String s) {
        clientHandlers.get(playerIndex).sendMessage(s);
    }

    public void newMessage(int playerIndex, MessageType type, String s) {
        clientHandlers.get(playerIndex).sendMessage(type, s);
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

    public String requestInformation(ObjectsToSelect selection, int[] cards, int player) throws InterruptedException {
        String result = getLatestMessageFromPlayer(player);

        Map<MessageType, String> message;

        //command card
        int cardNumber;

        //Play a character card
        if (result.equalsIgnoreCase("CARD")) {
            if (controller.getModel().gameRules[4] == 1) {
                printCharacterCards(player);
                newMessage(player, "Number of the card you want to play:");
                cardNumber = Integer.parseInt(requestInformation(ObjectsToSelect.CHARACTER_CARD, cards, player));
                playCard(cardNumber, player);
                return requestInformation(selection, cards, player);
            } else {
                newMessage(player, "You are playing a game in easy mode, there are no character cards");
            }
        }

        //Show character cards
        if (result.equalsIgnoreCase("CHARACTER CARDS")) {
            if (controller.getModel().gameRules[4] == 1) {
                printCharacterCards(player);
                return requestInformation(selection, cards, player);
            } else {
                newMessage(player, "You are playing a game in easy mode, there are no character cards");
            }
        }

        //extra commands
        //print all the students of the player
        if (result.equalsIgnoreCase("STUDENTS")) {
            int index;
            newMessage(player, "Select the player:");
            index = Integer.parseInt(requestInformation(ObjectsToSelect.PLAYER, cards, player));
            printStudents(player, index);
            return requestInformation(selection, cards, player);
        }

        //Show islands
        if (result.equalsIgnoreCase("ISLANDS")) {
            printIslands(player);
            return requestInformation(selection, cards, player);
        }

        //Show clouds
        if (result.equalsIgnoreCase("CLOUDS")) {
            printClouds(player);
            return requestInformation(selection, cards, player);
        }

        //Show teachers
        if (result.equalsIgnoreCase("TEACHERS")) {
            printTeachers(player);
            return requestInformation(selection, cards, player);
        }

        //Show assistant cards
        if (result.equalsIgnoreCase("ASSISTANT CARDS")) {
            int index;
            newMessage(player, "Select the player:");
            index = Integer.parseInt(requestInformation(ObjectsToSelect.PLAYER, cards, player));
            printAssistantCards(index);
            return requestInformation(selection, cards, player);
        }

        //creation of the message based on the object requested
        message = switch (selection) {
            case COLOR -> messageGenerator.colorSelection(result);
            case PLACE -> messageGenerator.placeSelection(result);
            case ISLAND ->
                    messageGenerator.islandSelection(result, controller.getModel().getIslandInteraction().getIslands().size());
            case STEPS ->
                    messageGenerator.stepsSelection(result, controller.getModel().getPlayerInteraction().getPlayer(player).getAssistants().get(cards[player]).getSteps());
            case PLAYER -> messageGenerator.playerIndexSelection(result, controller.getModel().gameRules[0]);
            case ASSISTANT_CARD ->
                    messageGenerator.assistantSelection(result, controller.getModel().gameRules[0], cards, controller.getModel().getPlayerInteraction().getPlayers().get(player).getAssistants());
            case CLOUD ->
                    messageGenerator.cloudSelection(result, controller.getModel().gameRules[0], controller.getModel().getBagNClouds());
            case CHARACTER_CARD -> messageGenerator.characterCardSelection(result);
        };

        if (message.containsKey(MessageType.CORRECT_INPUT)) {
            newMessage(player, message.get(MessageType.CORRECT_INPUT));
            return result;
        } else if (message.containsKey(MessageType.NOT_VALID_INPUT)) {
            newMessage(player, message.get(MessageType.NOT_VALID_INPUT));
            return requestInformation(selection, cards, player);
        } else if (message.containsKey(MessageType.NOT_VALID_INDEX)) {
            newMessage(player, message.get(MessageType.NOT_VALID_INDEX));
            return requestInformation(selection, cards, player);
        } else if (message.containsKey(MessageType.ALREADY_PLAYED)) {
            newMessage(player, message.get(MessageType.ALREADY_PLAYED));
            return requestInformation(selection, cards, player);
        } else if (message.containsKey(MessageType.ALREADY_PLAYED_THIS_TURN)) {
            newMessage(player, message.get(MessageType.ALREADY_PLAYED_THIS_TURN));
            return requestInformation(selection, cards, player);
        }

        return "false";
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
     * if the cost of the card is higher than the coins of the player it will do nothing
     * if the cost is lower:
     * reduce the coins in the hand of the player
     * increase the cost of the card if the boolean firstUsed is =0
     * collects all the information needed and calls useEffect
     */
    public void playCard(int cardPlayed, int player) throws InterruptedException {
        Map<Indexes, Integer> index = new HashMap<>();
        Colors color = null;
        Map<Colors, Integer> StudMap1 = new HashMap<>();
        Map<Colors, Integer> StudMap2 = new HashMap<>();

        for (Indexes i : Indexes.values()) {
            index.put(i, -1);
        }
        for (Colors c : Colors.values()) {
            StudMap1.put(c, 0);
        }
        for (Colors c : Colors.values()) {
            StudMap2.put(c, 0);
        }

        if (!(controller.getModel().getCharacterCards()[cardPlayed].isUsedThisTurn())) {
            if (controller.getModel().getPlayerInteraction().getPlayer(player).getCoins() >= controller.getModel().getCharacterCards()[cardPlayed].getCost()) {
                controller.getModel().getPlayerInteraction().getPlayer(player).removeCoins(controller.getModel().getCharacterCards()[cardPlayed].getCost());
                if (!(controller.getModel().getCharacterCards()[cardPlayed].isUsedThisGame())) {
                    controller.getModel().getCharacterCards()[cardPlayed].increaseCost();
                }

                switch (controller.getModel().getCharacterCards()[cardPlayed].getCardIndex()) {
                    case 1:
                        newMessage(player, "Select the colors of the student you want to move from the Card to the island");
                        for (int i = 0; i < Constants.CARD1_STUDENTS_TO_MOVE; i++) {
                            StudMap1.put(Colors.valueOf(requestInformation(ObjectsToSelect.COLOR, null, -1)), 1);
                        }
                        newMessage(player, "Select the island:");
                        index.put(Indexes.ISLAND_INDEX, Integer.parseInt(requestInformation(ObjectsToSelect.ISLAND, null, -1)));
                        break;
                    case 2:
                    case 6:
                        break;
                    case 3:
                        newMessage(player, "Select the island where you want to calculate the influence:");
                        index.put(Indexes.ISLAND_INDEX, Integer.parseInt(requestInformation(ObjectsToSelect.ISLAND, null, -1)));
                        break;
                    case 4:
                    case 8:
                        index.put(Indexes.PLAYER_INDEX, player);
                        break;
                    case 5:
                        newMessage(player, "Select the island where you want to add an inhibition card:");
                        index.put(Indexes.ISLAND_INDEX, Integer.parseInt(requestInformation(ObjectsToSelect.ISLAND, null, -1)));
                        break;
                    case 7:
                        index.put(Indexes.PLAYER_INDEX, player);
                        newMessage(player, "Select the students you want to take from the card:");
                        for (int i = 0; i < Constants.CARD7_MAX_STUDENTS_TO_MOVE; i++) {
                            StudMap1.put(Colors.valueOf(requestInformation(ObjectsToSelect.COLOR, null, -1)), 1);
                        }
                        newMessage(player, "Select the students you want to remove from the Entrance:");
                        for (int i = 0; i < Constants.CARD7_MAX_STUDENTS_TO_MOVE; i++) {
                            StudMap2.put(Colors.valueOf(requestInformation(ObjectsToSelect.COLOR, null, -1)), 1);
                        }
                        break;
                    case 9:
                        newMessage(player, "Select the colors of the student you want to exclude from the calculate influence");
                        color = Colors.valueOf(requestInformation(ObjectsToSelect.COLOR, null, -1));
                        break;
                    case 10:
                        index.put(Indexes.PLAYER_INDEX, player);
                        newMessage(player, "Select the students in Entrance:");
                        for (int i = 0; i < Constants.CARD10_MAX_STUDENTS_TO_MOVE; i++) {
                            StudMap1.put(Colors.valueOf(requestInformation(ObjectsToSelect.COLOR, null, -1)), 1);
                        }
                        newMessage(player, "Select the students in Hall:");
                        for (int i = 0; i < Constants.CARD10_MAX_STUDENTS_TO_MOVE; i++) {
                            StudMap2.put(Colors.valueOf(requestInformation(ObjectsToSelect.COLOR, null, -1)), 1);
                        }
                        break;
                    case 11:
                        index.put(Indexes.PLAYER_INDEX, 1);
                        newMessage(player, "Select the student you want take from the card and put in your Entrance:");
                        color = Colors.valueOf(requestInformation(ObjectsToSelect.COLOR, null, -1));
                        break;
                    case 12:
                        newMessage(player, "Select the color of students that will be removed from the Hall:");
                        color = Colors.valueOf(requestInformation(ObjectsToSelect.COLOR, null, -1));
                        break;
                }

                newMessage(player, "carta " + controller.getModel().getCharacterCards()[cardPlayed].getCardIndex() + " played");

                /*
                try {
                    model.getCharacterCards()[cardPlayed].useEffect(index, color, StudMap1, StudMap2);
                }
                catch (OutOfBoundException o) {

                }
                */

            } else {
                newMessage(player, "You don't have enough coins");
            }
        } else {
            newMessage(player, "This card has already been played this turn");
        }
    }


    /**
     * prints methods TODO
     */
    public void printClouds(int player) {
        String s;
        s = PrintMessageGenerator.printClouds(controller.getModel().getBagNClouds().getClouds());
        newMessage(player, s);
    }


    public void printStudents(int player, int playerIndex) {
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
        newMessage(player, s);
    }

    public void printAssistantCards(int player) {
        String s = PrintMessageGenerator.printAssistantCards(controller.getModel().getPlayerInteraction().getPlayer(player).getAssistants());
        newMessage(player, MessageType.PRINT_ASSISTANT_CARDS, s);
    }

    public void printCharacterCards(int player) {
        String s = "metodo da implementare";
        //Mario, returns a message
        newMessage(player, s);
    }

    public ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }
}
