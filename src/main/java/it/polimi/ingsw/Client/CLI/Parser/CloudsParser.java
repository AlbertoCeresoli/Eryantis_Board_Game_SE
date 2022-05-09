package it.polimi.ingsw.Client.CLI.Parser;

import it.polimi.ingsw.Client.CLI.ClientPrinter;
import it.polimi.ingsw.Constants.Colors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CloudsParser implements Parser {
    @Override
    public void parse(String s) {
        Scanner scanner = new Scanner(s);
        int numberOfClouds = scanner.nextInt();
        ArrayList<Map<Colors, Integer>> clouds = new ArrayList<>();
        for (int i = 0; i < numberOfClouds; i++) {
            Map<Colors, Integer> cloud = new HashMap<>();
            for (Colors c : Colors.values()) {
                cloud.put(c, scanner.nextInt());
            }
            clouds.add(cloud);
        }

        scanner.close();

        ClientPrinter.printClouds(clouds);
    }
}
