package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Island;

import java.util.ArrayList;

public class Card9Effect extends OtherEffect {
    int studentColor;

    public Card9Effect(int studentColor) {
        super();
        this.studentColor = studentColor;
    }
    @Override
    ArrayList<Integer> extra(int[] teachers, Island island, ArrayList<Integer> influences) {
        int thatColorStudents = island.getStudents()[studentColor];

        influences.set(teachers[studentColor], influences.get(teachers[studentColor]) - thatColorStudents);

        return influences;
    }
}
