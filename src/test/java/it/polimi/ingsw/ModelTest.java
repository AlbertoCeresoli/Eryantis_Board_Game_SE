package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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


}
