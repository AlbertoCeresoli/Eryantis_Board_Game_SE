package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Model.Player.PlayerInteraction;
import it.polimi.ingsw.Model.Teacher.EqualCheck;
import it.polimi.ingsw.Model.Player.hasSetTeacherInterface;

import java.util.Map;

public class Card2 extends CharacterCards {
    private final hasSetTeacherInterface hasSetTeacherInterface;

    /**
     * Card2 constructor
     */
    public Card2(int cost, PlayerInteraction playerInteraction) {
        super(cost);
        this.hasSetTeacherInterface = playerInteraction;
    }

    /**
     * The method changes the reference of teacherInterface in playerInteraction, in order to activate the correct checkTeacher method
     *
     * @param variables     not used
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //changing reference of TeacherInterface
        hasSetTeacherInterface.setTeacherInterface(new EqualCheck());

        return true;
    }
}
