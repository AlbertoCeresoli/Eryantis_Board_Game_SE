package it.polimi.ingsw.Messages.SelectionMessages;

public class IslandSelectionMessage implements SelectionMessage {
    private final int minimumIndex;
    private final int maximumIndex;

    /**
     * Notify to the client that the server is waiting for this kind of info
     * @param minimumIndex you can choose
     * @param maximumIndex you can choose
     */
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
