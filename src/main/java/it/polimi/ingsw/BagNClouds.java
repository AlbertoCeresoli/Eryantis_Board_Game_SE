package it.polimi.ingsw;

import it.polimi.ingsw.Exceptions.OutOfBoundException;

import java.util.ArrayList;
import java.util.Random;

public class BagNClouds {
    private static int cloudCapacity;
    private ArrayList<Integer> bag;
    private ArrayList<int[]> clouds;


    /**
     *  BagNClouds' constructor
     *  It also creates a cloud for each player in the game
     */
    public BagNClouds(int nPlayers) {
        cloudCapacity = nPlayers;

        clouds = new ArrayList<int[]>();
        for (int i=0; i<nPlayers; i++) {
            clouds.add(new int[Constants.NUMBER_OF_STUDENTS_COLOR]);
        }

        bag = new ArrayList<Integer>();
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
    public int[] drawStudents(int n) {
        int[] students = {0,0,0,0,0};
        int temp;
        Random rand = new Random();

        for (int i=0; i<n; i++){
            temp= rand.nextInt(bag.size());
            students[bag.get(temp)]++;
            bag.remove(temp);
        }

        return students;
    }
    /**
     * test cases:
     *      - simple cases
     *      - n<0?? TODO??
     *      - n>bag.size() TODO??
     */

    /**
     *  creates a temporary array of integers
     *  for nPlayers:
     * 	    temp = drawStudents (cloudCapacity)
     * 	    clouds(i) = clouds(i) + temp
     */
    public void studentsBagToCloud(){
        int[] students = {0,0,0,0,0};
        for (int i=0; i<clouds.size(); i++){
            students = drawStudents(cloudCapacity);
            clouds.add(students);
        }
    }
    /**
     * test cases:
     *      - it doesn't have test cases because there are no input values
     */

    /**
     * it adds 5*n students in the bag
     * each student's color is added n times
     */
    public void fillBag(int numStud){
        for (int i=0; i< numStud; i++){
            for (int color=0; color<Constants.NUMBER_OF_STUDENTS_COLOR; color++){
                bag.add(color);
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
        int[] clearCloud = {0,0,0,0,0};
        clouds.remove(cloudIndex);
        clouds.add(cloudIndex, clearCloud);
    }
    /**
     * test cases:
     *      - simple cases
     *      - case with not acceptable cloud index
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
    public int[] getCloud (int cloudIndex){
        return clouds.get(cloudIndex);
    }


}
