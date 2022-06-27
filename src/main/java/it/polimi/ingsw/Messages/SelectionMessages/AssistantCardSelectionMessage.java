package it.polimi.ingsw.Messages.SelectionMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintAssistantCardsMessage;

public class AssistantCardSelectionMessage implements SelectionMessage {
    private final PrintAssistantCardsMessage printAssistantCardsMessage;

    public AssistantCardSelectionMessage(PrintAssistantCardsMessage printAssistantCardsMessage) {
        this.printAssistantCardsMessage = printAssistantCardsMessage;
    }

    public PrintAssistantCardsMessage getPrintAssistantCardsMessage() {
        return printAssistantCardsMessage;
    }
}
