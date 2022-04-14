package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Cards.Card7;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Card7Test {
	Card7 card7;
	PlayerInteraction playerInteraction;

	@BeforeEach
	void setup() {
		//initializing playerInteraction and entrance of the first player
		playerInteraction = new PlayerInteraction(3);

		Map<Colors, Integer> entrance = new HashMap<>();
		entrance.put(Colors.YELLOW, 1);
		entrance.put(Colors.BLUE, 3);
		entrance.put(Colors.GREEN, 2);
		entrance.put(Colors.RED, 1);
		entrance.put(Colors.PINK, 2);
		playerInteraction.addToEntrance(0, entrance);

		//creating students that will be put on the card
		Map<Colors, Integer> card = new HashMap<>();
		card.put(Colors.YELLOW, 1);
		card.put(Colors.BLUE, 2);
		card.put(Colors.GREEN, 1);
		card.put(Colors.RED, 1);
		card.put(Colors.PINK, 1);

		//creating the card
		card7 = new Card7(1, playerInteraction, card);
	}

	/**
	 * The test verifies a simple working case, where student are available and are correctly exchanged
	 */
	@Test
	void useEffectTest() {
		Map<Indexes, Integer> variables = new HashMap<>();
		variables.put(Indexes.PLAYER_INDEX, 0);

		//creating students that will be picked up from the entrance and put on the card
		Map<Colors, Integer> studentsEntranceToCard = new HashMap<>();
		studentsEntranceToCard.put(Colors.YELLOW, 0);
		studentsEntranceToCard.put(Colors.BLUE, 1);
		studentsEntranceToCard.put(Colors.GREEN, 1);
		studentsEntranceToCard.put(Colors.RED, 1);
		studentsEntranceToCard.put(Colors.PINK, 0);

		//creating students that will be picked up from the card and put to the entrance
		Map<Colors, Integer> studentsCardToEntrance = new HashMap<>();
		studentsCardToEntrance.put(Colors.YELLOW, 1);
		studentsCardToEntrance.put(Colors.BLUE, 2);
		studentsCardToEntrance.put(Colors.GREEN, 0);
		studentsCardToEntrance.put(Colors.RED, 0);
		studentsCardToEntrance.put(Colors.PINK, 0);

		//saving card's old state
		Map<Colors, Integer> oldCardStudents = new HashMap<>();
		for (Colors c : Colors.values()) {
			oldCardStudents.put(c, card7.getStudents().get(c));
		}

		//saving entrance's old state
		Map<Colors, Integer> oldEntranceStudents = new HashMap<>();
		for (Colors c : Colors.values()) {
			oldEntranceStudents.put(c, playerInteraction.getPlayer(variables.get(Indexes.PLAYER_INDEX)).getBoard().getStudEntrance().get(c));
		}

		//using card7's effect
		card7.useEffect(variables, Colors.YELLOW, studentsCardToEntrance, studentsEntranceToCard);


		//saving card's new state
		Map<Colors, Integer> newCardStudents = new HashMap<>();
		for (Colors c : Colors.values()) {
			newCardStudents.put(c, card7.getStudents().get(c));
		}

		//saving entrance's new state
		Map<Colors, Integer> newEntranceStudents = new HashMap<>();
		for (Colors c : Colors.values()) {
			newEntranceStudents.put(c, playerInteraction.getPlayer(variables.get(Indexes.PLAYER_INDEX)).getBoard().getStudEntrance().get(c));
		}

		boolean check = true;

		//checking if all students where moved correctly from entrance to card and vice versa
		for (Colors c : Colors.values()) {
			if (newCardStudents.get(c) != oldCardStudents.get(c) - studentsCardToEntrance.get(c) + studentsEntranceToCard.get(c)) {
				check = false;
				return;
			}

			if (newEntranceStudents.get(c) != oldEntranceStudents.get(c) - studentsEntranceToCard.get(c) + studentsCardToEntrance.get(c)) {
				check = false;
				break;
			}
		}

		assertTrue(check);
	}
}