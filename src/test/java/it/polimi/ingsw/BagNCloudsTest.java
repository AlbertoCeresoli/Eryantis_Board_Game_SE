package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 Unit test for BagNClouds class. Tests:
 - drawStudents
 - fillBag
 - resetCloud
 */

public class BagNCloudsTest {
    BagNClouds bagNClouds;
    int numPlayers = 2;

    @BeforeEach
    void setUp() {
        bagNClouds = new BagNClouds(numPlayers);
    }

    @Test
    @DisplayName("simple cases drawStudents")
    void testDrawStudents() {
        int drawnStudents = 3;
        int studentsInBag = 5;
        int[] fullBag = new int[5];
        Map<Colors,Integer> finalBag = new HashMap<>();
        for (Colors c : Colors.values()){
            finalBag.put(c, 0);
        }
        Map<Colors,Integer> temp;
        int result = 0;

        for (int i=0; i<5; i++){
            fullBag[i]=studentsInBag;
        }
        bagNClouds.fillBag(studentsInBag);

        temp = bagNClouds.drawStudents(drawnStudents);
        for (int i=0; i<bagNClouds.getBag().size(); i++){
            finalBag.put(bagNClouds.getBag().get(i), finalBag.get(bagNClouds.getBag().get(i))+1);
        }
        //verify that the students caught + students left in the bag equals studentsInBag
        for (Colors c: Colors.values()){
            assertEquals(studentsInBag, finalBag.get(c)+temp.get(c), "test failed");
        }

        //verify that the number of students caught equals the number of students to be caught
        for (Colors c: Colors.values()){
            result += temp.get(c);
        }
        assertEquals(drawnStudents, result, "test failed");
    }

    @Test
    @DisplayName("n>bag.size() in drawStudents")
    void testOverloadDrawStudents() {
        int drawnStudents = 6;
        int studentsInBag = 1;
        int initialBagSize;
        int[] fullBag = new int[5];
        Map<Colors,Integer> finalBag = new HashMap<>();
        for (Colors c : Colors.values()){
            finalBag.put(c, 0);
        }
        Map<Colors,Integer> temp;
        int result = 0;

        for (int i=0; i<5; i++){
            fullBag[i]=studentsInBag;
        }
        bagNClouds.fillBag(studentsInBag);
        initialBagSize= bagNClouds.getBag().size();

        temp = bagNClouds.drawStudents(drawnStudents);
        for (int i=0; i<bagNClouds.getBag().size(); i++){
            finalBag.put(bagNClouds.getBag().get(i), finalBag.get(bagNClouds.getBag().get(i))+1);
        }
        //verify that the students caught + students left in the bag equals studentsInBag
        for (Colors c: Colors.values()){
            assertEquals(studentsInBag, finalBag.get(c)+temp.get(c), "test failed");
        }

        //verify that the number of students caught equals the number of students that was in the bag
        for (Colors c: Colors.values()){
            result += temp.get(c);
        }
        assertFalse(drawnStudents == result, "test failed");
        assertTrue(result==initialBagSize, "test failed");
    }

    @Test
    @DisplayName("simple cases in FillBag")
    void testFillBag() {
        int n=5;
        Map<Colors, Integer> bag = new HashMap<>();
        for (Colors c : Colors.values()){
            bag.put(c, 0);
        }

        bagNClouds.fillBag(n);

        bagNClouds.getBag().stream().forEach((color)-> bag.put(color, bag.get(color)+1));

        for (Colors c: Colors.values()){
            assertEquals(n, bag.get(c), "test failed");
        }
    }

    @Test
    @DisplayName("simple cases in ResetCloud")
    void testResetCloud() {
        Map<Colors, Integer> emptyCloud = new HashMap<>();
        for (Colors c: Colors.values()){
            emptyCloud.put(c,0);
        }
        final int[] cloud0 = {0,0,0,0,0};

        bagNClouds.fillBag(7);
        bagNClouds.studentsBagToCloud();

        //controls that the clouds aren't empty and reset only the first cloud
        assertNotSame(emptyCloud, bagNClouds.getClouds().get(1), "test failed");
        assertNotSame(emptyCloud, bagNClouds.getClouds().get(1), "test failed");
        bagNClouds.resetCloud(0);

        //controls that the first cloud is empty but the second one no
        for (Colors c: Colors.values()) {
            assertEquals(emptyCloud.get(c), bagNClouds.getClouds().get(0).get(c), "test failed");
        }
        assertNotSame(emptyCloud, bagNClouds.getClouds().get(1), "test failed");
    }

    @Test
    @DisplayName("simple cases in isEmpty")
    void testIsEmpty() {
        int n=1;
        bagNClouds.fillBag(n);
        assertFalse(bagNClouds.isEmpty(), "test failed");
        bagNClouds.drawStudents(5*n);
        assertTrue(bagNClouds.isEmpty(), "test failed");
    }

}