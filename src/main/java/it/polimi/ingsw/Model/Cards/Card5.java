package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import it.polimi.ingsw.Model.Island.hasInhibitionCard;

import java.util.Map;

public class Card5 extends CharacterCards {
    private final hasInhibitionCard hasInhibitionCard;

    /**
     * Card5 constructor
     */
    public Card5(int cost, IslandInteraction islandInteraction) {
        super(cost);
        this.hasInhibitionCard = islandInteraction;
    }

    /**
     * Place an inhibition card on the chosen island
     *
     * @param variables     is the island index where the inhibition card will be placed
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws OutOfBoundException {
        //removing inhibition card from islandInteraction and adding it to the chosen island
        hasInhibitionCard.removeInhibitionCard();
        hasInhibitionCard.addInhibitionCard(variables.get(Indexes.ISLAND_INDEX));

        return true;
    }
}
