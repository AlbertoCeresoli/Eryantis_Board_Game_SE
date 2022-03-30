package it.polimi.ingsw;

public class Player {
    private Board board= new Board();
    private int[] assistants;
    private String name;
    private int coins;

    /**
     * costruttore di player
     * chiama il costruttore di Board
     *
     * stringa name non inizializzara: TODO
     */
    public Player(){

    }
    /**
     * test cases: TODO
     */

    /**
     * Cambia l’unica enum a 1 che trova in 0
     * Cambia l’enum nella posizione passata in input ad 1
     */
    public void fixHand(int assistantIndex){

    }
    /**
     * test cases: TODO
     */

    /**
     * Si effettua una coin++
     */
    public void addCoin(){

    }
    /**
     * test cases: TODO
     */

    /**
     * fa coin=coin-n
     */
    public void removeCoins(int cost){

    }
    /**
     * test cases: TODO
     */

    /**
     * metodi get e set: TODO
     */
    public Board getBoard() {
        return board;
    }
}