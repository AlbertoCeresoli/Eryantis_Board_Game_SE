package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;

public abstract class CharacterCards {
    private int cost;
    private boolean usedThisTurn;
    private boolean usedThisGame;

    /**
     * CharacterCards' constructor
     *
     * @param cost is the number of coins needed in order to use the card
     */
    public CharacterCards(int cost) {
        this.cost = cost;
        this.usedThisGame = false;
        this.usedThisTurn = false;
    }

    abstract public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) throws OutOfBoundException, StudentNotAvailableException, WrongArrayException;

    /**
     * Cost has to be increased only when the card is used for the first time.
     */
    public void increaseCost() {
        if (!usedThisGame)
            this.cost++;
    }

    public int getCost() {
        return cost;
    }

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }

    public boolean isUsedThisGame() {
        return usedThisGame;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setUsedThisTurn(boolean usedThisTurn) {
        this.usedThisTurn = usedThisTurn;
    }

    public void setUsedThisGame(boolean usedThisGame) {
        this.usedThisGame = usedThisGame;
    }
}
