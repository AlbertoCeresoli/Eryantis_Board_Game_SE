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
    @DisplayName("simple cases drawStudents")
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
    @DisplayName("n>bag.size() in drawStudents")
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

    @Test
    @DisplayName("simple cases in FillBag")
    void testFillBag() {
        int n=1;

        bagNClouds.fillBag(n);

        assertEquals(n, bagNClouds.getBag().stream().filter(x -> x==0).count(), "test failed");
        assertEquals(n, bagNClouds.getBag().stream().filter(x -> x==1).count(), "test failed");
        assertEquals(n, bagNClouds.getBag().stream().filter(x -> x==2).count(), "test failed");
        assertEquals(n, bagNClouds.getBag().stream().filter(x -> x==3).count(), "test failed");
        assertEquals(n, bagNClouds.getBag().stream().filter(x -> x==4).count(), "test failed");
    }

    @Test
    @DisplayName("simple cases in ResetCloud")
    void testResetCloud() {
        int[] emptyCloud = {0,0,0,0,0};

        bagNClouds.fillBag(7);
        bagNClouds.studentsBagToCloud();
        for (int i=0; i<numPlayers; i++) {
            for (int j=0; j<5; j++){
                System.out.print(bagNClouds.getCloud(i)[j]);
            }
            System.out.println();
        }

        bagNClouds.resetCloud(0);
        assertArrayEquals(emptyCloud, bagNClouds.getCloud(0), "test failed");
        assertNotSame(emptyCloud, bagNClouds.getCloud(1), "test failed");
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