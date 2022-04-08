package it.polimi.ingsw;

public class Player {
    private final Board board;
    private int[][] assistants = {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {1, 1, 2, 2, 3, 3, 4, 4, 5, 5}};
    private String name;
    private int coins;

    /**
     * Player's constructor
     * it call's Board's constructor
     *
     * 'name' string not initialized: TODO
     * improve assistants: TODO
     */
    public Player() {
        coins = 1;
        board = new Board();
    }
    /**
     * No test cases because there are no input values
     */

    /**
     * it changes the only enum with '1' in '0'
     * if the card in the input position has the enum equals to 2 then
     * it changes the enum in input position in '1'
     * if not it return false
     */
    public boolean fixHand(int assistantIndex) {
        if (assistants[0][assistantIndex] != 2) {
            return false;
        }
        for (int i = 0; i < 10; i++) {
            if (assistants[0][i] == 1) {
                assistants[0][i] = 0;
            }
        }
        assistants[0][assistantIndex] = 1;
        return true;

    }


    /**
     * it adds 1 to the number of coins of a player
     */
    public void addCoin() {
        coins++;
    }
    /**
     * No test cases because there are no input values
     */

    /**
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
    // coins controlled in the preset

    /**
     * get methods
     */
    public Board getBoard() {
        return board;
    }

    public int[][] getAssistants() {
        return assistants;
    }

    public int getCoins() {
        return coins;
    }

}