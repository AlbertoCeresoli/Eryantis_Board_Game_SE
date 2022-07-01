package it.polimi.ingsw.Client;

import it.polimi.ingsw.Messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public interface UI {
    void elaborateMessage(Message message);
    ObjectInputStream getFromServerInput();
    void exit();
}
