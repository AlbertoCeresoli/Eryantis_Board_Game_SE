package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;

import java.util.Map;

public interface hasCalculateInfluence {
    void calculateInfluence(Map<Colors, Integer> teachers, int islandIndex);
}
