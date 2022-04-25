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

    /**
     * The method makes that a color is not considered in influence calculation this turn
     */
    @Override
    ArrayList<Integer> extra(Map<Colors, Integer> teachers, Island island, ArrayList<Integer> influences) {
        //saving the number of students of chosen color
        int thatColorStudents = island.getStudents().get(studentColor);

        //removes the number of students from influence of the player that controls the corresponding teacher
        influences.set(teachers.get(studentColor), influences.get(teachers.get(studentColor)) - thatColorStudents);

        return influences;
    }
}
