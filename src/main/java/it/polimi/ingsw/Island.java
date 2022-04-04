package it.polimi.ingsw;

public class Island implements StudentManagement {
    private int nTowers;
    private int controllerIndex;
    private int[] students;
    private int inhibitionCards;

    public Island() {
        this.nTowers = 0;
        this.students = new int[5];
        this.inhibitionCards = 0;
        this.controllerIndex = -1;
    }

    public boolean removeTower(){
        this.nTowers = 0;
        return true;
    }

    public boolean addTower(int player, int towers){
        this.controllerIndex = player;
        this.nTowers = towers;
        return true;
    }


    @Override
    public void addStudents(int[] students) {
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            this.students[i] += students[i];
        }
    }

    @Override
    public void removeStudents(int[] students) {
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

    public int[] getStudents() {
        return students;
    }

    public int getnTowers() {
        return nTowers;
    }

    public int getControllerIndex() {
        return controllerIndex;
    }
}
