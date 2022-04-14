package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Island;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Card8EffectTest {
    Influence card8Effect;
    Island island;
    Map<Colors, Integer> teachers;

    @BeforeEach
    void setup() {
        card8Effect = new Card8Effect(0);
        island = new Island();
        teachers = new HashMap<>();
    }

    @Test
    void normalEffectCalculateInfluenceTest() {
        Map<Colors, Integer> students = new HashMap<>();
        students.put(Colors.YELLOW, 1);
        students.put(Colors.BLUE, 1);
        students.put(Colors.GREEN, 3);
        students.put(Colors.RED, 1);
        students.put(Colors.PINK, 1);
        island.addStudents(students);

        teachers.put(Colors.YELLOW, 0);
        teachers.put(Colors.BLUE, 0);
        teachers.put(Colors.GREEN, 1);
        teachers.put(Colors.RED, 1);
        teachers.put(Colors.PINK, 0);

        ArrayList<Integer> influences = card8Effect.calculateInfluence(teachers, island, 2);

        assertEquals(5, (int) influences.get(0));
        assertEquals(4, (int) influences.get(1));
    }
}
