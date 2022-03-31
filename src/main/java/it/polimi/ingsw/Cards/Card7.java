package it.polimi.ingsw.Cards;

import it.polimi.ingsw.PlayerInteraction;

public class Card7 extends CharCardsPlayer {
    private int capacity;
    private int[] students;

    public Card7(int cost, PlayerInteraction playerInteraction, int capacity, int[] students) {
        super(cost, playerInteraction);
        this.students = new int[5];
        this.capacity = capacity;
        for (int i = 0; i < 5; i++) {
            this.students[i] = students[i];
        }
    }

    @Override
    public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {

    }
}
