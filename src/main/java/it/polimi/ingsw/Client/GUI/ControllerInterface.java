package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Messages.UpdateMessages.UpdateMessage;

import java.util.Map;

public interface ControllerInterface {

    /**
     * methods in common in the two controllers, one controller is used to modify the GUI when the game is with two players, the other controller is used when the players are 3
     */

    void updateGame(UpdateMessage message);

    void setUp(GUI gui);

    void printTable();

    void startEventHandling();

    void setNicknames(Map<Integer, String> indexToNick);

    GUIPrinter getPrinter();

    void printEasyMessage(String text);

    void onClickACP1();

    void onClickBoard(int numBoard);

    void onClickCharacterCards();

    void onClickClouds();

    void zoomIsland(int numIsland);

    void onClickTable();
}