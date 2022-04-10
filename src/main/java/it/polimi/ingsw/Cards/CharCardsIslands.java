package it.polimi.ingsw.Cards;

import it.polimi.ingsw.IslandInteraction;

public abstract class CharCardsIslands extends CharacterCards{
	IslandInteraction islandInteraction;
	/**
	 * CharacterCards' constructor
	 *
	 * @param cost is the number of coins needed in order to use the card
	 */
	public CharCardsIslands(int cost, IslandInteraction islandInteraction) {
		super(cost);
		this.islandInteraction = islandInteraction;
	}

	public IslandInteraction getIslandInteraction() {
		return islandInteraction;
	}
}
