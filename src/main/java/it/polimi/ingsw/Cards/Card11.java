package it.polimi.ingsw.Cards;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Card11 extends CharacterCards {
    private final hasHall hasHall;
    private final hasCheckTeacher hasCheckTeacher;
    private final hasSetTeacher hasSetTeacher;
    private final Map<Colors, Integer> students;
    private final BagNClouds bagNClouds;

    /**
     * Card11 constructor
     *
     * @param bagNClouds bagNClouds reference used to draw students in useEffect
     * @param students   students located on the card
     */
    public Card11(int cost, PlayerInteraction playerInteraction, IslandInteraction islandInteraction, BagNClouds bagNClouds, Map<Colors, Integer> students) {
        super(cost);
        this.hasHall = playerInteraction;
        this.hasCheckTeacher = playerInteraction;
        this.hasSetTeacher = islandInteraction;
        this.bagNClouds = bagNClouds;

        this.students = new HashMap<>();
        for (Colors c : Colors.values()) {
            this.students.put(c, 0);
        }

        for (Colors c : Colors.values()) {
            this.students.put(c, students.get(c));
        }
    }

    /**
     * The method removes a student from the card and place it in the player's hall,
     * then draws a new student from the bag and put it on the card
     *
     * @param index         is player index that takes the student to the hall
     * @param studentColor  student that will be removed from the card and will be put in the hall
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //removing from card's students the chosen one
        this.students.put(studentColor, this.students.get(studentColor) - studentArray1.get(studentColor));

        //creating a map for that student that will be used from addToHall
        Map<Colors, Integer> temp = new HashMap<>();
        for (Colors c : Colors.values()) {
            temp.put(c, 0);
        }
        temp.put(studentColor, 1);

        //adding the student to the hall
        this.hasHall.addToHall(index, temp);

        //checking if there is a new teacher controller
        ArrayList<Integer> player = this.hasCheckTeacher.checkTeacher(studentColor, index);
        if (player.size() == 1) {
            this.hasSetTeacher.setTeacher(player.get(0), studentColor);
        }

        //drawing students to refill the card
        temp = bagNClouds.drawStudents(Constants.CARD11_STUDENTS_TO_MOVE);

        //filling the card
        for (Colors c : Colors.values()) {
            this.students.put(c, this.students.get(c) + temp.get(c));
        }

        return true;
    }

    public Map<Colors, Integer> getStudents() {
        return students;
    }
}
