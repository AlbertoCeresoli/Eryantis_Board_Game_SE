package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Influence.Influence;
import it.polimi.ingsw.Influence.NormalEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IslandInteraction implements hasAddToIsland, hasCalculateInfluence, hasSetInfluence {
    private ArrayList<Island> islands;
    private Map<Colors, Integer> teachers= new HashMap<>();
    private int[] towersByPlayer;
    private int motherNature;
    private Influence influence;
    private int numberOfInhibitionCards;

    //IslandInteraction's constructor
    public IslandInteraction(int towersByPlayer, int nPlayers) {
        //teachers initialization
        teachers.forEach((key, value) -> value = -1);

        this.towersByPlayer = new int[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            this.towersByPlayer[i] = towersByPlayer;
        }
        islands = new ArrayList<>();
        numberOfInhibitionCards = Constants.CARD5_NUMBER_INHIBITION_CARD;
        influence = new NormalEffect();
    }

    public void placeTower(int player, int island) throws EndGameException {
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

    public void mergeIslands(int islandIndex) throws EndGameException {
        int newController = getIslands().get(islandIndex).getControllerIndex();

        //the controller is the same of the next island
        if (newController == getIslands().get(islandIndex + 1).getControllerIndex()) {
            //towers merge
            int towersToMerge = getIslands().get(islandIndex + 1).getnTowers();
            getIslands().get(islandIndex + 1).removeTower();
            getIslands().get(islandIndex).addTower(newController, towersToMerge);

            //students merge
            Map<Colors, Integer> studentsToMerge= new HashMap<>();
            studentsToMerge = getIslands().get(islandIndex + 1).getStudents();
            getIslands().get(islandIndex).addStudents(studentsToMerge);
            getIslands().remove(islandIndex + 1);
        }

        //the controller is the same of the previous island
        if (newController == getIslands().get(islandIndex - 1).getControllerIndex()) {
            //towers merge
            int towersToMerge = getIslands().get(islandIndex - 1).getnTowers();
            getIslands().get(islandIndex - 1).removeTower();
            getIslands().get(islandIndex).addTower(newController, towersToMerge);

            //students merge
            Map<Colors, Integer> studentsToMerge= new HashMap<>();
            studentsToMerge = getIslands().get(islandIndex - 1).getStudents();
            getIslands().get(islandIndex).addStudents(studentsToMerge);
            getIslands().remove(islandIndex - 1);
        }
        if(getIslands().size() <= 3){
            throw new EndGameException();
        }
    }

    @Override
    public void calculateInfluence(int island){
         influence.calculateInfluence(teachers, getIslands().get(island));

    }

    public void removeInhibitionCard() {
        numberOfInhibitionCards--;
    }

    public void addInhibitionCard() {
        numberOfInhibitionCards++;
    }

    /**
     * get methods
     */
    public ArrayList<Island> getIslands() {
        return islands;
    }

    public int getMotherNature() {
        return motherNature;
    }

    public int[] getTowersByPlayer() {
        return towersByPlayer;
    }

    public Map<Colors, Integer> getTeachers() {
        return teachers;
    }

    public Influence getInfluence() {
        return influence;
    }

    public int getNumberOfInhibitionCards() {
        return numberOfInhibitionCards;
    }

    /**
     * set methods
     */
    public void setMotherNature(int motherNature) {
        this.motherNature = motherNature;
    }

    @Override
    public void setInfluence(Influence influence) {
        this.influence = influence;
    }

    public void setTeacher(int teacherController, Colors color) {
        teachers.put(color, teacherController);
    }

    @Override
    public void addToIsland(int islandIndex, Map<Colors, Integer> students) {
        getIslands().get(islandIndex).addStudents(students);
    }
}
