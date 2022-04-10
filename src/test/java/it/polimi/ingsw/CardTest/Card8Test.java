package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card8;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Influence.Card8Effect;
import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card8Test {
	Card8 card8;
	IslandInteraction islandInteraction;

	@BeforeEach
	void setup() {
		islandInteraction = new IslandInteraction(6, 3);
		card8 = new Card8(1, islandInteraction);
	}

	/**
	 * The test verifies that in islandInteraction the reference Influence is an instance of Card8Effect,
	 * that will modify basic calculateInfluence method
	 */
	@Test
	void useEffectTest() {
		card8.useEffect(0, Colors.YELLOW, null, null);
		Influence influence = islandInteraction.getInfluence();

		assertTrue(influence instanceof Card8Effect);
	}
}
