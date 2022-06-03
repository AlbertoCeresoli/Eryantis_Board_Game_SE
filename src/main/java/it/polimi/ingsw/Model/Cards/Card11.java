package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Cards;
import it.polimi.ingsw.Model.BagNClouds.BagNClouds;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Model.BagNClouds.hasDrawStudents;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import it.polimi.ingsw.Model.Island.hasSetTeacher;
import it.polimi.ingsw.Model.Player.PlayerInteraction;
import it.polimi.ingsw.Model.Player.hasHall;
import it.polimi.ingsw.Model.Player.hasCheckTeacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Card11 extends CharacterCards {
    private final hasHall hasHall;
    private final hasCheckTeacher hasCheckTeacher;
    private final hasSetTeacher hasSetTeacher;
    private final hasDrawStudents hasDrawStudents;

    /**
     * Card11 constructor
     *
     * @param bagNClouds bagNClouds reference used to draw students in useEffect
     * @param students   students located on the card
     */
    public Card11(int cost, PlayerInteraction playerInteraction, IslandInteraction islandInteraction, BagNClouds bagNClouds, Map<Colors, Integer> students) {
        super(cost);

        this.name = Cards.SPOILED_PRINCESS.getName();
        this.cardIndex = 11;

        this.students = students;

        this.hasHall = playerInteraction;
        this.hasCheckTeacher = playerInteraction;
        this.hasSetTeacher = islandInteraction;
        this.hasDrawStudents = bagNClouds;
    }

    /**
     * The method removes a student from the card and place it in the player's hall,
     * then draws a new student from the bag and put it on the card
     *
     * @param variables     is player index that takes the student to the hall
     * @param studentColor  student that will be removed from the card and will be put in the hall
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor,
                             Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //removing from card's students the chosen one
        this.students.put(studentColor, this.students.get(studentColor) - studentArray1.get(studentColor));

        //creating a map for that student that will be used from addToHall
        Map<Colors, Integer> temp = new HashMap<>();
        for (Colors c : Colors.values()) {
            temp.put(c, 0);
        }
        temp.put(studentColor, 1);

        //adding the student to the hall
        this.hasHall.addToHall(variables.get(Indexes.PLAYER_INDEX), temp);

        //checking if there is a new teacher controller
        ArrayList<Integer> player = this.hasCheckTeacher.checkTeacher(studentColor, variables.get(Indexes.PLAYER_INDEX));
        if (player.size() == 1) {
            this.hasSetTeacher.setTeacher(player.get(0), studentColor);
        }

        //drawing students to refill the card
        temp = hasDrawStudents.drawStudents(Constants.SPOILED_PRINCESS_STUDENTS_TO_MOVE);

        //filling the card
        for (Colors c : Colors.values()) {
            this.students.put(c, this.students.get(c) + temp.get(c));
        }

        return true;
    }
}
