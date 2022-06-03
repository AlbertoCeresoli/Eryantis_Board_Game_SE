package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Cards;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Model.BagNClouds.BagNClouds;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Model.BagNClouds.hasAddToBag;
import it.polimi.ingsw.Model.Player.PlayerInteraction;
import it.polimi.ingsw.Model.Player.hasCard12Effect;

import java.util.Map;

public class Card12 extends CharacterCards {
    private final hasCard12Effect hasCard12Effect;
    private final hasAddToBag hasAddToBag;

    /**
     * Card12 constructor
     *
     * @param bagNClouds bagNClouds reference used to draw students in useEffect
     */
    public Card12(int cost, PlayerInteraction playerInteraction, BagNClouds bagNClouds) {
        super(cost, Constants.THIEF_EFFECT);

        this.name = Cards.THIEF.getName();
        this.cardIndex = 12;

        this.hasCard12Effect = playerInteraction;
        this.hasAddToBag = bagNClouds;
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
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor,
                             Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //removing students and counting them
        int count = hasCard12Effect.card12Effect(studentColor);

        //adding them to the bag
        hasAddToBag.addToBag(studentColor, count);

        return true;
    }
}
