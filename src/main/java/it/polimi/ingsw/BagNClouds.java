package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BagNClouds {
    private static int cloudCapacity;
    private ArrayList<Colors> bag;
    private ArrayList<Map<Colors, Integer>> clouds;


    /**
     *  BagNClouds' constructor
     *  It also creates a cloud for each player in the game
     */
    public BagNClouds(int nPlayers) {
        cloudCapacity = nPlayers+1;

        clouds = new ArrayList<>();
        for (int i=0; i<nPlayers; i++) {
            clouds.add(new HashMap<>());
            for(Colors c : Colors.values()){
                clouds.get(i).put(c, 0);
            }
        }

        bag = new ArrayList<>();
    }
    /**
     * test cases: TODO
     */

    /**
     * initialize a temporary ArrayList of 5 integers a 00000
     * for n:
     *      with a rand on the size of bag it removes the extracted cell from bag and puts the element in students
     * it returns students
     */
    public Map<Colors, Integer> drawStudents(int n) {
        Map<Colors, Integer> students= new HashMap<>();
        int temp;
        Random rand = new Random();
        for (Colors c : Colors.values()){
            students.put(c, 0);
        }

        for (int i=0; i<n; i++){
            if (bag.size()>0){
                temp = rand.nextInt(bag.size());
                students.put(bag.get(temp), students.get(bag.get(temp)) + 1);
                bag.remove(temp);
            }
        }
        return students;
    }
    /**
     * test cases:
     *      - simple cases
     *      - n>bag.size()
     *      - n<0?? TODO??
     */

    /**
     *  creates a temporary array of integers
     *  for nPlayers:
     * 	    temp = drawStudents (cloudCapacity)
     * 	    clouds(i) = clouds(i) + temp
     */
    public void studentsBagToCloud(){
        Map<Colors, Integer> students= new HashMap<>();
        for (Colors c : Colors.values()){
            students.put(c, 0);
        }
        for (int i=0; i<clouds.size(); i++){
            students = drawStudents(cloudCapacity);
            clouds.set(i, students);
        }
    }
    /**
     * test cases:
     *      - simple cases
     */

    /**
     * it adds 5*n students in the bag
     * each student's color is added n times
     */
    public void fillBag(int numStud){
        for (int i=0; i< numStud; i++){
            for (Colors c : Colors.values()){
                bag.add(c);
            }
        }
    }
    /**
     * test cases:
     *      - simple cases
     *      - numStud<0 TODO??
     */

    /**
     * resetCloud: method that removes the given cloud from the arrayList
     * and replace it with an empty one
     */
    public void resetCloud(int cloudIndex){
        Map<Colors, Integer> clearCloud= new HashMap<>();
        for (Colors c : Colors.values()){
            clearCloud.put(c, 0);
        }
        clouds.remove(cloudIndex);
        clouds.add(cloudIndex, clearCloud);
    }
    /**
     * test cases:
     *      - simple cases
     *      - case with not acceptable cloud index TODO??
     */

    /**
     * method that check if the bag is empty
     * TODO rivedere se è corretto o se si deve creare una flag in drawStudents
     */
    public boolean isEmpty(){
        if (bag.size()==0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * get and set methods
     */
    public ArrayList<Colors> getBag() {
        return bag;
    }

    public ArrayList<Map<Colors, Integer>> getClouds() {
        return clouds;
    }

    public void addToBag(Colors color, int numberOfStudents) {
        for (int i = 0; i < numberOfStudents; i++) {
            bag.add(color);
        }
    }
}
