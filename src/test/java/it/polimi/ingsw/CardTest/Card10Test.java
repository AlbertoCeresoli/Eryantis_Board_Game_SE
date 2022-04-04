package it.polimi.ingsw.CardTest;

import it.polimi.ingsw.Cards.Card10;
import it.polimi.ingsw.PlayerInteraction;
import org.junit.jupiter.api.BeforeEach;

public class Card10Test {
    Card10 card10;

    @BeforeEach
    void setup() {
        PlayerInteraction playerInteraction = new PlayerInteraction(2);
        playerInteraction.getPlayer(0).getBoard().addToEntrance(new int[]{2, 1, 2, 1, 1});
        playerInteraction.getPlayer(0).getBoard().addToHall(new int[]{1, 1, 1, 0, 1});
        card10 = new Card10(1, playerInteraction);
    }
}
