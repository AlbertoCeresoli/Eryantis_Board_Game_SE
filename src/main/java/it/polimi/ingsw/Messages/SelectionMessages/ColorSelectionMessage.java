package it.polimi.ingsw.Messages.SelectionMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintMessage;

public class ColorSelectionMessage implements SelectionMessage {
    private final PrintMessage printMessage;

    public ColorSelectionMessage(PrintMessage printMessage) {
        this.printMessage = printMessage;
    }

    public PrintMessage getPrintMessage() {
        return printMessage;
    }
}
