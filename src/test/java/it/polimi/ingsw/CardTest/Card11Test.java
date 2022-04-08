package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Cards.Card11;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card11Test {
    Card11 card11;

    /*@BeforeEach
    void setup() {
        PlayerInteraction playerInteraction = new PlayerInteraction(3);
        Player player = playerInteraction.getPlayer(0);
        player.getBoard().addToHall(new int[]{3, 2, 1, 3, 1});
        BagNClouds bagNClouds = new BagNClouds(3);
        bagNClouds.fillBag(10);
        card11 = new Card11(1, playerInteraction, bagNClouds, new int[]{0, 1, 1, 1, 1});
    }

    /**
     * The test controls the state of students on the chosen island before and after the method.
     * The array has to change only in one index, increasing the content by one
     */
    /*@Test
    void useEffectTest() throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
        int index = 0;
        int[] students = new int[]{0, 1, 0, 0, 0};

        int[] oldHallStudents = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(card11.getPlayerInteraction().getPlayer(index).getBoard().getStudHall(), 0, oldHallStudents, 0, Constants.NUMBER_OF_STUDENTS_COLOR);

        boolean result = card11.useEffect(index, 0, students, null);

        int[] newHallStudents = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(card11.getPlayerInteraction().getPlayer(index).getBoard().getStudHall(), 0, newHallStudents, 0, Constants.NUMBER_OF_STUDENTS_COLOR);

        boolean check = true;

        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (newHallStudents[i] != oldHallStudents[i] + students[i]) {
                check = false;
                break;
            }
        }

        assertTrue(result);
        assertTrue(check);
    }

    /**
     * The test verifies that a StudentNotAvailableException is thrown.
     * It is thrown because we are looking for a student that is not on the card
     */
    /*@Test
    void useEffectStudentExceptionTest() {
        int index = 0;
        int[] students = new int[]{1, 0, 0, 0, 0};

        assertThrows(StudentNotAvailableException.class, () -> card11.useEffect(index, 0, students, null));
    }

    /**
     * The test verifies that an OutOfBoundException is thrown when we try to access to an island using the index parameter given
     * that is minor than 0 or higher than islands arraylist' size
     */
    /*@Test
    void useEffectOutOfBoundExceptionTest() {
        int index = 5;
        int[] students = new int[]{0, 0, 1, 0, 0};

        assertThrows(OutOfBoundException.class, () -> card11.useEffect(index, 0, students, null));
    }

    /**
     * The test verifies that a WrongArrayException is thrown when it is passed as input an array which has
     * array.length() != Constants.NUMBER_OF_STUDENTS_COLOR,
     */
    /*@Test
    void useEffectWrongArrayLengthTest() {
        int index = 0;
        int[] students = new int[]{0, 0, 1, 0, 0, 0};

        assertThrows(WrongArrayException.class, () -> card11.useEffect(index, 0, students, null));
    }

    /**
     * The test verifies a WrongArrayException is thrown when one of the array's element is negative
     */
    /*@Test
    void useEffectWrongArrayNegativeContentTest() {
        int index = 0;
        int[] students = new int[]{0, -1, 1, 1, 0};

        assertThrows(WrongArrayException.class, () -> card11.useEffect(index, 0, students, null));
    }

    /**
     * The test verifies a WrongArrayException is thrown when the sum of requested students is different from the number indicated in the rules
     */
    /*@Test
    void useEffectWrongArrayContentTest() {
        int index = 0;
        int[] students = new int[]{0, 0, 1, 1, 0};

        assertThrows(WrongArrayException.class, () -> card11.useEffect(index, 0, students, null));
    }
     */
}
