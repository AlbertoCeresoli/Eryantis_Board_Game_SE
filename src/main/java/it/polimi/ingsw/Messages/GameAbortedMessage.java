package it.polimi.ingsw.Messages;

public class GameAbortedMessage implements Message{
    private final String text;

    /**
     * Sent when a game needs to be aborted
     * @param text the cause of the abortion
     */
    public GameAbortedMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
