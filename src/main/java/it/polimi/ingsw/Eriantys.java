package it.polimi.ingsw;

import java.io.IOException;
import java.util.Scanner;
public class Eriantys {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Eriantys!\nWhat do you want to launch?");
        System.out.println("0. SERVER\n1. CLIENT (CLI INTERFACE)");
        System.out.println("\n>Type the number of the desired option!");
        Scanner scanner = new Scanner(System.in);

        int input = 0;
        do {
            System.out.println("0 / 1");
            System.out.print(">");
            input = scanner.nextInt();
        }while(input != 0 && input != 1);

        switch (input) {
            case 0 -> Server.main(null);
            case 1 -> CLI.main(null);
        }
    }
}
