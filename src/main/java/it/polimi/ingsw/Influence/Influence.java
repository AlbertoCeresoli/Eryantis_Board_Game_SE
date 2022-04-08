package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Island;

import java.util.ArrayList;
import java.util.Map;

public interface Influence {
    ArrayList<Integer> calculateInfluence(Map<Colors, Integer> teachers, Island island);
}
