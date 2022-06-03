package it.polimi.ingsw;

import it.polimi.ingsw.Constants.*;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Model.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Controller {
    private final Model model;
    private final GameHandler gameHandler;
    private int firstPlayer;
    private int actualTurnPlayer;
    private final int[] lastAssistantCardsPlayed;
    private int[] roundPlayerOrder;
    private boolean end;

    /**
     * Controller's constructor
     * it initializes controller's attributes and calls model's constructor
     */
    public Controller(GameHandler gh) {
        int[] gameRules = new int[5];
        gameRules[0] = Constants.getNumPlayers();
        //2 players rules
        if (Constants.getNumPlayers() == 2) {
            gameRules[1] = Constants.STUDENTS_IN_ENTRANCE_2_PLAYERS;
            gameRules[2] = Constants.TOWERS_2_PLAYERS;
            gameRules[3] = 0;
        }
        //3 players rules
        if (Constants.getNumPlayers() == 3) {
            gameRules[1] = Constants.STUDENTS_IN_ENTRANCE_3_PLAYERS;
            gameRules[2] = Constants.TOWERS_3_PLAYERS;
            gameRules[3] = 0;
        }
        //gameMode hard
        if (Constants.isGameMode()) {
            gameRules[4] = 1;
        } else {
            gameRules[4] = 0;
        }

        model = new Model(gameRules);
        gameHandler = gh;

        lastAssistantCardsPlayed = new int[Constants.getNumPlayers()];
        end = false;
    }

    /**
     * Calls the method round until we have a winner
     */
    public void startGame() throws InterruptedException {
        model.initializeGame();

        firstPlayer();

        while (!end) {
            round();
        }

        gameHandler.messageToAll("END GAME, GG");
    }

    /**
     * This is used to randomly choose the player who'll go first
     */
    private void firstPlayer() {
        //first player is chosen randomly
        Random rand = new Random();
        firstPlayer = rand.nextInt(model.gameRules[0]);
        actualTurnPlayer = firstPlayer;
        gameHandler.messageToAll(gameHandler.getClientHandlers().get(firstPlayer).getNickName() + " will start playing");
    }

    /**
     * This is the method which continue iterating giving the rhythm to all the turn phases. It responds to the needs of the controller providing all the input from the players.
     */
    public void round() throws InterruptedException {
        try {
            planningPhase();
            actionPhase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * In the first part of the round the cloud tiles are filled with students, then each player plays an assistant cards
     * and their order of play in the action phase is decided up to the priority of the cards they choose.
     * @throws InterruptedException
     */
    private void planningPhase() throws InterruptedException, IOException {
        //fill the clouds with the new students
        model.getBagNClouds().studentsBagToCloud();

        //getting assistant cards that players decided to play
        playAssistantCards();

        //calculating player order according to played cards
        roundPlayerOrder = calculatePlayerOrder(lastAssistantCardsPlayed);

        //sending this round's order to all the players
        printPlayerOrder(roundPlayerOrder);
    }

    /**
     * During the action phase the players play following the playerOrder array. They move students from their entrance to hall/islands
     * and then mother nature to calculate influence on that island
     * @throws InterruptedException
     */
    private void actionPhase() throws InterruptedException, IOException {
        Colors color = null;

        //for the number of players
        for (int i = 0; i < model.gameRules[0]; i++) {
            nextTurnPlayer(roundPlayerOrder[i]);
            gameHandler.newMessage(actualTurnPlayer, "Player " + actualTurnPlayer + ": it's your turn");

            //1) student movement
            //for the students to move
            for (int j = 0; j < model.gameRules[0] + 1; j++) {
                color = colorSelection(color);

                studentDestination(color);
            }

            //MN movement
            if (moveMN(lastAssistantCardsPlayed)) return;

            cloudSelection();

            checkEnd();
        }
    }

    /**
     * Update the actual player turn
     * @param nextPlayer the player who plays next
     */
    private void nextTurnPlayer(int nextPlayer) {
        gameHandler.getClientHandlers().get(actualTurnPlayer).setYourTurn(false);
        actualTurnPlayer = nextPlayer;
        gameHandler.getClientHandlers().get(actualTurnPlayer).setYourTurn(true);
    }

    /**
     * Every player chooses the assistant card to play, an array represents the state of the playability of their assistant cards while
     * another array contains the cards played by each player in this turn. Sets the array containing the card played in this turn
     * @throws InterruptedException
     */
    private void playAssistantCards() throws InterruptedException, IOException {
        //initializes index of the card played by the player to -1
        for (int i = 0; i < Constants.getNumPlayers(); i++) {
            lastAssistantCardsPlayed[i] = -1;
        }

        //sets the player and makes it play an assistant card
        for (int i = 0; i < Constants.getNumPlayers(); i++) {
            nextTurnPlayer((firstPlayer + i) % Constants.getNumPlayers());

            gameHandler.printAssistantCards(actualTurnPlayer);
            gameHandler.newMessage(actualTurnPlayer, gameHandler.getClientHandlers().get(actualTurnPlayer).getNickName() + " play your assistant card");

            String temp;

            do {
                temp = gameHandler.requestInformation(ObjectsToSelect.ASSISTANT_CARD, actualTurnPlayer);
            } while (temp.equals("false"));

            lastAssistantCardsPlayed[actualTurnPlayer] = Integer.parseInt(temp);
        }
    }

    /**
     * The playerOrder array is defined
     * @param cards each player chose
     * @return playerOrder for the action phase
     */
    private int[] calculatePlayerOrder(int[] cards) {
        int[] playerOrder;
        playerOrder = model.getPlayerInteraction().playAssistantCard(cards);
        firstPlayer = playerOrder[0];
        return playerOrder;
    }

    /**
     * Printer
     * @param playerOrder
     */
    private void printPlayerOrder(int[] playerOrder) {
        gameHandler.messageToAll("Players order of the round will be:");
        for (int i = 0; i < model.gameRules[0]; i++) {
            gameHandler.messageToAll((i + 1) + ". " + gameHandler.getClientHandlers().get(playerOrder[i]).getNickName());
        }
    }

    /**
     * Let the player select the color of the student he wants to move making sure he has at least one of that color in his entrance
     * @param color color chosen (initialized NULL)
     * @return color chosen
     * @throws InterruptedException
     */
    private Colors colorSelection(Colors color) throws InterruptedException, IOException {
        String temp;
        boolean result = true;
        //select the color of the student to move
        while (result) {
            gameHandler.newMessage(actualTurnPlayer, "Select the color of the student you want to move:");
            gameHandler.printBoard(actualTurnPlayer, actualTurnPlayer);

            do {
                temp = gameHandler.requestInformation(ObjectsToSelect.COLOR, actualTurnPlayer);
            } while (temp.equals("false"));

            color = Colors.valueOf(temp.toUpperCase());
            if (model.getPlayerInteraction().getPlayer(actualTurnPlayer).getBoard().getStudEntrance().get(color) > 0) {
                result = false;
            } else {
                gameHandler.newMessage(actualTurnPlayer, "you don't have " + color + " students in Entrance");
            }
        }
        return color;
    }

    /**
     *  The player needs to provide the place where the student has to be moved
     * @param color of the student to be moved
     * @throws InterruptedException
     */
    private void studentDestination(Colors color) throws InterruptedException, IOException {
        String temp;
        int index;
        //select the place
        gameHandler.newMessage(actualTurnPlayer, "Where do you want to put the " + color + " student (Hall or Island)");
        temp = gameHandler.requestInformation(ObjectsToSelect.PLACE, actualTurnPlayer);
        while (temp.equals("false")) {
            temp = gameHandler.requestInformation(ObjectsToSelect.PLACE, actualTurnPlayer);
        }
        if (temp.equalsIgnoreCase("Hall")) {
            model.moveFromEntranceToHall(color, actualTurnPlayer);
            gameHandler.newMessage(actualTurnPlayer, "One " + color + " student moved from entrance to hall");
            gameHandler.printTeachers(actualTurnPlayer);
        }
        if (temp.equalsIgnoreCase("Island")) {
            gameHandler.newMessage(actualTurnPlayer, "Select the island:");
            do {
                temp = gameHandler.requestInformation(ObjectsToSelect.ISLAND, actualTurnPlayer);
            } while (temp.equals("false"));

            index = Integer.parseInt(temp);
            model.moveFromEntranceToIsland(color, actualTurnPlayer, index);
            gameHandler.newMessage(actualTurnPlayer, "One " + color + " student moved from entrance to island " + index);
            gameHandler.printIslands(actualTurnPlayer);
        }
    }

    /**
     * The player chooses how many steps mother nature will move, then the influence is calculated on the island selected.
     * Eventually checkEnd looks for a candidate winner if model.moveMN throws an EndGameException
     * @param cards cards played this turn to not exceed the max amount of steps for mother nature
     * @return true (ending the game) if an EndGameException occurs
     * @throws InterruptedException
     */
    private boolean moveMN(int[] cards) throws InterruptedException, IOException {
        String temp;
        int index;
        gameHandler.newMessage(actualTurnPlayer, "Player" + actualTurnPlayer + ": How many steps you want MN to do?");
        gameHandler.newMessage(actualTurnPlayer, "you can choose from 1 to " + model.getPlayerInteraction().getPlayer(actualTurnPlayer).getAssistants().get(cards[actualTurnPlayer]).getSteps());

        do {
            temp = gameHandler.requestInformation(ObjectsToSelect.STEPS, actualTurnPlayer);
        } while (temp.equals("false"));

        index = Integer.parseInt(temp);
        try {
            model.moveMN(index);
        } catch (EndGameException e) {
            checkEnd();
            return true;
        }
        gameHandler.newMessage(actualTurnPlayer, "MN moved " + index + "steps");
        return false;
    }

    /**
     * At the end of every action phase the player has to select a cloud to re-fill his entrance
     * @throws InterruptedException
     */
    private void cloudSelection() throws InterruptedException, IOException {
        String temp;
        int index;
        //cloud selection
        gameHandler.newMessage(actualTurnPlayer, "You have to select the cloud with the students you want in your entrance");
        gameHandler.printClouds(actualTurnPlayer);
        gameHandler.newMessage(actualTurnPlayer, "Select the cloud index:");
        do {
            temp = gameHandler.requestInformation(ObjectsToSelect.CLOUD, actualTurnPlayer);
        } while (temp.equals("false"));

        index = Integer.parseInt(temp);
        model.studentsCloudToEntrance(actualTurnPlayer, index);
        gameHandler.newMessage(actualTurnPlayer, "Cloud " + index + " students moved in the Entrance of player " + actualTurnPlayer);
    }

    /**
     * Using the getWinner method finds the winner and prints it
     */
    public void checkEnd() {
        if (model.endGame()) {
            end = true;
            int winner = model.getWinner();
            if (winner == -1) {
                gameHandler.messageToAll("The game ended with a tie");
            } else {
                gameHandler.messageToAll("The winner is player " + winner);
            }
        }
    }

    /**
     * if the cost of the card is higher than the coins of the player it will do nothing
     * if the cost is lower:
     * reduce the coins in the hand of the player
     * increase the cost of the card if the boolean firstUsed is =0
     * collects all the information needed and calls useEffect
     */
    public void playCard(int cardPlayed, int player) throws InterruptedException, IOException {
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

        if (!(getModel().getCharacterCards()[cardPlayed].isUsedThisTurn())) {
            if (getModel().getPlayerInteraction().getPlayer(player).getCoins() >= getModel().getCharacterCards()[cardPlayed].getCost()) {
                getModel().getPlayerInteraction().getPlayer(player).removeCoins(getModel().getCharacterCards()[cardPlayed].getCost());
                if (!(getModel().getCharacterCards()[cardPlayed].isUsedThisGame())) {
                    getModel().getCharacterCards()[cardPlayed].increaseCost();
                }

                int islandIndex;
                int numberOfIslands;

                switch (getModel().getCharacterCards()[cardPlayed].getCardIndex()) {
                    case 1:
                        gameHandler.newMessage(player, "Select the colors of the student you want to move from the Card to the island");
                        for (int i = 0; i < Constants.MONK_STUDENTS_TO_MOVE; i++) {
                            Colors selectedColor = selectColor();

                            while (thatColorIsNotAvailable(getModel().getCharacterCards()[cardPlayed].getStudents(),
                                    selectedColor, StudMap1.get(selectedColor) + 1)) {
                                gameHandler.newMessage(player, "That color is not available on the card, please select another one");
                                selectedColor = selectColor();
                            }

                            StudMap1.put(selectedColor, StudMap1.get(selectedColor) + 1);
                        }

                        gameHandler.newMessage(player, "Select the island:");
                        islandIndex = selectIslandIndex();
                        numberOfIslands = getModel().getIslandInteraction().getIslands().size();

                        while (islandIndex < 0 || islandIndex >= numberOfIslands) {
                            gameHandler.newMessage(player, "You selected an out of bound index, please insert a correct one");
                            islandIndex = selectIslandIndex();
                        }

                        index.put(Indexes.ISLAND_INDEX, islandIndex);
                        break;
                    case 2:
                    case 6:
                        break;
                    case 3:
                        gameHandler.newMessage(player, "Select the island where you want to calculate the influence:");
                        islandIndex = selectIslandIndex();
                        numberOfIslands = getModel().getIslandInteraction().getIslands().size();

                        while (islandIndex < 0 || islandIndex >= numberOfIslands) {
                            gameHandler.newMessage(player, "You selected an out of bound index, please insert a correct one");
                            islandIndex = selectIslandIndex();
                        }

                        index.put(Indexes.ISLAND_INDEX, islandIndex);
                        break;
                    case 4:
                    case 8:
                        index.put(Indexes.PLAYER_INDEX, player);
                        break;
                    case 5:
                        gameHandler.newMessage(player, "Select the island where you want to add an inhibition card:");
                        islandIndex = selectIslandIndex();
                        numberOfIslands = getModel().getIslandInteraction().getIslands().size();

                        while (islandIndex < 0 || islandIndex >= numberOfIslands) {
                            gameHandler.newMessage(player, "You selected an out of bound index, please insert a correct one");
                            islandIndex = selectIslandIndex();
                        }

                        index.put(Indexes.ISLAND_INDEX, islandIndex);
                        break;
                    case 7:
                        index.put(Indexes.PLAYER_INDEX, player);

                        gameHandler.newMessage(player, "Select the students you want to take from the card:");
                        for (int i = 0; i < Constants.JOKER_MAX_STUDENTS_TO_MOVE; i++) {
                            Colors selectedColor = selectColor();

                            while (thatColorIsNotAvailable(getModel().getCharacterCards()[cardPlayed].getStudents(),
                                    selectedColor, StudMap1.get(selectedColor) + 1)) {
                                gameHandler.newMessage(player, "That color is not available on the card, please select another one");
                                selectedColor = selectColor();
                            }

                            StudMap1.put(selectedColor, StudMap1.get(selectedColor) + 1);
                        }

                        gameHandler.newMessage(player, "Select the students you want to remove from the Entrance:");
                        for (int i = 0; i < Constants.JOKER_MAX_STUDENTS_TO_MOVE; i++) {
                            Colors selectedColor = selectColor();

                            while (thatColorIsNotAvailable(getModel().getPlayerInteraction().getPlayer(player).getBoard().getStudEntrance(),
                                    selectedColor, StudMap1.get(selectedColor) + 1)) {
                                gameHandler.newMessage(player, "That color is not available in the entrance, please select another one");
                                selectedColor = selectColor();
                            }

                            StudMap2.put(selectedColor, StudMap2.get(selectedColor) + 1);
                        }
                        break;
                    case 9:
                        gameHandler.newMessage(player, "Select the colors of the student you want to exclude from the calculate influence");
                        color = selectColor();
                        break;
                    case 10:
                        index.put(Indexes.PLAYER_INDEX, player);

                        gameHandler.newMessage(player, "Select the students in Entrance:");
                        for (int i = 0; i < Constants.MINSTREL_MAX_STUDENTS_TO_MOVE; i++) {
                            Colors selectedColor = selectColor();

                            while (thatColorIsNotAvailable(getModel().getPlayerInteraction().getPlayer(player).getBoard().getStudEntrance(),
                                    selectedColor, StudMap1.get(selectedColor) + 1)) {
                                gameHandler.newMessage(player, "That color is not available in the entrance, please select another one");
                                selectedColor = selectColor();
                            }

                            StudMap1.put(selectedColor, StudMap1.get(selectedColor) + 1);
                        }

                        gameHandler.newMessage(player, "Select the students in Hall:");
                        for (int i = 0; i < Constants.MINSTREL_MAX_STUDENTS_TO_MOVE; i++) {
                            Colors selectedColor = selectColor();

                            while (thatColorIsNotAvailable(getModel().getPlayerInteraction().getPlayer(player).getBoard().getStudHall(),
                                    selectedColor, StudMap1.get(selectedColor) + 1)) {
                                gameHandler.newMessage(player, "That color is not available in the hall, please select another one");
                                selectedColor = selectColor();
                            }

                            StudMap2.put(selectedColor, StudMap2.get(selectedColor) + 1);
                        }
                        break;
                    case 11:
                        index.put(Indexes.PLAYER_INDEX, player);

                        gameHandler.newMessage(player, "Select the student you want take from the card and put in your Entrance:");

                        color = selectColor();

                        while (thatColorIsNotAvailable(getModel().getCharacterCards()[cardPlayed].getStudents(),
                                color, StudMap1.get(color) + 1)) {
                            gameHandler.newMessage(player, "That color is not available on the card, please select another one");
                            color = selectColor();
                        }

                        break;
                    case 12:
                        gameHandler.newMessage(player, "Select the color of students that will be removed from the Hall:");

                        color = selectColor();

                        while (thatColorIsNotAvailable(getModel().getCharacterCards()[cardPlayed].getStudents(),
                                color, StudMap1.get(color) + 1)) {
                            gameHandler.newMessage(player, "That color is not available on the card, please select another one");
                            color = selectColor();
                        }

                        break;
                }

                gameHandler.newMessage(player, "Card " + getModel().getCharacterCards()[cardPlayed].getCardIndex() + " played");

                try {
                    getModel().getCharacterCards()[cardPlayed].useEffect(index, color, StudMap1, StudMap2);
                } catch (EndGameException e) {
                    checkEnd();
                }

            } else {
                gameHandler.newMessage(player, "You don't have enough coins");
            }
        } else {
            gameHandler.newMessage(player, "This card has already been played this turn");
        }
    }

    public Colors selectColor() throws InterruptedException, IOException {
        Colors color;

        String temp;

        do {
            temp = gameHandler.requestInformation(ObjectsToSelect.COLOR, -1);
        } while (temp.equals("false"));

        color = Colors.valueOf(temp);

        return color;
    }

    public int selectIslandIndex() throws InterruptedException, IOException {
        String temp;

        do {
            temp = gameHandler.requestInformation(ObjectsToSelect.ISLAND, -1);
        } while (temp.equals("false"));

        return Integer.parseInt(temp);
    }

    public boolean thatColorIsNotAvailable(Map<Colors, Integer> students, Colors color, int numberOfThatColorsRequested) {
        return students.get(color) < numberOfThatColorsRequested;
    }

    /**
     * get methods
     */
    public Model getModel() {
        return model;
    }

    public int getActualTurnPlayer() {
        return actualTurnPlayer;
    }

    public int[] getLastAssistantCardsPlayed() {
        return lastAssistantCardsPlayed;
    }
}
