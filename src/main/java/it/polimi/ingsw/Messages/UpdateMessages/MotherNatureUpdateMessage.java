package it.polimi.ingsw.Messages.UpdateMessages;

public class MotherNatureUpdateMessage implements UpdateMessage {
    private final int position;

    public MotherNatureUpdateMessage(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
