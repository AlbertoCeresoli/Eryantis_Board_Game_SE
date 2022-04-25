package it.polimi.ingsw.Teacher;

import java.util.ArrayList;

public interface TeacherInterface {
    /**
     * @param thatColorStudentsByPlayer is an arraylist that contains the number of students of one color controlled by each player
     * @param actualPlayer              is actual turn's player
     * @return returning arraylist could be empty or with only one element: if it is empty no change is needed, if it is not
     * a new teacher controller will be set
     */
    ArrayList<Integer> checkTeacher(ArrayList<Integer> thatColorStudentsByPlayer, int actualPlayer);
}
