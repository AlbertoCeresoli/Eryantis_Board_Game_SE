package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Island;

import java.util.ArrayList;

public class Card8Effect extends OtherEffect {
    int playerIndex;

    public Card8Effect(int index) {
        super();
        this.playerIndex = index;
    }

    @Override
    ArrayList<Integer> extra(int[] teachers, Island island, ArrayList<Integer> influences) {
        influences.set(this.playerIndex, influences.get(playerIndex) + 2);
        return influences;
    }
}
