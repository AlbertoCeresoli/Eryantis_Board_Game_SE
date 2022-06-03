package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Cards;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Model.Influence.Card8Effect;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import it.polimi.ingsw.Model.Island.hasSetInfluence;

import java.util.Map;

public class Card8 extends CharacterCards {
    private final hasSetInfluence hasSetInfluence;

    /**
     * Card8 constructor
     */
    public Card8(int cost, IslandInteraction islandInteraction) {
        super(cost, Constants.KNIGHT_EFFECT);

        this.name = Cards.KNIGHT.getName();
        this.cardIndex = 8;

        this.hasSetInfluence = islandInteraction;
    }

    /**
     * The method changes reference of influence in islandInteraction, in order to change the calculateInfluence
     * method that will be called
     *
     * @param variables     contains index of actual turn's player
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor,
                             Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //changing reference of Influence
        hasSetInfluence.setInfluence(new Card8Effect(variables.get(Indexes.PLAYER_INDEX)));

        return true;
    }
}
