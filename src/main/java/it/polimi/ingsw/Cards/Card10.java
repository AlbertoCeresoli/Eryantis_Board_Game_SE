package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

import java.util.Map;

public class Card10 extends CharCardsPlayer {
    /**
     * Card10 constructor
     */
    public Card10(int cost, PlayerInteraction playerInteraction) {
        super(cost, playerInteraction);
    }

    /**
     * The method removes students saved in studentArray1 from the entrance,
     * removes students saved in studentArray2 from the hall,
     * adds studentArray1 to the hall,
     * adds studentArray2 to the entrance
     * <p>
     * A maximum of 2 students can be chosen.
     *
     * @param index         is the player index that will exchange the students
     * @param studentColor  not used
     * @param studentArray1 students from entrance
     * @param studentArray2 students from hall
     */
    @Override
    public boolean useEffect(int index, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
        checkInputs(index, studentArray1, studentArray2);

        Player player = getPlayerInteraction().getPlayer(index);

        boolean check = true;
        check = player.getBoard().removeFromEntrance(studentArray1);
        if (!check){
            return false;
        }
        check = player.getBoard().removeFromHall(studentArray2);
        if (!check){
            return false;
        }
        check = player.getBoard().addToEntrance(studentArray2);
        if (!check){
            return false;
        }
        check = player.getBoard().addToHall(studentArray1);
        if (!check){
            return false;
        }

        return true;
    }

    private void checkInputs(int index, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws OutOfBoundException, WrongArrayException, StudentNotAvailableException {
        if (index < 0 || index >= getPlayerInteraction().getPlayers().size()) {
            throw new OutOfBoundException();
        }

        if (studentArray1.size() != Constants.NUMBER_OF_STUDENTS_COLOR || studentArray2.size() != Constants.NUMBER_OF_STUDENTS_COLOR) {
            throw new WrongArrayException();
        }

        Map<Colors, Integer> hall = getPlayerInteraction().getPlayer(index).getBoard().getStudHall();
        for (Colors c: Colors.values()) {
            if (hall.get(c) < studentArray2.get(c)) {
                throw new StudentNotAvailableException();
            }
        }

        Map<Colors, Integer> entrance = getPlayerInteraction().getPlayer(index).getBoard().getStudEntrance();
        for (Colors c: Colors.values()) {
            if (entrance.get(c) < studentArray2.get(c)) {
                throw new StudentNotAvailableException();
            }
        }

        int sumHall = 0;
        int sumEntrance = 0;
        for (Colors c: Colors.values()) {
            if (studentArray1.get(c) < 0 || studentArray2.get(c) < 0) {
                throw new WrongArrayException();
            }
            sumHall += studentArray2.get(c);
            sumEntrance += studentArray1.get(c);
        }
        if (sumHall > Constants.CARD7_MAX_STUDENTS_TO_MOVE || sumEntrance > Constants.CARD7_MAX_STUDENTS_TO_MOVE || sumHall != sumEntrance) {
            throw new WrongArrayException();
        }
    }
}
