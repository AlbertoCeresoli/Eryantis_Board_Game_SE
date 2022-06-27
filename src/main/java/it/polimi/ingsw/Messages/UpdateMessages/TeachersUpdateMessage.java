package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintTeachersMessage;

public class TeachersUpdateMessage implements UpdateMessage {
    private final PrintTeachersMessage printTeachersMessage;

    public TeachersUpdateMessage(PrintTeachersMessage printTeachersMessage) {
        this.printTeachersMessage = printTeachersMessage;
    }

    public PrintTeachersMessage getPrintTeachersMessage() {
        return printTeachersMessage;
    }
}
