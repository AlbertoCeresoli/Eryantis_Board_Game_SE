package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.PlayerInteraction;
import it.polimi.ingsw.hasEntrance;

import java.util.HashMap;
import java.util.Map;

public class Card7 extends CharacterCards {
	private final hasEntrance hasEntrance;
	private final Map<Colors, Integer> students;

	/**
	 * Card7 constructor
	 *
	 * @param students students located on the card
	 */
	public Card7(int cost, PlayerInteraction playerInteraction, Map<Colors, Integer> students) {
		super(cost);
		this.hasEntrance = playerInteraction;
		this.students = new HashMap<>();

		for (Colors c : Colors.values()) {
			students.put(c, 0);
		}

		for (Colors c : Colors.values()) {
			this.students.put(c, students.get(c));
		}
	}

	/**
	 * The method removes from the card the students chosen by player and add them to player's entrance
	 * <p>
	 * The player can choose a maximum of 3 students
	 *
	 * @param variables     is the player index
	 * @param studentColor  not used
	 * @param studentArray1 students on the card chosen by the player
	 * @param studentArray2 students in the entrance chosen by the player
	 */
	@Override
	public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
		//removing students from the entrance of the player
		hasEntrance.removeFromEntrance(variables.get(Indexes.PLAYER_INDEX), studentArray2);

		//removing students from the card
		for (Colors c : Colors.values()) {
			this.students.put(c, this.students.get(c) - studentArray1.get(c));
		}

		//adding students that have been removed from the entrance to the card
		for (Colors c : Colors.values()) {
			this.students.put(c, this.students.get(c) + studentArray2.get(c));
		}

		//adding students that have been removed from the card to the entrance of the player
		hasEntrance.addToEntrance(variables.get(Indexes.PLAYER_INDEX), studentArray1);

		return true;
	}

	public Map<Colors, Integer> getStudents() {
		return students;
	}
}