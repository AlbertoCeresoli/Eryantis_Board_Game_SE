package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("simple cases")
    void testFixHand(){
        int [][] assistantsNow = {{2,1,2,2,2,2,2,2,2,2}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {1, 1, 2, 2, 3, 3, 4, 4, 5, 5}};
        assertTrue(player.fixHand(1), "card played");
        assertArrayEquals(assistantsNow, player.getAssistants(), "card played correctly");
    }

    @Test
    @DisplayName("case with an already played card")
    void testFixHandCardAlreadyPlayed(){
        int [][] assistantsNow = {{2,1,2,2,2,2,2,2,2,2}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {1, 1, 2, 2, 3, 3, 4, 4, 5, 5}};
        assertTrue(player.fixHand(1), "card played");
        assertArrayEquals(assistantsNow, player.getAssistants(), "card played correctly");
        assertFalse(player.fixHand(1), "card already played");
        assertArrayEquals(assistantsNow, player.getAssistants(), "card played correctly");
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
}
