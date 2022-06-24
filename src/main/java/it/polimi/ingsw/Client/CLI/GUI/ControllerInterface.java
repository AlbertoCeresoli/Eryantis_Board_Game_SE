package it.polimi.ingsw.Client.CLI.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.TypesOfUpdate;

import java.util.HashMap;
import java.util.Map;

public interface ControllerInterface {

    void updateGame(TypesOfUpdate selection, int index, Colors c, int num, HashMap<Colors, Integer> cloudStudents, int newController);

    void setUp();

    void printTable();

    void startEventHandling();

    void setNicknames(Map<Integer, String> indexToNick);
}
