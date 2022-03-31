package it.polimi.ingsw.Cards;

import it.polimi.ingsw.BagNClouds;
import it.polimi.ingsw.PlayerInteraction;

public class Card11 extends CharCardsPlayer {
    BagNClouds bagNClouds;
    int[] students;
    int capacity;

    public Card11(int cost, PlayerInteraction playerInteraction, BagNClouds bagNClouds, int capacity, int[] students) {
        super(cost, playerInteraction);
        this.bagNClouds = bagNClouds;
        this.capacity = capacity;
        this.students = new int[5];
        for (int i = 0; i < 5; i++) {
            this.students[i] = students[i];
        }
    }

    @Override
    public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {

    }
}
