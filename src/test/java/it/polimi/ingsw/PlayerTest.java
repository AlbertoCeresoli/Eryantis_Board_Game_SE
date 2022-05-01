package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for BagNClouds class. Tests:
 *      - fixHand
 *      - addCoin
 *      - removeCoin
 */

public class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    @DisplayName("simple cases of fixHand")
    void testFixHand(){
        int[] cardState1 = {2,1,2,2,2,2,2,2,2,2};
        int[] cardState2 = {2,0,1,2,2,2,2,2,2,2};

        assertTrue(player.fixHand(1), "card played");
        for (int i=0; i<Constants.NUMBER_OF_ASSISTANT_CARDS; i++){
            assertEquals(cardState1[i], player.getAssistants().get(i).getCardState());
        }
        assertTrue(player.fixHand(2), "card played");
        for (int i=0; i<Constants.NUMBER_OF_ASSISTANT_CARDS; i++){
            assertEquals(cardState2[i], player.getAssistants().get(i).getCardState());
        }
    }

    @Test
    @DisplayName("case with an already played card")
    void testFixHandCardAlreadyPlayed(){
        int[] cardState1 = {2,1,2,2,2,2,2,2,2,2};

        assertTrue(player.fixHand(1), "card played");
        for (int i=0; i<Constants.NUMBER_OF_ASSISTANT_CARDS; i++){
            assertEquals(cardState1[i], player.getAssistants().get(i).getCardState());
        }
        assertFalse(player.fixHand(1), "card played");
        for (int i=0; i<Constants.NUMBER_OF_ASSISTANT_CARDS; i++){
            assertEquals(cardState1[i], player.getAssistants().get(i).getCardState());
        }
    }

    @Test
    @DisplayName("simple case")
    void testRemoveCoins(){
        int testCoins=2;
        for (int i=0; i<testCoins-1; i++) {
            player.addCoin();
        }
        assertTrue(player.removeCoins(1), "coin removed");
        assertEquals(1, player.getCoins());
    }

    @Test
    @DisplayName("case with not enough coins")
    void testOverloadRemoveCoins(){
        int testCoins=4;
        for (int i=0; i<testCoins-1; i++) {
            player.addCoin();
        }
        assertTrue(player.removeCoins(1), "coin removed");
        assertEquals(3, player.getCoins(),"coins removed correctly");
        assertFalse(player.removeCoins(5), "not enough coins");
        assertEquals(3, player.getCoins(), "coins not removed");
    }

    @Test
    @DisplayName("Simple cases addCoin")
    void testAddCoin(){
        assertEquals(1, player.getCoins(), "test failed");
        player.addCoin();
        assertEquals(2, player.getCoins(), "test failed");
    }

}
