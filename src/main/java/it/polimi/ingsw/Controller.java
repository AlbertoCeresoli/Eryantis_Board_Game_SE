package it.polimi.ingsw;


import it.polimi.ingsw.Constants.*;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Model.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private int firstPlayer;
    private final Model model;
    private boolean end;
    private final MessageGenerator messageGenerator;

    /**
     * Controller's constructor
     * it initializes controller's attributes and calls model's constructor
     */
    public Controller(int numberPlayers, boolean gameMode){
        int[] gameRules = new int[5];
        gameRules[0]= numberPlayers;
        //2 players rules
        if (numberPlayers==2){
            gameRules[1]=7;
            gameRules[2]=8;
            gameRules[3]=0;
        }
        //3 players rules
        if (numberPlayers==3){
            gameRules[1]=9;
            gameRules[2]=6;
            gameRules[3]=0;
        }
        //gameMode hard
        if (gameMode){
            gameRules[4]= 1;
        }
        else {gameRules[4]=0;}
        model = new Model(gameRules);
        messageGenerator = new MessageGenerator();

        end = false;
    }

    /**
     * si occupa di chiamare round fino a quando il flag di fine partita non si alza
     */
    public void startGame(Scanner input){
        model.initializeGame();

        //selection of the first player
        Random rand = new Random();
        firstPlayer = rand.nextInt(model.gameRules[0]);
        System.out.println("the first player will be: " + firstPlayer);

        while (!end) {
            round(input);
        }

        System.out.println("END GAME, GG");
    }

    /**
     *
     */
    public void round(Scanner input){
        int[] cards = new int[model.gameRules[0]];
        int[] playerOrder;

        //fill the clouds with the new students
        model.getBagNClouds().studentsBagToCloud();

        //play assistant card
        for (int i=0; i<model.gameRules[0]; i++){
            cards[i]=-1;
        }

        for (int i=0; i<model.gameRules[0]; i++){
            int player = (firstPlayer + i) % model.gameRules[0];
            System.out.println("assistant cards of " + player);
            printAssistantCards(player);
            System.out.println("player " + player + " play your assistant card:");

            cards[player] = Integer.parseInt(getTheLine(ObjectsToSelect.ASSISTANT_CARD, cards, player, input));
        }
        playerOrder = model.getPlayerInteraction().playAssistantCard(cards);
        firstPlayer = playerOrder[0];

        System.out.println("the player order in this round will be:");
        for (int i=0; i<model.gameRules[0]; i++) {
            System.out.println(playerOrder[i] + " ");
        }

        //action phase
        //for the number of players
        Colors color = null;
        String temp;
        int index;


        //for the number of players
        for (int i=0; i<model.gameRules[0]; i++){
            System.out.println("Player " + playerOrder[i] + ": it's your turn");

            //1) student movement
            //for the students to move
            for (int j=0; j<model.gameRules[0]+1; j++){
                boolean result = true;
                //select the color of the student to move
                while (result) {
                    System.out.println("Select the color of the student you want to move:");
                    printStudents(playerOrder[i]);
                    temp = getTheLine(ObjectsToSelect.COLOR, cards, playerOrder[i], input);
                    color = Colors.valueOf(temp.toUpperCase());
                    if (model.getPlayerInteraction().getPlayer(playerOrder[i]).getBoard().getStudEntrance().get(color)>0){
                        result = false;
                    }
                    else {
                        System.out.println("you don't have " + color + " students in Entrance");
                    }
                }

                //select the place
                System.out.println("Where do you want to put the " + color + " student (Hall or Island)");
                temp = getTheLine(ObjectsToSelect.PLACE, cards, playerOrder[i], input);
                while (temp.equals("false")) {
                    temp = getTheLine(ObjectsToSelect.PLACE, cards, playerOrder[i], input);
                }
                if (temp.equalsIgnoreCase("Hall")){
                    model.moveFromEntranceToHall(color, playerOrder[i]);
                    System.out.println("One " + color + " student moved from entrance to hall");
                    printTeachers();
                }
                if (temp.equalsIgnoreCase("Island")){
                    System.out.println("Select the island:");
                    temp = getTheLine(ObjectsToSelect.ISLAND, cards, playerOrder[i], input);
                    index = Integer.parseInt(temp);
                    model.moveFromEntranceToIsland(color, playerOrder[i], index);
                    System.out.println("One " + color + " student moved from entrance to island " + index);
                    printIslands();
                }
            }

            //MN movement
            System.out.println("Player" + playerOrder[i] + ": How many steps you want MN to do?");
            System.out.println("you can choose from 1 to " + model.getPlayerInteraction().getPlayer(playerOrder[i]).getAssistants().get(cards[playerOrder[i]]).getSteps());
            temp = getTheLine(ObjectsToSelect.STEPS, cards, playerOrder[i], input);
            index = Integer.parseInt(temp);
            try {
                model.moveMN(index);
            }
            catch (EndGameException e){
                end = true;
                int winner = model.getWinner();
                if (winner == -1) {
                    System.out.println("The game ended with a tie");
                }
                else {
                    System.out.println("The winner is player " + winner);
                }
                return;
            }
            System.out.println("MN moved " + index + "steps");

            //cloud selection
            System.out.println("You have to select the cloud with the students you want in your entrance");
            printClouds();
            System.out.println("Select the cloud index:");
            temp = getTheLine(ObjectsToSelect.CLOUD, cards, playerOrder[i], input);
            index = Integer.parseInt(temp);
            model.studentsCloudToEntrance(playerOrder[i], index);
            System.out.println("Cloud " + index + " students moved in the Entrance of player " + playerOrder[i]);

            if (model.endGame()){
                end = true;
                int winner = model.getWinner();
                if (winner == -1) {
                    System.out.println("The game ended with a tie");
                }
                else {
                    System.out.println("The winner is player " + winner);
                }
            }
        }
    }

    public String getTheLine(ObjectsToSelect selection, int[] cards, int player, Scanner input){
        String result;
        result=input.nextLine();
        Map<MessageType, String> message;

        //command card
        int cardNumber;

        //Play a character card
        if (result.equalsIgnoreCase("CARD")) {
            if (model.gameRules[4] == 1) {
                printCards();
                System.out.println("Number of the card you want to play:");
                cardNumber = Integer.parseInt(getTheLine(ObjectsToSelect.CHARACTER_CARD, cards, player, input));
                playCard(cardNumber, player, input);
                return getTheLine(selection, cards, player, input);
            }
            else {
                System.out.println("You are playing a game in easy mode, there are no character cards");
            }
        }

        //Show character cards
        if (result.equalsIgnoreCase("CHARACTER CARDS")){
            if (model.gameRules[4] == 1) {
                printCards();
                return getTheLine(selection, cards, player, input);
            }
            else {
                System.out.println("You are playing a game in easy mode, there are no character cards");
            }
        }

        //extra commands
        //print all the students of the player
        if (result.equalsIgnoreCase("STUDENTS")){
            int index;
            System.out.println("Select the player:");
            index = Integer.parseInt(getTheLine(ObjectsToSelect.PLAYER, cards, player, input));
            printStudents(index);
            return getTheLine(selection, cards, player, input);
        }

        //Show islands
        if (result.equalsIgnoreCase("ISLANDS")){
            printIslands();
            return getTheLine(selection, cards, player, input);
        }

        //Show clouds
        if (result.equalsIgnoreCase("CLOUDS")){
            printClouds();
            return getTheLine(selection, cards, player, input);
        }

        //Show teachers
        if (result.equalsIgnoreCase("TEACHERS")){
            printTeachers();
            return getTheLine(selection, cards, player, input);
        }

        //Show assistant cards
        if (result.equalsIgnoreCase("ASSISTANT CARDS")){
            int index;
            System.out.println("Select the player:");
            index = Integer.parseInt(getTheLine(ObjectsToSelect.PLAYER, cards, player, input));
            printAssistantCards(index);
            return getTheLine(selection, cards, player, input);
        }

        //help
        if (result.equalsIgnoreCase("HELP")){
            System.out.println("Students: shows all the students in the board");
            System.out.println("Islands: shows all the islands of the game");
            System.out.println("Clouds: shows all the clouds");
            System.out.println("Teachers: shows all the teachers");
            System.out.println("Card: if you want to play a character card");
            System.out.println("Character cards: shows the effects of the character cards");
            System.out.println("Assistant cards: shows the playable assistant cards");
            return getTheLine(selection, cards, player, input);
        }

        //creation of the message based on the object requested
        message = switch (selection) {
            case COLOR -> messageGenerator.colorSelection(result);
            case PLACE -> messageGenerator.placeSelection(result);
            case ISLAND -> messageGenerator.islandSelection(result, model.getIslandInteraction().getIslands().size());
            case STEPS -> messageGenerator.stepsSelection(result, model.getPlayerInteraction().getPlayer(player).getAssistants().get(cards[player]).getSteps());
            case PLAYER -> messageGenerator.playerIndexSelection(result, model.gameRules[0]);
            case ASSISTANT_CARD -> messageGenerator.assistantSelection(result, model.gameRules[0], cards, model.getPlayerInteraction().getPlayers().get(player).getAssistants());
            case CLOUD -> messageGenerator.cloudSelection(result, model.gameRules[0], model.getBagNClouds());
            case CHARACTER_CARD -> messageGenerator.characterCardSelection(result);
        };

        if(message.containsKey(MessageType.CORRECT_INPUT)){
            System.out.println(message.get(MessageType.CORRECT_INPUT));
            return result;
        }
        else if (message.containsKey(MessageType.NOT_VALID_INPUT)){
            System.out.println(message.get(MessageType.NOT_VALID_INPUT));
            return getTheLine(selection, cards, player, input);
        }
        else if (message.containsKey(MessageType.NOT_VALID_INDEX)){
            System.out.println(message.get(MessageType.NOT_VALID_INDEX));
            return getTheLine(selection, cards, player, input);
        }
        else if (message.containsKey(MessageType.ALREADY_PLAYED)){
            System.out.println(message.get(MessageType.ALREADY_PLAYED));
            return getTheLine(selection, cards, player, input);
        }
        else if (message.containsKey(MessageType.ALREADY_PLAYED_THIS_TURN)){
            System.out.println(message.get(MessageType.ALREADY_PLAYED_THIS_TURN));
            return getTheLine(selection, cards, player, input);
        }

        return "false";
    }

    /**
     *  se il costo della carta è superiore alle monete del player non fa nulla
     *  se il costo è inferiore
     * 	    riduce il numero di monete in mano al player
     * 	    incrementa il costo della carta se il booleano firstuUsed è = 0
     * 	    accede all’indice corretto della carta giocata dall’array del model
     * 	    chiama useEffect della carta passando in input i valori necessari
     */
    public void playCard(int cardPlayed, int player, Scanner input){
        Map<Indexes, Integer> index = new HashMap<>();
        Colors color = null;
        Map<Colors, Integer> StudMap1 = new HashMap<>();
        Map<Colors, Integer> StudMap2 = new HashMap<>();

        for (Indexes i: Indexes.values()){
            index.put(i, -1);
        }
        for (Colors c: Colors.values()){
            StudMap1.put(c, 0);
        }
        for (Colors c: Colors.values()){
            StudMap2.put(c, 0);
        }

        if (!(model.getCharacterCards()[cardPlayed].isUsedThisTurn())){
            if (model.getPlayerInteraction().getPlayer(player).getCoins() >= model.getCharacterCards()[cardPlayed].getCost()) {
                model.getPlayerInteraction().getPlayer(player).removeCoins(model.getCharacterCards()[cardPlayed].getCost());
                if (!(model.getCharacterCards()[cardPlayed].isUsedThisGame())) {
                    model.getCharacterCards()[cardPlayed].increaseCost();
                }

                switch (model.getCharacterCards()[cardPlayed].getCardIndex()){
                    case 1:
                        System.out.println("Select the colors of the student you want to move from the Card to the island");
                        for (int i=0; i<Constants.CARD1_STUDENTS_TO_MOVE; i++){
                            StudMap1.put(Colors.valueOf(getTheLine(ObjectsToSelect.COLOR, null, -1, input)), 1);
                        }
                        System.out.println("Select the island:");
                        index.put(Indexes.ISLAND_INDEX, Integer.parseInt(getTheLine(ObjectsToSelect.ISLAND, null, -1, input)));
                        break;
                    case 2:
                    case 6:
                        break;
                    case 3:
                        System.out.println("Select the island where you want to calculate the influence:");
                        index.put(Indexes.ISLAND_INDEX, Integer.parseInt(getTheLine(ObjectsToSelect.ISLAND, null, -1, input)));
                        break;
                    case 4:
                    case 8:
                        index.put(Indexes.PLAYER_INDEX, player);
                        break;
                    case 5:
                        System.out.println("Select the island where you want to add an inhibition card:");
                        index.put(Indexes.ISLAND_INDEX, Integer.parseInt(getTheLine(ObjectsToSelect.ISLAND, null, -1, input)));
                        break;
                    case 7:
                        index.put(Indexes.PLAYER_INDEX, player);
                        System.out.println("Select the students you want to take from the card:");
                        for (int i=0; i<Constants.CARD7_MAX_STUDENTS_TO_MOVE; i++){
                            StudMap1.put(Colors.valueOf(getTheLine(ObjectsToSelect.COLOR, null, -1, input)), 1);
                        }
                        System.out.println("Select the students you want to remove from the Entrance:");
                        for (int i=0; i<Constants.CARD7_MAX_STUDENTS_TO_MOVE; i++){
                            StudMap2.put(Colors.valueOf(getTheLine(ObjectsToSelect.COLOR, null, -1, input)), 1);
                        }
                        break;
                    case 9:
                        System.out.println("Select the colors of the student you want to exclude from the calculate influence");
                        color = Colors.valueOf(getTheLine(ObjectsToSelect.COLOR, null, -1, input));
                        break;
                    case 10:
                        index.put(Indexes.PLAYER_INDEX, player);
                        System.out.println("Select the students in Entrance:");
                        for (int i=0; i<Constants.CARD10_MAX_STUDENTS_TO_MOVE; i++){
                            StudMap1.put(Colors.valueOf(getTheLine(ObjectsToSelect.COLOR, null, -1, input)), 1);
                        }
                        System.out.println("Select the students in Hall:");
                        for (int i=0; i<Constants.CARD10_MAX_STUDENTS_TO_MOVE; i++){
                            StudMap2.put(Colors.valueOf(getTheLine(ObjectsToSelect.COLOR, null, -1, input)), 1);
                        }
                        break;
                    case 11:
                        index.put(Indexes.PLAYER_INDEX, 1);
                        System.out.println("Select the student you want take from the card and put in your Entrance:");
                        color = Colors.valueOf(getTheLine(ObjectsToSelect.COLOR, null, -1, input));
                        break;
                    case 12:
                        System.out.println("Select the color of students that will be removed from the Hall:");
                        color = Colors.valueOf(getTheLine(ObjectsToSelect.COLOR, null, -1, input));
                        break;
                }

                System.out.println("carta " + model.getCharacterCards()[cardPlayed].getCardIndex() + " played");
                /*
                try {
                    model.getCharacterCards()[cardPlayed].useEffect(index, color, StudMap1, StudMap2);
                }
                catch (OutOfBoundException o) {

                }
                */

            } else {
                System.out.println("You don't have enough coins");
            }
        }
        else {
            System.out.println("This card has already been played this turn");
        }
    }

    /**
     * prints methods
     */
    public void printStudents(int playerIndex){
        System.out.println("Students in player " + playerIndex + " Entrance:");
        for (Colors c: Colors.values()){
            System.out.println(c + ": " + model.getPlayerInteraction().getPlayer(playerIndex).getBoard().getStudEntrance().get(c));
        }
        System.out.println("Students in player " + playerIndex + " Hall:");
        for (Colors c: Colors.values()){
            System.out.println(c + ": " + model.getPlayerInteraction().getPlayer(playerIndex).getBoard().getStudHall().get(c));
        }
    }

    public void printIslands(){
        for (int i=0; i<model.getIslandInteraction().getIslands().size(); i++) {
            System.out.print("Island " + i);
            if (model.getIslandInteraction().getMotherNature() == i) {
                System.out.print(" (MN)");
            }
            System.out.println(":");
            for (Colors c : Colors.values()) {
                System.out.print(c + ": " + model.getIslandInteraction().getIslands().get(i).getStudents().get(c) + "; ");
            }
            System.out.println();
            System.out.println("Number of towers: " + model.getIslandInteraction().getIslands().get(i).getnTowers() + " Controller: " + model.getIslandInteraction().getIslands().get(i).getControllerIndex());
        }
    }

    public void printTeachers(){
        for (Colors c: Colors.values()){
            System.out.print(c + " teacher: ");
            System.out.println(model.getIslandInteraction().getTeachers().get(c));
        }
    }

    public void printClouds(){
        for (int i=0; i<model.gameRules[0]; i++){
            System.out.println("cloud "+ i + ":");
            for (Colors c: Colors.values()){
                System.out.print(c + ": " + model.getBagNClouds().getClouds().get(i).get(c) + "; ");
            }
            System.out.println();
        }
    }

    public void printCards(){
        for (int i=0; i<3; i++){
            System.out.println("Card "+ i + ": " + model.getCharacterCards()[i].getEffect());
        }
    }

    public void printAssistantCards(int player){
        for (int j = 0; j< Constants.NUMBER_OF_ASSISTANT_CARDS; j++){
            if (model.getPlayerInteraction().getPlayers().get(player).getAssistants().get(j).getCardState()==2){
                System.out.print("Card " + j + ": ");
                System.out.print("priority:  " + model.getPlayerInteraction().getPlayers().get(player).getAssistants().get(j).getPriority());
                System.out.println(" steps: " + model.getPlayerInteraction().getPlayers().get(player).getAssistants().get(j).getSteps());
            }
        }
    }

    /**
     * get methods
     */
    public Model getModel() {
        return model;
    }
}
