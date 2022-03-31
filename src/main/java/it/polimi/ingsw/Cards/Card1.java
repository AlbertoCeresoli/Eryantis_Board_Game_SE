package it.polimi.ingsw.Cards;

import it.polimi.ingsw.BagNClouds;
import it.polimi.ingsw.IslandInteraction;

public class Card1 extends CharCardsIslands {
    BagNClouds bagNClouds;
    int[] students;
    int capacity;

    public Card1(int cost, IslandInteraction islandInteraction, BagNClouds bagNClouds, int capacity, int[] students) {
        super(cost, islandInteraction);
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

    public BagNClouds getBagNClouds() {
        return bagNClouds;
    }

    public int[] getStudents() {
        return students;
    }

    public void setBagNClouds(BagNClouds bagNClouds) {
        this.bagNClouds = bagNClouds;
    }

    public void setStudents(int[] students) {
        this.students = students;
    }
}
