package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Island;

import java.util.ArrayList;

public interface Influence {
    ArrayList<Integer> calculateInfluence(int[] teachers, Island island);
}
