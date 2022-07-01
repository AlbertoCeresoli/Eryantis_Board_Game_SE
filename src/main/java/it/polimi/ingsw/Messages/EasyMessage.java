package it.polimi.ingsw.Messages;

public class EasyMessage implements Message {
	private final String text;

	/**
	 * Just a message to be printed
	 * @param text to be printed
	 */
	public EasyMessage(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
