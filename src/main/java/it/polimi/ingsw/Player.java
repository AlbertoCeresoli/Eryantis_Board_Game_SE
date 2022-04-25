package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;

import java.util.ArrayList;

public class Player {
    private final Board board;
    private final ArrayList<AssistantCard> assistants;
    private String name;
    private int coins;

    /**
     * Player's constructor
     * it call's Board's constructor
     *
     * 'name' string not initialized: TODO
     */
    public Player() {
        coins = 1;
        board = new Board();
        assistants = new ArrayList<>();
        for (int i = 1; i <= Constants.NUMBER_OF_ASSISTANT_CARDS; i++){
            assistants.add(new AssistantCard(2, i, (i+1)/2));
        }
    }

    /**
     * preset: 0 <= assistantIndex < NUMBER_OF_ASSISTANT_CARDS TODO
     *
     * it changes the only enum with '1' in '0'
     * if the card in the input position has the enum equals to 2 then
     * it changes the enum in input position in '1'
     * if not it return false
     */
    public boolean fixHand(int assistantIndex) {
        if (assistants.get(assistantIndex).getCardState() != 2) {
            return false;
        }
        for (int i = 0; i < Constants.NUMBER_OF_ASSISTANT_CARDS; i++) {
            if (assistants.get(i).getCardState() == 1) {
                assistants.get(i).setCardState(0);
            }
        }
        assistants.get(assistantIndex).setCardState(1);
        return true;
    }


    /**
     * it adds 1 to the number of coins of a player
     */
    public void addCoin() {
        coins++;
    }

    /**
     * preset: cost>0 TODO
     *
     * it checks if the player has enough coins
     * after that, if there are enough coins it does coins-=cost
     */
    public boolean removeCoins(int cost) {
        if (coins<cost){
            return false;
        }
        coins -= cost;
        return true;
    }

    /**
     * get methods
     */
    public Board getBoard() {
        return board;
    }

    public ArrayList<AssistantCard> getAssistants() {
        return assistants;
    }

    public int getCoins() {
        return coins;
    }

}