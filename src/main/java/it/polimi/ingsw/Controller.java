package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.EndGameException;

import java.util.Random;
import java.util.Scanner;

public class Controller {
    private int firstPlayer;
    private final Model model;
    private boolean end;

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

        end = false;
    }
    /**
     * test cases: TODO
     */

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
     * test cases: TODO
     */

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
            for (int j = 0; j< Constants.NUMBER_OF_ASSISTANT_CARDS; j++){
                if (model.getPlayerInteraction().getPlayers().get(player).getAssistants().get(j).getCardState()==2){
                    System.out.print("Card " + j + ": ");
                    System.out.print("priority:  " + model.getPlayerInteraction().getPlayers().get(player).getAssistants().get(j).getPriority());
                    System.out.println(" steps: " + model.getPlayerInteraction().getPlayers().get(player).getAssistants().get(j).getSteps());
                }
            }
            System.out.println("player " + player + " play your assistant card:");

            cards[player] = Integer.parseInt(getTheLine(5, cards, player, input));
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
                    temp = getTheLine(0, cards, playerOrder[i], input);
                    color = Colors.valueOf(temp);
                    if (model.getPlayerInteraction().getPlayer(playerOrder[i]).getBoard().getStudEntrance().get(color)>0){
                        result = false;
                        System.out.println(color + " color selected");
                    }
                    else {
                        System.out.println("you don't have " + color + " students in Entrance");
                    }
                }

                //select the place
                System.out.println("Where do you want to put the " + color + " student (Hall or Island)");
                temp = getTheLine(1, cards, playerOrder[i], input);
                while (temp.equals("false")) {
                    temp = getTheLine(1, cards, playerOrder[i], input);
                }
                if (temp.equals("Hall")){
                    model.moveFromEntranceToHall(color, playerOrder[i]);
                    System.out.println("One " + color + " student moved from entrance to hall");
                    printTeachers();
                }
                if (temp.equals("Island")){
                    System.out.println("Select the island:");
                    temp = getTheLine(2, cards, playerOrder[i], input);
                    index = Integer.parseInt(temp);
                    model.moveFromEntranceToIsland(color, playerOrder[i], index);
                    System.out.println("One " + color + " student moved from entrance to island " + index);
                    printIslands();
                }
            }

            //MN movement
            System.out.println("Player" + playerOrder[i] + ": How many steps you want MN to do?");
            System.out.println("you can choose from 1 to " + model.getPlayerInteraction().getPlayer(playerOrder[i]).getAssistants().get(cards[playerOrder[i]]).getSteps());
            temp = getTheLine(3, cards, playerOrder[i], input);
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
            temp = getTheLine(4, cards, playerOrder[i], input);
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
    /**
     * test cases: TODO
     */

    public String getTheLine(int selection, int[] cards, int player, Scanner input){
        String result;
        result=input.nextLine();

        //command card
        int cardNumber;
        if (result.equals("Card") || result.equals("card") || result.equals("CARD")){
            System.out.println("Number of the card you want to play:");
            cardNumber= input.nextInt();
            playCard(cardNumber);
            System.out.println("card " + cardNumber + " played");
            return getTheLine(selection, cards, player, input);
        }

        //extra commands
        //print all the students of the player
        if (result.equals("Students") || result.equals("students") || result.equals("STUDENTS")){
            int index;
            System.out.println("Select the player:");
            index = Integer.parseInt(getTheLine(4, cards, player, input));
            printStudents(index);
            return getTheLine(selection, cards, player, input);
        }

        //Show islands
        if (result.equals("Islands") || result.equals("islands") || result.equals("ISLANDS")){
            printIslands();
            return getTheLine(selection, cards, player, input);
        }

        //Show clouds
        if (result.equals("Clouds") || result.equals("clouds") || result.equals("CLOUDS")){
            printClouds();
            return getTheLine(selection, cards, player, input);
        }

        //Show teachers
        if (result.equals("Teachers") || result.equals("teachers") || result.equals("TEACHERS")){
            printTeachers();
            return getTheLine(selection, cards, player, input);
        }

        //help
        if (result.equals("Help") || result.equals("help") || result.equals("HELP")){
            System.out.println("Students: shows all the students in the board");
            System.out.println("Islands: shows all the islands of the game");
            System.out.println("Clouds: shows all the clouds");
            System.out.println("Teachers: shows all the teachers");
            System.out.println("Card: if you want to play a character card");
            return getTheLine(selection, cards, player, input);
        }

        //Color selection:
        if (selection == 0) {
            switch (result) {
                case "Yellow":
                case "yellow":
                case "YELLOW":
                    return "YELLOW";
                case "Blue":
                case "blue":
                case "BLUE":
                    return "BLUE";
                case "Green":
                case "green":
                case "GREEN":
                    return "GREEN";
                case "Red":
                case "red":
                case "RED":
                    return "RED";
                case "Pink":
                case "pink":
                case "PINK":
                    return "PINK";
                default:
                    System.out.println("not valid Color, you can select yellow, blue, green, red and pink");
                    System.out.println("select the color:");
                    return getTheLine(0, cards, player, input);
            }
        }

        //Hall or Island selection
        if (selection == 1) {
            if (result.equals("Island") || result.equals("island") || result.equals("ISLAND")) {
                return "Island";
            }
            else if (result.equals("Hall") || result.equals("hall") || result.equals("HALL")){
                return "Hall";
            }
            else {
                System.out.println("not valid Input, you can select Island or Hall");
                return getTheLine(1, cards, player, input);
            }
        }

        //Island index selection
        if (selection == 2) {
            int index;
            try{
                index = Integer.parseInt(result);
                if (index < 0 || index > model.getIslandInteraction().getIslands().size()){
                    System.out.println("not valid index, the input must be in (0 - " + model.getIslandInteraction().getIslands().size() + ")");
                }
                return result;
            }
            catch (NumberFormatException e) {
                System.out.println("not valid Input, the input must be in (0 - " + model.getIslandInteraction().getIslands().size() + ")");
                return getTheLine(2, cards, player, input);
            }
        }

        //MN steps selection
        if (selection == 3){
            int index;

            try{
                index = Integer.parseInt(result);
                if (index < 0 || index > model.getPlayerInteraction().getPlayer(player).getAssistants().get(cards[player]).getSteps()){
                    System.out.println("not valid index, the input must be in (1 - " + model.getPlayerInteraction().getPlayer(player).getAssistants().get(cards[player]).getSteps() + ")");
                    return getTheLine(3, cards, player, input);
                }
                else {
                    return result;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("not valid Input, the input must be in (1 - " + model.getPlayerInteraction().getPlayer(player).getAssistants().get(cards[player]).getSteps() + ")");
                return getTheLine(3, cards, player, input);
            }
        }

        //player index Selection
        if (selection == 4){
            int index;

            try{
                index = Integer.parseInt(result);
                if (index < 0 || index > model.gameRules[0]){
                    System.out.println("not valid index, the input must be in (0 - " + (model.gameRules[0] - 1) + ")");
                }
                return result;
            }
            catch (NumberFormatException e) {
                System.out.println("not valid Input, the input must be in (0 - " + (model.gameRules[0] - 1) + ")");
                return getTheLine(4, cards, player, input);
            }
        }

        //assistant card selection
        if (selection == 5){
            int card;
            card = selectAssistantCard(player, cards, result);
            if (card == -1){
                return getTheLine(5, cards, player, input);
            }
            else {
                return String.valueOf(card);
            }
        }

        //cloud selection
        if (selection == 6){
            int index;

            try{
                index = Integer.parseInt(result);
                if (index < 0 || index > model.gameRules[0]){
                    System.out.println("not valid index, the input must be in (0 - " + (model.gameRules[0] - 1) + ")");
                    return getTheLine(6, cards, player, input);
                }
                else if (!model.getBagNClouds().emptyCloud(index))
                    return result;
                else{
                    System.out.println("The cloud you have selected is already empty, select another one");
                    return getTheLine(6, cards, player, input);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("not valid Input, the input must be in (0 - " + (model.gameRules[0] - 1) + ")");
                return getTheLine(6, cards, player, input);
            }
        }

        return "false";
    }

    public int selectAssistantCard (int player, int[] cardsPlayed, String string){
        String temp;
        Boolean alreadyPlayed = false;
        int card = -1;

        temp = string;
        try {
            card = Integer.parseInt(temp);

            for (int i=0; i<model.gameRules[0]; i++){
                if (card == cardsPlayed[i]){
                    alreadyPlayed = true;
                }
            }

            if(card>=0 && card<Constants.NUMBER_OF_ASSISTANT_CARDS){
                if (model.getPlayerInteraction().getPlayers().get(player).getAssistants().get(card).getCardState()==2 && !alreadyPlayed){
                    return card;
                }
                else if (alreadyPlayed){
                    System.out.println("Another player has already played this card in this round");
                    return -1;
                }
                else if (model.getPlayerInteraction().getPlayers().get(player).getAssistants().get(card).getCardState()!=2){
                    System.out.println("You have already played this card");
                    return -1;
                }
            }
            else {
                System.out.println("Not valid card value");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Not valid input");
            return -1;
        }
        return -1;
    }

    /**
     *  se il costo della carta è superiore alle monete del player non fa nulla
     *  se il costo è inferiore
     * 	    riduce il numero di monete in mano al player
     * 	    incrementa il costo della carta se il booleano firstuUsed è = 0
     * 	    accede all’indice corretto della carta giocata dall’array del model
     * 	    chiama useEffect della carta passando in input i valori necessari
     */
    public void playCard(int cardPlayed){

    }
    /**
     * test cases: TODO
     */

    /**
     * prints methods
     */
    public void printStudents(int studIndex){
        int index = studIndex;
        System.out.println("Students in player " + index + " Entrance:");
        for (Colors c: Colors.values()){
            System.out.println(c + ": " + model.getPlayerInteraction().getPlayer(index).getBoard().getStudEntrance().get(c));
        }
        System.out.println("Students in player " + index + " Hall:");
        for (Colors c: Colors.values()){
            System.out.println(c + ": " + model.getPlayerInteraction().getPlayer(index).getBoard().getStudHall().get(c));
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

    /**
     * get methods
     */
    public Model getModel() {
        return model;
    }
}
