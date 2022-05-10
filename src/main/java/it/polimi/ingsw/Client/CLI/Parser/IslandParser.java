package it.polimi.ingsw.Client.CLI.Parser;

import it.polimi.ingsw.Client.CLI.ClientPrinter;
import it.polimi.ingsw.Constants.Colors;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IslandParser implements Parser {
    @Override
    public void parse(String s) {
        Scanner scanner = new Scanner(s);
        int islandIndex = scanner.nextInt();
        boolean MN = scanner.nextBoolean();
        Map<Colors, Integer> students = new HashMap<>();
        for (Colors c : Colors.values()) {
            students.put(c, scanner.nextInt());
        }
        String controller = scanner.next();
        int towers = scanner.nextInt();
        int inhCards = scanner.nextInt();

        scanner.close();

        ClientPrinter.printIsland(students, towers, inhCards, controller, islandIndex, MN);
    }
}