package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Island;

import java.util.ArrayList;

public class NormalEffect implements Influence {
    @Override
    public ArrayList<Integer> calculateInfluence(int[] teachers, Island island) {
        ArrayList<Integer> playerInfluence = new ArrayList<>();

        int maxIndex = teachers[0];
        for (int i = 1; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (teachers[i] > maxIndex) {
                maxIndex = teachers[i];
            }
        }
        if (maxIndex < 0) {
            return playerInfluence;
        }

        int[] students = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(island.getStudents(), 0, students, 0, Constants.NUMBER_OF_STUDENTS_COLOR);

        for (int i = 0; i < maxIndex; i++) {
            int influence = 0;
            for (int j = 0; j < Constants.NUMBER_OF_STUDENTS_COLOR; j++) {
                if (teachers[j] == i) {
                    influence += students[j];
                }
            }
            playerInfluence.add(influence);
        }

        return playerInfluence;
    }
}
