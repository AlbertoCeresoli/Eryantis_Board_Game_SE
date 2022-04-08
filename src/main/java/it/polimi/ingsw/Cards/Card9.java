package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Influence.Card9Effect;
import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.Influence.NormalEffect;
import it.polimi.ingsw.IslandInteraction;

import java.util.Map;

public class Card9 extends CharCardsIslands {
    /**
     * Card9 constructor
     */
    public Card9(int cost, IslandInteraction islandInteraction) {
        super(cost, islandInteraction);
    }

    /**
     * The method changes reference of influence in islandInteraction, in order to change the calculateInfluence
     * method that will be called
     *
     * @param index         not used
     * @param studentColor  is the kind of students that has not to be considered in calculateInfluence
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        getIslandInteraction().setInfluence(new Card9Effect(studentColor));

        return true;
    }
}