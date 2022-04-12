package it.polimi.ingsw.Cards;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Constants.Colors;

import java.util.ArrayList;
import java.util.Map;

public class Card10 extends CharacterCards {
    private final hasEntrance hasEntrance;
    private final hasHall hasHall;
    private final hasCheckTeacher hasCheckTeacher;
    private final hasSetTeacher hasSetTeacher;

    /**
     * Card10 constructor
     */
    public Card10(int cost, PlayerInteraction playerInteraction, IslandInteraction islandInteraction) {
        super(cost);
        hasEntrance = playerInteraction;
        hasHall = playerInteraction;
        hasCheckTeacher = playerInteraction;
        hasSetTeacher = islandInteraction;
    }

    /**
     * The method removes students saved in studentArray1 from the entrance,
     * removes students saved in studentArray2 from the hall,
     * adds studentArray1 to the hall,
     * adds studentArray2 to the entrance
     * <p>
     * A maximum of 2 students can be chosen.
     *
     * @param index         is the player index that will exchange the students
     * @param studentColor  not used
     * @param studentArray1 students from entrance
     * @param studentArray2 students from hall
     */
    @Override
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //removing students from entrance
        hasEntrance.removeFromEntrance(index, studentArray1);

        //removing students from hall
        hasHall.removeFromHall(index, studentArray2);

        //adding students that were previously removed from the hall to the entrance
        hasEntrance.addToEntrance(index, studentArray2);

        //adding students that were previously removed from the entrance to the hall
        hasHall.addToHall(index, studentArray1);

        //checking if there is a new teacher controller
        for (Colors c : Colors.values()) {
            ArrayList<Integer> temp = hasCheckTeacher.checkTeacher(c, index);
            if (temp.size() == 1) {
                hasSetTeacher.setTeacher(temp.get(0), c);
            }
        }

        return true;
    }
}
