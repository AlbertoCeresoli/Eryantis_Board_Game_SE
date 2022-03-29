package it.polimi.ingsw;

import java.util.ArrayList;

public class IslandInteraction {
    private ArrayList<Island> islands;
    private int[] teachers;
    private int[] ntowers;
    private int motherNature;
    private Influence influence;
    private int numberOfInhibitionCards;

    public IslandInteraction(int ntowers, int nplayers) {
        this.ntowers = new int[nplayers];
        for (int i = 0; i < nplayers; i++) {
            this.ntowers[i] = ntowers;
        }
    }
}
