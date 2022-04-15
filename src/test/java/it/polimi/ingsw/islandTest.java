package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class islandTest {

    /**
     * island Class' tests don't need any particular edge cases
     * cause all possible exceptions will be caught at an upper stage
     * add students method neither, since there aren't any limits
     * to the number of students on an island
     * (you can't remove students from the islands)
     */
    Island island;
    PlayerInteraction playerInteraction;

    @BeforeEach
    void setUp(){
        island = new Island();
        playerInteraction = new PlayerInteraction(2);
    }

    @Test
    @DisplayName("Standard tower building")
    void testAddTower(){
        assertTrue(island.addTower(1,1), "Tower addition failed");
        assertEquals(1, island.getnTowers(), "Tower added incorrectly");
        assertEquals(1, island.getControllerIndex(), "Controller assigned incorrectly");
        assertTrue(island.addTower(2,2), "Tower addition failed");
        assertEquals(2, island.getnTowers(), "Tower added incorrectly");
        assertEquals(2, island.getControllerIndex(), "Controller assigned incorrectly");
    }

    @Test
    @DisplayName("Standard tower removal")
    void testRemoveTower(){
        assertTrue(island.removeTower(), "Tower removal failed");
        assertEquals(0, island.getnTowers(), "Tower removed incorrectly");
    }

    @Test
    @DisplayName("Inhibition Card standard put/removal")
    void testAddRemoveInhibitionCard(){
        island.addInhibitionCard();
        island.addInhibitionCard();
        island.removeInhibitionCard();
        assertEquals(1, island.getInhibitionCards() , "Inhibition Card removed incorrectly");
    }

    @Test
    @DisplayName("Standard students addition")
    void testAddStudents(){
        Map<Colors, Integer> finalStudents = new HashMap<>();
        finalStudents.put(Colors.PINK, 0);
        finalStudents.put(Colors.YELLOW, 0);
        finalStudents.put(Colors.GREEN, 3);
        finalStudents.put(Colors.BLUE, 2);
        finalStudents.put(Colors.RED, 1);

        island.addStudents(finalStudents);

        assertEquals(finalStudents, island.students, "Students addition failed");
    }
}
