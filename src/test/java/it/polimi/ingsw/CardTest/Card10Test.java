package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card10;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.IslandInteraction;
import it.polimi.ingsw.PlayerInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Card10Test {
    Card10 card10;
    PlayerInteraction playerInteraction;
    IslandInteraction islandInteraction;

    @BeforeEach
    void setup() {
        //initializing playerInteraction and islandInteraction
        playerInteraction = new PlayerInteraction(2);
        islandInteraction = new IslandInteraction(8, 2);

        //creating students of the entrance
        Map<Colors, Integer> entrance = new HashMap<>();
        entrance.put(Colors.YELLOW, 2);
        entrance.put(Colors.BLUE, 1);
        entrance.put(Colors.GREEN, 2);
        entrance.put(Colors.RED, 1);
        entrance.put(Colors.PINK, 1);

        //creating students of the hall
        Map<Colors, Integer> hall = new HashMap<>();
        hall.put(Colors.YELLOW, 1);
        hall.put(Colors.BLUE, 1);
        hall.put(Colors.GREEN, 1);
        hall.put(Colors.RED, 0);
        hall.put(Colors.PINK, 1);

        //adding students to entrance and to hall
        playerInteraction.addToEntrance(0, entrance);
        playerInteraction.addToHall(0, hall);

        //creating the card
        card10 = new Card10(1, playerInteraction, islandInteraction);
    }

    @Test
    void useEffectTest() {
        Map<Indexes, Integer> variables = new HashMap<>();
        variables.put(Indexes.PLAYER_INDEX, 0);

        //creating students will be moved from entrance to hall
        Map<Colors, Integer> studentsEntranceToHall = new HashMap<>();
        studentsEntranceToHall.put(Colors.YELLOW, 1);
        studentsEntranceToHall.put(Colors.BLUE, 0);
        studentsEntranceToHall.put(Colors.GREEN, 1);
        studentsEntranceToHall.put(Colors.RED, 0);
        studentsEntranceToHall.put(Colors.PINK, 0);

        //creating students will be moved from hall to entrance
        Map<Colors, Integer> studentsHallToEntrance = new HashMap<>();
        studentsHallToEntrance.put(Colors.YELLOW, 0);
        studentsHallToEntrance.put(Colors.BLUE, 1);
        studentsHallToEntrance.put(Colors.GREEN, 0);
        studentsHallToEntrance.put(Colors.RED, 0);
        studentsHallToEntrance.put(Colors.PINK, 1);

        //saving entrance's old state
        Map<Colors, Integer> oldEntrance = new HashMap<>();
        for (Colors c : Colors.values()) {
            oldEntrance.put(c, playerInteraction.getPlayer(variables.get(Indexes.PLAYER_INDEX)).getBoard().getStudEntrance().get(c));
        }

        //saving hall's old state
        Map<Colors, Integer> oldHall = new HashMap<>();
        for (Colors c : Colors.values()) {
            oldHall.put(c, playerInteraction.getPlayer(variables.get(Indexes.PLAYER_INDEX)).getBoard().getStudHall().get(c));
        }

        //using card10's effect
        card10.useEffect(variables, Colors.YELLOW, studentsEntranceToHall, studentsHallToEntrance);

        //saving entrance's new state
        Map<Colors, Integer> newEntrance = new HashMap<>();
        for (Colors c : Colors.values()) {
            newEntrance.put(c, playerInteraction.getPlayer(variables.get(Indexes.PLAYER_INDEX)).getBoard().getStudEntrance().get(c));
        }

        //saving hall's new state
        Map<Colors, Integer> newHall = new HashMap<>();
        for (Colors c : Colors.values()) {
            newHall.put(c, playerInteraction.getPlayer(variables.get(Indexes.PLAYER_INDEX)).getBoard().getStudHall().get(c));
        }

        boolean check = true;

        //checking if all students where moved correctly from entrance to hall and vice versa
        for (Colors c : Colors.values()) {
            if (newEntrance.get(c) != oldEntrance.get(c) - studentsEntranceToHall.get(c) + studentsHallToEntrance.get(c)) {
                check = false;
                break;
            }

            if (newHall.get(c) != oldHall.get(c) - studentsHallToEntrance.get(c) + studentsEntranceToHall.get(c)) {
                check = false;
                break;
            }
        }

        assertTrue(check);
    }
}
