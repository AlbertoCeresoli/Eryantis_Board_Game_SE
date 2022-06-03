package it.polimi.ingsw.Messages;

public class EasyMessage implements Message {
	private final String text;

	public EasyMessage(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
