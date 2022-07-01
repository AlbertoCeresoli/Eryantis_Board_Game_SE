package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Constants.Colors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrintCloudsMessage implements PrintMessage{
	private final ArrayList<Map<Colors, Integer>> clouds;

	/**
	 *  This method updates this class Hashmaps with the students in each cloud, so they are ready for a print
	 *  on request through the get methods
	 * @param clouds from the model
	 */
	public PrintCloudsMessage(ArrayList<Map<Colors, Integer>> clouds) {
		this.clouds = new ArrayList<>();
		for (Map<Colors, Integer> cloud : clouds) {
			Map<Colors, Integer> students = new HashMap<>();

			for (Colors c : Colors.values()) {
				students.put(c, cloud.get(c));
			}

			this.clouds.add(students);
		}
	}

	public ArrayList<Map<Colors, Integer>> getClouds() {
		return clouds;
	}
}
