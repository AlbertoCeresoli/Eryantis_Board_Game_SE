package it.polimi.ingsw.Model.Island;

import it.polimi.ingsw.Exceptions.EndGameException;

public interface hasCalculateInfluence {
    void calculateInfluence(int islandIndex, int numberOfPlayers) throws EndGameException;
}
