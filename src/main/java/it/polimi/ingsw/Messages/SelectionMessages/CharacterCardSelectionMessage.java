package it.polimi.ingsw.Messages.SelectionMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintCharacterCardsMessage;

public class CharacterCardSelectionMessage implements SelectionMessage{
    private final PrintCharacterCardsMessage printCharacterCardsMessage;

    public CharacterCardSelectionMessage(PrintCharacterCardsMessage printCharacterCardsMessage) {
        this.printCharacterCardsMessage = printCharacterCardsMessage;
    }

    public PrintCharacterCardsMessage getPrintCharacterCardsMessage() {
        return printCharacterCardsMessage;
    }
}
