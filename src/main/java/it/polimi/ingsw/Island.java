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
    }

    public void removeTower(){
        this.nTowers = 0;
    }

    public void addTower(int player, int towers){
        this.controllerIndex = player;
        this.nTowers = towers;
    }


    @Override
    public void addStudents(int[] students) {
        for (int i = 0; i < students.length; i++){
            this.students[i] = students[i];
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
}
