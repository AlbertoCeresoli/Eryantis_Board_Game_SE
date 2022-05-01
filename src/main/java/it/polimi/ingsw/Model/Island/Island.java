package it.polimi.ingsw.Model.Island;

import it.polimi.ingsw.Constants.Colors;

import java.util.HashMap;
import java.util.Map;

public class Island {
    private int nTowers;
    private int controllerIndex;
    private int inhibitionCards;
    Map<Colors, Integer> students;


    public Island() {
        this.nTowers = 0;
        this.students = new HashMap<>();
        for (Colors c : Colors.values()){
            students.put(c,0);
        }
        this.inhibitionCards = 0;
        this.controllerIndex = -1;
    }

    /**
     *  take all towers on the island off
     * @return true if the process finish successfully
     */
    public boolean removeTower(){
        this.nTowers = 0;
        return true;
    }

    /**
     *  Add a certain number of towers of the specified player
     * @param player who puts the towers
     * @param towers number of towers he puts
     * @return true if the process finishes successfully
     */
    public boolean addTower(int player, int towers){
        this.controllerIndex = player;
        this.nTowers += towers;
        return true;
    }

    /**
     * Sums the students already on the island with the new ones incoming in the map
     * @param students map of the new students coming on the island
     */
    public void addStudents(Map<Colors, Integer> students) {
        for (Colors c: Colors.values()) {
            this.students.put(c, students.get(c)+this.students.get(c));
        }
    }

    public void addInhibitionCard() {
        inhibitionCards++;
    }

    public void removeInhibitionCard() {
        inhibitionCards--;
    }

    public int getInhibitionCards() {
        return inhibitionCards;
    }

    public Map<Colors, Integer> getStudents() {
        return students;
    }

    public int getnTowers() {
        return nTowers;
    }

    public int getControllerIndex() {
        return controllerIndex;
    }
}
