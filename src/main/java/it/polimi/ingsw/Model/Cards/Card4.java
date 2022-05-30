package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Cards;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Model.Player.PlayerInteraction;
import it.polimi.ingsw.Model.Player.hasAddStepsToMNMovement;

import java.util.Map;

public class Card4 extends CharacterCards {
    hasAddStepsToMNMovement hasAddStepsToMNMovement;

    /**
     * Card4 constructor
     */
    public Card4(int cost, PlayerInteraction playerInteraction) {
        super(cost);

        this.name = Cards.MAGIC_POSTMAN.getName();
        this.cardIndex = 4;

        this.hasAddStepsToMNMovement = playerInteraction;
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
    public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor,
                             Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) {
        //setting new possible movement for MN
        hasAddStepsToMNMovement.addStepsToMNMovement(variables.get(Indexes.PLAYER_INDEX));

        return true;
    }
}