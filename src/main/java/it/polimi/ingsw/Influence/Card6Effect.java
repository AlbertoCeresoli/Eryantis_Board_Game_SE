package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Island;

import java.util.ArrayList;
import java.util.Map;


public class Card6Effect extends OtherEffect {
    public Card6Effect() {
        super();
    }

    /**
     * The method removes from normalEffect calculation the count of towers on the island
     */
    @Override
    ArrayList<Integer> extra(Map<Colors, Integer> teachers, Island island, ArrayList<Integer> influences) {
        int controller = island.getControllerIndex();

        //if there is no controller (default value is -1) the method returns
        if (controller < 0) {
            return influences;
        }

        //removing from actual island's controller's influence the count of towers
        int towers = island.getnTowers();
        influences.set(controller, influences.get(controller) - towers);

        return influences;
    }
}
