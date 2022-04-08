package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Island;

import java.util.ArrayList;
import java.util.Map;

public class Card8Effect extends OtherEffect {
    int playerIndex;

    public Card8Effect(int index) {
        super();
        this.playerIndex = index;
    }

    @Override
    ArrayList<Integer> extra(Map<Colors, Integer> teachers, Island island, ArrayList<Integer> influences) {
        influences.set(this.playerIndex, influences.get(playerIndex) + 2);
        return influences;
    }
}
