package it.polimi.ingsw.Cards;

import it.polimi.ingsw.PlayerInteraction;

public abstract class CharCardsPlayer extends CharacterCards {
    private PlayerInteraction playerInteraction;

    public CharCardsPlayer(int cost, PlayerInteraction playerInteraction) {
        super(cost);
        this.playerInteraction = playerInteraction;
    }

    public PlayerInteraction getPlayerInteraction() {
        return playerInteraction;
    }
}
