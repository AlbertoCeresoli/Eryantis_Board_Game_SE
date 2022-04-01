package it.polimi.ingsw.Cards;

import it.polimi.ingsw.PlayerInteraction;
import it.polimi.ingsw.Teacher.EqualCheck;

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
    public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {
        getPlayerInteraction().setTeacherInterface(new EqualCheck());
    }
}
