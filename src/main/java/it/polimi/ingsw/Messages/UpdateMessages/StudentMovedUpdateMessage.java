package it.polimi.ingsw.Messages.UpdateMessages;

import it.polimi.ingsw.Constants.Colors;

public class StudentMovedUpdateMessage implements UpdateMessage {
    private final String nickname;
    private final String fromWhere;
    private final String toWhere;
    private int islandIndex;
    private final Colors color;

    public StudentMovedUpdateMessage(String nickname, String fromWhere, String toWhere, Colors color) {
        this.nickname = nickname;
        this.fromWhere = fromWhere;
        this.toWhere = toWhere;
        this.color = color;
    }

    public StudentMovedUpdateMessage(String nickname, String fromWhere, String toWhere, int islandIndex, Colors color) {
        this.nickname = nickname;
        this.fromWhere = fromWhere;
        this.toWhere = toWhere;
        this.islandIndex = islandIndex;
        this.color = color;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public String getToWhere() {
        return toWhere;
    }

    public int getIslandIndex() {
        return islandIndex;
    }

    public Colors getColor() {
        return color;
    }
}
