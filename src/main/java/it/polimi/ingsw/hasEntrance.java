package it.polimi.ingsw;

import java.util.Map;

public interface hasEntrance {
    void addToEntrance(int playerIndex, Map<Colors, Integer> students);
    void removeFromEntrance(int playerIndex, Map<Colors, Integer> students);
}
