package it.polimi.ingsw.Model.Island;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Model.Influence.Influence;
import it.polimi.ingsw.Model.Influence.NormalEffect;

import java.util.*;

public class IslandInteraction implements hasAddToIsland, hasCalculateInfluence, hasSetInfluence, hasInhibitionCard, hasSetTeacher {
    private ArrayList<Island> islands;
    private Map<Colors, Integer> teachers;
    private int[] towersByPlayer;
    private int motherNature;
    private Influence influence;
    private int numberOfInhibitionCards;

    /**
     * IslandInteraction constructor instantiates the island interaction once creating the arraylist for all the others islands
     * @param towersPerPlayer depending on the number of player
     * @param nPlayers who play
     */
    public IslandInteraction(int towersPerPlayer, int nPlayers) {
        //teachers initialization
        teachers = new HashMap<>();
        for (Colors c: Colors.values()) {
            teachers.put(c, -1);
        }

        this.towersByPlayer = new int[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            this.towersByPlayer[i] = towersPerPlayer;
        }
        islands = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            islands.add(i,new Island());
        }
        numberOfInhibitionCards = Constants.CARD5_NUMBER_INHIBITION_CARD;
        influence = new NormalEffect();


        Random random = new Random();
        motherNature = random.nextInt(12);

    }

    /**
     * Whenever the controller of an island changes or a tower needs to be added on an island, this method does it
     * managing towers in the players' boards
     * @param player who puts the new tower(s)
     * @param island which the tower goes on
     * @return true if the action doesn't fail
     * @throws EndGameException
     */
    public boolean placeTower(int player, int island) throws EndGameException {
        int oldController = getIslands().get(island).getControllerIndex();
        int oldNumTowers = getIslands().get(island).getnTowers();
        if (oldController == -1) {
            getIslands().get(island).addTower(player, 1);
            towersByPlayer[player]--;
        } else {
            getIslands().get(island).removeTower();
            towersByPlayer[oldController] += oldNumTowers;
            if (towersByPlayer[player] < towersByPlayer[player] - oldNumTowers){
                getIslands().get(island).addTower(player, towersByPlayer[player]);
                towersByPlayer[player] = 0;
            }else{
                towersByPlayer[player] -= oldNumTowers;
                getIslands().get(island).addTower(player, oldNumTowers);
            }
            if(towersByPlayer[player] == 0){
                throw new EndGameException();
            }

        }
        mergeIslands(island);
        return true;
    }

    /**
     * Every time a tower il placed on an island this method checks whether the previous or next island has the same controller,
     * then, if yes, those islands are merged together as well as students and towers on those islands
     * @param islandIndex of the island to check
     * @throws EndGameException if there are only 3 islands left
     */
    public void mergeIslands(int islandIndex) throws EndGameException {
        int newController = getIslands().get(islandIndex).getControllerIndex();

        //the controller is the same of the next island
        if (newController == getIslands().get((islandIndex + 1) % getIslands().size()).getControllerIndex()) {
            //towers merge
            int towersToMerge = getIslands().get((islandIndex + 1) % getIslands().size()).getnTowers();
            getIslands().get((islandIndex + 1) % getIslands().size()).removeTower();
            getIslands().get(islandIndex).addTower(newController, towersToMerge);

            //students merge
            Map<Colors, Integer> studentsToMerge;
            studentsToMerge = getIslands().get((islandIndex + 1) % getIslands().size()).getStudents();
            getIslands().get(islandIndex).addStudents(studentsToMerge);
            getIslands().remove((islandIndex + 1) % getIslands().size());
        }
        //the controller is the same of the previous island
        if (newController == getIslands().get(Math.floorMod(islandIndex - 1, getIslands().size()) ).getControllerIndex()) {
            //towers merge
            int towersToMerge = getIslands().get(Math.floorMod(islandIndex - 1, getIslands().size()) ).getnTowers();
            getIslands().get(Math.floorMod(islandIndex - 1, getIslands().size()) ).removeTower();
            getIslands().get(islandIndex).addTower(newController, towersToMerge);

            //students merge
            Map<Colors, Integer> studentsToMerge;
            studentsToMerge = getIslands().get(Math.floorMod(islandIndex - 1, getIslands().size()) ).getStudents();
            getIslands().get(islandIndex).addStudents(studentsToMerge);
            getIslands().remove(Math.floorMod(islandIndex - 1, getIslands().size()) );

            motherNature--;
        }
        if(getIslands().size() <= 3){
            throw new EndGameException();
        }
    }



    @Override
    public void addInhibitionCard(int islandIndex) {
        islands.get(islandIndex).addInhibitionCard();
    }

    @Override
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

    @Override
    public void setTeacher(int teacherController, Colors color) {
        teachers.put(color, teacherController);
    }

    @Override
    public void addToIsland(int islandIndex, Map<Colors, Integer> students) {
        getIslands().get(islandIndex).addStudents(students);
    }

    /**
     * Calculate the influence of every player on an island and determines who has the highest one, then if the controller
     * needs to be changed, it calls placeTower to put the new controller's towers on it.
     * @param island index
     * @param numberOfPlayers who play
     * @throws EndGameException
     */
    @Override
    public void calculateInfluence(int island, int numberOfPlayers) throws EndGameException {
        ArrayList<Integer> influences = influence.calculateInfluence(teachers, getIslands().get(island), numberOfPlayers);
        int maxI = Collections.max(influences);
        for (int i = 0; i < numberOfPlayers; i++){
            if(influences.get(i) == maxI){
                if(i == getIslands().get(island).getControllerIndex()){
                    return;
                }
                else{
                    placeTower(i, island);
                }
            }
        }
    }
}
