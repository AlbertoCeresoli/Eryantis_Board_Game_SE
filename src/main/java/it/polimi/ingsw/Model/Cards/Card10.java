package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Cards;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import it.polimi.ingsw.Model.Island.hasSetTeacher;
import it.polimi.ingsw.Model.Player.PlayerInteraction;
import it.polimi.ingsw.Model.Player.hasEntrance;
import it.polimi.ingsw.Model.Player.hasHall;
import it.polimi.ingsw.Model.Player.hasCheckTeacher;

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

        this.name = Cards.MINSTREL.getName();
        this.cardIndex = 10;

        this.hasEntrance = playerInteraction;
        this.hasHall = playerInteraction;
        this.hasCheckTeacher = playerInteraction;
        this.hasSetTeacher = islandInteraction;
    }

    /**
     * The method removes students saved in studentArray1 from the entrance,
     * removes students saved in studentArray2 from the hall,
     * adds studentArray1 to the hall,
     * adds studentArray2 to the entrance
     * <p>
     * A maximum of 2 students can be chosen.
     *
     * @param variables     is the player index that will exchange the students
     * @param studentColor  not used
     * @param studentArray1 students from entrance
     * @param studentArray2 students from hall
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor,
                             Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //removing students from entrance
        hasEntrance.removeFromEntrance(variables.get(Indexes.PLAYER_INDEX), studentArray1);

        //removing students from hall
        hasHall.removeFromHall(variables.get(Indexes.PLAYER_INDEX), studentArray2);

        //adding students that were previously removed from the hall to the entrance
        hasEntrance.addToEntrance(variables.get(Indexes.PLAYER_INDEX), studentArray2);

        //adding students that were previously removed from the entrance to the hall
        hasHall.addToHall(variables.get(Indexes.PLAYER_INDEX), studentArray1);

        //checking if there is a new teacher controller
        for (Colors c : Colors.values()) {
            ArrayList<Integer> temp = hasCheckTeacher.checkTeacher(c, variables.get(Indexes.PLAYER_INDEX));
            if (temp.size() == 1) {
                hasSetTeacher.setTeacher(temp.get(0), c);
            }
        }

        return true;
    }
}
