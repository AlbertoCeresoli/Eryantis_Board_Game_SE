package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintAssistantCardsMessage;

public class AssistantCardUpdateMessage implements UpdateMessage {
    private final PrintAssistantCardsMessage printAssistantCardsMessage;
    private final int playerIndex;

    public AssistantCardUpdateMessage(PrintAssistantCardsMessage printAssistantCardsMessage, int playerIndex) {
        this.printAssistantCardsMessage = printAssistantCardsMessage;
        this.playerIndex = playerIndex;
    }

    public PrintAssistantCardsMessage getPrintAssistantCardsMessage() {
        return printAssistantCardsMessage;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }
}
