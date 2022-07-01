package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Model.Island.Island;

import java.util.Map;

public class PrintIslandMessage implements PrintMessage{
	private final int islandIndex;
	private final String islandController;
	private final Map<Colors, Integer> students;
	private final boolean motherNatureInHere;
	private final int numberOfTowers;
	private final int inhibitionCards;

	/**
	 * This method updates this class attributes with a specific island infos coming from the model, so they are ready for a
	 * print on request through the get methods below
	 * @param island to be printed
	 * @param islandIndex of the specific island
	 * @param MN boolean for the presence of mother nature
	 * @param players to see who controls it
	 */
	public PrintIslandMessage(Island island, int islandIndex, boolean MN, Map<Integer, String> players) {
		this.islandIndex = islandIndex;
		int controllerIndex = island.getControllerIndex();

		if (controllerIndex == -1)
			this.islandController = "Nobody";
		else
			this.islandController = players.get(island.getControllerIndex());

		this.students = island.getStudents();
		this.numberOfTowers = island.getnTowers();
		this.motherNatureInHere = MN;
		this.inhibitionCards = island.getInhibitionCards();
	}

	public int getIslandIndex() {
		return islandIndex;
	}

	public String getIslandController() {
		return islandController;
	}

	public Map<Colors, Integer> getStudents() {
		return students;
	}

	public boolean isMotherNatureInHere() {
		return motherNatureInHere;
	}

	public int getNumberOfTowers() {
		return numberOfTowers;
	}

	public int getInhibitionCards() {
		return inhibitionCards;
	}
}
