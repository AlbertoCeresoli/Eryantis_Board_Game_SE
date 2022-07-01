package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintCloudsMessage;

public class CloudsUpdateMessage implements UpdateMessage {
    private final PrintCloudsMessage printCloudsMessage;

        /**
     * Used from the GUI to update cloud tiles
     * @param printCloudsMessage to update and print them
     */
     public CloudsUpdateMessage(PrintCloudsMessage printCloudsMessage) {
        this.printCloudsMessage = printCloudsMessage;
    }

    public PrintCloudsMessage getPrintCloudsMessage() {
        return printCloudsMessage;
    }
}
