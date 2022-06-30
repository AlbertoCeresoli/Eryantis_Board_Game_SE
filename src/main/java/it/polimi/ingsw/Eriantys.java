package it.polimi.ingsw;

import it.polimi.ingsw.Client.CLI.CLI;
import it.polimi.ingsw.Client.GUI.GUI;

import java.io.IOException;
import java.util.Scanner;

public class Eriantys {
    /**
     * This is the main manifest class as it has the main function to let the user choose what he needs to launch between Server, CLI or GUI
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Eriantys!\nWhat do you want to launch?");
        System.out.println("0. SERVER\n1. CLIENT (CLI INTERFACE)\n2. CLIENT (GUI INTERFACE)");
        System.out.println("\n>Type the number of the desired option!");
        Scanner scanner = new Scanner(System.in);

        int input;
        do {
            System.out.println("0 / 1 / 2");
            System.out.print(">");
            input = scanner.nextInt();
        } while (input != 0 && input != 1 && input != 2);

        switch (input) {
            case 0 -> {
                try {
                    Server.main(null);
                } catch (IOException e) {
                    System.out.println("Cannot launch server on the port chosen, maybe it is already occupied");
                }
            }
            case 1 -> {
                try {
                    CLI.main(null);
                } catch (IOException e) {
                    System.out.println("Connection failed, application will close...");
                }
            }
            case 2 -> GUI.main(null);
        }
    }
}
