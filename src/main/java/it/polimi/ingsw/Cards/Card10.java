package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

public class Card10 extends CharCardsPlayer {
    /**
     * Card10 constructor
     */
    public Card10(int cost, PlayerInteraction playerInteraction) {
        super(cost, playerInteraction);
    }

    /**
     * The method removes students saved in studentArray1 from the entrance,
     * removes students saved in studentArray2 from the hall,
     * adds studentArray1 to the hall,
     * adds studentArray2 to the entrance
     * <p>
     * A maximum of 2 students can be chosen.
     *  @param index         is the player index that will exchange the students
     * @param studentColor  not used
	 * @param studentArray1 students from entrance
	 * @param studentArray2 students from hall
	 */
    @Override
    public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {
        Player player = getPlayerInteraction().getPlayer(index);

        player.getBoard().removeStudent(studentArray1);
        player.getBoard().removeFromHall(studentArray2);
        player.getBoard().addToEntrance(studentArray2);
        player.getBoard().addToHall(studentArray1);
    }
}
