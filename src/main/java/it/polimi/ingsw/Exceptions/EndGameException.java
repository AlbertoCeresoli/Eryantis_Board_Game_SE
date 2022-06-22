package it.polimi.ingsw.Exceptions;

public class EndGameException extends Throwable{
    private static final String THREE_OR_LESS_ISLANDS = "Game is over";

    public EndGameException() {
        super(THREE_OR_LESS_ISLANDS);
    }
}
