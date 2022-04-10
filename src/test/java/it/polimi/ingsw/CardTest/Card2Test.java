package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card2;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.PlayerInteraction;
import it.polimi.ingsw.Teacher.EqualCheck;
import it.polimi.ingsw.Teacher.TeacherInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Card2Test {
	Card2 card2;
	PlayerInteraction playerInteraction;

	@BeforeEach
	void setup() {
		//initializing playerInteraction
		playerInteraction = new PlayerInteraction(3);

		//creating the card
		card2 = new Card2(1, playerInteraction);
	}

	/**
	 * The test verifies that useEffect correctly sets the reference of TeacherInterface to EqualCheck
	 */
	@Test
	void useEffectTest() {
		//using card2's effect
		card2.useEffect(0, Colors.YELLOW, null, null);

		//controlling that the set was correctly done
		TeacherInterface teacherInterface = playerInteraction.getTeacherInterface();
		assertTrue(teacherInterface instanceof EqualCheck);
	}
}
