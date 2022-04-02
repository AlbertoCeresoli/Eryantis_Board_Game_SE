package it.polimi.ingsw.Cards;

import it.polimi.ingsw.IslandInteraction;

/**
 * CharCardsIslands will be extended by cards that work with islands or with calculateInfluence method
 */
public abstract class CharCardsIslands extends CharacterCards {
    private IslandInteraction islandInteraction;

    /**
     * CharCardsIslands' constructor
     *
     * @param islandInteraction is a reference to islandInteraction that will allow cards that extend this class
     * to work with islands
     */
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
