package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintAssistantCardsMessage;

public class AssistantCardUpdateMessage implements UpdateMessage {
    private final PrintAssistantCardsMessage printAssistantCardsMessage;
    private final int playerIndex;

    /**
     * Used from the GUI to update the assistant cards of a specific player
     * @param printAssistantCardsMessage of the player whose assistant card have to be updated
     * @param playerIndex of the player whose assistant card have to be updated
     */
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
