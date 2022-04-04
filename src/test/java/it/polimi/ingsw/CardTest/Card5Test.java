package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card5;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Island;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card5Test {
    Card5 card5;

    @BeforeEach
    void setup() {
        IslandInteraction islandInteraction = new IslandInteraction(6, 3);
        islandInteraction.getIslands().add(new Island());
        card5 = new Card5(1, islandInteraction);
    }

    /**
     * The test verifies that the inhibition card is correctly removed from the available ones and added to the chosen island
     */
    @Test
    void useEffectTest() throws OutOfBoundException {
        int index = 0;
        int oldCardLeft = card5.getIslandInteraction().getNumberOfInhibitionCards();
        int oldIslandCards = card5.getIslandInteraction().getIslands().get(index).getInhibitionCards();

        card5.useEffect(0, 0, null, null);

        int newCardLeft = card5.getIslandInteraction().getNumberOfInhibitionCards();
        int newIslandCards = card5.getIslandInteraction().getIslands().get(index).getInhibitionCards();

        boolean check = newCardLeft == oldCardLeft - 1 && newIslandCards == oldIslandCards + 1;

        assertTrue(check);
    }

    /**
     * The test verifies that an OutOfBoundException is thrown when we try to access to an island using the index parameter given
     * that is minor than 0 or higher than islands arraylist' size
     */
    @Test
    void useEffectIndexException() {
        int index = -1;

        assertThrows(OutOfBoundException.class, () -> card5.useEffect(index, 0, null, null));
    }
}
