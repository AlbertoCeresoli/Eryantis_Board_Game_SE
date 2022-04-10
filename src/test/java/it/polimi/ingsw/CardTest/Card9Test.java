package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card9;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Influence.Card9Effect;
import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card9Test {
	Card9 card9;
	IslandInteraction islandInteraction;

	@BeforeEach
	void setup() {
		islandInteraction = new IslandInteraction(6, 3);
		card9 = new Card9(1, islandInteraction);
	}

	/**
	 * The test verifies that in islandInteraction the interface reference Influence is an instance of Card9Effect,
	 * that will modify basic calculateInfluence method
	 */
	@Test
	void useEffectTest() {
		card9.useEffect(0, Colors.YELLOW, null, null);
		Influence influence = islandInteraction.getInfluence();

		assertTrue(influence instanceof Card9Effect);
	}
}
