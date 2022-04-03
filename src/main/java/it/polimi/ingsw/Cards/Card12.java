package it.polimi.ingsw.Cards;

import it.polimi.ingsw.BagNClouds;
import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

import java.util.ArrayList;

public class Card12 extends CharCardsPlayer {
    BagNClouds bagNClouds;

    /**
     * Card12 constructor
     *
     * @param bagNClouds bagNClouds reference used to draw students in useEffect
     */
    public Card12(int cost, PlayerInteraction playerInteraction, BagNClouds bagNClouds) {
        super(cost, playerInteraction);
        this.bagNClouds = bagNClouds;
    }

    /**
     * The method removes from each board a maximum of @Constants.CARD12_MAX_STUDENTS_TO_REMOVE
     * @param index         not used
     * @param studentColor  is the color of students that will be removed
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {
        ArrayList<Player> players = getPlayerInteraction().getPlayers();

        for (Player player : players) {
            if (player.getBoard().getStudHall()[studentColor] <= Constants.CARD12_MAX_STUDENTS_TO_MOVE)
                player.getBoard().getStudHall()[studentColor] = 0;
            else
                player.getBoard().getStudHall()[studentColor] -= Constants.CARD12_MAX_STUDENTS_TO_MOVE;
        }

        return true;
    }
}
