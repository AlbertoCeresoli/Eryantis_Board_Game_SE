package it.polimi.ingsw.Model.Cards;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Indexes;
import it.polimi.ingsw.Exceptions.EndGameException;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;

import java.lang.reflect.Parameter;
import java.util.Map;

public abstract class CharacterCards {
    protected String name;
    protected int cardIndex;
    private int cost;
    protected Map<Colors, Integer> students;
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

    abstract public boolean useEffect(Map<Indexes, Integer> variables, Colors studentColor,
                                      Map<Colors, Integer> studentArray1, Map<Colors, Integer> studentArray2) throws EndGameException;

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

    public Map<Colors, Integer> getStudents() {
        return students;
    }

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }

    public boolean isUsedThisGame() {
        return usedThisGame;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public String getName() {
        return name;
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
