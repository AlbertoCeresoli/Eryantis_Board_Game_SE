package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Influence.Card8Effect;
import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.Influence.NormalEffect;
import it.polimi.ingsw.IslandInteraction;

import java.util.Map;

public class Card8 extends CharCardsIslands {
    /**
     * Card8 constructor
     */
    public Card8(int cost, IslandInteraction islandInteraction) {
        super(cost, islandInteraction);
    }

    /**
     * The method changes reference of influence in islandInteraction, in order to change the calculateInfluence
     * method that will be called
     *
     * @param index         of actual turn's player
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        getIslandInteraction().setInfluence(new Card8Effect(index));

        return true;
    }
}
