package it.polimi.ingsw;

import it.polimi.ingsw.Constants.MessageType;

import java.util.HashMap;
import java.util.Map;

public class MessageParserClient {
    public static final Map<MessageType, Parser> parseMessages = new HashMap<>();

    public MessageParserClient() {
        Parser allIslandsParser = new AllIslandsParser();
        parseMessages.put(MessageType.PRINT_ALL_ISLANDS, allIslandsParser);

        Parser islandParser = new IslandParser();
        parseMessages.put(MessageType.PRINT_ISLAND, islandParser);

        Parser allBoardsParser = new AllBoardsParser();
        parseMessages.put(MessageType.PRINT_ALL_BOARDS, allBoardsParser);

        Parser boardParser = new BoardParser();
        parseMessages.put(MessageType.PRINT_BOARD, boardParser);

        Parser teachersParser = new TeachersParser();
        parseMessages.put(MessageType.PRINT_TEACHERS, teachersParser);

        Parser cloudsParser = new CloudsParser();
        parseMessages.put(MessageType.PRINT_ALL_CLOUDS, cloudsParser);

        Parser defaultParser = new DefaultParser();
        parseMessages.put(MessageType.EASY_MESSAGE, defaultParser);
    }


}
