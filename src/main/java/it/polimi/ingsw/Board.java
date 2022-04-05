package it.polimi.ingsw;

public class Board {
    private int[] studEntrance;
    private int[] studHall;
    //NB: java initialize the arrays with 0

    /**
     * Board's builder
     */
    public Board() {
        studEntrance = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
        studHall = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
    }
    /**
     * No test cases because there are no input values
     */

    /**
     * This method verifies that the students of each color can't be more than 10
     * if the students doesn't exceed the cap than it adds 'students' Array in the arrayHall
     */
    public Boolean addToHall(int[] students){
        for (int i=0; i<Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (studHall[i]+students[i]>Constants.NUMBER_OF_STUDENTS_IN_HALL) {
                return false;
            }
        }
        for (int i=0; i<Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            studHall[i] = studHall[i] + students[i];
        }
        return true;
    }
    /**
     * test cases:
     *      - empty or null array TODO preset
     */

    /**
     * This method verifies if the students can be removed from the array studHall
     * if the students given can be removed then it removes them
     */
    public boolean removeFromHall(int[] students) {
        for (int i=0; i<Constants.NUMBER_OF_STUDENTS_COLOR; i++){
            if (studHall[i]<students[i]){
                return false;
            }
        }
        for (int i=0; i<Constants.NUMBER_OF_STUDENTS_COLOR; i++){
            studHall[i] -= students[i];
        }
        return true;
    }
    /**
     * test cases:
     *      - empty or null array TODO preset
     */

    /**
     * This method checks if the given students plus the students already
     * in the entrance exceed the cap op the gameMode
     * if the students doesn't exceed the cap then it adds students in StudEntrance
     */
    public Boolean addToEntrance (int[] newStud){
        int temp=0;
        for (int i=0; i<Constants.NUMBER_OF_STUDENTS_COLOR; i++){
            temp = temp + studEntrance[i]+ newStud[i];
        }
        // TODO??
        if (temp > 9) {
            return false;
        }
        for (int i=0; i<Constants.NUMBER_OF_STUDENTS_COLOR; i++){
            studEntrance[i]=studEntrance[i]+newStud[i];
        }
        return true;
    }
    /**
     * test cases:
     *      - empty or null array TODO preset
     */

    /**
     * This method verifies if the students can be removed from the array StudEntrance
     * if the students given can be removed then it removes them
     */
    public Boolean removeFromEntrance(int[] students){
        for (int i=0; i<Constants.NUMBER_OF_STUDENTS_COLOR; i++){
            if (students[i]>studEntrance[i]){
                return false;
            }
        }
        for (int i=0; i<Constants.NUMBER_OF_STUDENTS_COLOR; i++){
            studEntrance[i] = studEntrance[i] - students[i];
        }
        return true;
    }
    /**
     * test cases:
     *      - empty or null array TODO preset
     */

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
