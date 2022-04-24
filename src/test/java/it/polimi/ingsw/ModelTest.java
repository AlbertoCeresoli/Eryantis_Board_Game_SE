package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Exceptions.EndGameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    Model model;

    @BeforeEach
    void setUp(){
        model = new Model(new int[]{3, 9, 6, 0, 1});
    }

    @Test
    @DisplayName("Testing correct Initialization")
    void testInitializeGame(){
        assertArrayEquals(new int[]{3, 9, 6, 0, 1}, model.gameRules, "Failed initialization for gamerules param");
        assertTrue(model.initializeGame(),"Game Initialization failed");
    }

    @Test
    @DisplayName("Test Entrance to Hall")
    void testMoveFromEntranceTotHall(){
        assertTrue(model.initializeGame(),"Game Initialization failed");
        assertTrue(model.moveFromEntranceToHall(Colors.RED, 2), "Action failed");
        assertTrue(model.moveFromEntranceToHall(Colors.RED, 2), "Action failed");
        assertTrue(model.moveFromEntranceToHall(Colors.RED, 2), "Action failed");
        assertEquals(3, model.getPlayerInteraction().getPlayer(2).getBoard().getStudHall().get(Colors.RED),"Action Failed Miserably");
        assertEquals(1+1  , model.getPlayerInteraction().getPlayer(2).getCoins(), "Coin added Incorrectly");
        assertTrue(model.moveFromEntranceToHall(Colors.BLUE, 2), "Action failed");
        assertEquals(1, model.getPlayerInteraction().getPlayer(2).getBoard().getStudHall().get(Colors.BLUE), "action gone wrong");
        assertEquals(2, model.getIslandInteraction().getTeachers().get(Colors.RED));
        assertTrue(model.moveFromEntranceToHall(Colors.BLUE, 1), "Action failed");
        assertTrue(model.moveFromEntranceToHall(Colors.BLUE, 1), "Action failed");
        assertEquals(1, model.getIslandInteraction().getTeachers().get(Colors.BLUE), "Control hasn't changed");

    }


    @Test
    @DisplayName("Test Entrance to Island")
    void testMoveFromEntranceToIsland(){
        assertTrue(model.moveFromEntranceToIsland(Colors.RED, 0, 11), "Students shift went wrong");
        assertEquals(1, model.getIslandInteraction().getIslands().get(11).getStudents().get(Colors.RED),"Student wasn't received");
    }


    @Test
    @DisplayName("Testing Cloud to Entrance")
    void testStudentsFromCloudToEntrance(){
        assertTrue(model.initializeGame(),"Game Initialization failed");
        model.getBagNClouds().studentsBagToCloud();
        Map<Colors, Integer> empty = new HashMap<>();
        for (Colors c : Colors.values()){
            empty.put(c, 0);
        }
        for(Colors c : Colors.values()) {
            model.getPlayerInteraction().getPlayer(0).getBoard().getStudEntrance().put(c, 0);
        }
        Map<Colors, Integer> expected = new HashMap<>();
        for (Colors c : Colors.values()){
            expected.put(c, model.getBagNClouds().getClouds().get(1).get(c));
        }

        assertTrue(model.studentsCloudToEntrance(0, 1), "Action Failed");
        assertEquals(empty, model.getBagNClouds().getClouds().get(1), "Cloud wasn't reset");
        assertEquals(expected, model.getPlayerInteraction().getPlayer(0).getBoard().getStudEntrance(), "Entrance wrong");
    }


    @Test
    @DisplayName("Testing Move Mother Nature")
    void testMoveMN() throws EndGameException {
        assertTrue(model.initializeGame(),"Game Initialization failed");
        int MN = model.getIslandInteraction().getMotherNature();
        assertTrue(model.moveMN(4), "MN shift failed");
        MN = (MN + 4) % model.getIslandInteraction().getIslands().size();
        assertEquals(MN, model.getIslandInteraction().getMotherNature(), "MN moved badly");
        assertTrue(model.moveMN(10), "MN shift failed");
        MN = (MN + 10) % model.getIslandInteraction().getIslands().size();
        assertEquals(MN, model.getIslandInteraction().getMotherNature(), "MN moved badly");
        model.getIslandInteraction().getIslands().remove(1);
        assertTrue(model.moveMN(10), "MN shift failed");
        MN = (MN + 10) % model.getIslandInteraction().getIslands().size();
        assertEquals(MN, model.getIslandInteraction().getMotherNature(), "MN moved badly");
        MN = (MN + 1) % model.getIslandInteraction().getIslands().size();
        model.getIslandInteraction().getIslands().get(MN).addInhibitionCard();
        assertEquals(1, model.getIslandInteraction().getIslands().get((MN + 1) % model.getIslandInteraction().getIslands().size()).getInhibitionCards(),"IC added badly");
        assertTrue(model.moveMN(1),"MN shift failed");

        assertEquals(0, model.getIslandInteraction().getIslands().get(MN).getInhibitionCards(), "IC removed badly");
    }

    @Test
    @DisplayName("Testing end game")
    void testEndGame(){
        assertTrue(model.initializeGame(),"Game Initialization failed");
        assertFalse(model.endGame(), "Cards shouldn't already been used");
        model.getBagNClouds().getBag().clear();
        assertTrue(model.endGame(), "Bag should be empty");
        setUp();
        model.initializeGame();
        for(int j = 0; j < model.getPlayerInteraction().getPlayers().size(); j++) {
            for (int i = 0; i < model.getPlayerInteraction().getPlayer(j).getAssistants().size(); i++) {
                model.getPlayerInteraction().getPlayer(j).getAssistants().get(i).setCardState(0);
            }
        }
        assertTrue(model.endGame(), "Player 1's assistants should all be used");
    }

    @Test
    @DisplayName("Testing Get Winner")
    void testGetWinner(){
        assertTrue(model.initializeGame(),"Game Initialization failed");
        model.getIslandInteraction().getTeachers().put(Colors.BLUE,1);
        model.getIslandInteraction().getTeachers().put(Colors.RED,1);
        model.getIslandInteraction().getTeachers().put(Colors.PINK,2);
        model.getIslandInteraction().getTeachers().put(Colors.GREEN,2);
        model.getIslandInteraction().getTeachers().put(Colors.YELLOW,0);
        model.getIslandInteraction().getTowersByPlayer()[0] = 6;
        model.getIslandInteraction().getTowersByPlayer()[1] = 5;
        model.getIslandInteraction().getTowersByPlayer()[2] = 5;
        assertEquals(-1, model.getWinner(), "should be tied");

        setUp();
        assertTrue(model.initializeGame(),"Game Initialization failed");
        model.getIslandInteraction().getTeachers().put(Colors.BLUE,1);
        model.getIslandInteraction().getTeachers().put(Colors.RED,1);
        model.getIslandInteraction().getTeachers().put(Colors.PINK,2);
        model.getIslandInteraction().getTeachers().put(Colors.GREEN,2);
        model.getIslandInteraction().getTeachers().put(Colors.YELLOW,0);
        model.getIslandInteraction().getTowersByPlayer()[0] = 6;
        model.getIslandInteraction().getTowersByPlayer()[1] = 5;
        model.getIslandInteraction().getTowersByPlayer()[2] = 4;
        assertEquals(2, model.getWinner(), "2 should win for towers");

        setUp();
        assertTrue(model.initializeGame(),"Game Initialization failed");
        model.getIslandInteraction().getTeachers().put(Colors.BLUE,1);
        model.getIslandInteraction().getTeachers().put(Colors.RED,2);
        model.getIslandInteraction().getTeachers().put(Colors.PINK,2);
        model.getIslandInteraction().getTeachers().put(Colors.GREEN,2);
        model.getIslandInteraction().getTeachers().put(Colors.YELLOW,0);
        model.getIslandInteraction().getTowersByPlayer()[0] = 6;
        model.getIslandInteraction().getTowersByPlayer()[1] = 5;
        model.getIslandInteraction().getTowersByPlayer()[2] = 5;
        assertEquals(2, model.getWinner(), "2 should win for teachers");
    }


}
