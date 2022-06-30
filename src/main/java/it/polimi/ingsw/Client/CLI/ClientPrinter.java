package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Messages.PrintMessages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientPrinter {
	private static final Map<Colors, String> colorsChars = new HashMap<>();
	private static final Map<Colors, String> colorsBackground = new HashMap<>();

	/**
	 * These two map are used as references for the ansi color codes
	 */
	public ClientPrinter() {
		colorsChars.put(Colors.YELLOW, Constants.ANSI_YELLOW);
		colorsChars.put(Colors.BLUE, Constants.ANSI_BLUE);
		colorsChars.put(Colors.GREEN, Constants.ANSI_GREEN);
		colorsChars.put(Colors.RED, Constants.ANSI_RED);
		colorsChars.put(Colors.PINK, Constants.ANSI_PINK);

		colorsBackground.put(Colors.YELLOW, Constants.ANSI_YELLOW_BACKGROUND);
		colorsBackground.put(Colors.BLUE, Constants.ANSI_BLUE_BACKGROUND);
		colorsBackground.put(Colors.GREEN, Constants.ANSI_GREEN_BACKGROUND);
		colorsBackground.put(Colors.RED, Constants.ANSI_RED_BACKGROUND);
		colorsBackground.put(Colors.PINK, Constants.ANSI_PURPLE_BACKGROUND);
	}

	/**
	 * Prints the information ofthe assistant cards of the actual turn player
	 * @param message of request
	 */
	public static void printAssistantCards(PrintAssistantCardsMessage message) {
		int[] states = message.getStateOfCards();
		int[] priorities = message.getPriority();
		int[] steps = message.getSteps();

		System.out.println("\nYour available Assistants Cards are:");
		for (int i = 0; i < states.length; i++) {
			String state;
			if (states[i] == 0) {
				state = "Not Available";
			} else if (states[i] == 1) {
				state = "Last Used";
			} else {
				state = "Available";
			}

			System.out.println("Assistant Card " + i + " (" + state + "):");
			System.out.println("     Priority: " + priorities[i] + "     Steps: " + steps[i]);
		}
	}

	/**
	 * Calls printIsland for every island
	 * @param message of request
	 */
	public static void printIslands(PrintIslandsMessage message) {
		for (int i = 0; i < message.getIslandMessages().size(); i++) {
			printIsland(message.getIslandMessages().get(i));
		}
	}

	/**
	 * This prints all the information of a specific island
	 * @param message of request
	 */
	public static void printIsland(PrintIslandMessage message) {
		Map<Colors, Integer> students = message.getStudents();
		int towers = message.getNumberOfTowers();
		int inhCards = message.getInhibitionCards();
		String controller = message.getIslandController();
		int islandIndex = message.getIslandIndex();
		boolean MN = message.isMotherNatureInHere();

		System.out.println("\nIsland " + islandIndex + ", controlled by " + controller);

		printIslandStudents(students, islandIndex, MN);
		if (towers != 0) {
			System.out.println("Towers on the island:\n");
			printTowers(towers);
		}
		if (inhCards != 0)
			printInhCards(inhCards);
	}

	/**
	 * This contains the ASCI art for the island and prints it on request
	 * @param students of that island
	 * @param islandIndex of that island
	 * @param MN boolean for Mother Nature presence
	 */
	public static void printIslandStudents(Map<Colors, Integer> students, int islandIndex, boolean MN) {
		System.out.println("Students on the island:\n");

		if (MN) {
			printMN();
		}

		System.out.println(
				"      " + Constants.ANSI_GREEN_BACKGROUND + "         " + Constants.ANSI_RESET + "      \n" +
						"    " + Constants.ANSI_GREEN_BACKGROUND + "             " + Constants.ANSI_RESET + "    \n" +
						"  " + Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + " " + Constants.ANSI_PINK + students.get(Colors.PINK) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + " " + Constants.ANSI_RESET + " " + Constants.ANSI_YELLOW + students.get(Colors.YELLOW) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + "  \n" +
						Constants.ANSI_GREEN_BACKGROUND + "                     " + Constants.ANSI_RESET + "\n" +
						Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + " " + Constants.ANSI_RED + students.get(Colors.RED) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + "      " + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN + students.get(Colors.GREEN) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + "\n" +
						Constants.ANSI_GREEN_BACKGROUND + "                     " + Constants.ANSI_RESET + "\n" +
						Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "     " + Constants.ANSI_RESET + " " + Constants.ANSI_BLUE + students.get(Colors.BLUE) + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN_BACKGROUND + "         " + Constants.ANSI_RESET + "\n" +
						"  " + Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "             " + Constants.ANSI_RESET + "\n" +
						"    " + Constants.ANSI_YELLOW_BACKGROUND + "    " + Constants.ANSI_GREEN_BACKGROUND + "         " + Constants.ANSI_RESET + "\n" +
						"      " + Constants.ANSI_YELLOW_BACKGROUND + Constants.ANSI_BLACK + "Island " + islandIndex + Constants.ANSI_RESET + "\n\n"
		);
	}

	/**
	 * This contains the ASCI art for Mother Nature and prints it on request
	 */
	static void printMN() {
		System.out.println(
				Constants.ANSI_RESET + "         " + Constants.ANSI_YELLOW_BACKGROUND + "   " + Constants.ANSI_RESET + "\n" +
						Constants.ANSI_RESET + "        " + Constants.ANSI_YELLOW_BACKGROUND + "     " + Constants.ANSI_RESET + "\n" +
						Constants.ANSI_RESET + "       " + Constants.ANSI_YELLOW_BACKGROUND + "       " + Constants.ANSI_RESET + "\n"
		);
	}

	/**
	 * This contains the ASCI art for the towers and prints them on request
	 * @param towers number of towers on that island or on that board
	 */
	public static void printTowers(int towers) {
		for (int i = 0; i < towers; i++) {
			System.out.print(
					"   " + Constants.ANSI_WHITE_BACKGROUND + " " + Constants.ANSI_RESET + "   " + Constants.ANSI_WHITE_BACKGROUND + " " + Constants.ANSI_RESET + "   " + Constants.ANSI_WHITE_BACKGROUND + " " + Constants.ANSI_RESET
			);
		}
		System.out.println();
		for (int i = 0; i < towers; i++) {
			System.out.print(
					"   " + Constants.ANSI_WHITE_BACKGROUND + "         " + Constants.ANSI_RESET
			);
		}
		System.out.println();
		for (int i = 0; i < towers; i++) {
			System.out.print(
					"    " + Constants.ANSI_WHITE_BACKGROUND + "       " + Constants.ANSI_RESET + " "
			);
		}
		System.out.println();
		for (int i = 0; i < towers; i++) {
			System.out.print(
					"    " + Constants.ANSI_WHITE_BACKGROUND + "       " + Constants.ANSI_RESET + " "
			);
		}
		System.out.println();
		for (int i = 0; i < towers; i++) {
			System.out.print(
					"    " + Constants.ANSI_WHITE_BACKGROUND + "       " + Constants.ANSI_RESET + " "
			);
		}
		System.out.println("\n");
	}

	/**
	 * This contains the ASCI art for the inhibition cards and prints it on request
	 * @param inhCards number of inhibition card on a specific island
	 */
	public static void printInhCards(int inhCards) {
		System.out.println("Inhibition cards on the island:\n");
		for (int i = 0; i < inhCards; i++) {
			System.out.print(
					Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "          " + Constants.ANSI_RESET
			);
		}
		System.out.println();
		for (int i = 0; i < inhCards; i++) {
			System.out.print(
					Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "   " + Constants.ANSI_RED_BACKGROUND + "    " + Constants.ANSI_BLUE_BACKGROUND + "   " + Constants.ANSI_RESET
			);
		}
		System.out.println();
		for (int i = 0; i < inhCards; i++) {
			System.out.print(
					Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + " " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RESET
			);
		}
		System.out.println();
		for (int i = 0; i < inhCards; i++) {
			System.out.print(
					Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + " " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RED_BACKGROUND + " " + Constants.ANSI_BLUE_BACKGROUND + "  " + Constants.ANSI_RESET
			);
		}
		System.out.println();
		for (int i = 0; i < inhCards; i++) {
			System.out.print(
					Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "   " + Constants.ANSI_RED_BACKGROUND + "    " + Constants.ANSI_BLUE_BACKGROUND + "   " + Constants.ANSI_RESET
			);
		}
		System.out.println();
		for (int i = 0; i < inhCards; i++) {
			System.out.print(
					Constants.ANSI_RESET + "   " + Constants.ANSI_BLUE_BACKGROUND + "          " + Constants.ANSI_RESET
			);
		}
		System.out.println("\n");
	}

	/**
	 * This contains the ASCI art for the board of a player and prints it on request
	 * @param message of request
	 */
	public static void printBoard(PrintBoardMessage message) {
		String nickname = message.getNickname();
		Map<Colors, Integer> entrance = message.getEntrance();
		Map<Colors, Integer> hall = message.getHall();
		int towers = message.getNumberOfTowers();

		ArrayList<Colors> students = new ArrayList<>();
		for (Colors c : Colors.values()) {
			for (int i = 0; i < entrance.get(c); i++) {
				students.add(c);
			}
		}

		System.out.println("\nBoard of " + nickname + "\n");
		if (Constants.isGameMode()) {
			System.out.println("\nNumber of Coins: " + message.getCoins() + "\n");
		}
		System.out.println("Entrance:          Hall:\n");

		int i = 0;
		for (Colors c : Colors.values()) {
			for (int j = 0; j < 2; j++) {
				if (i + j < students.size()) {
					System.out.print(Constants.ANSI_RESET + "  " + colorsBackground.get(students.get(i + j)) + "  " + Constants.ANSI_RESET);
				} else {
					System.out.print(Constants.ANSI_RESET + "    " + Constants.ANSI_RESET);
				}
			}

			System.out.print(Constants.ANSI_RESET + "           ");

			for (int j = 0; j < hall.get(c); j++) {
				System.out.print(colorsBackground.get(c) + "  " + Constants.ANSI_RESET + "  ");
			}
			System.out.println("\n");

			i += 2;
		}

		System.out.println("Towers of " + nickname + ":\n");
		printTowers(towers);
	}

	/**
	 * This prints the teachers controllers
	 * @param message of request
	 */
	public static void printTeachers(PrintTeachersMessage message) {
		Map<Colors, String> teachers = message.getTeachers();

		System.out.println("\nTeachers are controlled by:\n");
		for (Colors c : Colors.values()) {
			System.out.println(colorsChars.get(c) + teachers.get(c));
		}
		System.out.println(Constants.ANSI_RESET);
	}

	/**
	 * This contains the ASCI art for the clouds and prints them on request
	 * @param message of request
	 */
	public static void printClouds(PrintCloudsMessage message) {
		ArrayList<Map<Colors, Integer>> clouds = message.getClouds();

		System.out.println();
		for (int i = 0; i < clouds.size(); i++) {
			System.out.print(Constants.ANSI_RESET + "     " + Constants.ANSI_WHITE_BACKGROUND + "            " + Constants.ANSI_RESET + "       ");
		}
		System.out.println();
		for (Map<Colors, Integer> cloud : clouds) {
			System.out.print(Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "        " + Constants.ANSI_RESET + Constants.ANSI_RESET + " " + Constants.ANSI_YELLOW + cloud.get(Colors.YELLOW) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "         " + Constants.ANSI_RESET + "   ");
		}
		System.out.println();
		for (Map<Colors, Integer> cloud : clouds) {
			System.out.print(Constants.ANSI_WHITE_BACKGROUND + "     " + Constants.ANSI_RESET + Constants.ANSI_RESET + " " + Constants.ANSI_GREEN + cloud.get(Colors.GREEN) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "      " + Constants.ANSI_RESET + " " + Constants.ANSI_BLUE + cloud.get(Colors.BLUE) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "     " + Constants.ANSI_RESET + "  ");
		}
		System.out.println();
		for (int i = 0; i < clouds.size(); i++) {
			System.out.print(Constants.ANSI_WHITE_BACKGROUND + "                      " + Constants.ANSI_RESET + "  ");
		}
		System.out.println();
		for (Map<Colors, Integer> cloud : clouds) {
			System.out.print(Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "      " + Constants.ANSI_RESET + " " + Constants.ANSI_RED + cloud.get(Colors.RED) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "  " + Constants.ANSI_RESET + " " + Constants.ANSI_PINK + cloud.get(Colors.PINK) + Constants.ANSI_RESET + " " + Constants.ANSI_WHITE_BACKGROUND + "      " + Constants.ANSI_RESET + "   ");
		}
		System.out.println();
		for (int i = 0; i < clouds.size(); i++) {
			System.out.print(Constants.ANSI_RESET + "     " + Constants.ANSI_WHITE_BACKGROUND + Constants.ANSI_BLACK + "   Cloud " + i + "  " + Constants.ANSI_RESET + "       ");
		}
		System.out.println("\n");
	}

	/**
	 * This contains the description of the three character cards in the game and prints them on request
	 * @param name of the character
	 * @param cost of the card
	 * @param effect of the card
	 * @param areThereStudentsOnTheCard boolean for the presence of students to be managed from the card effect
	 * @param students if needed on the card
	 */
	public static void printCharacterCard(String name, int cost, String effect, boolean areThereStudentsOnTheCard, ArrayList<Map<Colors, Integer>> students) {
		System.out.println("\nCharacter card: " + name);
		System.out.println("Cost: " + cost + "\n");
		System.out.println("Description:");
		System.out.println(effect + "\n");
		if (areThereStudentsOnTheCard) {
			for (Colors c : Colors.values()) {
				for (int i = 0; i < students.get(0).get(c); i++) {
					System.out.print(colorsBackground.get(c) + "  " + Constants.ANSI_RESET + "  ");
				}
			}

			students.remove(0);
		}


		System.out.println();
	}

	/**
	 * Calls the previous one for each card in the game
	 * @param message of request
	 */
	public static void printCharacterCards(PrintCharacterCardsMessage message) {
		for (int i = 0; i < message.getNames().length; i++) {
			printCharacterCard(message.getNames()[i], message.getCosts()[i],
					message.getEffects()[i], message.getAreThereStudentsOnTheCard()[i], message.getStudents());
		}
	}

	/**
	 * Defaut text printer for the client
	 * @param text to be printed
	 */
	public static void easyPrint(String text) {
		System.out.println(text);
	}
}
