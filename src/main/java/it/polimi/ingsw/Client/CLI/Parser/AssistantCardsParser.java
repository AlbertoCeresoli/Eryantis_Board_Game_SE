package it.polimi.ingsw.Client.CLI.Parser;

import it.polimi.ingsw.Client.CLI.ClientPrinter;

import java.util.ArrayList;
import java.util.Scanner;

public class AssistantCardsParser implements Parser{

    @Override
    public void parse(String s) {
        Scanner scanner = new Scanner(s);
        int numberOfCards = scanner.nextInt();
        int[] indexes = new int[numberOfCards];
        int[] priorities = new int[numberOfCards];
        int[] steps = new int[numberOfCards];
        for (int i = 0; i < numberOfCards; i++) {
            indexes[i] = scanner.nextInt();
            priorities[i] = scanner.nextInt();
            steps[i] = scanner.nextInt();
        }

        ArrayList<int[]> cardsInformation = new ArrayList<>();
        cardsInformation.add(indexes);
        cardsInformation.add(priorities);
        cardsInformation.add(steps);

        scanner.close();

        ClientPrinter.printAssistantCards(cardsInformation);
    }
}