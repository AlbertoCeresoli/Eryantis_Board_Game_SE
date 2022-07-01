package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Model.Player.AssistantCard;

import java.util.ArrayList;

public class PrintAssistantCardsMessage implements PrintMessage{
	private final int[] stateOfCards;
	private final int[] priority;
	private final int[] steps;

	/**
	 * Thanks to the "cards" parameter which contains all the assistants cards infos, this method updates the 3 arrays so thay
	 * they are ready for a new print request through the get method below
	 * @param cards assistant cards from the model
	 */
	public PrintAssistantCardsMessage(ArrayList<AssistantCard> cards) {
		this.stateOfCards = new int[cards.size()];
		this.priority = new int[cards.size()];
		this.steps = new int[cards.size()];

		for (int i = 0; i < cards.size(); i++) {
			this.stateOfCards[i] = cards.get(i).getCardState();
			this.priority[i] = cards.get(i).getPriority();
			this.steps[i] = cards.get(i).getSteps();
		}
	}

	public int[] getStateOfCards() {
		return stateOfCards;
	}

	public int[] getPriority() {
		return priority;
	}

	public int[] getSteps() {
		return steps;
	}
}
