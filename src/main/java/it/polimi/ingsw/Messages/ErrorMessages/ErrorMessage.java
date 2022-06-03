package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.Message;

public class ErrorMessage implements Message {
	private final String text;

	public ErrorMessage(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
