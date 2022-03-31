package it.polimi.ingsw.Cards;

import it.polimi.ingsw.BagNClouds;
import it.polimi.ingsw.PlayerInteraction;

public class Card12 extends CharCardsPlayer {
    BagNClouds bagNClouds;

    public Card12(int cost, PlayerInteraction playerInteraction, BagNClouds bagNClouds) {
        super(cost, playerInteraction);
        this.bagNClouds = bagNClouds;
    }

    @Override
    public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {

    }
}
