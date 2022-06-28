package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.TypesOfUpdate;

import java.util.HashMap;
import java.util.Map;

public interface ControllerInterface {

    /**
     * methods in common in the two controllers, one controller is used to modify the GUI when the game is with two players, the other controller is used when the players are 3
     */

    void updateGame(TypesOfUpdate selection, int index, Colors c, int num, HashMap<Colors, Integer> cloudStudents, int newController);

    void setUp();

    void printTable();

    void startEventHandling();

    void setNicknames(Map<Integer, String> indexToNick);

    GUIPrinter getPrinter();
}