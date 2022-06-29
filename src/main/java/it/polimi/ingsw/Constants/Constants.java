package it.polimi.ingsw.Constants;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Service class that keep global constants used
 */
public final class Constants {
    public static final int STUDENTS_IN_ENTRANCE_2_PLAYERS = 7;
    public static final int STUDENTS_IN_ENTRANCE_3_PLAYERS = 9;
    public static final int TOWERS_2_PLAYERS = 8;
    public static final int TOWERS_3_PLAYERS = 6;
    public static final int STUDENTS_ON_CLOUD_2_PLAYERS = 3;
    public static final int STUDENTS_ON_CLOUD_3_PLAYERS = 4;
    public static final int NUMBER_OF_ASSISTANT_CARDS = 10;
    public static final int NUMBER_OF_CHARACTER_CARDS = 3;
    public static final int NUMBER_OF_STUDENTS_IN_HALL = 10;
    public static final int MONK_STUDENTS_CAPACITY = 4;
    public static final int MONK_STUDENTS_TO_MOVE = 1;
    public static final int MAGIC_POSTMAN_ADDITION_MOVEMENT = 2;
    public static final int GRANDMA_HERBS_NUMBER_INHIBITION_CARD = 4;
    public static final int JOKER_STUDENTS_CAPACITY = 6;
    public static final int JOKER_MAX_STUDENTS_TO_MOVE = 3;
    public static final int KNIGHT_ADDITIONAL_INFLUENCE_POINTS = 2;
    public static final int MINSTREL_MAX_STUDENTS_TO_MOVE = 2;
    public static final int SPOILED_PRINCESS_STUDENTS_CAPACITY = 4;
    public static final int SPOILED_PRINCESS_STUDENTS_TO_MOVE = 1;
    public static final int THIEF_MAX_STUDENTS_TO_MOVE = 3;
    public static final String MONK_EFFECT = "At the start of the game, draw 4 students and put then upon this card.\n" +
            "Take 1 student from the card and put it on an island of your choice. " +
            "Then, draw 1 student from the bag and put it on this card.";
    public static final String FARMER_EFFECT = "During this turn, " +
            "you take control of a teacher even if in your hall you have the same number of student of the player " +
            "that controls them in that moment.";
    public static final String HERALD_EFFECT = "Choose an island and calculate the influence " +
            "as if Mother Nature has terminated its movement there. In this turn, Mother Nature will move as usual and where " +
            "it will terminate its movement influence will be normally calculated.";
    public static final String MAGIC_POSTMAN_EFFECT = "You can move Mother Nature up to 2 " +
            "additional islands with respect to what is indicated on the assistant card you have played this turn.";
    public static final String GRANDMA_HERBS_EFFECT = "At the start of the game, put 4 Inhibition Cards on this card.\n" +
            "Put an Inhibition Card on an island of your choice. The first time that Mother Nature " +
            "terminate its movement there, put the inhibition card back to the card WITHOUT calculating the influence " +
            "on the island and putting towers on it.";
    public static final String CENTAUR_EFFECT = "Towers are not counted during " +
            "influence calculation on an island (or a group of islands).";
    public static final String JOKER_EFFECT = "At the start of the game, draw 6 students and put them on this card.\n" +
            "You can take up to 3 students from this card and exchange them " +
            "with as many students in your Entrance.";
    public static final String KNIGHT_EFFECT = "In this turn, you have 2 additional points " +
            "during influence calculation.";
    public static final String MUSHROOMS_MAN_EFFECT = "Choose a color; in this turn, " +
            "that color is not counted during influence calculation.";
    public static final String MINSTREL_EFFECT = "You can exchange up to 2 student " +
            "between your Entrance and your Hall.";
    public static final String SPOILED_PRINCESS_EFFECT = "At the start of the game, draw 4 students and put them on the card.\n" +
            "Take 1 student from this card and put it in your Hall. " +
            "Then, draw 1 new student from the bag and put it on this card.";
    public static final String THIEF_EFFECT = "Choose a color; each player (you are included) " +
            "has to put in the bag 3 students of that color that has in the Hall. Who has less than 3 student will remove " +
            "all the students of that color in the Hall.";
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
    public static boolean gameMode;
    public static int numPlayers;

