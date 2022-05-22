package it.polimi.ingsw.Constants;

/**
 * Service class that keep global constants used
 */
public final class Constants {
    public static boolean gameMode;
    public static int numPlayers;
    public static final int STUDENTS_IN_ENTRANCE_2_PLAYERS = 7;
    public static final int STUDENTS_IN_ENTRANCE_3_PLAYERS = 9;
    public static final int TOWERS_2_PLAYERS = 8;
    public static final int TOWERS_3_PLAYERS = 6;
    public static final int STUDENTS_ON_CLOUD_2_PLAYERS = 3;
    public static final int STUDENTS_ON_CLOUD_3_PLAYERS = 4;
    public static final int NUMBER_OF_ASSISTANT_CARDS = 10;
    public static final int NUMBER_OF_STUDENTS_IN_HALL = 10;
    public static final int CARD1_STUDENTS_CAPACITY = 4;
    public static final int CARD1_STUDENTS_TO_MOVE = 1;
    public static final int CARD4_ADDITION_MOVEMENT = 2;
    public static final int CARD5_NUMBER_INHIBITION_CARD = 4;
    public static final int CARD7_STUDENTS_CAPACITY = 6;
    public static final int CARD7_MAX_STUDENTS_TO_MOVE = 3;
    public static final int CARD8_ADDITIONAL_INFLUENCE_POINTS = 2;
    public static final int CARD10_MAX_STUDENTS_TO_MOVE = 2;
    public static final int CARD11_STUDENTS_CAPACITY = 4;
    public static final int CARD11_STUDENTS_TO_MOVE = 1;
    public static final int CARD12_MAX_STUDENTS_TO_MOVE = 3;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PINK = "\u001B[35m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\033[104m";
    public static final String ANSI_RED_BACKGROUND = "\033[101m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";

    private Constants() {

    }

    public static boolean isGameMode() {
        return gameMode;
    }

    public static int getNumPlayers() {
        return numPlayers;
    }

    public static void setGameMode(boolean gameMode) {
        Constants.gameMode = gameMode;
    }

    public static void setNumPlayers(int numPlayers) {
        Constants.numPlayers = numPlayers;
    }
}
