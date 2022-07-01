package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintIslandsMessage;

import java.util.HashMap;
import java.util.Map;

public class IslandsUpdateMessage implements UpdateMessage {
    private final PrintIslandsMessage printIslandsMessage;
    private final int[] towers;
    private final Map<String, Integer> nickToIndex;

    /**
     * Used from the GUI to update the general state of a specific island shown
     * @param printIslandsMessage to update that island state
     * @param towers on that island
     * @param nickToIndex to match each player's name to its index
     */
    public IslandsUpdateMessage(PrintIslandsMessage printIslandsMessage, int[] towers, Map<String, Integer> nickToIndex) {
        this.printIslandsMessage = printIslandsMessage;
        this.towers = towers;

        this.nickToIndex = new HashMap<>();

        for (String nick : nickToIndex.keySet()) {
            this.nickToIndex.put(nick, nickToIndex.get(nick));
        }
        this.nickToIndex.put("Nobody", -1);
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
