package it.polimi.ingsw.Messages.SelectionMessages;

public class IslandSelectionMessage implements SelectionMessage {
    private final int minimumIndex;
    private final int maximumIndex;

    public IslandSelectionMessage(int minimumIndex, int maximumIndex) {
        this.minimumIndex = minimumIndex;
        this.maximumIndex = maximumIndex;
    }

    public int getMinimumIndex() {
        return minimumIndex;
    }

    public int getMaximumIndex() {
        return maximumIndex;
    }
}
