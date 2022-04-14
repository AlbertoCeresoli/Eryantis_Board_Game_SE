package it.polimi.ingsw.Cards;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;

import java.util.Map;

public class Card12 extends CharacterCards {
    private final hasCard12Effect hasCard12Effect;
    BagNClouds bagNClouds;

    /**
     * Card12 constructor
     *
     * @param bagNClouds bagNClouds reference used to draw students in useEffect
     */
    public Card12(int cost, PlayerInteraction playerInteraction, BagNClouds bagNClouds) {
        super(cost);
        this.hasCard12Effect = playerInteraction;
        this.bagNClouds = bagNClouds;
    }

    /**
     * The method removes from each board a maximum of Constants.CARD12_MAX_STUDENTS_TO_REMOVE
     *
     * @param variables     not used
     * @param studentColor  is the color of students that will be removed
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //removing students and counting them
        int count = hasCard12Effect.card12Effect(studentColor);

        //adding them to the bag
        bagNClouds.addToBag(studentColor, count);

        return true;
    }
}
