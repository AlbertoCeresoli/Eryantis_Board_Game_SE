package it.polimi.ingsw.TeacherTest;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.PlayerInteraction;
import it.polimi.ingsw.Teacher.EqualCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EqualCheckTest {
    PlayerInteraction playerInteraction;

    @BeforeEach
    void setup() {
        //initializing playerInteraction, setting TeacherInterface as EqualCheck
        playerInteraction = new PlayerInteraction(3);
        playerInteraction.setTeacherInterface(new EqualCheck());
    }

    /**
     * The test verifies that, after adding a student in a hall in order to have the same number of them in each players' hall,
     * the returned ArrayList contains the actualPlayer
     */
    @Test
    void equalCheckChangingTeacherOwnerTest() {
        //creating halls for each player
        Map<Colors, Integer> hall1 = new HashMap<>();
        hall1.put(Colors.YELLOW, 2);
        hall1.put(Colors.BLUE, 0);
        hall1.put(Colors.GREEN, 2);
        hall1.put(Colors.RED, 2);
        hall1.put(Colors.PINK, 1);
        playerInteraction.addToHall(0, hall1);
        Map<Colors, Integer> hall2 = new HashMap<>();
        hall2.put(Colors.YELLOW, 0);
        hall2.put(Colors.BLUE, 1);
        hall2.put(Colors.GREEN, 2);
        hall2.put(Colors.RED, 1);
        hall2.put(Colors.PINK, 0);
        playerInteraction.addToHall(1, hall2);
        Map<Colors, Integer> hall3 = new HashMap<>();
        hall3.put(Colors.YELLOW, 3);
        hall3.put(Colors.BLUE, 1);
        hall3.put(Colors.GREEN, 1);
        hall3.put(Colors.RED, 2);
        hall3.put(Colors.PINK, 1);
        playerInteraction.addToHall(2, hall3);

        //student that will be added to the hall of a player in order to change the controller of the teacher, using EqualCheck
        Map<Colors, Integer> studentToHall = new HashMap<>();
        studentToHall.put(Colors.YELLOW, 0);
        studentToHall.put(Colors.BLUE, 0);
        studentToHall.put(Colors.GREEN, 1);
        studentToHall.put(Colors.RED, 0);
        studentToHall.put(Colors.PINK, 0);
        playerInteraction.addToHall(2, studentToHall);

        //using checkTeacher of EqualCheck, expecting that the result is not empty and that contains the actual player
        ArrayList<Integer> result = playerInteraction.checkTeacher(Colors.GREEN, 2);

        assertTrue(result.size() == 1 && result.get(0) == 2);
    }

    /**
     * The test verifies that no change is needed after placing a student into the hall
     */
    @Test
    void equalCheckNOTChangingTeacherOwnerTest() {
        //creating halls for each player
        Map<Colors, Integer> hall1 = new HashMap<>();
        hall1.put(Colors.YELLOW, 2);
        hall1.put(Colors.BLUE, 0);
        hall1.put(Colors.GREEN, 2);
        hall1.put(Colors.RED, 2);
        hall1.put(Colors.PINK, 1);
        playerInteraction.addToHall(0, hall1);
        Map<Colors, Integer> hall2 = new HashMap<>();
        hall2.put(Colors.YELLOW, 0);
        hall2.put(Colors.BLUE, 1);
        hall2.put(Colors.GREEN, 2);
        hall2.put(Colors.RED, 1);
        hall2.put(Colors.PINK, 0);
        playerInteraction.addToHall(1, hall2);
        Map<Colors, Integer> hall3 = new HashMap<>();
        hall3.put(Colors.YELLOW, 3);
        hall3.put(Colors.BLUE, 1);
        hall3.put(Colors.GREEN, 1);
        hall3.put(Colors.RED, 2);
        hall3.put(Colors.PINK, 1);
        playerInteraction.addToHall(2, hall3);

        //student that will be added to the hall of a player in order to change the controller of the teacher, using EqualCheck
        Map<Colors, Integer> studentToHall = new HashMap<>();
        studentToHall.put(Colors.YELLOW, 1);
        studentToHall.put(Colors.BLUE, 0);
        studentToHall.put(Colors.GREEN, 0);
        studentToHall.put(Colors.RED, 0);
        studentToHall.put(Colors.PINK, 0);
        playerInteraction.addToHall(1, studentToHall);

        //using checkTeacher of EqualCheck, expecting the result is empty
        ArrayList<Integer> result = playerInteraction.checkTeacher(Colors.YELLOW, 1);

        assertEquals(0, result.size());
    }
}
