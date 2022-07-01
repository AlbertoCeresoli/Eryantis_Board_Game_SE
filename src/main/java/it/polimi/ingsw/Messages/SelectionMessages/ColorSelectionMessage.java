package it.polimi.ingsw.Messages.SelectionMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintMessage;

public class ColorSelectionMessage implements SelectionMessage {
    private final PrintMessage printMessage;

    /**
     * Notify to the client that the server is waiting for this kind of info
     * @param printMessage To print the request to the client
     */
    public ColorSelectionMessage(PrintMessage printMessage) {
        this.printMessage = printMessage;
    }

    public PrintMessage getPrintMessage() {
        return printMessage;
    }
}
