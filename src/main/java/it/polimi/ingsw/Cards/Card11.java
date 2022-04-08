package it.polimi.ingsw.Cards;

import it.polimi.ingsw.BagNClouds;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;
import java.util.HashMap;
import java.util.Map;

public class Card11 extends CharCardsPlayer {
    BagNClouds bagNClouds;
    Map<Colors, Integer> students;
    private static final int capacity = Constants.CARD11_STUDENTS_CAPACITY;

    /**
     * Card1 constructor
     *
     * @param bagNClouds bagNClouds reference used to draw students in useEffect
     * @param students   students located on the card
     */
    public Card11(int cost, PlayerInteraction playerInteraction, BagNClouds bagNClouds, Map<Colors, Integer> students) {
        super(cost, playerInteraction);
        this.bagNClouds = bagNClouds;
        this.students = new HashMap<>();

        this.students.forEach((key,value)-> value = students.get(key));
    }

     /**
     * The method removes a student from the card and place it in the player's hall,
     * then draws a new student from the bag and put it on the card
     *
     * @param index         is player index that takes the student to the hall
     * @param studentColor  not used
     * @param studentArray1 student that will be removed from the card and will be put in the hall
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
        checkInputs(index, studentArray1);

        for (Colors c: Colors.values()) {
            students.put(c, students.get(c)- studentArray1.get(c));
        }

        Player player = getPlayerInteraction().getPlayer(index);
        boolean check = player.getBoard().addToHall(studentArray1);
        if (!check) {
            return false;
        }

        Map<Colors, Integer> temp = bagNClouds.drawStudents(Constants.CARD11_STUDENTS_TO_MOVE);

        for (Colors c: Colors.values()) {
            students.put(c, students.get(c)+temp.get(c));
        }

        return true;
    }

    /**
     * The method is called before starting the operations in useEffect. It controls if there are any issues in the input parameters.
     * Exceptions are thrown when requirements are not satisfied
     */
    private void checkInputs(int index, Map<Colors, Integer> studentArray1) throws OutOfBoundException, WrongArrayException, StudentNotAvailableException {
        if (index < 0 || index >= getPlayerInteraction().getPlayers().size()) {
            throw new OutOfBoundException();
        }

        if (studentArray1.size() != Constants.NUMBER_OF_STUDENTS_COLOR) {
            throw new WrongArrayException();
        }

        for (Colors c: Colors.values()) {
            if (students.get(c) - studentArray1.get(c) < 0) {
                throw new StudentNotAvailableException();
            }
        }

        int sum = 0;
        for (Colors c: Colors.values()) {
            if (studentArray1.get(c) < 0) {
                throw new WrongArrayException();
            }
            sum += studentArray1.get(c);
        }
        if (sum != Constants.CARD11_STUDENTS_TO_MOVE) {
            throw new WrongArrayException();
        }
    }

    public Map<Colors, Integer> getStudents() {
        return students;
    }
}
