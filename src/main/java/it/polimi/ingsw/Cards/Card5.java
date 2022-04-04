package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Exceptions.OutOfBoundException;
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
     *
     * @param index         is the island index where the inhibition card will be placed
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) throws OutOfBoundException {
        checkInputs(index);

        getIslandInteraction().removeInhibitionCard();
        getIslandInteraction().getIslands().get(index).addInhibitionCard();

        return true;
    }

    /**
     * The method is called before starting the operations in useEffect. It controls if there are any issues in the input parameters.
     * Exceptions are thrown when requirements are not satisfied
     */
    private void checkInputs(int index) throws OutOfBoundException {
        if (index < 0 || index >= getIslandInteraction().getIslands().size()) {
            throw new OutOfBoundException();
        }
    }
}
