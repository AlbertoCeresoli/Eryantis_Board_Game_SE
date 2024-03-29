package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Cards;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Model.Influence.Card9Effect;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import it.polimi.ingsw.Model.Island.hasSetInfluence;

import java.util.Map;

public class Card9 extends CharacterCards {
    private final hasSetInfluence hasSetInfluence;

    /**
     * Card9 constructor
     */
    public Card9(int cost, IslandInteraction islandInteraction) {
        super(cost, Constants.MUSHROOMS_MAN_EFFECT);

        this.name = Cards.MUSHROOMS_MAN.getName();
        this.cardIndex = 9;

        this.hasSetInfluence = islandInteraction;
    }

    /**
     * The method changes reference of influence in islandInteraction, in order to change the calculateInfluence
     * method that will be called
     *
     * @param variables     not used
     * @param studentColor  is the kind of students that has not to be considered in calculateInfluence
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor,
                             Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //changing reference of Influence
        hasSetInfluence.setInfluence(new Card9Effect(studentColor));

        return true;
    }
}