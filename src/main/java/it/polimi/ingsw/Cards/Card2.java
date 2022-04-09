package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.PlayerInteraction;
import it.polimi.ingsw.Teacher.EqualCheck;

import java.util.Map;

public class Card2 extends CharCardsPlayer {
    /**
     * Card2 constructor
     */
    public Card2(int cost, PlayerInteraction playerInteraction) {
        super(cost, playerInteraction);
    }

    /**
     * The method changes the reference of teacherInterface in playerInteraction, in order to activate the correct checkTeacher method
     *
     * @param index         not used
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        getPlayerInteraction().setTeacherInterface(new EqualCheck());
        return true;
    }
}
