package it.polimi.ingsw.Client.CLI.Parser;

import it.polimi.ingsw.Client.CLI.ClientPrinter;
import it.polimi.ingsw.Constants.Colors;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TeachersParser implements Parser {
    @Override
    public void parse(String s) {
        Scanner scanner = new Scanner(s);
        Map<Colors, String> teachers = new HashMap<>();
        for (Colors c : Colors.values()) {
            String temp = scanner.next();

            if (temp.equals("null")) {
                temp = "Nobody";
            }

            teachers.put(c, temp);
        }

        scanner.close();

        ClientPrinter.printTeachers(teachers);
    }
}