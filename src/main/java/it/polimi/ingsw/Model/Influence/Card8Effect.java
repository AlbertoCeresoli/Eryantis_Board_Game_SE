package it.polimi.ingsw.Model.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Model.Island.Island;

import java.util.ArrayList;
import java.util.Map;

public class Card8Effect extends OtherEffect {
    int playerIndex;

    public Card8Effect(int index) {
        super();
        this.playerIndex = index;
    }

    /**
     * The method adds influence points to the player that played the card this turn
     */
    @Override
    ArrayList<Integer> extra(Map<Colors, Integer> teachers, Island island, ArrayList<Integer> influences) {
        influences.set(this.playerIndex, influences.get(playerIndex) + Constants.CARD8_ADDITIONAL_INFLUENCE_POINTS);
        return influences;
    }
}
