package it.polimi.ingsw.Messages.SelectionMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintCloudsMessage;

public class CloudSelectionMessage implements SelectionMessage {
    private final PrintCloudsMessage printCloudsMessage;

    /**
     * Notify to the client that the server is waiting for this kind of info
     * @param printCloudsMessage to print the clouds to the client
     */
    public CloudSelectionMessage(PrintCloudsMessage printCloudsMessage) {
        this.printCloudsMessage = printCloudsMessage;
    }

    public PrintCloudsMessage getPrintCloudsMessage() {
        return printCloudsMessage;
    }
}
