package it.polimi.ingsw.Cards;

import it.polimi.ingsw.IslandInteraction;

public abstract class CharCardsIslands extends CharacterCards {
    IslandInteraction islandInteraction;

    public CharCardsIslands(int cost, IslandInteraction islandInteraction) {
        super(cost);
        this.islandInteraction = islandInteraction;
    }

    public IslandInteraction getIslandInteraction() {
        return this.islandInteraction;
    }

    public void setIslandInteraction(IslandInteraction islandInteraction) {
        this.islandInteraction = islandInteraction;
    }
}
