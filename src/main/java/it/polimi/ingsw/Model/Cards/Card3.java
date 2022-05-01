package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import it.polimi.ingsw.Model.Island.hasCalculateInfluence;

import java.util.Map;

public class Card3 extends CharacterCards {
    private final hasCalculateInfluence hasCalculateInfluence;

    /**
     * Card3 constructor
     */
    public Card3(int cost, IslandInteraction islandInteraction) {
        super(cost);
        this.hasCalculateInfluence = islandInteraction;

        effect = "Card effect";
        cardIndex = 3;
    }

    /**
     * The method activate calculateInfluence method in islandInteraction on the chosen island
     *
     * @param variables     contains the island index where the influence will be calculated and the number of players
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws EndGameException {
        //calling calculate influence on the chosen island
        this.hasCalculateInfluence.calculateInfluence(variables.get(Indexes.ISLAND_INDEX), variables.get(Indexes.NUMBER_OF_PLAYERS));

        return true;
    }
}
