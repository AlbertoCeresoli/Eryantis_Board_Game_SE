package it.polimi.ingsw;

import it.polimi.ingsw.Exceptions.EndGameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IslandInteractionTest {

    IslandInteraction islandInteraction;

    @BeforeEach
    void setUp(){
        islandInteraction = new IslandInteraction(6, 3);
    }

    /**
     *  unit test for the placeTower method, every time it gets called, the control
     *  of the island changes correctly.
     * @throws EndGameException caught from the lower method "mergeIsland" when there
     * are only three islands left. the game has to end at the end of the turn
     */
    @Test
    @DisplayName("Placing Tower cases")
    void testPlaceTower() throws EndGameException {
        assertEquals(6,islandInteraction.getTowersByPlayer()[1], "Tower initialization went wrong");
        assertEquals(-1, islandInteraction.getIslands().get(4).getControllerIndex(),"Wrong initialization of ControllerIndex");
        assertTrue(islandInteraction.placeTower(2, 4),"Tower positioning gone wrong");
        assertEquals(2, islandInteraction.getIslands().get(4).getControllerIndex(),"Wrong ControllerIndex");
        assertTrue(islandInteraction.placeTower(1, 4),"Tower Switch gone wrong");
        assertEquals(1, islandInteraction.getIslands().get(4).getControllerIndex(),"Wrong ControllerIndex");
    }

    /**
     *  Before setUp:
     *  tests the correct merging of two islands in one at one time
     *  After 1st setUp:
     *  tests the correct merging of three islands into one at one time
     *  After 2nd setUp:
     *  tests the endGameException for 3 or less islands
     * @throws EndGameException if there are 3 or less islands left
     */
    @Test
    @DisplayName("Merging Islands")
    void testMergeIslands() throws EndGameException {
        assertEquals(12, islandInteraction.getIslands().size(),"initialization failed");
        assertEquals(-1, islandInteraction.getIslands().get(4).getControllerIndex(),"Wrong initialization of ControllerIndex");
        assertEquals(-1, islandInteraction.getIslands().get(5).getControllerIndex(),"Wrong initialization of ControllerIndex");
        assertTrue(islandInteraction.placeTower(2, 4),"Tower positioning gone wrong");
        assertEquals(2, islandInteraction.getIslands().get(4).getControllerIndex(),"Wrong ControllerIndex");
        assertTrue(islandInteraction.placeTower(2, 5),"Tower positioning gone wrong");
        assertEquals(11, islandInteraction.getIslands().size(),"Wrong merging");
        assertTrue(islandInteraction.placeTower(2, 3),"Tower positioning gone wrong");
        assertEquals(10, islandInteraction.getIslands().size(),"Wrong merging");

        setUp();

        assertEquals(12, islandInteraction.getIslands().size(),"initialization failed");
        assertEquals(-1, islandInteraction.getIslands().get(3).getControllerIndex(),"Wrong initialization of ControllerIndex");
        assertEquals(-1, islandInteraction.getIslands().get(4).getControllerIndex(),"Wrong initialization of ControllerIndex");
        assertEquals(-1, islandInteraction.getIslands().get(5).getControllerIndex(),"Wrong initialization of ControllerIndex");
        assertTrue(islandInteraction.placeTower(2, 3),"Tower positioning gone wrong");
        assertEquals(2, islandInteraction.getIslands().get(3).getControllerIndex(),"Wrong ControllerIndex");
        assertTrue(islandInteraction.placeTower(2, 5),"Tower positioning gone wrong");
        assertEquals(12, islandInteraction.getIslands().size(),"Wrong merging");
        assertTrue(islandInteraction.placeTower(2, 4),"Tower positioning gone wrong");
        assertEquals(10, islandInteraction.getIslands().size(),"Wrong merging");

        setUp();

        islandInteraction.getIslands().subList(3, 12).clear();
        assertEquals(3, islandInteraction.getIslands().size());
        assertThrows(
                EndGameException.class,
                () -> islandInteraction.placeTower(2,1),
                "End game exception expected");
    }

}
