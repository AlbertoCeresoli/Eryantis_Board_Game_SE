package it.polimi.ingsw;

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
            teachers.put(c, scanner.next());
        }

        scanner.close();

        ClientPrinter.printTeachers(teachers);
    }
}
