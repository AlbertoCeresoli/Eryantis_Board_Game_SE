package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Model.Player.Board;

import java.util.HashMap;
import java.util.Map;

public class PrintBoardMessage implements PrintMessage{
	private final String nickname;
	private final Map<Colors, Integer> entrance;
	private final Map<Colors, Integer> hall;
	private final int numberOfTowers;
	private final int playerIndex;
	private final int coins;

	public PrintBoardMessage(String nickname, Board board,
							 int towers, int index, int coins) {

		this.nickname = nickname;
		this.entrance = new HashMap<>();
		this.hall = new HashMap<>();

		for (Colors c : Colors.values()) {
			this.entrance.put(c, board.getStudEntrance().get(c));
			this.hall.put(c, board.getStudHall().get(c));
		}

		this.numberOfTowers = towers;
		this.playerIndex = index;
		this.coins = coins;
	}

	public String getNickname() {
		return nickname;
	}

	public Map<Colors, Integer> getEntrance() {
		return entrance;
	}

	public Map<Colors, Integer> getHall() {
		return hall;
	}

	public int getNumberOfTowers() {
		return numberOfTowers;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public int getCoins() {
		return coins;
	}
}
