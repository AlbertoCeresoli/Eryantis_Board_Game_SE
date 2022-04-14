package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.BagNClouds;
import it.polimi.ingsw.Cards.Card1;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import it.polimi.ingsw.Island;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Card1Test {
	Card1 card1;
	IslandInteraction islandInteraction;

	@BeforeEach
	void setup() {
		//initializing islandInteraction, an island with its students
		islandInteraction = new IslandInteraction(6, 3);
		Island island = new Island();
		Map<Colors, Integer> students = new HashMap<>();
		students.put(Colors.YELLOW, 0);
		students.put(Colors.BLUE, 1);
		students.put(Colors.GREEN, 2);
		students.put(Colors.RED, 2);
		students.put(Colors.PINK, 1);
		island.addStudents(students);
		islandInteraction.getIslands().add(island);

		//initializing bagNClouds and drawing students that will be on the card
		BagNClouds bagNClouds = new BagNClouds(3);
		bagNClouds.fillBag(10);
		Map<Colors, Integer> temp = new HashMap<>();
		temp.put(Colors.YELLOW, 0);
		temp.put(Colors.BLUE, 1);
		temp.put(Colors.GREEN, 1);
		temp.put(Colors.RED, 1);
		temp.put(Colors.PINK, 1);

		//crating the card
		card1 = new Card1(1, islandInteraction, bagNClouds, temp);
	}

	/**
	 * The test controls the state of students on the chosen island before and after the method.
	 * The array has to change only in one index, increasing the content by one
	 */

	@Test
	void useEffectTest() throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
		Map<Indexes, Integer> variables = new HashMap<>();
		variables.put(Indexes.ISLAND_INDEX, 0);

		//creating students that will be picked up from the card and put to the chosen island
		Map<Colors, Integer> students = new HashMap<>();
		students.put(Colors.YELLOW, 0);
		students.put(Colors.BLUE, 1);
		students.put(Colors.GREEN, 0);
		students.put(Colors.RED, 0);
		students.put(Colors.PINK, 0);

		//saving the old state of the island
		Map<Colors, Integer> oldIslandStudents = new HashMap<>();
		Island island = islandInteraction.getIslands().get(0);
		for (Colors c : Colors.values()) {
			oldIslandStudents.put(c, island.getStudents().get(c));
		}

		//using card1's effect
		card1.useEffect(variables, Colors.YELLOW, students, null);

		//saving the new state of the island
		Map<Colors, Integer> newIslandStudents = new HashMap<>();
		for (Colors c : Colors.values()) {
			newIslandStudents.put(c, island.getStudents().get(c));
		}

		boolean check = true;

		//checking if displacement was correctly done
		for (Colors c : Colors.values()) {
			if (oldIslandStudents.get(c) + students.get(c) != newIslandStudents.get(c)) {
				check = false;
				break;
			}
		}

		assertTrue(check);
	}


}