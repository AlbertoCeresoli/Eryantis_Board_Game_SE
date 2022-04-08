package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Island;

import java.util.ArrayList;
import java.util.Map;


public class Card6Effect extends OtherEffect {
    public Card6Effect() {
        super();
    }

    @Override
    ArrayList<Integer> extra(Map<Colors, Integer> teachers, Island island, ArrayList<Integer> influences) {
        int controller = island.getControllerIndex();
        if (controller < 0) {
            return influences;
        }
        int towers = island.getnTowers();

        influences.set(controller, influences.get(controller) - towers);

        return influences;
    }
}
