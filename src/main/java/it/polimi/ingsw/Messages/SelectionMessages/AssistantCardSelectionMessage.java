package it.polimi.ingsw.Messages.SelectionMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintAssistantCardsMessage;

public class AssistantCardSelectionMessage implements SelectionMessage {
    private final PrintAssistantCardsMessage printAssistantCardsMessage;

    /**
     * Notify to the client that the server is waiting for this kind of info
     * @param printAssistantCardsMessage to print the assistant cards to the client
     */
    public AssistantCardSelectionMessage(PrintAssistantCardsMessage printAssistantCardsMessage) {
        this.printAssistantCardsMessage = printAssistantCardsMessage;
    }

    public PrintAssistantCardsMessage getPrintAssistantCardsMessage() {
        return printAssistantCardsMessage;
    }
}
