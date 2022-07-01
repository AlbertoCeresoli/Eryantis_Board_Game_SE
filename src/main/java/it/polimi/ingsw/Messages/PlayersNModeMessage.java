package it.polimi.ingsw.Messages;

public class PlayersNModeMessage implements Message {
    private final int numberOfPlayers;
    private final boolean gameMode;

    public PlayersNModeMessage(int numberOfPlayers, boolean gameMode) {
        this.numberOfPlayers = numberOfPlayers;
        this.gameMode = gameMode;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isGameMode() {
        return gameMode;
    }
}
