package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoardParser implements Parser {
    @Override
    public void parse(String s) {
        Scanner scanner = new Scanner(s);
        String nickname = scanner.next();
        Map<Colors, Integer> entrance = new HashMap<>();
        for (Colors c : Colors.values()) {
            entrance.put(c, scanner.nextInt());
        }
        Map<Colors, Integer> hall = new HashMap<>();
        for (Colors c : Colors.values()) {
            hall.put(c, scanner.nextInt());
        }
        int towers = scanner.nextInt();

        scanner.close();

        ClientPrinter.printBoard(nickname, entrance, hall, towers);
    }
}
