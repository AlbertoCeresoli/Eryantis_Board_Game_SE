package it.polimi.ingsw;

import it.polimi.ingsw.Constants.MessageType;

import java.io.Serializable;

public class Message implements Serializable {
    private MessageType type;
    private String text;

    public Message(MessageType type, String text) {
        this.type = type;
        this.text = text;
    }

    public MessageType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }
}
