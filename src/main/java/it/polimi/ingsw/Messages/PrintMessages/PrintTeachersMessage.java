package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Constants.Colors;

import java.util.HashMap;
import java.util.Map;

public class PrintTeachersMessage implements PrintMessage{
	private final Map<Colors, String> teachers;
	Map<String, Integer> nickToIndex;

	public PrintTeachersMessage(Map<Colors, Integer> teachers, Map<Integer, String> players, Map<String, Integer> nickToIndex) {
		this.teachers = new HashMap<>();

		for (Colors c : Colors.values()) {
			if (teachers.get(c) == -1) {
				this.teachers.put(c, "Nobody");
			} else {
				String nickname = players.get(teachers.get(c));
				this.teachers.put(c, nickname);
			}
		}

		this.nickToIndex = nickToIndex;
	}

	public Map<Colors, String> getTeachers() {
		return teachers;
	}

	public Map<String, Integer> getNickToIndex() {
		return nickToIndex;
	}
}
