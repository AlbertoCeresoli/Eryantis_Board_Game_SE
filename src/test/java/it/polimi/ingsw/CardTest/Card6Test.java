package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card6;
import it.polimi.ingsw.Influence.Card6Effect;
import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card6Test {
    Card6 card6;

    /*@BeforeEach
    void setup() {
        IslandInteraction islandInteraction = new IslandInteraction(6, 3);
        card6 = new Card6(1, islandInteraction);
    }

    /**
     * The test verifies that in islandInteraction the interface reference Influence is an instance of Card6Effect,
     * that will modify basic calculateInfluence method
     */
    /*@Test
    void useEffectTest() {
        card6.useEffect(0, 0, null, null);
        Influence influence = card6.getIslandInteraction().getInfluence();

        assertTrue(influence instanceof Card6Effect);
    }
     */
}
