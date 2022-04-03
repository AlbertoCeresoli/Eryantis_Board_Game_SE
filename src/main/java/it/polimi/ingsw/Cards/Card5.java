package it.polimi.ingsw.Cards;

import it.polimi.ingsw.IslandInteraction;

public class Card5 extends CharCardsIslands {
    /**
     * Card5 constructor
     */
    public Card5(int cost, IslandInteraction islandInteraction) {
        super(cost, islandInteraction);
    }

    /**
     * Place an inhibition card on the chosen island
     *  @param index         is the island index where the inhibition card will be placed
     * @param studentColor  not used
	 * @param studentArray1 not used
	 * @param studentArray2 not used
	 */
    @Override
    public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {
        getIslandInteraction().removeInhibitionCard();
        getIslandInteraction().getIslands().get(index).addInhibitionCard();
    }
}
