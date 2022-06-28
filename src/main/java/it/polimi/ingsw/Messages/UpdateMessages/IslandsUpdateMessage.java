package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintIslandsMessage;

public class IslandsUpdateMessage implements UpdateMessage {
    private final PrintIslandsMessage printIslandsMessage;
    private final int[] towers;

    public IslandsUpdateMessage(PrintIslandsMessage printIslandsMessage, int[] towers) {
        this.printIslandsMessage = printIslandsMessage;
        this.towers = towers;
    }

    public PrintIslandsMessage getPrintIslandsMessage() {
        return printIslandsMessage;
    }

    public int[] getTowers() {
        return towers;
    }
}
