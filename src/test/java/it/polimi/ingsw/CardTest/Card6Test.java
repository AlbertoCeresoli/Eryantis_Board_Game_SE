package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Model.Cards.Card6;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Model.Influence.Card6Effect;
import it.polimi.ingsw.Model.Influence.Influence;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card6Test {
    Card6 card6;
    IslandInteraction islandInteraction;

    @BeforeEach
    void setup() {
        //initializing islandInteraction
        islandInteraction = new IslandInteraction(6, 3);

        //creating the card
        card6 = new Card6(1, islandInteraction);
    }

    /**
     * The test verifies that in islandInteraction the interface reference Influence is an instance of Card6Effect,
     * that will modify basic calculateInfluence method
     */
    @Test
    void useEffectTest() {
        //using card6's effect
        card6.useEffect(null, Colors.YELLOW, null, null);

        //controlling that the set was correctly done
        Influence influence = islandInteraction.getInfluence();
        assertTrue(influence instanceof Card6Effect);
    }
}
