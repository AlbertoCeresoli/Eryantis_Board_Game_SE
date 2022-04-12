package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Cards.Card11;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Card11Test {
    Card11 card11;
    PlayerInteraction playerInteraction;
    IslandInteraction islandInteraction;

    @BeforeEach
    void setup() {
        //initializing playerInteraction and hall of the first player
        playerInteraction = new PlayerInteraction(3);
        islandInteraction = new IslandInteraction(6, 3);

        Map<Colors, Integer> hall = new HashMap<>();
        hall.put(Colors.YELLOW, 0);
        hall.put(Colors.BLUE, 1);
        hall.put(Colors.GREEN, 2);
        hall.put(Colors.RED, 1);
        hall.put(Colors.PINK, 1);
        playerInteraction.addToHall(0, hall);

        //initializing bagNClouds
        BagNClouds bagNClouds = new BagNClouds(3);
        bagNClouds.fillBag(10);

        //creating students will be put on the card
        Map<Colors, Integer> card = new HashMap<>();
        card.put(Colors.YELLOW, 1);
        card.put(Colors.BLUE, 1);
        card.put(Colors.GREEN, 0);
        card.put(Colors.RED, 1);
        card.put(Colors.PINK, 1);

        //creating the card
        card11 = new Card11(1, playerInteraction, islandInteraction, bagNClouds, card);
    }

    /**
     * The test controls the state of students on the chosen island before and after the method.
     * The array has to change only in one index, increasing the content by one
     */
    @Test
    void useEffectTest() {
        int index = 0;

        //creating students will be picked up from the card and put to the hall
        Map<Colors, Integer> students = new HashMap<>();
        students.put(Colors.YELLOW, 1);
        students.put(Colors.BLUE, 0);
        students.put(Colors.GREEN, 0);
        students.put(Colors.RED, 0);
        students.put(Colors.PINK, 0);

        //saving hall's old state
        Map<Colors, Integer> oldHall = new HashMap<>();
        for (Colors c : Colors.values()) {
            oldHall.put(c, playerInteraction.getPlayer(index).getBoard().getStudHall().get(c));
        }

        //using card11's effect
        card11.useEffect(index, Colors.YELLOW, students, null);

        //saving hall's new state
        Map<Colors, Integer> newHall = new HashMap<>();
        for (Colors c : Colors.values()) {
            newHall.put(c, playerInteraction.getPlayer(index).getBoard().getStudHall().get(c));
        }

        boolean check = true;

        //checking if all students where moved correctly from card to hall
        for (Colors c : Colors.values()) {
            if (newHall.get(c) != oldHall.get(c) + students.get(c)) {
                check = false;
                break;
            }
        }

        assertTrue(check);
    }
}
