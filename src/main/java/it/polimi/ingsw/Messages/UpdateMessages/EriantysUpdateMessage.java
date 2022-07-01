package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Messages.PrintMessages.PrintBoardMessage;
import it.polimi.ingsw.Messages.PrintMessages.PrintCharacterCardsMessage;
import it.polimi.ingsw.Messages.PrintMessages.PrintCloudsMessage;
import it.polimi.ingsw.Messages.PrintMessages.PrintIslandsMessage;

import java.util.HashMap;
import java.util.Map;

public class EriantysUpdateMessage implements UpdateMessage {
    private final Map<Integer, String> players;
    private final PrintBoardMessage[] printBoardMessages;
    private final PrintIslandsMessage printIslandsMessage;
    private final PrintCloudsMessage printCloudsMessage;
    private final PrintCharacterCardsMessage printCharacterCardsMessage;
    private final Map<String, Integer> nickToIndex;

    /**
     * Used from the GUI tu apdeit the state of every item shown on the interface
     * @param players all the players
     * @param printBoardMessages to update every player's board
     * @param printIslandsMessage to update every island
     * @param printCloudsMessage to update the clouds
     * @param nickToIndex to match each player's name and index
     */
    public EriantysUpdateMessage(Map<Integer, String> players, PrintBoardMessage[] printBoardMessages,
                                 PrintIslandsMessage printIslandsMessage, PrintCloudsMessage printCloudsMessage,
                                 PrintCharacterCardsMessage printCharacterCardsMessage,
                                 Map<String, Integer> nickToIndex) {
        this.players = new HashMap<>();

        for (int index : players.keySet()) {
            this.players.put(index, players.get(index));
        }

        this.printBoardMessages = printBoardMessages;
        this.printIslandsMessage = printIslandsMessage;
        this.printCloudsMessage = printCloudsMessage;
        this.printCharacterCardsMessage = printCharacterCardsMessage;

        this.nickToIndex = new HashMap<>();
        for (String nick : nickToIndex.keySet()) {
            this.nickToIndex.put(nick, nickToIndex.get(nick));
        }
        this.nickToIndex.put("Nobody", -1);
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

    public PrintCharacterCardsMessage getPrintCharacterCardsMessage() {
        return printCharacterCardsMessage;
    }

    public Map<String, Integer> getNickToIndex() {
        return nickToIndex;
    }
}