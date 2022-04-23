package it.polimi.ingsw;

import it.polimi.ingsw.Exceptions.EndGameException;

public interface hasCalculateInfluence {
    void calculateInfluence(int islandIndex, int numberOfPlayers) throws EndGameException;
}
