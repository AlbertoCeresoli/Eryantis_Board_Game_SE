package it.polimi.ingsw.Messages.SelectionMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintCloudsMessage;

public class CloudSelectionMessage implements SelectionMessage {
    private final PrintCloudsMessage printCloudsMessage;

    public CloudSelectionMessage(PrintCloudsMessage printCloudsMessage) {
        this.printCloudsMessage = printCloudsMessage;
    }

    public PrintCloudsMessage getPrintCloudsMessage() {
        return printCloudsMessage;
    }
}
