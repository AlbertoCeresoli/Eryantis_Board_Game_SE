package it.polimi.ingsw.Exceptions;

public class WrongArrayException extends Throwable {

	public static final String WRONG_ARRAY = "Input array does not match requirements";

	public WrongArrayException() {
		super(WRONG_ARRAY);
	}
}
