package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Influence.Card6Effect;
import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.Influence.NormalEffect;
import it.polimi.ingsw.IslandInteraction;

public class Card6 extends CharCardsIslands {
    /**
     * Card6 constructor
     */
    public Card6(int cost, IslandInteraction islandInteraction) {
        super(cost, islandInteraction);
    }

    /**
     * The method changes reference of influence in islandInteraction, in order to change the calculateInfluence
     * method that will be called
     * @param index         not used
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {
        Influence influence = new NormalEffect();
        getIslandInteraction().setInfluence(new Card6Effect(influence));
        return true;
    }
}
