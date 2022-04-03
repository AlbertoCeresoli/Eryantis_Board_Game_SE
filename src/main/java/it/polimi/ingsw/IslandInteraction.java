package it.polimi.ingsw;

import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.Influence.NormalEffect;

import java.util.ArrayList;

public class IslandInteraction {
    private ArrayList<Island> islands;
    private int[] teachers;
    private int[] ntowers;
    private int motherNature;
    private Influence influence;
    private int numberOfInhibitionCards;

    public ArrayList<Island> getIslands() {
        return islands;
    }

    //CONSTRUCTOR: ISLANDINTERACTION
    public IslandInteraction(int ntowers, int nplayers) {
        this.ntowers = new int[nplayers];
        for (int i = 0; i < nplayers; i++) {
            this.ntowers[i] = ntowers;
        }
        islands = new ArrayList<>();
        numberOfInhibitionCards = Constants.CARD5_NUMBER_INHIBITION_CARD;
        influence = new NormalEffect();
    }

    public int getMotherNature() {
        return motherNature;
    }

    public void setMotherNature(int motherNature) {
        this.motherNature = motherNature;
    }

    public int[] getNtowers() {
        return ntowers;
    }

    public int[] getTeachers() {
        return teachers;
    }

    public void placeTower(int player, int island){
       // if(islands.get(island).)
    }

    public void mergeIslands(){

    }

    public int getNumberOfInhibitionCards() {
        return numberOfInhibitionCards;
    }

    public void removeInhibitionCard() {
        numberOfInhibitionCards--;
    }

    public void addInhibitionCard() {
        numberOfInhibitionCards++;
    }

    public void setInfluence(Influence influence) {
        this.influence = influence;
    }

    public Influence getInfluence() {
        return influence;
    }
}
