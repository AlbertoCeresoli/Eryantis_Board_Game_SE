package it.polimi.ingsw;

public class Board {
    private int[] studEntrance;
    private int[] studHall;
    //NB: java initialize the arrays with 0

    /**
     * Board's builder
     */
    public Board() {
        studEntrance = new int[5];
        studHall = new int[5];
    }
    /**
     * No test cases because there are not input values
     */

    /**
     * This method verifies that the students of each color can't be more than 10
     * if the students doesn't exceed the cap than it adds 'students' Array in the arrayHall
     */
    public Boolean addToHall(int[] students){
        for (int i=0; i<5; i++) {
            if (studHall[i]+students[i]>10) {
                return false;
            }
        }
        for (int i=0; i<5; i++) {
            studHall[i] = studHall[i] + students[i];
        }
        return true;
    }
    /**
     * test cases:
     *      - empty or null array TODO??
     */

    /**
     * This method verifies if the students can be removed from the array
     * if the students given can be removed then it removes the them from studEntrance
     */
    public Boolean removeStudent(int[] students){
        for (int i=0; i<5; i++){
            if (students[i]>studEntrance[i]){
                return false;
            }
        }
        for (int i=0; i<5; i++){
            studEntrance[i] = studEntrance[i] - students[i];
        }
        return true;
    }
    /**
     * test cases:
     *      - simple case
     *      - given students are more than the students in studEntrance
     *      - empty or null array TODO??
     */

    /**
     * This method checks if the given students plus the students already
     * in the entrance exceed the cap op the gameMode
     * if the students doesn't exceed the cap then it adds students in StudEntrance
     */
    public Boolean addToEntrance (int[] newStud){
        int temp=0;
        for (int i=0; i<5; i++){
            temp = temp + studEntrance[i]+ newStud[i];
        }
        // TODO??
        if (temp > 9) {
            return false;
        }
        for (int i=0; i<5; i++){
            studEntrance[i]=studEntrance[i]+newStud[i];
        }
        return true;
    }
    /**
     * test cases:
     *      - simple case
     *      - the students exceed the cap of the Entrance
     *      - empty or null array TODO??
     */

    //to be implemented
    public boolean removeFromHall(int[] students) {
        return false;
    }

    /**
     * get methods
     */
    public int[] getStudEntrance() {
        return studEntrance;
    }

    public int[] getStudHall() {
        return studHall;
    }
}
