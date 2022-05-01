package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Model.BagNClouds.BagNClouds;
import it.polimi.ingsw.Model.Cards.Card12;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Model.Player.PlayerInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Card12Test {
    Card12 card12;
    PlayerInteraction playerInteraction;

    @BeforeEach
    void setup() {
        //initializing playerInteraction and three players' halls
        playerInteraction = new PlayerInteraction(3);
        Map<Colors, Integer> hall1 = new HashMap<>();
        hall1.put(Colors.YELLOW, 0);
        hall1.put(Colors.BLUE, 0);
        hall1.put(Colors.GREEN, 2);
        hall1.put(Colors.RED, 0);
        hall1.put(Colors.PINK, 0);
        playerInteraction.addToHall(0, hall1);
        Map<Colors, Integer> hall2 = new HashMap<>();
        hall2.put(Colors.YELLOW, 0);
        hall2.put(Colors.BLUE, 0);
        hall2.put(Colors.GREEN, 3);
        hall2.put(Colors.RED, 0);
        hall2.put(Colors.PINK, 0);
        playerInteraction.addToHall(1, hall2);
        Map<Colors, Integer> hall3 = new HashMap<>();
        hall3.put(Colors.YELLOW, 0);
        hall3.put(Colors.BLUE, 0);
        hall3.put(Colors.GREEN, 4);
        hall3.put(Colors.RED, 0);
        hall3.put(Colors.PINK, 0);
        playerInteraction.addToHall(2, hall3);

        //initializing bagNClouds and filling it
        BagNClouds bagNClouds = new BagNClouds(3);
        bagNClouds.fillBag(10);

        //creating the card
        card12 = new Card12(1, playerInteraction, bagNClouds);
    }

    @Test
    void useEffectTest() {
        //saving old students of that color
        ArrayList<Integer> oldStudents = new ArrayList<>();
        oldStudents.add(playerInteraction.getPlayer(0).getBoard().getStudHall().get(Colors.GREEN));
        oldStudents.add(playerInteraction.getPlayer(1).getBoard().getStudHall().get(Colors.GREEN));
        oldStudents.add(playerInteraction.getPlayer(2).getBoard().getStudHall().get(Colors.GREEN));

        //using Card12's effect
        card12.useEffect(null, Colors.GREEN, null, null);

        //saving new students of that color
        ArrayList<Integer> newStudents = new ArrayList<>();
        newStudents.add(playerInteraction.getPlayer(0).getBoard().getStudHall().get(Colors.GREEN));
        newStudents.add(playerInteraction.getPlayer(1).getBoard().getStudHall().get(Colors.GREEN));
        newStudents.add(playerInteraction.getPlayer(2).getBoard().getStudHall().get(Colors.GREEN));

        boolean check = true;

        //checking if students were correctly removed
        for (int i = 0; i < oldStudents.size(); i++) {
            if (oldStudents.get(i) < 3) {
                if (newStudents.get(i) != 0) {
                    check = false;
                    break;
                }
            } else {
                if (newStudents.get(i) != oldStudents.get(i) - 3) {
                    check = false;
                    break;
                }
            }
        }

        assertTrue(check);
    }
}
