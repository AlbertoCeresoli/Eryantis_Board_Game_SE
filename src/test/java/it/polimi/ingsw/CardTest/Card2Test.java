package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card2;
import it.polimi.ingsw.PlayerInteraction;
import it.polimi.ingsw.Teacher.EqualCheck;
import it.polimi.ingsw.Teacher.TeacherInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card2Test {
    Card2 card2;

    /*@BeforeEach
    void setup() {
        PlayerInteraction playerInteraction = new PlayerInteraction(3);
        card2 = new Card2(1, playerInteraction);
    }

    /**
     * The test verifies that useEffect correctly sets the reference of TeacherInterface to EqualCheck
     */
    /*@Test
    void useEffectTest() {
        card2.useEffect(0, 0, null, null);
        TeacherInterface teacherInterface = card2.getPlayerInteraction().getTeacherInterface();
        assertTrue(teacherInterface instanceof EqualCheck);
    }

     */
}
