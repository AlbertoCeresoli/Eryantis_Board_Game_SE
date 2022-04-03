package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Cards.Card7;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card7Test {
    Card7 card7;

    @BeforeEach
    void setup() {
        PlayerInteraction playerInteraction = new PlayerInteraction(3);
        playerInteraction.getPlayer(0).getBoard().addToEntrance(new int[]{1, 3, 2, 1, 2});
        card7 = new Card7(1, playerInteraction, new int[]{1, 1, 2, 1, 1});
    }

    /**
     * The test verifies a simple working case, where student are available and are correctly exchanged
     */
    @Test
    void useEffectTest() throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
        int index = 0;
        int[] students1 = new int[]{0, 1, 1, 1, 0};
        int[] students2 = new int[]{1, 2, 0, 0, 0};

        int[] oldCardStudents = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(card7.getStudents(), 0, oldCardStudents, 0, Constants.NUMBER_OF_STUDENTS_COLOR);
        int[] oldEntranceStudents = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(card7.getPlayerInteraction().getPlayer(index).getBoard().getStudEntrance(), 0, oldEntranceStudents, 0, Constants.NUMBER_OF_STUDENTS_COLOR);

        boolean result = card7.useEffect(index, 0, students1, students2);

        int[] newCardStudents = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(card7.getStudents(), 0, newCardStudents, 0, Constants.NUMBER_OF_STUDENTS_COLOR);
        int[] newEntranceStudents = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(card7.getPlayerInteraction().getPlayer(index).getBoard().getStudEntrance(), 0, newEntranceStudents, 0, Constants.NUMBER_OF_STUDENTS_COLOR);

        boolean check = true;

        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (newCardStudents[i] != oldCardStudents[i] - students1[i] + students2[i]) {
                check = false;
                break;
            }

            if (newEntranceStudents[i] != oldEntranceStudents[i] - students2[i] + students1[i]) {
                check = false;
                break;
            }
        }

        assertTrue(result);
        assertTrue(check);
    }

    /**
     * The test verifies a StudentNotAvailableException is thrown when we want to pick a student that is not on the card
     */
    @Test
    void useEffectStudentExceptionTest1() {
        int index = 0;
        int[] students1 = new int[]{2, 0, 0, 0, 1};
        int[] students2 = new int[]{0, 2, 1, 0, 0};

        assertThrows(StudentNotAvailableException.class, () -> card7.useEffect(index, 0, students1, students2));
    }

    /**
     * The test verifies a StudentNotAvailableException is thrown when we want to pick a student that is not in the entrance
     */
    @Test
    void useEffectStudentExceptionTest2() {
        int index = 0;
        int[] students1 = new int[]{1, 1, 0, 0, 1};
        int[] students2 = new int[]{2, 1, 0, 0, 0};

        assertThrows(StudentNotAvailableException.class, () -> card7.useEffect(index, 0, students1, students2));
    }

    /**
     * The test verifies that an OutOfBoundException is thrown when we try to access to a player using the index parameter given
     * that is minor than 0 or higher than players arraylist' size
     */
    @Test
    void useEffectOutOfBoundExceptionTest() {
        int index = -1;
        int[] students1 = new int[]{1, 1, 1, 0, 0};
        int[] students2 = new int[]{0, 0, 0, 1, 2};

        assertThrows(OutOfBoundException.class, () -> card7.useEffect(index, 0, students1, students2));
    }

    /**
     * The test verifies a WrongArrayException is thrown when the input is an array with length different from Constants.NUMBER_OF_STUDENT_COLOR
     */
    @Test
    void useEffectWrongArrayLengthTest() {
        int index = 0;
        int[] students1 = new int[]{0, 0, 1, 0, 0, 0};
        int[] students2 = new int[]{0, 1, 1, 1, 0};

        assertThrows(WrongArrayException.class, () -> card7.useEffect(index, 0, students1, students2));
    }

    /**
     * The test verifies a WrongArrayException is thrown when there is a negative element
     */
    @Test
    void useEffectWrongArrayNegativeContentTest() {
        int index = 0;
        int[] students1 = new int[]{0, -1, 1, 1, 0};
        int[] students2 = new int[]{0, 1, 1, 1, 0};

        assertThrows(WrongArrayException.class, () -> card7.useEffect(index, 0, students1, students2));
    }

    /**
     * The test verifies a WrongArrayException when the number od students we want to move from the card is different from the entrance's one
     */
    @Test
    void useEffectWrongArrayContentTest() {
        int index = 0;
        int[] students1 = new int[]{0, 1, 1, 1, 0};
        int[] students2 = new int[]{0, 1, 1, 0, 0};

        assertThrows(WrongArrayException.class, () -> card7.useEffect(index, 0, students1, students2));
    }
}