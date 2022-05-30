package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Constants.Cards;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientPrinter {
    private static final Map<String, String> characterCardEffects = new HashMap<>();
    private static final Map<Colors, String> colorsChars = new HashMap<>();
    private static final Map<Colors, String> colorsBackground = new HashMap<>();

    public ClientPrinter() {
        characterCardEffects.put(Cards.MONK.getName(), Constants.MONK_EFFECT);
        characterCardEffects.put(Cards.HERALD.getName(), Constants.HERALD_EFFECT);
        characterCardEffects.put(Cards.FARMER.getName(), Constants.FARMER_EFFECT);
        characterCardEffects.put(Cards.MINSTREL.getName(), Constants.MINSTREL_EFFECT);
        characterCardEffects.put(Cards.JOKER.getName(), Constants.JOKER_EFFECT);
        characterCardEffects.put(Cards.THIEF.getName(), Constants.THIEF_EFFECT);
        characterCardEffects.put(Cards.SPOILED_PRINCESS.getName(), Constants.SPOILED_PRINCESS_EFFECT);
        characterCardEffects.put(Cards.CENTAUR.getName(), Constants.CENTAUR_EFFECT);
        characterCardEffects.put(Cards.MAGIC_POSTMAN.getName(), Constants.MAGIC_POSTMAN_EFFECT);
        characterCardEffects.put(Cards.GRANDMA_HERBS.getName(), Constants.GRANDMA_HERBS_EFFECT);
        characterCardEffects.put(Cards.KNIGHT.getName(), Constants.KNIGHT_EFFECT);
        characterCardEffects.put(Cards.MUSHROOMS_MAN.getName(), Constants.MUSHROOMS_MAN_EFFECT);

        colorsChars.put(Colors.YELLOW, Constants.ANSI_YELLOW);
        colorsChars.put(Colors.BLUE, Constants.ANSI_BLUE);
        colorsChars.put(Colors.GREEN, Constants.ANSI_GREEN);
        colorsChars.put(Colors.RED, Constants.ANSI_RED);
        colorsChars.put(Colors.PINK, Constants.ANSI_PINK);

        colorsBackground.put(Colors.YELLOW, Constants.ANSI_YELLOW_BACKGROUND);
        colorsBackground.put(Colors.BLUE, Constants.ANSI_BLUE_BACKGROUND);
        colorsBackground.put(Colors.GREEN, Constants.ANSI_GREEN_BACKGROUND);
        colorsBackground.put(Colors.RED, Constants.ANSI_RED_BACKGROUND);
        colorsBackground.put(Colors.PINK, Constants.ANSI_PURPLE_BACKGROUND);
    }

    public static void printAssistantCards(ArrayList<int[]> cardsInformation) {
        System.out.println("\nYour available Assistants Cards are:");
        for (int i = 0; i < cardsInformation.get(0).length; i++) {
            System.out.println("Assistant Card " + cardsInformation.get(0)[i] + ":");
            System.out.println("     Priority: " + cardsInformation.get(1)[i] + "     Steps: " + cardsInformation.get(2)[i]);
        }
    }

    public static void printIsland(Map<Colors, Integer> students, int towers, int inhCards, String controller, int islandIndex, boolean MN) {
        System.out.println("\nIsland " + islandIndex + ", controlled by " + controller + "\n");

        printIslandStudents(students, islandIndex, MN);
        if (towers != 0) {
            System.out.println("Towers on the island:\n");
            printTowers(towers);
        }
        if (inhCards != 0)
            printInhCards(inhCards);
    }

    public static void printIslandStudents(Map<Colors, Integer> students, int islandIndex, boolean MN) {
        System.out.println("\nStudents on the island:\n");

        if (MN) {
            printMN();
        }

        System.out.println(
                "      " + Constants.ANSI_GREEN_BACKGROUND + "         " + Constants.ANSI_RESET + "      \n" +
                        "    " + Constants.ANSI_GREEN_BACKGROUND + "             " + Constants.ANSI_RESET + "    \n" +
                        "  " + Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + " " + Constants.ANSI_PINK + students.get(Colors.PINK) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + " " + Constants.ANSI_RESET + " " + Constants.ANSI_YELLOW + students.get(Colors.YELLOW) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + "  \n" +
                        Constants.ANSI_GREEN_BACKGROUND + "                     " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + " " + Constants.ANSI_RED + students.get(Colors.RED) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + "      " + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN + students.get(Colors.GREEN) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_GREEN_BACKGROUND + "                     " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + " " + Constants.ANSI_BLUE + students.get(Colors.BLUE) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + "         " + Constants.ANSI_RESET + "\n" +
                        "  " + Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "             " + Constants.ANSI_RESET + "\n" +
                        "    " + Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "         " + Constants.ANSI_RESET + "\n" +
                        "      " + Constants.ANSI_YELLOW_BACKGROUND + Constants.ANSI_BLACK + "Island " + islandIndex + Constants.ANSI_RESET + "\n"
        );
    }

    static void printMN() {
        System.out.println(
                Constants.ANSI_RESET + "         " + Constants.ANSI_YELLOW_BACKGROUND + "   " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_RESET + "        " + Constants.ANSI_YELLOW_BACKGROUND + "     " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_RESET + "       " + Constants.ANSI_YELLOW_BACKGROUND + "       " + Constants.ANSI_RESET + "\n"
        );
    }

    public static void printTowers(int towers) {
        for (int i = 0; i < towers; i++) {
            System.out.print(
                    "   " + Constants.ANSI_WHITE_BACKGROUND + " " + Constants.ANSI_RESET + "   " + Constants.ANSI_WHITE_BACKGROUND + " " + Constants.ANSI_RESET + "   " + Constants.ANSI_WHITE_BACKGROUND + " " + Constants.ANSI_RESET
            );
        }
        System.out.println();
        for (int i = 0; i < towers; i++) {
            System.out.print(
                    "   " + Constants.ANSI_WHITE_BACKGROUND + "         " + Constants.ANSI_RESET
            );
        }
        System.out.println();
        for (int i = 0; i < towers; i++) {
            System.out.print(
                    "    " + Constants.ANSI_WHITE_BACKGROUND + "       " + Constants.ANSI_RESET + " "
            );
        }
        System.out.println();
        for (int i = 0; i < towers; i++) {
            System.out.print(
                    "    " + Constants.ANSI_WHITE_BACKGROUND + "       " + Constants.ANSI_RESET + " "
            );
        }
        System.out.println();
        for (int i = 0; i < towers; i++) {
            System.out.print(
                    "    " + Constants.ANSI_WHITE_BACKGROUND + "       " + Constants.ANSI_RESET + " "
            );
        }
        System.out.println("\n");
    }

    public static void printInhCards(int inhCards) {
        System.out.println("Inhibition cards on the island:\n");
        for (int i = 0; i < inhCards; i++) {
            System.out.print(
                    Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "          " + Constants.ANSI_RESET
            );
        }
        System.out.println();
        for (int i = 0; i < inhCards; i++) {
            System.out.print(
                    Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "   " + Constants.ANSI_RED_BACKGROUND + "    " + Constants.ANSI_BLUE_BACKGROUND + "   " + Constants.ANSI_RESET
            );
        }
        System.out.println();
        for (int i = 0; i < inhCards; i++) {
            System.out.print(
                    Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + " " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RESET
            );
        }
        System.out.println();
        for (int i = 0; i < inhCards; i++) {
            System.out.print(
                    Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + " " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RESET
            );
        }
        System.out.println();
        for (int i = 0; i < inhCards; i++) {
            System.out.print(
                    Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "   " + Constants.ANSI_RED_BACKGROUND + "    " + Constants.ANSI_BLUE_BACKGROUND + "   " + Constants.ANSI_RESET
            );
        }
        System.out.println();
        for (int i = 0; i < inhCards; i++) {
            System.out.print(
                    Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "          " + Constants.ANSI_RESET
            );
        }
        System.out.println("\n");
    }

    public static void printBoard(String nickname, Map<Colors, Integer> entrance, Map<Colors, Integer> hall, int towers) {
        ArrayList<Colors> students = new ArrayList<>();
        for (Colors c : Colors.values()) {
            for (int i = 0; i < entrance.get(c); i++) {
                students.add(c);
            }
        }

        System.out.println("\nBoard of " + nickname + "\n");
        System.out.println("Entrance:          Hall:\n");

        int i = 0;
        for (Colors c : Colors.values()) {
            for (int j = 0; j < 2; j++) {
                if (i + j < students.size()) {
                    System.out.print(Constants.ANSI_RESET + "  " + colorsBackground.get(students.get(i + j)) + "  " + Constants.ANSI_RESET);
                } else {
                    System.out.print(Constants.ANSI_RESET + "    " + Constants.ANSI_RESET);
                }
            }

            System.out.print(Constants.ANSI_RESET + "           ");

            for (int j = 0; j < hall.get(c); j++) {
                System.out.print(colorsBackground.get(c) + "  " + Constants.ANSI_RESET + "  ");
            }
            System.out.println("\n");

            i += 2;
        }

        System.out.println("Towers of " + nickname + ":\n");
        printTowers(towers);
    }

    public static void printTeachers(Map<Colors, String> teachers) {
        System.out.println("\nTeachers are controlled by:\n");
        for (Colors c : Colors.values()) {
            System.out.println(colorsChars.get(c) + teachers.get(c));
        }
        System.out.println(Constants.ANSI_RESET);
    }

    public static void printClouds(ArrayList<Map<Colors, Integer>> clouds) {
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_RESET + "     " + Constants.ANSI_WHITE_BACKGROUND + "            " + Constants.ANSI_RESET + "       ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "        " + Constants.ANSI_RESET + Constants.ANSI_RESET + " " + Constants.ANSI_YELLOW + clouds.get(i).get(Colors.YELLOW) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "         " + Constants.ANSI_RESET + "   ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_WHITE_BACKGROUND + "     " + Constants.ANSI_RESET + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN + clouds.get(i).get(Colors.GREEN) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "      " + Constants.ANSI_RESET + " " + Constants.ANSI_BLUE + clouds.get(i).get(Colors.BLUE) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "     " + Constants.ANSI_RESET + "  ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_WHITE_BACKGROUND + "                      " + Constants.ANSI_RESET + "  ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "      " + Constants.ANSI_RESET + " " + Constants.ANSI_RED + clouds.get(i).get(Colors.RED) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "  " + Constants.ANSI_RESET + " " + Constants.ANSI_PINK + clouds.get(i).get(Colors.PINK) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "      " + Constants.ANSI_RESET + "   ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_RESET + "     " + Constants.ANSI_WHITE_BACKGROUND + Constants.ANSI_BLACK + "   Cloud " + i + "  " + Constants.ANSI_RESET + "       ");
        }
        System.out.println("\n");
    }

    public static void printCharacterCard(String name, int cost) {
        System.out.println("Character card: " + name);
        System.out.println("Cost: " + cost + "\n");
        System.out.println("Description:");
        System.out.println(characterCardEffects.get(name) + "\n");
    }

    public static void printCharacterCard(String name, int cost, Map<Colors, Integer> students) {
        printCharacterCard(name, cost);

        for (Colors c : Colors.values()) {
            for (int i = 0; i < students.get(c); i++) {
                System.out.print(colorsBackground.get(c) + "  " + Constants.ANSI_RESET + "  ");
            }
        }
    }
}
