package it.polimi.ingsw.Messages.SelectionMessages;

public class MNStepsSelectionMessage implements SelectionMessage {
    private final int minSteps;
    private final int maxSteps;

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
