package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Constants;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

public class Card4 extends CharCardsPlayer {
    /**
     * Card4 constructor
     */
    public Card4(int cost, PlayerInteraction playerInteraction) {
        super(cost, playerInteraction);
    }

    /**
     * Changes the number of maximum movement of the assistant card played this turn by the player
     *
     * @param index         is the index of the player of the turn
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) {
        Player player = getPlayerInteraction().getPlayers().get(index);
        int[][] assistants = player.getAssistants();
        int lastCardUsedIndex = -1;
        for (int i = 0; i < Constants.NUMBER_OF_ASSISTANT_CARDS; i++) {
            if (assistants[0][i] == 1) {
                lastCardUsedIndex = i;
                i = Constants.NUMBER_OF_ASSISTANT_CARDS;
            }
        }
        assistants[2][lastCardUsedIndex] += Constants.CARD4_ADDITION_MOVEMENT;
        return true;
    }
}