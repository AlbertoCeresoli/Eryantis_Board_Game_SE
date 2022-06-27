package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintCloudsMessage;

public class CloudsUpdateMessage implements UpdateMessage {
    private final PrintCloudsMessage printCloudsMessage;

    public CloudsUpdateMessage(PrintCloudsMessage printCloudsMessage) {
        this.printCloudsMessage = printCloudsMessage;
    }

    public PrintCloudsMessage getPrintCloudsMessage() {
        return printCloudsMessage;
    }
}
