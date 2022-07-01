package it.polimi.ingsw.Messages;

public class PlayersNModeMessage implements Message {
    private final int numberOfPlayers;
    private final boolean gameMode;

    /**
     * Sent to the GUI users when they need to wait for other players to join the lobby
     * @param numberOfPlayers in the game
     * @param gameMode 0=easy / 1=hard
     */
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
