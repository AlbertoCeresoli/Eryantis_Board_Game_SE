package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("simple cases")
    void testDrawStudents() {
        int drawnStudents = 3;
        int studentsInBag = 5;
        int[] temp;
        int[] fullBag = new int[5];
        int[] finalBag = new int[5];
        int result = 0;

        for (int i=0; i<5; i++){
            fullBag[i]=studentsInBag;
        }

        bagNClouds.fillBag(studentsInBag);

        temp = bagNClouds.drawStudents(drawnStudents);
        for (int i=0; i<bagNClouds.getBag().size(); i++){
            finalBag[bagNClouds.getBag().get(i)]++;
        }
        for (int i=0; i<5; i++){
            assertEquals(studentsInBag, finalBag[i]+temp[i], "test failed");
        }
        for (int i=0; i<5; i++){
            result += temp[i];
        }
        assertEquals(drawnStudents, result, "test failed");
    }

    @Test
    @DisplayName("n>bag.size()")
    void testOverloadDrawStudents() {
        int drawnStudents = 6;
        int studentsInBag = 1;
        int initialBagSize;
        int[] temp;
        int[] fullBag = new int[5];
        int[] finalBag = new int[5];
        int result = 0;

        for (int i=0; i<5; i++){
            fullBag[i]=studentsInBag;
        }

        bagNClouds.fillBag(studentsInBag);
        initialBagSize=bagNClouds.getBag().size();

        temp = bagNClouds.drawStudents(drawnStudents);
        for (int i=0; i<bagNClouds.getBag().size(); i++){
            finalBag[bagNClouds.getBag().get(i)]++;
        }
        for (int i=0; i<5; i++){
            assertEquals(studentsInBag, finalBag[i]+temp[i], "test failed");
        }
        for (int i=0; i<5; i++){
            result += temp[i];
        }
        assertFalse(drawnStudents == result, "test failed");
        assertTrue(result==initialBagSize, "test failed");
    }
}
