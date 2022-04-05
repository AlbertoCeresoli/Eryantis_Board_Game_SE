package it.polimi.ingsw;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
    Unit test for Board class. Tests:
    - checkTeacher
    - playAssistantCard
 */
public class PlayerInteractionTest {
    PlayerInteraction playerInteraction;
    static int nPlayers = 2;


    @BeforeEach
    void setUp(){
        playerInteraction = new PlayerInteraction(nPlayers);
    }

    @Test
    @DisplayName("simple cases normal check")
    void testCheckTeacher(){
        int[] player0 = {1,3,4,3,1};
        int[] player1 = {0,4,2,3,1};
        //int[] player3 = {5,3,1,7,9};
        ArrayList<Integer> expectedArray;
        expectedArray = new ArrayList<>();
        expectedArray.add(1);

        playerInteraction.getPlayer(0).getBoard().addToHall(player0);
        playerInteraction.getPlayer(1).getBoard().addToHall(player1);
        //playerInteraction.getPlayer(2).getBoard().addToHall(player2);

        System.out.println(expectedArray.get(0) + " " +  playerInteraction.checkTeacher(1, 1).size());
        assertEquals(expectedArray, playerInteraction.checkTeacher(1, 1), "test failed");
    }

    @Test
    @DisplayName("simple cases")
    void testPlayAssistantCard(){

    }
}
