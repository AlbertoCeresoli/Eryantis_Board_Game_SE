package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ControllerTest {
    Controller controller;

    @BeforeEach
    void setUp(){
        controller = new Controller(2, false);
    }

    /**
     * selectAssistantCard Tests
     */
    @Test
    @DisplayName("simple cases of SelectAssistantCard")
    void testSelectAssistantCard(){
        int[] playedCards = {-1,-1};
        String s = "2";

        assertEquals(2, controller.selectAssistantCard(0,playedCards, s));
    }

    @Test
    @DisplayName("Test another player has already played this card in this round")
    void testAnotherPlayerPlayedTheAC(){
        int[] playedCards = {2, -1};
        String s = "2";

        assertEquals(-1, controller.selectAssistantCard(0,playedCards, s));
    }

    @Test
    @DisplayName("Test you have already played this card")
    void testYouAlreadyPlayedTheAC(){
        int[] playedCards = {-1,-1};
        int[] Cards = {2, 3};
        String s = "2";

        controller.getModel().getPlayerInteraction().playAssistantCard(Cards);
        assertEquals(-1, controller.selectAssistantCard(0,playedCards, s));
    }

    @Test
    @DisplayName("Not valid assistant card value")
    void testSelectACNotValidValue(){
        int[] playedCards = {-1,-1};
        String s = "12";

        assertEquals(-1, controller.selectAssistantCard(0,playedCards, s));
    }

    @Test
    @DisplayName("Not valid input")
    void testSelectACNotValidInput(){
        int[] playedCards = {-1,-1};
        String s = "a";

        assertEquals(-1, controller.selectAssistantCard(0,playedCards, s));
    }

}
