package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

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
    public boolean useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) throws StudentNotAvailableException, OutOfBoundException, WrongArrayException {
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

    private void checkInputs(int index, int[] studentArray1, int[] studentArray2) throws OutOfBoundException, WrongArrayException, StudentNotAvailableException {
        if (index < 0 || index >= getPlayerInteraction().getPlayers().size()) {
            throw new OutOfBoundException();
        }

        if (studentArray1.length != Constants.NUMBER_OF_STUDENTS_COLOR || studentArray2.length != Constants.NUMBER_OF_STUDENTS_COLOR) {
            throw new WrongArrayException();
        }

        int[] hall = getPlayerInteraction().getPlayer(index).getBoard().getStudHall();
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (hall[i] < studentArray2[i]) {
                throw new StudentNotAvailableException();
            }
        }

        int[] entrance = getPlayerInteraction().getPlayer(index).getBoard().getStudEntrance();
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (entrance[i] < studentArray2[i]) {
                throw new StudentNotAvailableException();
            }
        }

        int sumHall = 0;
        int sumEntrance = 0;
        for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
            if (studentArray1[i] < 0 || studentArray2[i] < 0) {
                throw new WrongArrayException();
            }
            sumHall += studentArray2[i];
            sumEntrance += studentArray1[i];
        }
        if (sumHall > Constants.CARD7_MAX_STUDENTS_TO_MOVE || sumEntrance > Constants.CARD7_MAX_STUDENTS_TO_MOVE || sumHall != sumEntrance) {
            throw new WrongArrayException();
        }
    }
}
