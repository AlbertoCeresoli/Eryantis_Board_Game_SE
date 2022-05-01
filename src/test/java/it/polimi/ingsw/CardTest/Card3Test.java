package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Model.Cards.Card3;
import it.polimi.ingsw.Model.Island.IslandInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Card3Test {
    Card3 card3;
    IslandInteraction islandInteraction;
    int islandIndex = 0;
    int playerIndex = 1;
    int numberOfPlayers = 2;

    @BeforeEach
    void setup() {
        //initializing islandInteraction
        islandInteraction = new IslandInteraction(8, numberOfPlayers);

        //creating the card
        card3 = new Card3(1, islandInteraction);

        //setting teachers' table for the test
        islandInteraction.setTeacher(playerIndex, Colors.BLUE);

        //adding students to an island in order to correctly
        Map<Colors, Integer> students = new HashMap<>();
        students.put(Colors.YELLOW, 0);
        students.put(Colors.BLUE, 1);
        students.put(Colors.GREEN, 0);
        students.put(Colors.RED, 0);
        students.put(Colors.PINK, 0);
        islandInteraction.getIslands().get(islandIndex).addStudents(students);
    }

    @Test
    void useEffectTest() throws EndGameException {
        Map<Indexes, Integer> variables = new HashMap<>();
        variables.put(Indexes.ISLAND_INDEX, islandIndex);
        variables.put(Indexes.NUMBER_OF_PLAYERS, numberOfPlayers);

        card3.useEffect(variables, Colors.YELLOW, null, null);

        int newController = islandInteraction.getIslands().get(variables.get(Indexes.ISLAND_INDEX)).getControllerIndex();
        int towersIsland = islandInteraction.getIslands().get(variables.get(Indexes.ISLAND_INDEX)).getnTowers();
        int towersPlayer = islandInteraction.getTowersByPlayer()[playerIndex];

        assertEquals(1, newController);
        assertEquals(1, towersIsland);
        assertEquals(7, towersPlayer);
    }
}