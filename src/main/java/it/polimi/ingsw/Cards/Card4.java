package it.polimi.ingsw.Cards;

import it.polimi.ingsw.AssistantCard;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerInteraction;

import java.util.ArrayList;
import java.util.Map;

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
     * @param variables     is the index of the player of the turn
     * @param studentColor  not used
     * @param studentArray1 not used
     * @param studentArray2 not used
     */
    @Override
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor, Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        Player player = getPlayerInteraction().getPlayers().get(variables.get(Indexes.PLAYER_INDEX));
        ArrayList<AssistantCard> assistants = player.getAssistants();
        int lastCardUsedIndex = -1;
        for (int i = 0; i < Constants.NUMBER_OF_ASSISTANT_CARDS; i++) {
            if (assistants.get(i).getCardState() == 1) {
                lastCardUsedIndex = i;
                i = Constants.NUMBER_OF_ASSISTANT_CARDS;
            }
        }
        assistants.get(lastCardUsedIndex).setSteps(assistants.get(lastCardUsedIndex).getSteps() + Constants.CARD4_ADDITION_MOVEMENT);
        return true;
    }
}