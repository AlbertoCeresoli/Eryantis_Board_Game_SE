package it.polimi.ingsw;


import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Model.Player.PlayerInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
    Unit test for PlayerInteraction class. Tests:
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
        int[] V0 = {1,3,4,3,1};
        int[] V1 = {0,4,2,3,1};
        //int[] player2 = {5,3,1,7,9};
        int i=0;
        Map<Colors, Integer> player0 = new HashMap<>();
        Map<Colors, Integer> player1 = new HashMap<>();
        //Map<Colors, Integer> player2 = new HashMap<>();
        for (Colors c : Colors.values()){
            player0.put(c, V0[i]);
            player1.put(c, V1[i]);
            //player2.put(c, V2[i]);
            i++;
        }
        ArrayList<Integer> expectedArray;
        expectedArray = new ArrayList<>();
        expectedArray.add(1);

        playerInteraction.getPlayer(0).getBoard().addToHall(player0);
        playerInteraction.getPlayer(1).getBoard().addToHall(player1);
        //playerInteraction.getPlayer(2).getBoard().addToHall(player2);

        assertEquals(expectedArray, playerInteraction.checkTeacher(Colors.BLUE, 1), "test failed");
    }

    @Test
    @DisplayName("simple cases")
    void testPlayAssistantCard(){
        int[] cards = new int[nPlayers];
        int[] playerOrder = new int[nPlayers];
        int[] temp = new int[nPlayers];
        if (nPlayers==2){
            cards[0]=9;
            cards[1]=4;
            playerOrder[0]=1;
            playerOrder[1]=0;
        }
        else if (nPlayers==3){
            cards[0]=3;
            cards[1]=4;
            cards[2]=9;
            playerOrder[0]=0;
            playerOrder[1]=1;
            playerOrder[2]=2;
        }

        assertArrayEquals(playerOrder, playerInteraction.playAssistantCard(cards));
    }
}
