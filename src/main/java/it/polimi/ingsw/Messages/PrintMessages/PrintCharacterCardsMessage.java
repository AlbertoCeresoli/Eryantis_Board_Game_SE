package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Model.Cards.CharacterCards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrintCharacterCardsMessage implements PrintMessage{
	private final String[] names;
	private final int[] costs;
	private final String[] effects;
	private final boolean[] areThereStudentsOnTheCard;
	private final ArrayList<Map<Colors, Integer>> students;

	public PrintCharacterCardsMessage(CharacterCards[] cards) {
		this.names = new String[cards.length];
		this.costs = new int[cards.length];
		this.effects = new String[cards.length];
		this.areThereStudentsOnTheCard = new boolean[cards.length];
		this.students = new ArrayList<>();

		for (int i = 0; i < cards.length; i++) {
			this.names[i] = cards[i].getName();
			this.costs[i] = cards[i].getCost();
			this.effects[i] = cards[i].getEffect();

			if (cards[i].getStudents() == null) {
				areThereStudentsOnTheCard[i] = false;

			}
			else {
				areThereStudentsOnTheCard[i] = true;

				Map<Colors, Integer> studs = new HashMap<>();

				for (Colors c : Colors.values()) {
					studs.put(c, cards[i].getStudents().get(c));
				}

				students.add(studs);
			}
		}
	}

	public String[] getNames() {
		return names;
	}

	public int[] getCosts() {
		return costs;
	}

	public String[] getEffects() {
		return effects;
	}

	public boolean[] getAreThereStudentsOnTheCard() {
		return areThereStudentsOnTheCard;
	}

	public ArrayList<Map<Colors, Integer>> getStudents() {
		return students;
	}
}
