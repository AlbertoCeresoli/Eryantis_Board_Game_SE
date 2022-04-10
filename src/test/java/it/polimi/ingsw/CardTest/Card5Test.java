package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card5;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Island;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card5Test {
	Card5 card5;
	IslandInteraction islandInteraction;

	@BeforeEach
	void setup() {
		//initializing islandInteraction
		islandInteraction = new IslandInteraction(6, 3);
		islandInteraction.getIslands().add(new Island());

		//creating the card
		card5 = new Card5(1, islandInteraction);
	}

	/**
	 * The test verifies that the inhibition card is correctly removed from the available ones and added to the chosen island
	 */
	@Test
	void useEffectTest() throws OutOfBoundException {
		int index = 0;

		//saving old state of card and island
		int oldCardLeft = islandInteraction.getNumberOfInhibitionCards();
		int oldIslandCards = islandInteraction.getIslands().get(index).getInhibitionCards();

		//using card5's effect
		card5.useEffect(0, Colors.YELLOW, null, null);

		//saving new state of card and island
		int newCardLeft = islandInteraction.getNumberOfInhibitionCards();
		int newIslandCards = islandInteraction.getIslands().get(index).getInhibitionCards();

		//controlling if inhibition card was correctly removed from islandInteraction and added to the chosen island
		boolean check = newCardLeft == oldCardLeft - 1 && newIslandCards == oldIslandCards + 1;

		assertTrue(check);
	}
}
