package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Island;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NormalEffect implements Influence {
    @Override
    public ArrayList<Integer> calculateInfluence(Map<Colors, Integer> teachers, Island island) {
        ArrayList<Integer> playerInfluence = new ArrayList<>();

        int maxIndex = teachers.get(Colors.YELLOW);
        for (Colors c: Colors.values()) {
            if (teachers.get(c) > maxIndex) {
                maxIndex = teachers.get(c);
            }
        }
        if (maxIndex < 0) {
            return playerInfluence;
        }

        Map<Colors, Integer> students = new HashMap<Colors, Integer>();
        students.forEach((key, value) -> value = island.getStudents().get(key));

        for (int i = 0; i < maxIndex; i++) {
            int influence = 0;
            for (Colors c: Colors.values()) {
                if (teachers.get(c) == i) {
                    influence += students.get(c);
                }
            }
            playerInfluence.add(influence);
        }

        return playerInfluence;
    }
}
