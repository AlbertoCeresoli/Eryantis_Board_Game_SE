package it.polimi.ingsw;

public class Player {
    private final Board board;
    private int[][] assistants = {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {1, 1, 2, 2, 3, 3, 4, 4, 5, 5}};
    private String name;
    private int coins;

    /**
     * Player's constructor
     * it call's Board's constructor
     * <p>
     * 'name' string not initialized: TODO
     */
    public Player() {
        coins = 1;
        board = new Board();
    }
    /**
     * No test cases because there are not input values
     */

    /**
     * it changes the only enum with '1' in '0'
     * if the card in the input position has the enum equals to 2 then
     * it changes the enum in input position in '1'
     * if not it return false
     */
    public boolean fixHand(int assistantIndex) {
        for (int i = 0; i < 10; i++) {
            if (assistants[1][i] == 1) {
                assistants[1][i] = 0;
            }
        }
        if (assistants[1][assistantIndex] == 2) {
            assistants[1][assistantIndex] = 1;
            return true;
        }
        return false;
    }
    /**
     * test cases:
     *      - simple cases
     *      - case with an already played card
     */

    /**
     * it adds 1 to the number of coins of a player
     */
    public void addCoin() {
        coins++;
    }
    /**
     * No test cases because there are not input values
     */

    /**
     * it does coin=coin-n
     * controls TODO??
     */
    public void removeCoins(int cost) {
        coins -= cost;
    }
    /**
     * test cases: TODO
     */

    /**
     * get methods
     */
    public Board getBoard() {
        return board;
    }

    public int[][] getAssistants() {
        return assistants;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    /**
     * set methods
     */
    public void setName(String name) {
        this.name = name;
    }
}