package it.polimi.ingsw.Influence;

public class Card9Effect extends OtherEffect {
    int studentColor;

    public Card9Effect(Influence influence, int studentColor) {
        super(influence);
        this.studentColor = studentColor;
    }

    @Override
    void extra() {

    }
}
