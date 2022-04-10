package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;
import it.polimi.ingsw.hasEntrance;
import it.polimi.ingsw.hasHall;

import java.util.Map;

public class Card10 extends CharacterCards {
    private hasEntrance hasEntrance;
    private hasHall hasHall;

    /**
     * Card10 constructor
     */
    public Card10(int cost, PlayerInteraction playerInteraction) {
        super(cost);
        hasEntrance = playerInteraction;
        hasHall = playerInteraction;
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

        return true;
    }
}