    private Constants() {

    }

    public static boolean isGameMode() {
        return gameMode;
    }

    public static void setGameMode(boolean gameMode) {
        Constants.gameMode = gameMode;
    }

    public static int getNumPlayers() {
        return numPlayers;
    }

    public static void setNumPlayers(int numPlayers) {
        Constants.numPlayers = numPlayers;
    }


    //GUI
    private static boolean somethingClicked = false;

    public static void moveObject(Node node, double X, double Y, Rectangle rectOpaqueBackground){
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.millis(1000));
        transition.setByX(X);
        transition.setByY(Y);
        transition.setOnFinished(Event -> {
            rectOpaqueBackground.setWidth(1000);
            rectOpaqueBackground.setHeight(600);
        });
        transition.play();
    }

    public static void zoomObject(Node node, double scaleValueX, double scaleValueY){
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(node);
        scale.setDuration(Duration.millis(1000));
        scale.setByX(scaleValueX);
        scale.setByY(scaleValueY);

        scale.play();
    }

    public static void zoomBackObject(Node node, double scaleValueX, double scaleValueY){
        ScaleTransition reduction = new ScaleTransition();
        reduction.setNode(node);
        reduction.setDuration(Duration.millis(1000));
        reduction.setByX(scaleValueX);
        reduction.setByY(scaleValueY);
        reduction.play();
    }

    public static void moveBackObject(Node node, double X, double Y){
        TranslateTransition moveBack = new TranslateTransition();
        moveBack.setNode(node);
        moveBack.setDuration(Duration.millis(1000));
        moveBack.setByX(X);
        moveBack.setByY(Y);
        moveBack.setOnFinished(Event -> setSomethingClicked(false));
        moveBack.play();
    }

    public static ArrayList<Image> createArrayImagesAC(){
        ArrayList<Image> assistantCards = new ArrayList<>();
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (1).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (2).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (3).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (4).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (5).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (6).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (7).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (8).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (9).png"));
        assistantCards.add(new Image ("file:../resources/Images/Assistants/Assistant (10).png"));

        return assistantCards;
    }

    public static ArrayList<Image> createArrayImagesIslands(){
        ArrayList<Image> islands = new ArrayList<>();
        islands.add(new Image("file:../resources/Images/Islands/Island1.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island2.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island3.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island4.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island5.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island6.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island7.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island8.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island9.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island10.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island11.png"));
        islands.add(new Image("file:../resources/Images/Islands/Island12.png"));

        return islands;
    }

    public static ArrayList<Image> createArrayImagesCC(){
        ArrayList<Image> characterCards = new ArrayList<>();
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC1.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC2.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC3.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC4.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC5.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC6.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC7.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC8.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC9.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC10.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC11.jpg"));
        characterCards.add(new Image("file:../resources/Images/Character_Cards/CC12.jpg"));

        return characterCards;
    }

    public static ArrayList<Image> createArrayImagesClouds(){
        ArrayList<Image> clouds = new ArrayList<>();
        clouds.add(new Image("file:../resources/Images/Clouds/2_Players/3_Players/3P_Cloud1.png"));
        clouds.add(new Image("file:../resources/Images/Clouds/2_Players/3_Players/3P_Cloud2.png"));
        if (numPlayers==3) {
            clouds.add(new Image("file:../resources/Images/Clouds/2_Players/3_Players/3P_Cloud3.png"));
        }

        return clouds;
    }

    //methods for the boolean somethingClicked
    public static boolean isSomethingClicked() {
        return somethingClicked;
    }

    public static void setSomethingClicked(boolean bool) {
        somethingClicked = bool;
    }

}
