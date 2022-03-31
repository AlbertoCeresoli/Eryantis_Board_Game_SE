package it.polimi.ingsw.Influence;

import it.polimi.ingsw.Island;

abstract public class OtherEffect implements Influence {
    Influence wrappee;

    public OtherEffect(Influence influence) {
        this.wrappee = influence;
    }

    @Override
    public void calculateInfluence(int[] teachers, Island island) {
        wrappee.calculateInfluence(teachers, island);
        extra();
    }

    abstract void extra();
}