package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

public class Card7 extends CharCardsPlayer {
    private static final int capacity = Constants.CARD7_STUDENTS_CAPACITY;
    private int[] students;

    /**
     * Card7 constructor
     *
     * @param students students located on the card
     */
    public Card7(int cost, PlayerInteraction playerInteraction, int[] students) {
        super(cost, playerInteraction);
        this.students = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(students, 0, this.students, 0, Constants.NUMBER_OF_STUDENTS_COLOR);
    }

    /**
     * The method removes from the array the students chosen by player and add them to the player's entrance
     * <p>
     * The player can choose a maximum of 3 students
     *
     * @param index         is the player index
     * @param studentColor  not used
     * @param studentArray1 students on the card chosen by the player
     * @param studentArray2 students in the entrance chosen by the player
     */
    @Override
    public boolean useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
        checkInputs(index, studentArray1, studentArray2);

        Player player = getPlayerInteraction().getPlayer(index);
        boolean check = player.getBoard().removeStudent(studentArray2);
        if (!check)
            return false;
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            students[i] -= studentArray1[i];
            students[i] += studentArray2[i];
        }
        check = player.getBoard().addToEntrance(studentArray1);
        if (!check)
            return false;

        return true;
    }

    /**
     * The method is called before starting the operations in useEffect. It controls if there are any issues in the input parameters.
     * Exceptions are thrown when requirements are not satisfied
     */
    private void checkInputs(int index, int[] studentArray1, int[] studentArray2) throws OutOfBoundException, WrongArrayException, StudentNotAvailableException {
        if (index < 0 || index >= getPlayerInteraction().getPlayers().size()) {
            throw new OutOfBoundException();
        }

        if (studentArray1.length != Constants.NUMBER_OF_STUDENTS_COLOR || studentArray2.length != Constants.NUMBER_OF_STUDENTS_COLOR) {
            throw new WrongArrayException();
        }

        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (students[i] - studentArray1[i] < 0) {
                throw new StudentNotAvailableException();
            }
        }

        int[] entrance = getPlayerInteraction().getPlayer(index).getBoard().getStudEntrance();
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (entrance[i] < studentArray2[i]) {
                throw new StudentNotAvailableException();
            }
        }

        int sumCard = 0;
        int sumEntrance = 0;
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (studentArray1[i] < 0 || studentArray2[i] < 0) {
                throw new WrongArrayException();
            }
            sumCard += studentArray1[i];
            sumEntrance += studentArray2[i];
        }
        if (sumCard > Constants.CARD7_MAX_STUDENTS_TO_MOVE || sumEntrance > Constants.CARD7_MAX_STUDENTS_TO_MOVE || sumCard != sumEntrance) {
            throw new WrongArrayException();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int[] getStudents() {
        return students;
    }

    public void setStudents(int[] students) {
        this.students = students;
    }
}