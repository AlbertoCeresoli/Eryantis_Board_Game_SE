package it.polimi.ingsw.Model.Teacher;

import java.util.ArrayList;

public class EqualCheck implements TeacherInterface {
    /**
     * @param thatColorStudentsByPlayer contains the number of student of requested color controlled by each player
     * @param actualPlayer              is the player that has moved something in the hall, so needs to check if has the right of controlling the teacher
     * @return could be in two different states: if ArrayList is empty, the caller will not modify the teacher's control;
     * if the size equal to 1, it means that the controller of that teacher will be changed to the one indicated in the ArrayList returned
     */
    @Override
    public ArrayList<Integer> checkTeacher(ArrayList<Integer> thatColorStudentsByPlayer, int actualPlayer) {
        //finding players that have the most number of students for that teacher
        ArrayList<Integer> newPlayersForThatTeacher = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < thatColorStudentsByPlayer.size(); i++) {
            if (thatColorStudentsByPlayer.get(i) > max) {
                newPlayersForThatTeacher.clear();
                newPlayersForThatTeacher.add(i);
                max = thatColorStudentsByPlayer.get(i);
            }
            //saving also eventual other players that has the same number of students of the actual maximum
            else if (thatColorStudentsByPlayer.get(i) == max && max != 0) {
                newPlayersForThatTeacher.add(i);
            }
        }

        //controlling the final ArrayList: if it contains the actualPlayer, it is returned, removing others.
        //The controller changes because actualPlayer has reached the same number of students of the previous maximum
        if (newPlayersForThatTeacher.contains(actualPlayer)) {
            newPlayersForThatTeacher.clear();
            newPlayersForThatTeacher.add(actualPlayer);
            return newPlayersForThatTeacher;
        }
        //if not, an empty ArrayList is returned, no change is needed
        else {
            newPlayersForThatTeacher.clear();
            return newPlayersForThatTeacher;
        }
    }
}
