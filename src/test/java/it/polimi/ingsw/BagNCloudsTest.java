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
    void setUp(){
        bagNClouds = new BagNClouds(numPlayers);
    }

    @Test
    @DisplayName("simple cases")
    void testDrawStudents(){

    }
}
