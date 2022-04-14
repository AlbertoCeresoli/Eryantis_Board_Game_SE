package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card8;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Influence.Card8Effect;
import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Card8Test {
	Card8 card8;
	IslandInteraction islandInteraction;

	@BeforeEach
	void setup() {
		//initializing islandInteraction
		islandInteraction = new IslandInteraction(6, 3);

		//creating the card
		card8 = new Card8(1, islandInteraction);
	}

	/**
	 * The test verifies that in islandInteraction the reference of Influence is an instance of Card8Effect,
	 * that will modify basic calculateInfluence method
	 */
	@Test
	void useEffectTest() {
		Map<Indexes, Integer> variables = new HashMap<>();
		variables.put(Indexes.PLAYER_INDEX, 0);
		//using card8's effect
		card8.useEffect(variables, Colors.YELLOW, null, null);

		//controlling that the set was correctly done
		Influence influence = islandInteraction.getInfluence();
		assertTrue(influence instanceof Card8Effect);
	}
}
