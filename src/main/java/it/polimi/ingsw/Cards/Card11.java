package it.polimi.ingsw.Cards;

import it.polimi.ingsw.BagNClouds;
import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

public class Card11 extends CharCardsPlayer {
    BagNClouds bagNClouds;
    int[] students;
    private static final int capacity = Constants.CARD11_STUDENTS_CAPACITY;

    /**
     * Card1 constructor
     *
     * @param bagNClouds bagNClouds reference used to draw students in useEffect
     * @param students   students located on the card
     */
    public Card11(int cost, PlayerInteraction playerInteraction, BagNClouds bagNClouds, int[] students) {
        super(cost, playerInteraction);
        this.bagNClouds = bagNClouds;
        this.students = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        System.arraycopy(students, 0, this.students, 0, Constants.NUMBER_OF_STUDENTS_COLOR);
    }

    /**
     * The method removes a student from the card and place it in the player's hall,
     * then draws a new student from the bag and put it on the card
     *  @param index         is player index that takes the student to the hall
     * @param studentColor  not used
	 * @param studentArray1 student that will be removed from the card and will be put in the hall
	 * @param studentArray2 not used
     */
    @Override
    public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            students[i] -= studentArray1[i];
        }

        Player player = getPlayerInteraction().getPlayer(index);
        player.getBoard().addToHall(studentArray1);
        int numberStudentsToDraw = 1;
        int[] temp = bagNClouds.drawStudents(numberStudentsToDraw);

        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            students[i] += temp[i];
        }
    }
}
