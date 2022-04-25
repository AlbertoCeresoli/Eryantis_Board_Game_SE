package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card4;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.PlayerInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Card4Test {
    Card4 card4;
    PlayerInteraction playerInteraction;
    int playerIndex = 0;
    int assistantIndex = 2;

    @BeforeEach
    void setup() {
        //initializing playerInteraction
        playerInteraction = new PlayerInteraction(2);

        //creating the card
        card4 = new Card4(1, playerInteraction);

        //setting a card as the last used one
        playerInteraction.getPlayer(playerIndex).fixHand(assistantIndex);
    }

    /**
     * The test verifies that number of steps permitted by last used assistant card for mother nature's movement
     * is correctly updated according to character card description
     */
    @Test
    void useEffectTest() {
        //saving old number of steps that the card let to MN to do
        int oldAssistantSteps = playerInteraction.getPlayer(playerIndex).getAssistants().get(assistantIndex).getSteps();

        //setting useful variables for useEffect
        Map<Indexes, Integer> variables = new HashMap<>();
        variables.put(Indexes.PLAYER_INDEX, playerIndex);

        //using card4's effect
        card4.useEffect(variables, Colors.YELLOW, null, null);

        //saving new number of steps after change
        int newAssistantSteps = playerInteraction.getPlayer(playerIndex).getAssistants().get(assistantIndex).getSteps();

        assertEquals(oldAssistantSteps + Constants.CARD4_ADDITION_MOVEMENT, newAssistantSteps);
    }
}
