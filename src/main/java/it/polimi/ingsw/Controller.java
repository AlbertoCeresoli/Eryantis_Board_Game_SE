package it.polimi.ingsw;

public class Controller {
    private int firstPlayer;
    private Model model;
    private boolean end;

    /**
     *
     */
    public void round(){

    }
    /**
     * test cases: TODO
     */

    /**
     * si occupa di chiamare round fino a quando il flag di fine partita non si alza
     */
    public void startGame(){

    }
    /**
     * test cases: TODO
     */

    /**
     * È il costruttore del controller
     * Inizializza gli attributi del controller
     * In base a questi attributi chiama il costruttore di model con in input le gamerules
     */
    public Controller(int numberPlayers, boolean gameMode){

    }
    /**
     * test cases: TODO
     */

    /**
     *  se il costo della carta è superiore alle monete del player non fa nulla
     *  se il costo è inferiore
     * 	    riduce il numero di monete in mano al player
     * 	    incrementa il costo della carta se il booleano firstuUsed è = 0
     * 	    accede all’indice corretto della carta giocata dall’array del model
     * 	    chiama useEffect della carta passando in input i valori necessari
     */
    public void playCard(){

    }
    /**
     * test cases: TODO
     */
}
