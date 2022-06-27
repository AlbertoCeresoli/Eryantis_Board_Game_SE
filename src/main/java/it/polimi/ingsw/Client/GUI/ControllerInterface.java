package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.TypesOfUpdate;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public interface ControllerInterface {

    void updateGame(TypesOfUpdate selection, int index, Colors c, int num, HashMap<Colors, Integer> cloudStudents, int newController);

    void setUp(GUI gui, Stage primaryStage);

    void printTable();

    void startEventHandling();

    void setNicknames(Map<Integer, String> indexToNick);

    void selectColor();

    void selectAC();

    void selectIsland();

    void selectCloud();

    void selectSteps(int maxSteps);

    void selectPlace();

    void quitGUI();

    void selectIP();

    void selectNickname();

    void selectServerPort();

    void selectNumPlayers();

    void selectGamemode();

    GUIPrinter getPrinter();
}