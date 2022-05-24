package it.polimi.ingsw.Client.CLI.Parser;

import it.polimi.ingsw.Constants.Colors;

import java.util.Scanner;

public class AllIslandsParser implements Parser {
    @Override
    public void parse(String s) {
        Scanner scanner = new Scanner(s);
        int numberOfIslands = scanner.nextInt();
        for (int i = 0; i < numberOfIslands; i++) {
            String island = "";
            island += scanner.next() + "\n";
            island += scanner.next() + "\n";
            for (Colors c : Colors.values()) {
                island += "" + scanner.next() + "\n";
            }
            island += scanner.next() + "\n";
            island += scanner.next() + "\n";
            island += scanner.next() + "\n";

            Parser islandParser = new IslandParser();
            islandParser.parse(island);
        }

        scanner.close();
    }
}