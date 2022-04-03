package it.polimi.ingsw.Exceptions;

public class OutOfBoundException extends Throwable {
	@Override
	public String getMessage() {
		return ("Index out of bound");
	}
}
