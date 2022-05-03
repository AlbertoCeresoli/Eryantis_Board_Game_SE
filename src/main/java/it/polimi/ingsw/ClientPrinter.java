package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientPrinter {
    public static final Map<Colors, String> colorsChars = new HashMap<>();
    public static final Map<Colors, String> colorsBackground = new HashMap<>();

    public ClientPrinter() {
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

    static void printIsland(Map<Colors, Integer> students, int towers, int inhCards, String controller, int islandIndex, boolean MN) {
        System.out.println("Island " + islandIndex + ", controlled by " + controller + "\n");

        printIslandStudents(students, islandIndex, MN);
        if (towers != 0) {
            System.out.println("Towers on the island:\n");
            printTowers(towers);
        }
        if (inhCards != 0)
            printInhCards(inhCards);
    }

    public static void printBoard(String nickname, Map<Colors, Integer> entrance, Map<Colors, Integer> hall, int towers) {
        ArrayList<Colors> students = new ArrayList<>();
        for (Colors c : Colors.values()) {
            for (int i = 0; i < entrance.get(c); i++) {
                students.add(c);
            }
        }

        System.out.println("Board of " + nickname + "\n");
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

    public static void printIslandStudents(Map<Colors, Integer> students, int islandIndex, boolean MN) {
        System.out.println("Students on the island:\n");

        if (MN) {
            printMN();
        }

        System.out.println(
                "      " + Constants.ANSI_GREEN_BACKGROUND + "         " + Constants.ANSI_RESET + "      \n" +
                        "    " + Constants.ANSI_GREEN_BACKGROUND + "             " + Constants.ANSI_RESET + "    \n" +
                        "  " + Constants.ANSI_GREEN_BACKGROUND  + "      " + Constants.ANSI_RESET + Constants.ANSI_PINK + students.get(Colors.PINK) + Constants.ANSI_GREEN_BACKGROUND + "   " + Constants.ANSI_RESET + Constants.ANSI_YELLOW + students.get(Colors.YELLOW) + Constants.ANSI_GREEN_BACKGROUND + "      " + Constants.ANSI_RESET + "  \n" +
                        Constants.ANSI_GREEN_BACKGROUND + "                     " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_GREEN_BACKGROUND + "      " + Constants.ANSI_RESET + Constants.ANSI_RED + students.get(Colors.RED) + Constants.ANSI_RESET +  Constants.ANSI_GREEN_BACKGROUND + "       " + Constants.ANSI_RESET + Constants.ANSI_GREEN + students.get(Colors.GREEN) + Constants.ANSI_RESET + Constants.ANSI_GREEN_BACKGROUND + "      " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_GREEN_BACKGROUND + "                     " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "      " + Constants.ANSI_RESET + Constants.ANSI_BLUE + students.get(Colors.BLUE) + Constants.ANSI_RESET + Constants.ANSI_GREEN_BACKGROUND + "          " + Constants.ANSI_RESET + "\n" +
                        "  " + Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "             " + Constants.ANSI_RESET + "\n" +
                        "    " + Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "         " + Constants.ANSI_RESET + "\n" +
                        "      " + Constants.ANSI_YELLOW_BACKGROUND + Constants.ANSI_BLACK + "Island " + islandIndex + Constants.ANSI_RESET + "\n"
        );
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

    public static void printTeachers(Map<Colors, String> teachers) {
        System.out.println("Teachers are controlled by:\n");
        for (Colors c : Colors.values()) {
            System.out.println(colorsChars.get(c) + teachers.get(c));
        }
        System.out.println();
    }

    public static void printClouds(ArrayList<Map<Colors, Integer>> clouds) {
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_RESET + "     " + Constants.ANSI_WHITE_BACKGROUND + "            " + Constants.ANSI_RESET + "       ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "         " + Constants.ANSI_RESET + Constants.ANSI_YELLOW + clouds.get(i).get(Colors.YELLOW) + Constants.ANSI_WHITE_BACKGROUND + "          " + Constants.ANSI_RESET + "   ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_WHITE_BACKGROUND + "      " + Constants.ANSI_RESET + Constants.ANSI_GREEN + clouds.get(i).get(Colors.GREEN) + Constants.ANSI_WHITE_BACKGROUND + "        " + Constants.ANSI_RESET + Constants.ANSI_BLUE + clouds.get(i).get(Colors.BLUE) + Constants.ANSI_WHITE_BACKGROUND + "      " + Constants.ANSI_RESET + "  ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_WHITE_BACKGROUND + "                      " + Constants.ANSI_RESET + "  ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "       " + Constants.ANSI_RESET +  Constants.ANSI_RED + clouds.get(i).get(Colors.RED) + Constants.ANSI_WHITE_BACKGROUND + "    " + Constants.ANSI_RESET + Constants.ANSI_PINK + clouds.get(i).get(Colors.PINK) + Constants.ANSI_WHITE_BACKGROUND + "       " + Constants.ANSI_RESET + "   ");
        }
        System.out.println();
        for (int i = 0; i < clouds.size(); i++) {
            System.out.print(Constants.ANSI_RESET + "     " + Constants.ANSI_WHITE_BACKGROUND + Constants.ANSI_BLACK + "   Cloud " + i + "  " + Constants.ANSI_RESET + "       ");
        }
        System.out.println("\n");
    }

    static void printMN() {
        System.out.println(
                Constants.ANSI_RESET + "         " + Constants.ANSI_YELLOW_BACKGROUND + "   " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_RESET + "        " + Constants.ANSI_YELLOW_BACKGROUND + "     " + Constants.ANSI_RESET + "\n" +
                        Constants.ANSI_RESET + "       " + Constants.ANSI_YELLOW_BACKGROUND + "       " + Constants.ANSI_RESET + "\n"
        );
    }
}
