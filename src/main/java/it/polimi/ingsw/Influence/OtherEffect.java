package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Island;

import java.util.ArrayList;
import java.util.Map;

abstract public class OtherEffect implements Influence {
    Influence wrappee;

    public OtherEffect() {
        wrappee = new NormalEffect();
    }

    @Override
    public ArrayList<Integer> calculateInfluence(Map<Colors, Integer> teachers, Island island) {

        ArrayList<Integer> influences = wrappee.calculateInfluence(teachers, island);
        influences = extra(teachers, island, influences);

        return influences;
    }

    abstract ArrayList<Integer> extra(Map<Colors, Integer> teachers, Island island, ArrayList<Integer> influences);
}