package it.polimi.ingsw.Cards;

import it.polimi.ingsw.PlayerInteraction;

public abstract class CharCardsPlayer extends CharacterCards{
	PlayerInteraction playerInteraction;
	/**
	 * CharacterCards' constructor
	 *
	 * @param cost is the number of coins needed in order to use the card
	 */
	public CharCardsPlayer(int cost, PlayerInteraction playerInteraction) {
		super(cost);
		this.playerInteraction = playerInteraction;
	}

	public PlayerInteraction getPlayerInteraction() {
		return playerInteraction;
	}
}
