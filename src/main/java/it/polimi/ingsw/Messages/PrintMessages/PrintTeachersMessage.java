package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Constants.Colors;

import java.util.HashMap;
import java.util.Map;

public class PrintTeachersMessage implements PrintMessage{
	private final Map<Colors, String> teachers;
	private final Map<String, Integer> nickToIndex;

	/**
	 * This method updates this class attributes with the teacher controllers provided from the parameters coming from the model,
	 *  so they are ready for a print on request through the get methods below
	 * @param teachers from the model
	 * @param players who control teachers
	 * @param nickToIndex to match each player with his name
	 */
	public PrintTeachersMessage(Map<Colors, Integer> teachers, Map<Integer, String> players,
								Map<String, Integer> nickToIndex) {
		this.teachers = new HashMap<>();
		this.nickToIndex = new HashMap<>();

		for (Colors c : Colors.values()) {
			if (teachers.get(c) == -1) {
				this.teachers.put(c, "Nobody");
			} else {
				String nickname = players.get(teachers.get(c));
				this.teachers.put(c, nickname);
			}
		}

		for (String nick : nickToIndex.keySet()) {
			this.nickToIndex.put(nick, nickToIndex.get(nick));
		}
		this.nickToIndex.put("Nobody", -1);
	}

	public Map<Colors, String> getTeachers() {
		return teachers;
	}

	public Map<String, Integer> getNickToIndex() {
		return nickToIndex;
	}
}
