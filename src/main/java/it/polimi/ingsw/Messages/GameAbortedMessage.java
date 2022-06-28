package it.polimi.ingsw.Messages;

public class GameAbortedMessage implements Message{
    private final String text;

    public GameAbortedMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
