package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.Message;

/**
 * As the other error messages in this package, these classes have the purpose to type a specific kind of error
 */

public class ErrorMessage implements Message {
	private final String text;

	public ErrorMessage(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
