package it.polimi.ingsw.Model.Island;

import it.polimi.ingsw.Constants.Colors;

import java.util.Map;

public interface hasAddToIsland {
    void addToIsland(int islandIndex, Map<Colors, Integer> students);
}
