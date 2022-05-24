package it.polimi.ingsw;


import it.polimi.ingsw.Constants.*;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Model.Model;

import java.util.Random;

public class Controller {
    private final Model model;
    private final GameHandler gameHandler;
    private int firstPlayer;
    private int actualTurnPlayer;
    private int[] lastAssistantCardsPlayed;
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
            gameRules[1] = 7;
            gameRules[2] = 8;
            gameRules[3] = 0;
        }
        //3 players rules
        if (Constants.getNumPlayers() == 3) {
            gameRules[1] = 9;
            gameRules[2] = 6;
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
        planningPhase();

        actionPhase();
    }

    /**
     * During the action phase the players play following the playerOrder array. They move students from their entrance to hall/islands
     * and then mother nature to calculate influence on that island
     * @throws InterruptedException
     */
    private void actionPhase() throws InterruptedException {
        Colors color = null;

        //for the number of players
        for (int i = 0; i < model.gameRules[0]; i++) {
            gameHandler.getClientHandlers().get(actualTurnPlayer).setYourTurn(false);
            actualTurnPlayer = roundPlayerOrder[i];
            gameHandler.getClientHandlers().get(actualTurnPlayer).setYourTurn(true);
            gameHandler.newMessage(actualTurnPlayer, "Player " + actualTurnPlayer + ": it's your turn");

            //1) student movement
            //for the students to move
            for (int j = 0; j < model.gameRules[0] + 1; j++) {
                color = colorSelection(lastAssistantCardsPlayed, color);

                studentDestination(lastAssistantCardsPlayed, color);
            }

            //MN movement
            if (moveMN(lastAssistantCardsPlayed)) return;

            cloudSelection(lastAssistantCardsPlayed);

            checkEnd();
        }
    }

    /**
     * The player chooses how many steps mother nature will move, then the influence is calculated on the island selected.
     * Eventually checkEnd looks for a candidate winner if model.moveMN throws an EndGameException
     * @param cards cards played this turn to not exceed the max amount of steps for mother nature
     * @return true (ending the game) if an EndGameException occurs
     * @throws InterruptedException
     */
    private boolean moveMN(int[] cards) throws InterruptedException {
        String temp;
        int index;
        gameHandler.newMessage(actualTurnPlayer, "Player" + actualTurnPlayer + ": How many steps you want MN to do?");
        gameHandler.newMessage(actualTurnPlayer, "you can choose from 1 to " + model.getPlayerInteraction().getPlayer(actualTurnPlayer).getAssistants().get(cards[actualTurnPlayer]).getSteps());

        do {
            temp = gameHandler.requestInformation(ObjectsToSelect.STEPS, cards, actualTurnPlayer);
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
     * Using the getWinner method finds the winner and prints it
     */
    private void checkEnd() {
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
     * At the end of every action phase the player has to select a cloud to re-fill his entrance
     * @param cards cards played in that turn
     * @throws InterruptedException
     */
    private void cloudSelection(int[] cards) throws InterruptedException {
        String temp;
        int index;
        //cloud selection
        gameHandler.newMessage(actualTurnPlayer, "You have to select the cloud with the students you want in your entrance");
        gameHandler.printClouds(actualTurnPlayer);
        gameHandler.newMessage(actualTurnPlayer, "Select the cloud index:");
        do {
            temp = gameHandler.requestInformation(ObjectsToSelect.CLOUD, cards, actualTurnPlayer);
        } while (temp.equals("false"));

        index = Integer.parseInt(temp);
        model.studentsCloudToEntrance(actualTurnPlayer, index);
        gameHandler.newMessage(actualTurnPlayer, "Cloud " + index + " students moved in the Entrance of player " + actualTurnPlayer);
    }

    /**
     *  The player needs to provide the place where the student has to be moved
     * @param cards played this turn
     * @param color of the student to be moved
     * @throws InterruptedException
     */
    private void studentDestination(int[] cards, Colors color) throws InterruptedException {
        String temp;
        int index;
        //select the place
        gameHandler.newMessage(actualTurnPlayer, "Where do you want to put the " + color + " student (Hall or Island)");
        temp = gameHandler.requestInformation(ObjectsToSelect.PLACE, cards, actualTurnPlayer);
        while (temp.equals("false")) {
            temp = gameHandler.requestInformation(ObjectsToSelect.PLACE, cards, actualTurnPlayer);
        }
        if (temp.equalsIgnoreCase("Hall")) {
            model.moveFromEntranceToHall(color, actualTurnPlayer);
            gameHandler.newMessage(actualTurnPlayer, "One " + color + " student moved from entrance to hall");
            gameHandler.printTeachers(actualTurnPlayer);
        }
        if (temp.equalsIgnoreCase("Island")) {
            gameHandler.newMessage(actualTurnPlayer, "Select the island:");
            do {
                temp = gameHandler.requestInformation(ObjectsToSelect.ISLAND, cards, actualTurnPlayer);
            } while (temp.equals("false"));

            index = Integer.parseInt(temp);
            model.moveFromEntranceToIsland(color, actualTurnPlayer, index);
            gameHandler.newMessage(actualTurnPlayer, "One " + color + " student moved from entrance to island " + index);
            gameHandler.printIslands(actualTurnPlayer);
        }
    }

    /**
     * Let the player select the color of the student he wants to move making sure he has at least one of that color in his entrance
     * @param cards cards played this turn
     * @param color color chosen (initialized NULL)
     * @return color chosen
     * @throws InterruptedException
     */
    private Colors colorSelection(int[] cards, Colors color) throws InterruptedException {
        String temp;
        boolean result = true;
        //select the color of the student to move
        while (result) {
            gameHandler.newMessage(actualTurnPlayer, "Select the color of the student you want to move:");
            gameHandler.printStudents(actualTurnPlayer, actualTurnPlayer);

            do {
                temp = gameHandler.requestInformation(ObjectsToSelect.COLOR, cards, actualTurnPlayer);
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
     * In the first part of the round the cloud tiles are filled with students, then each player plays an assistant cards
     * and their order of play in the action phase is decided up to the priority of the cards they choose.
     * @throws InterruptedException
     */
    private void planningPhase() throws InterruptedException {
        //fill the clouds with the new students
        model.getBagNClouds().studentsBagToCloud();

        //getting assistant cards that players decided to play
        lastAssistantCardsPlayed = playAssistantCards();

        //calculating player order according to played cards
        roundPlayerOrder = calculatePlayerOrder(lastAssistantCardsPlayed);

        //sending this round's order to all the players
        printPlayerOrder(roundPlayerOrder);
    }

    /**
     * Every player chooses the assistant card to play, an array represents the state of the playability of their assistant cards while
     * another array contains the cards played by each player in this turn
     * @return  the array containing the card played in this turn
     * @throws InterruptedException
     */
    private int[] playAssistantCards() throws InterruptedException {
        int[] cards = new int[Constants.getNumPlayers()];

        //initializes index of the card played by the player to -1
        for (int i = 0; i < model.gameRules[0]; i++) {
            cards[i] = -1;
        }

        //sets the player and makes it play an assistant card
        for (int i = 0; i < Constants.getNumPlayers(); i++) {
            nextTurnPlayer((firstPlayer + i) % Constants.getNumPlayers());

            gameHandler.printAssistantCards(actualTurnPlayer);
            gameHandler.newMessage(actualTurnPlayer, gameHandler.getClientHandlers().get(actualTurnPlayer).getNickName() + " play your assistant card");

            String temp;

            do {
                temp = gameHandler.requestInformation(ObjectsToSelect.ASSISTANT_CARD, cards, actualTurnPlayer);
            } while (temp.equals("false"));

            cards[actualTurnPlayer] = Integer.parseInt(temp);
        }

        return cards;
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
     * get methods
     */
    public Model getModel() {
        return model;
    }

    public int getActualTurnPlayer() {
        return actualTurnPlayer;
    }
}
