package it.polimi.ingsw.Messages.SelectionMessages;

public class MNStepsSelectionMessage implements SelectionMessage {
    private final int minSteps;
    private final int maxSteps;

    /**
     * Notify to the client that the server is waiting for this kind of info
     * @param minSteps MN can make
     * @param maxSteps MN can make
     */
    public MNStepsSelectionMessage(int minSteps, int maxSteps) {
        this.minSteps = minSteps;
        this.maxSteps = maxSteps;
    }

    public int getMinSteps() {
        return minSteps;
    }

    public int getMaxSteps() {
        return maxSteps;
    }
}
