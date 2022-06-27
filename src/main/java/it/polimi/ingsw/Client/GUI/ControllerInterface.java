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

    public void selectColor();

    public void selectAC();

    public void selectIsland();

    public void selectCloud();

    public void selectSteps(int maxSteps);

    public void selectPlace();

    public void quitGUI();
}
