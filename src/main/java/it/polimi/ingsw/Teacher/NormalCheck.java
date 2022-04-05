package it.polimi.ingsw.Teacher;

import java.util.ArrayList;

public class NormalCheck implements TeacherInterface {
    @Override
    public ArrayList<Integer> checkTeacher(ArrayList<Integer> thatColorStudentsByPlayer, int actualPlayer) {
        ArrayList<Integer> newPlayersForThatTeacher = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < thatColorStudentsByPlayer.size(); i++) {
            if (thatColorStudentsByPlayer.get(i) > max) {
                newPlayersForThatTeacher.clear();
                newPlayersForThatTeacher.add(i);
                max = thatColorStudentsByPlayer.get(i);
            }
            else if (thatColorStudentsByPlayer.get(i) == max && max != 0) {
                newPlayersForThatTeacher.add(i);
            }
        }

        if (newPlayersForThatTeacher.contains(actualPlayer)) {
            if (newPlayersForThatTeacher.size() == 1){
                return newPlayersForThatTeacher;
            }
            else {
                newPlayersForThatTeacher.clear();
                return newPlayersForThatTeacher;
            }
        }
        else {
            newPlayersForThatTeacher.clear();
            return newPlayersForThatTeacher;
        }
    }
}
