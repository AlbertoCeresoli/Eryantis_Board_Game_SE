package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.BagNClouds;
import it.polimi.ingsw.Cards.Card1;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import it.polimi.ingsw.Island;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Card1Test {
    Card1 card1;

    /*@BeforeEach
    void setup() {
        IslandInteraction islandInteraction = new IslandInteraction(6, 3);
        Island island = new Island();
        island.addStudents(new int[]{0, 1, 2, 2, 1});
        islandInteraction.getIslands().add(island);
        BagNClouds bagNClouds = new BagNClouds(3);
        bagNClouds.fillBag(10);
        Map<Colors, Integer> temp;
        card1 = new Card1(1, islandInteraction, bagNClouds, temp);
    }

    /**
     * The test controls the state of students on the chosen island before and after the method.
     * The array has to change only in one index, increasing the content by one
     */

    /*@Test
    void useEffectTest() throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
        int index = 0;
        int[] students = new int[]{0, 1, 0, 0, 0};

        int[] oldIslandStudents = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(card1.getIslandInteraction().getIslands().get(index).getStudents(), 0, oldIslandStudents, 0, Constants.NUMBER_OF_STUDENTS_COLOR);

        card1.useEffect(index, 0, students, null);

        int[] newIslandStudents = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(card1.getIslandInteraction().getIslands().get(index).getStudents(), 0, newIslandStudents, 0, Constants.NUMBER_OF_STUDENTS_COLOR);

        boolean check = true;

        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (oldIslandStudents[i] + students[i] != newIslandStudents[i]) {
                check = false;
                break;
            }
        }

        assertTrue(check);
    }

    /**
     * The test verifies that a StudentNotAvailableException is thrown.
     * It is thrown because we are looking for a student that is not on the card
     */
    /*@Test
    void useEffectStudentExceptionTest() {
        int index = 0;
        int[] students = new int[]{0, 0, 0, 0, 1};

        assertThrows(StudentNotAvailableException.class, () -> card1.useEffect(index, 0, students, null));
    }

    /**
     * The test verifies that an OutOfBoundException is thrown when we try to access to an island using the index parameter given
     * that is minor than 0 or higher than islands arraylist' size
     */
    /*@Test
    void useEffectOutOfBoundExceptionTest() {
        int index = -1;
        int[] students = new int[]{0, 0, 1, 0, 0};

        assertThrows(OutOfBoundException.class, () -> card1.useEffect(index, 0, students, null));
    }

    /**
     * The test verifies that a WrongArrayException is thrown when it is passed as input an array which has
     * array.length() != Constants.NUMBER_OF_STUDENTS_COLOR,
     */
    /*@Test
    void useEffectWrongArrayLengthTest() {
        int index = 0;
        int[] students = new int[]{0, 0, 1, 0, 0, 0};

        assertThrows(WrongArrayException.class, () -> card1.useEffect(index, 0, students, null));
    }

    /**
     * The test verifies a WrongArrayException is thrown when one of the array's element is negative
     */
    /*@Test
    void useEffectWrongArrayNegativeContentTest() {
        int index = 0;
        int[] students = new int[]{0, -1, 1, 1, 0};

        assertThrows(WrongArrayException.class, () -> card1.useEffect(index, 0, students, null));
    }

    /**
     * The test verifies a WrongArrayException is thrown when the sum of requested students is different from the number indicated in the rules
     */
    /*@Test
    void useEffectWrongArrayContentTest() {
        int index = 0;
        int[] students = new int[]{0, 0, 1, 1, 0};

        assertThrows(WrongArrayException.class, () -> card1.useEffect(index, 0, students, null));
    }
     */
}
