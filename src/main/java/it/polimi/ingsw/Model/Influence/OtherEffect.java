package it.polimi.ingsw.Model.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Model.Island.Island;

import java.util.ArrayList;
import java.util.Map;

abstract public class OtherEffect implements Influence {
    Influence wrapper;

    /**
     * The constructor set the attribute wrapper to NormalEffect
     */
    public OtherEffect() {
        wrapper = new NormalEffect();
    }

    /**
     *
     * The method calculates influence of each player. It uses at first NormalEffect's calculateInfluence,
     * then extra() method is called in order to modify what we receive from first calculation
     */
    @Override
    public ArrayList<Integer> calculateInfluence(Map<Colors, Integer> teachers, Island island, int numberOfPlayers) {
        ArrayList<Integer> influences = wrapper.calculateInfluence(teachers, island, numberOfPlayers);
        influences = extra(teachers, island, influences);

        return influences;
    }

    abstract ArrayList<Integer> extra(Map<Colors, Integer> teachers, Island island, ArrayList<Integer> influences);
}