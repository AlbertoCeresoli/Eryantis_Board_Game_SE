package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Random;

public class BagNClouds {
    private static int cloudCapacity;
    private ArrayList<Integer> bag;
    private ArrayList<int[]> clouds;


    /**
     *  costruttore della classe BagNClouds
     *  creazione clouds in base a numPlayers TODO
     */
    public BagNClouds(int nPlayers) {
        cloudCapacity = nPlayers;
        clouds = new ArrayList<int[]>();
        for (int i=0; i<nPlayers; i++) {
            clouds.add(new int[5]);
        }
        bag = new ArrayList<Integer>();
    }
    /**
     * test cases: TODO
     */

    /**
     * inizializza un ArrayList di 5 interi a 00000
     * per n volte:
     *      attraverso un rand sulla size dell’arrayList fa remove della cella dall’arraylist
     * restituisce l’Array
     */
    public int[] drawStudents(int n){
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
     * test cases: TODO
     */

    /**
     *  crea un array temporaneo di 5 interi
     *  per il numero di giocatori
     * 	    temp = drawStudents (numero degli studenti per nuvola)
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
     * test cases: TODO
     */

    /**
     * Inserisce 5*n valori
     * nell’arraylist (n volte 0, n volte 1, …)
     */
    public void fillBag(int numStud){
        for (int i=0; i< numStud; i++){
            for (int color=0; color<5; color++){
                bag.add(color);
            }
        }
    }
    /**
     * test cases: TODO
     */

    /**
     * resetCloud: metodo che azzera la nuvola data in input
     */
    public void resetCloud(int cloudIndex){
        int[] clearCloud = {0,0,0,0,0};
        clouds.remove(cloudIndex);
        clouds.add(cloudIndex, clearCloud);
    }
    /**
     * test cases: TODO
     */

    /**
     * metodi get e set
     */
    public int[] getCloud (int cloudIndex){
        return clouds.get(cloudIndex);
    }

    public boolean isEmpty(){
        if (bag.size()==0) {
            return true;
        }
        else {
            return false;
        }
    }
}
