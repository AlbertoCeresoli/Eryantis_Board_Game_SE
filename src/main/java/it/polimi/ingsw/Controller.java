package it.polimi.ingsw;


import it.polimi.ingsw.Constants.*;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Model.Model;

import java.util.Random;

public class Controller {
    private int firstPlayer;
    private final Model model;
    private boolean end;
    private final GameHandler gameHandler;
    private int actualTurnPlayer;

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

        //selection of the first player
        Random rand = new Random();
        firstPlayer = rand.nextInt(model.gameRules[0]);
        actualTurnPlayer = firstPlayer;
        gameHandler.messageToAll("the first player will be: " + firstPlayer);

        while (!end) {
            round();
        }

        gameHandler.messageToAll("END GAME, GG");
    }

    /**
     *
     */
    public void round() throws InterruptedException {
        int[] cards = new int[model.gameRules[0]];
        int[] playerOrder;

        //fill the clouds with the new students
        model.getBagNClouds().studentsBagToCloud();

        //play assistant card
        for (int i = 0; i < model.gameRules[0]; i++) {
            cards[i] = -1;
        }

        for (int i = 0; i < model.gameRules[0]; i++) {
            gameHandler.getClientHandlers().get(actualTurnPlayer).setYourTurn(false);
            actualTurnPlayer = (firstPlayer + i) % model.gameRules[0];
            gameHandler.getClientHandlers().get(actualTurnPlayer).setYourTurn(true);
            gameHandler.newMessage(actualTurnPlayer, "Your assistant cards:");
            gameHandler.printAssistantCards(actualTurnPlayer);
            gameHandler.newMessage(actualTurnPlayer,"player " + actualTurnPlayer + " play your assistant card:");

            cards[actualTurnPlayer] = Integer.parseInt(gameHandler.requestInformation(ObjectsToSelect.ASSISTANT_CARD, cards, actualTurnPlayer));
        }
        playerOrder = model.getPlayerInteraction().playAssistantCard(cards);
        firstPlayer = playerOrder[0];

        gameHandler.messageToAll("the player order in this round will be:");
        for (int i = 0; i < model.gameRules[0]; i++) {
            gameHandler.messageToAll(playerOrder[i] + " ");
        }

        //action phase
        //for the number of players
        Colors color = null;
        String temp;
        int index;


        //for the number of players
        for (int i = 0; i < model.gameRules[0]; i++) {
            gameHandler.getClientHandlers().get(actualTurnPlayer).setYourTurn(false);
            actualTurnPlayer = playerOrder[i];
            gameHandler.getClientHandlers().get(actualTurnPlayer).setYourTurn(true);
            gameHandler.newMessage(actualTurnPlayer,"Player " + actualTurnPlayer + ": it's your turn");

            //1) student movement
            //for the students to move
            for (int j = 0; j < model.gameRules[0] + 1; j++) {
                boolean result = true;
                //select the color of the student to move
                while (result) {
                    gameHandler.newMessage(actualTurnPlayer,"Select the color of the student you want to move:");
                    gameHandler.printStudents(actualTurnPlayer, actualTurnPlayer);
                    temp = gameHandler.requestInformation(ObjectsToSelect.COLOR, cards, actualTurnPlayer);
                    color = Colors.valueOf(temp.toUpperCase());
                    if (model.getPlayerInteraction().getPlayer(actualTurnPlayer).getBoard().getStudEntrance().get(color) > 0) {
                        result = false;
                    } else {
                        gameHandler.newMessage(actualTurnPlayer, "you don't have " + color + " students in Entrance");
                    }
                }

                //select the place
                gameHandler.newMessage(actualTurnPlayer,"Where do you want to put the " + color + " student (Hall or Island)");
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
                    temp = gameHandler.requestInformation(ObjectsToSelect.ISLAND, cards, actualTurnPlayer);
                    index = Integer.parseInt(temp);
                    model.moveFromEntranceToIsland(color, actualTurnPlayer, index);
                    gameHandler.newMessage(actualTurnPlayer, "One " + color + " student moved from entrance to island " + index);
                    gameHandler.printIslands(actualTurnPlayer);
                }
            }

            //MN movement
            gameHandler.newMessage(actualTurnPlayer, "Player" + actualTurnPlayer + ": How many steps you want MN to do?");
            gameHandler.newMessage(actualTurnPlayer, "you can choose from 1 to " + model.getPlayerInteraction().getPlayer(actualTurnPlayer).getAssistants().get(cards[actualTurnPlayer]).getSteps());
            temp = gameHandler.requestInformation(ObjectsToSelect.STEPS, cards, actualTurnPlayer);
            index = Integer.parseInt(temp);
            try {
                model.moveMN(index);
            } catch (EndGameException e) {
                end = true;
                int winner = model.getWinner();
                if (winner == -1) {
                    gameHandler.messageToAll("The game ended with a tie");
                } else {
                    gameHandler.messageToAll("The winner is player " + winner);
                }
                return;
            }
            gameHandler.newMessage(actualTurnPlayer, "MN moved " + index + "steps");

            //cloud selection
            gameHandler.newMessage(actualTurnPlayer, "You have to select the cloud with the students you want in your entrance");
            gameHandler.printClouds(actualTurnPlayer);
            gameHandler.newMessage(actualTurnPlayer, "Select the cloud index:");
            temp = gameHandler.requestInformation(ObjectsToSelect.CLOUD, cards, actualTurnPlayer);
            index = Integer.parseInt(temp);
            model.studentsCloudToEntrance(actualTurnPlayer, index);
            gameHandler.newMessage(actualTurnPlayer, "Cloud " + index + " students moved in the Entrance of player " + actualTurnPlayer);

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
