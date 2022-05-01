package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Constants.Colors;

import java.util.Map;

public interface hasHall {
    void addToHall(int playerIndex, Map<Colors, Integer> students);
    void removeFromHall(int playerIndex, Map<Colors, Integer> students);
}
