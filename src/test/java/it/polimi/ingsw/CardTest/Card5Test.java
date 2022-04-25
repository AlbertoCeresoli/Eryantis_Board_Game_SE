package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card5;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Card5Test {
    Card5 card5;
    IslandInteraction islandInteraction;
    int islandIndex = 0;

    @BeforeEach
    void setup() {
        //initializing islandInteraction
        islandInteraction = new IslandInteraction(6, 3);

        //creating the card
        card5 = new Card5(1, islandInteraction);
    }

    /**
     * The test verifies that the inhibition card is correctly removed from the available ones and added to the chosen island
     */
    @Test
    void useEffectTest() throws OutOfBoundException {
        Map<Indexes, Integer> variables = new HashMap<>();
        variables.put(Indexes.ISLAND_INDEX, islandIndex);

        //saving old state of card and island
        int oldCardLeft = islandInteraction.getNumberOfInhibitionCards();
        int oldIslandCards = islandInteraction.getIslands().get(variables.get(Indexes.ISLAND_INDEX)).getInhibitionCards();

        //using card5's effect
        card5.useEffect(variables, Colors.YELLOW, null, null);

        //saving new state of card and island
        int newCardLeft = islandInteraction.getNumberOfInhibitionCards();
        int newIslandCards = islandInteraction.getIslands().get(variables.get(Indexes.ISLAND_INDEX)).getInhibitionCards();

        //controlling if inhibition card was correctly removed from islandInteraction and added to the chosen island
        boolean check = newCardLeft == oldCardLeft - 1 && newIslandCards == oldIslandCards + 1;

        assertTrue(check);
    }
}
