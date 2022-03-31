package it.polimi.ingsw.Cards;

public abstract class CharacterCards {
    private int cost;
    private boolean usedThisTurn;
    private boolean usedThisGame;

    public CharacterCards(int cost) {
        this.cost = cost;
        this.usedThisGame = false;
        this.usedThisTurn = false;
    }

    abstract public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2);

    public void increaseCost() {
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
