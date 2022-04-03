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
        System.arraycopy(students, 0, this.students, 0, students.length);
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
