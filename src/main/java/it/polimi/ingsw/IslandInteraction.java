package it.polimi.ingsw;

import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.Influence.NormalEffect;

import java.util.ArrayList;

public class IslandInteraction {
    private ArrayList<Island> islands;
    private int[] teachers;
    private int[] towersByPlayer;
    private int motherNature;
    private Influence influence;
    private int numberOfInhibitionCards;

    public ArrayList<Island> getIslands() {
        return islands;
    }

    //CONSTRUCTOR: ISLANDINTERACTION
    public IslandInteraction(int towersByPlayer, int nplayers) {
        this.towersByPlayer = new int[nplayers];
        for (int i = 0; i < nplayers; i++) {
            this.towersByPlayer[i] = towersByPlayer;
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

    public int[] getTowersByPlayer() {
        return towersByPlayer;
    }

    public int[] getTeachers() {
        return teachers;
    }

    public void placeTower(int player, int island) {
        int oldController = getIslands().get(island).getControllerIndex();
        int oldNumTowers = getIslands().get(island).getnTowers();
        if (oldController == -1) {
            getIslands().get(island).addTower(player, 1);
        } else {
            getIslands().get(island).removeTower();
            towersByPlayer[oldController] += oldNumTowers;
            towersByPlayer[player] -= oldNumTowers;
            getIslands().get(island).addTower(player, oldNumTowers);
        }
        mergeIslands(island);
    }

    public void mergeIslands(int islandIndex){

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
