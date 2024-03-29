package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class Board {
    Map<Colors, Integer> studEntrance;
    Map<Colors, Integer> studHall;

    /**
     * Board's builder.
     * It initialize Entrance and Hall maps with 0 students in each color
     */
    public Board() {
        studEntrance = new HashMap<>();
        studHall = new HashMap<>();
        for (Colors c : Colors.values()){
            studEntrance.put(c, 0);
            studHall.put(c,0);
        }
    }

    /**
     * preset: students.size() != 0 TODO
     *
     * This method verifies that the students of each color can't be more than 10
     * if the students doesn't exceed the cap than it adds 'students' Array in the arrayHall
     */
    public Boolean addToHall(Map<Colors, Integer> students){
        for(Colors c : Colors.values()){
            if (studHall.get(c)+students.get(c)>Constants.NUMBER_OF_STUDENTS_IN_HALL){
                return false;
            }
        }
        for (Colors c : Colors.values()){
            studHall.put(c, studHall.get(c)+students.get(c));
        }
        return true;
    }

    /**
     * preset: students.size() != 0 TODO
     *
     * This method verifies if the students can be removed from the array studHall
     * if the students given can be removed then it removes them
     */
    public boolean removeFromHall(Map<Colors, Integer> students) {
        return remove(students, studHall);
    }

    /**
     * preset: newStud.size()!=0 TODO
     *
     * This method checks if the given students plus the students already
     * in the entrance exceed the cap op the gameMode
     * if the students doesn't exceed the cap then it adds students in StudEntrance
     */
    public Boolean addToEntrance (Map<Colors, Integer> newStud){
        int temp = 0;
        for (Colors c : Colors.values()){
            temp += studEntrance.get(c)+newStud.get(c);

        }
        // TODO??
        if (temp > 9) {
            return false;
        }

        for (Colors c : Colors.values()){
            studEntrance.put(c, studEntrance.get(c)+newStud.get(c));
        }
        return true;
    }

    /**
     * preset: students.size()!=0 TODO
     *
     * This method verifies if the students can be removed from the array StudEntrance
     * if the students given can be removed then it removes them
     */
    public Boolean removeFromEntrance(Map<Colors, Integer> students){
        return remove(students, studEntrance);
    }

    public Boolean remove(Map<Colors, Integer> students, Map<Colors, Integer> place){
        for (Colors c : Colors.values()){
            if (students.get(c)>place.get(c)){
                return false;
            }
        }
        for (Colors c : Colors.values()){
            place.put(c, place.get(c)-students.get(c));
        }
        return true;
    }

    public void setStudHall(Map<Colors, Integer> studHall) {
        this.studHall = studHall;
    }

    public void setStudEntrance(Map<Colors, Integer> studEntrance) {
        this.studEntrance = studEntrance;
    }

    /**
     * get methods
     */
    public Map<Colors, Integer> getStudEntrance() {
        return studEntrance;
    }

    public Map<Colors, Integer> getStudHall() {
        return studHall;
    }
}
