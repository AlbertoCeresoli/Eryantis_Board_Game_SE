package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintIslandsMessage;

import java.util.HashMap;
import java.util.Map;

public class IslandsUpdateMessage implements UpdateMessage {
    private final PrintIslandsMessage printIslandsMessage;
    private final int[] towers;
    private final Map<String, Integer> nickToIndex;

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
