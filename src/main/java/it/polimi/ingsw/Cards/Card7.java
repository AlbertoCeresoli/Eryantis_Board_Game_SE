package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

import java.util.Map;

public class Card7 extends CharCardsPlayer {
    private static final int capacity = Constants.CARD7_STUDENTS_CAPACITY;
    private Map<Colors, Integer> students;

    /**
     * Card7 constructor
     *
     * @param students students located on the card
     */
    public Card7(int cost, PlayerInteraction playerInteraction, Map<Colors, Integer> students) {
        super(cost, playerInteraction);
        for (Colors c : Colors.values()){
            students.put(c, 0);
        }
        this.students.forEach((key,value)-> value = students.get(key));
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
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
        checkInputs(index, studentArray1, studentArray2);

        Player player = getPlayerInteraction().getPlayer(index);
        boolean check = player.getBoard().removeFromEntrance(studentArray2);
        if (!check)
            return false;
        for (Colors c: Colors.values()) {
            students.put(c, students.get(c)-studentArray1.get(c));
            students.put(c, students.get(c)+studentArray1.get(c));
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
    private void checkInputs(int index, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws OutOfBoundException, WrongArrayException, StudentNotAvailableException {
        if (index < 0 || index >= getPlayerInteraction().getPlayers().size()) {
            throw new OutOfBoundException();
        }

        if (studentArray1.size() != Constants.NUMBER_OF_STUDENTS_COLOR || studentArray2.size() != Constants.NUMBER_OF_STUDENTS_COLOR) {
            throw new WrongArrayException();
        }

        for (Colors c: Colors.values()) {
            if (students.get(c) - studentArray1.get(c) < 0) {
                throw new StudentNotAvailableException();
            }
        }

        Map<Colors, Integer> entrance = getPlayerInteraction().getPlayer(index).getBoard().getStudEntrance();
        for (Colors c: Colors.values()) {
            if (entrance.get(c) < studentArray2.get(c)) {
                throw new StudentNotAvailableException();
            }
        }

        int sumCard = 0;
        int sumEntrance = 0;
        for (Colors c: Colors.values()) {
            if (studentArray1.get(c) < 0 || studentArray2.get(c) < 0) {
                throw new WrongArrayException();
            }
            sumCard += studentArray1.get(c);
            sumEntrance += studentArray2.get(c);
        }
        if (sumCard > Constants.CARD7_MAX_STUDENTS_TO_MOVE || sumEntrance > Constants.CARD7_MAX_STUDENTS_TO_MOVE || sumCard != sumEntrance) {
            throw new WrongArrayException();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<Colors, Integer> getStudents() {
        return students;
    }

    public void setStudents(Map<Colors, Integer> students) {
        this.students.forEach((key, value)-> value = students.get(key));
    }
}