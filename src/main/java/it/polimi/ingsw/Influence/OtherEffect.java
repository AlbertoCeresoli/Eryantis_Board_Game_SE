package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Island;

import java.util.ArrayList;

abstract public class OtherEffect implements Influence {
    Influence wrappee;

    public OtherEffect() {
        wrappee = new NormalEffect();
    }

    @Override
    public ArrayList<Integer> calculateInfluence(int[] teachers, Island island) {
        ArrayList<Integer> influences = new ArrayList<>();

        influences = wrappee.calculateInfluence(teachers, island);
        influences = extra(teachers, island, influences);

        return influences;
    }

    abstract ArrayList<Integer> extra(int[] teachers, Island island, ArrayList<Integer> influences);
}