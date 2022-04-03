package it.polimi.ingsw.Exceptions;

public class StudentNotAvailableException extends Throwable {
	private static final String STUDENT_NOT_ON_CARD = "Requested student that is not on the card";

	public StudentNotAvailableException() {
		super(STUDENT_NOT_ON_CARD);
	}
}
