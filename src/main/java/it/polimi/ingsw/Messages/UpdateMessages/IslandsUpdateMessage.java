package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintIslandsMessage;

import java.util.Map;

public class IslandsUpdateMessage implements UpdateMessage {
    private final PrintIslandsMessage printIslandsMessage;
    private final int[] towers;
    Map<String, Integer> nickToIndex;

    public IslandsUpdateMessage(PrintIslandsMessage printIslandsMessage, int[] towers, Map<String, Integer> nickToIndex) {
        this.printIslandsMessage = printIslandsMessage;
        this.towers = towers;
        this.nickToIndex = nickToIndex;
    }

    public PrintIslandsMessage getPrintIslandsMessage() {
        return printIslandsMessage;
    }

    public int[] getTowers() {
        return towers;
    }

    public Map<String, Integer> getNickToIndex() {
        return nickToIndex;
    }
}
