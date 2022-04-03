package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants;
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
     *  @param index         is the player index
     * @param studentColor  not used
	 * @param studentArray1 students chosen by the player
	 * @param studentArray2 not used
     */
    @Override
    public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            students[i] -= studentArray1[i];
            Player player = getPlayerInteraction().getPlayer(index);
            player.getBoard().addToEntrance(studentArray1);
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