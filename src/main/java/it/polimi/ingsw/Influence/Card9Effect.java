package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Island;

import java.util.ArrayList;
import java.util.Map;

public class Card9Effect extends OtherEffect {
    Colors studentColor;

    public Card9Effect(Colors studentColor) {
        super();
        this.studentColor = studentColor;
    }
    @Override
    ArrayList<Integer> extra(Map<Colors, Integer> teachers, Island island, ArrayList<Integer> influences) {
        int thatColorStudents = island.getStudents().get(studentColor);

        influences.set(teachers.get(studentColor), influences.get(teachers.get(studentColor)) - thatColorStudents);

        return influences;
    }
}
