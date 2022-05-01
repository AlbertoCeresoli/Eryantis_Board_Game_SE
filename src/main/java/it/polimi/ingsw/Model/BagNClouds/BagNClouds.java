package it.polimi.ingsw.Model.BagNClouds;

import it.polimi.ingsw.Constants.Colors;

import java.util.*;

public class BagNClouds implements hasAddToBag, hasDrawStudents {
    private static int cloudCapacity;
    private final ArrayList<Colors> bag;
    private final ArrayList<Map<Colors, Integer>> clouds;


    /**
     *  preset: nPlayers==2 or nPlayers==3 TODO
     *
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
     * preset: n>0 TODO
     *
     * initialize a temporary ArrayList of 5 integers
     * for n:
     *      with a rand on the size of bag it removes the extracted cell from bag and puts the element in students
     *
     * return: "students"
     */
    @Override
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
     * preset: numStud>0 TODO
     *
     * it adds 5*n students in the bag
     * each student's color is added n times
     */
    public void fillBag(int numStud){
        for (int i=0; i< numStud; i++){
            Collections.addAll(bag, Colors.values());
        }
    }

    /**
     * preset: 0 <= cloudIndex < nPlayers TODO
     *
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
     * method that check if the bag is empty
     */
    public boolean isEmpty(){
        return bag.size() == 0;
    }

    public boolean emptyCloud(int cloudIndex){
        boolean result = true;

        for (Colors c: Colors.values()){
            if (clouds.get(cloudIndex).get(c)!=0){
                result= false;
            }
        }
        return result;
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

    @Override
    public void addToBag(Colors color, int numberOfStudents) {
        for (int i = 0; i < numberOfStudents; i++) {
            bag.add(color);
        }
    }
}
