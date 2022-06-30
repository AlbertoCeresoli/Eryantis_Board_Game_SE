package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Constants.Colors;

import java.util.HashMap;
import java.util.Map;

public class BoardUpdateMessage implements UpdateMessage {
    private final Map<Colors, Integer> entrance;
    private final Map<Colors, Integer> hall;
    private final String nickname;
    private final int playerIndex;
    private final int towers;
    private final int coins;

    public BoardUpdateMessage(Map<Colors, Integer> entrance, Map<Colors, Integer> hall, String nickname,
                              int playerIndex, int towers, int coins) {
        this.nickname = nickname;
        this.playerIndex = playerIndex;
        this.entrance = new HashMap<>();
        this.hall = new HashMap<>();

        for (Colors c : Colors.values()) {
            this.entrance.put(c, entrance.get(c));
            this.hall.put(c, hall.get(c));
        }

        this.towers = towers;
        this.coins = coins;
    }

    public Map<Colors, Integer> getEntrance() {
        return entrance;
    }

    public Map<Colors, Integer> getHall() {
        return hall;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getTowers() {
        return towers;
    }

    public int getCoins() {
        return coins;
    }
}
