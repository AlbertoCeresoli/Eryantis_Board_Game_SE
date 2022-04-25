package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Scanner;

public class GameInitializer {
    public static void main (String[] args){
        Scanner input=new Scanner(System.in);
        int numPlayers=0;
        boolean gameMode;
        ArrayList<String> nickNames = new ArrayList<>();
        Controller controller;

        //numPlayers and gameMode selection
        System.out.println("Welcome in Eriantys");
        System.out.println("How many players?");
        while (!(numPlayers == 2 || numPlayers == 3)){
            System.out.println("The players must be 2 or 3");
            numPlayers = input.nextInt();
        }
        System.out.println("You have selected " + numPlayers + " players game");

        System.out.println("Do you want to play the hard (1) or easy (0) mode?");
        System.out.println("The gameMode must be 0 (easy) or 1 (hard)");
        if (input.nextInt() == 1) {gameMode = true;}
        else {gameMode = false;}

        if (!gameMode){System.out.println("You have selected the easy mode");}
        else if (gameMode) {System.out.println("You have selected the hard mode");}

        //inserting nicknames
        input.nextLine(); //clean the scanner TODO better
        for (int i=0; i<numPlayers; i++){
            String temp;
            System.out.println("Player " + i + " insert your nickname:");
            temp = input.nextLine();
            nickNames.add(temp);
            System.out.println("Player " + i + ": " + nickNames.get(i));
        }

        controller = new Controller(numPlayers, gameMode);

        //starting the game
        System.out.println("Starting the game...");
        controller.startGame(input);
    }
}