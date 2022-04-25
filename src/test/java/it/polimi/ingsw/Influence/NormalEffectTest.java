package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Island;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NormalEffectTest {
    Influence normalEffect;
    Island island;
    Map<Colors, Integer> teachers;

    @BeforeEach
    void setup() {
        //initializing
        normalEffect = new NormalEffect();
        island = new Island();
        teachers = new HashMap<>();
    }

    @Test
    void normalEffectCalculateInfluenceTest() {
        //creating students and adding them to the island
        Map<Colors, Integer> students = new HashMap<>();
        students.put(Colors.YELLOW, 1);
        students.put(Colors.BLUE, 1);
        students.put(Colors.GREEN, 3);
        students.put(Colors.RED, 1);
        students.put(Colors.PINK, 1);
        island.addStudents(students);

        //initializing teachers' table for the test
        teachers.put(Colors.YELLOW, 0);
        teachers.put(Colors.BLUE, 0);
        teachers.put(Colors.GREEN, 1);
        teachers.put(Colors.RED, 1);
        teachers.put(Colors.PINK, 0);

        //calculating influence
        ArrayList<Integer> influences = normalEffect.calculateInfluence(teachers, island, 2);

        assertEquals(3, (int) influences.get(0));
        assertEquals(4, (int) influences.get(1));
    }

    @Test
    void normalEffectCalculateInfluenceWithTowersTest() {
        //creating students and adding them to the island
        Map<Colors, Integer> students = new HashMap<>();
        students.put(Colors.YELLOW, 1);
        students.put(Colors.BLUE, 1);
        students.put(Colors.GREEN, 3);
        students.put(Colors.RED, 1);
        students.put(Colors.PINK, 1);
        island.addStudents(students);

        //adding towers to the island, which are controlled by player 0
        int towers = 2;
        island.addTower(0, towers);

        //initializing teachers' table for the test
        teachers.put(Colors.YELLOW, 0);
        teachers.put(Colors.BLUE, 0);
        teachers.put(Colors.GREEN, 1);
        teachers.put(Colors.RED, 1);
        teachers.put(Colors.PINK, 0);

        //calculating influence
        ArrayList<Integer> influences = normalEffect.calculateInfluence(teachers, island, 2);

        assertEquals(5, (int) influences.get(0));
        assertEquals(4, (int) influences.get(1));
    }
}
