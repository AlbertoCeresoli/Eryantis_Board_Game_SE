package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Model.Influence.Card6Effect;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import it.polimi.ingsw.Model.Island.hasSetInfluence;

import java.util.Map;

public class Card6 extends CharacterCards {
    private final hasSetInfluence hasSetInfluence;

    /**
     * Card6 constructor
     */
    public Card6(int cost, IslandInteraction islandInteraction) {
        super(cost);
        hasSetInfluence = islandInteraction;
    }

    /**
     * The method changes reference of influence in islandInteraction, in order to change the calculateInfluence
     * method that will be called
     *
     * @param variables     not used
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //changing reference of Influence
        hasSetInfluence.setInfluence(new Card6Effect());

        return true;
    }
}
