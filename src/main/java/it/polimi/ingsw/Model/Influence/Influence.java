package it.polimi.ingsw.Model.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Model.Island.Island;

import java.util.ArrayList;
import java.util.Map;

public interface Influence {
    /**
     * The method calculates influence of each player
     *
     * @param teachers controlled by players. They are -1 if are not controlled by anyone
     * @param island   is the island where we want to calculate influence
     */
    ArrayList<Integer> calculateInfluence(Map<Colors, Integer> teachers, Island island, int numberOfPlayers);
}
