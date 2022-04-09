package it.polimi.ingsw.Cards;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;

import java.util.HashMap;
import java.util.Map;


public class Card1 extends CharCardsIslands {
    private BagNClouds bagNClouds;
    private Map<Colors, Integer> students;
    private static final int capacity = Constants.CARD1_STUDENTS_CAPACITY;

    /**
     * Card1 constructor
     *
     * @param bagNClouds bagNClouds reference used to draw students in useEffect
     * @param students   students located on the card
     */
    public Card1(int cost, IslandInteraction islandInteraction, BagNClouds bagNClouds, Map<Colors, Integer> students) {
        super(cost, islandInteraction);
        this.bagNClouds = bagNClouds;
        for (Colors c : Colors.values()){
            students.put(c, 0);
        }
        this.students.forEach((key,value)-> value = students.get(key));

    }

    /**
     * The method removes the student in input, put it on the chosen island
     * and substitute it with a new student drawn from the bag
     *
     * @param index         island's index where the student will be placed
     * @param studentColor  not used
     * @param studentArray1 student that will be removed from the card and will be put on the chosen island
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws OutOfBoundException, StudentNotAvailableException, WrongArrayException {
        checkInputs(index, studentArray1);

        for (Colors c: Colors.values()) {
            students.put(c, students.get(c)-studentArray1.get(c));
        }

        this.getIslandInteraction().getIslands().get(index).addStudents(studentArray1);
        Map<Colors, Integer> temp = bagNClouds.drawStudents(1);
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
        if (index < 0 || index >= getIslandInteraction().getIslands().size()) {
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
        if (sum != Constants.CARD1_STUDENTS_TO_MOVE) {
            throw new WrongArrayException();
        }
    }

    public BagNClouds getBagNClouds() {
        return bagNClouds;
    }

    public Map<Colors, Integer> getStudents() {
        return students;
    }

    public void setBagNClouds(BagNClouds bagNClouds) {
        this.bagNClouds = bagNClouds;
    }

    public void setStudents(Map<Colors, Integer> students) {
        this.students.forEach((key, value)-> value = students.get(key));
    }
}
