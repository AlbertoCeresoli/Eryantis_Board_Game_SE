package it.polimi.ingsw.Messages.UpdateMessages;

public class MotherNatureUpdateMessage implements UpdateMessage {
    private final int position;

    /**
     * Used from the GUI to update Mother Nature's position
     * @param position of Mother Nature
     */
    public MotherNatureUpdateMessage(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
