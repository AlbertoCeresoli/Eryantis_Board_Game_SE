package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Island;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NormalEffect implements Influence {
    /**
     * The method calculates influence of each player in the standard way, with no additional effect
     */
    @Override
    public ArrayList<Integer> calculateInfluence(Map<Colors, Integer> teachers, Island island, int numberOfPlayers) {
        ArrayList<Integer> playerInfluence = new ArrayList<>();
        int controllerIndex = island.getControllerIndex();

        //saving students of the island
        Map<Colors, Integer> students = new HashMap<>();
        for (Colors c : Colors.values()) {
            students.put(c, island.getStudents().get(c));
        }

        //calculating influence for each player, checking which teachers they control
        for (int i = 0; i < numberOfPlayers; i++) {
            int influence = 0;
            if (i == controllerIndex) {
                influence += island.getnTowers();
            }
            for (Colors c : Colors.values()) {
                if (teachers.get(c) == i) {
                    influence += students.get(c);
                }
            }
            playerInfluence.add(influence);
        }

        return playerInfluence;
    }
}
