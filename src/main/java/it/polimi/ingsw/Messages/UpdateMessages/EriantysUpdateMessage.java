package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintBoardMessage;
import it.polimi.ingsw.Messages.PrintMessages.PrintCloudsMessage;
import it.polimi.ingsw.Messages.PrintMessages.PrintIslandsMessage;

import java.util.Map;

public class EriantysUpdateMessage implements UpdateMessage {
    private final Map<Integer, String> players;
    private final PrintBoardMessage[] printBoardMessages;
    private final PrintIslandsMessage printIslandsMessage;
    private final PrintCloudsMessage printCloudsMessage;
    Map<String, Integer> nickToIndex;

    public EriantysUpdateMessage(Map<Integer, String> players, PrintBoardMessage[] printBoardMessages,
                                 PrintIslandsMessage printIslandsMessage, PrintCloudsMessage printCloudsMessage, Map<String, Integer> nickToIndex) {
        this.players = players;
        this.printBoardMessages = printBoardMessages;
        this.printIslandsMessage = printIslandsMessage;
        this.printCloudsMessage = printCloudsMessage;
        this.nickToIndex = nickToIndex;
    }

    public Map<Integer, String> getPlayers() {
        return players;
    }

    public PrintBoardMessage[] getPrintBoardMessages() {
        return printBoardMessages;
    }

    public PrintIslandsMessage getPrintIslandsMessage() {
        return printIslandsMessage;
    }

    public PrintCloudsMessage getPrintCloudsMessage() {
        return printCloudsMessage;
    }

    public Map<String, Integer> getNickToIndex() {
        return nickToIndex;
    }
}