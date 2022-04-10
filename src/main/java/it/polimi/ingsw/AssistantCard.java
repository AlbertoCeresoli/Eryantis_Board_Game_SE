package it.polimi.ingsw;

public class AssistantCard {
    int cardState;
    int priority;
    int steps;

    public AssistantCard(int cardState, int priority, int steps) {
        this.cardState = cardState;
        this.priority = priority;
        this.steps = steps;
    }

    public int getCardState() {
        return cardState;
    }

    public int getPriority() {
        return priority;
    }

    public int getSteps() {
        return steps;
    }

    public void setCardState(int cardState) {
        this.cardState = cardState;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
