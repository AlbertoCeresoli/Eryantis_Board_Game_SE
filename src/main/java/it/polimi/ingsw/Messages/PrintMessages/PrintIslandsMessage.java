package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Model.Island.Island;

import java.util.ArrayList;
import java.util.Map;

public class PrintIslandsMessage implements PrintMessage {
	private final ArrayList<PrintIslandMessage> islandMessages;

	public PrintIslandsMessage(ArrayList<Island> islands, int motherNature, Map<Integer, String> players) {
		islandMessages = new ArrayList<>();

		for (int i = 0; i < islands.size(); i++) {
			islandMessages.add(new PrintIslandMessage(islands.get(i), i, i == motherNature, players));
		}
	}

	public ArrayList<PrintIslandMessage> getIslandMessages() {
		return islandMessages;
	}
}
