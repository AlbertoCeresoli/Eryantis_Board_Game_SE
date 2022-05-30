package it.polimi.ingsw.Client.CLI.Parser;

import it.polimi.ingsw.Client.CLI.ClientPrinter;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class CharacterCardsParser implements Parser {
    @Override
    public void parse(String s) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(s));

        for (int i = 0; i < Constants.NUMBER_OF_CHARACTER_CARDS; i++) {
            String name = bufferedReader.readLine();
            int cost = Integer.parseInt(bufferedReader.readLine());
            int thereAreStudentsOnTheCard = Integer.parseInt(bufferedReader.readLine());
            Map<Colors, Integer> students;

            if (thereAreStudentsOnTheCard == 1) {
                students = new HashMap<>();

                for (Colors c : Colors.values()) {
                    int num = Integer.parseInt(bufferedReader.readLine());
                    students.put(c, num);
                }

                ClientPrinter.printCharacterCard(name, cost, students);
            }
            else {
                ClientPrinter.printCharacterCard(name, cost);
            }
        }

        bufferedReader.close();
    }
}
