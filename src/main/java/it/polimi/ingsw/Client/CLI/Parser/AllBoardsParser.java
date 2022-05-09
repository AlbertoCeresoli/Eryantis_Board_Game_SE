package it.polimi.ingsw.Client.CLI.Parser;

import it.polimi.ingsw.Constants.Colors;

import java.util.Scanner;

public class AllBoardsParser implements Parser {
    @Override
    public void parse(String s) {
        Scanner scanner = new Scanner(s);
        int numberOfBoards = scanner.nextInt();
        for (int i = 0; i < numberOfBoards; i++) {
            String board = "";
            board += scanner.next() + "\n";
            for (Colors c : Colors.values()) {
                board += "" + scanner.next() + "\n";
            }
            for (Colors c : Colors.values()) {
                board += "" + scanner.next() + "\n";
            }
            board += scanner.next() + "\n";

            Parser boardPrinter = new BoardParser();
            boardPrinter.parse(board);
        }

        scanner.close();
    }
}
