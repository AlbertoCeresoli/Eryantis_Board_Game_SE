package it.polimi.ingsw.Messages.PrintMessages;

import it.polimi.ingsw.Model.Player.AssistantCard;

import java.util.ArrayList;

public class PrintAssistantCardsMessage implements PrintMessage{
	private final int[] stateOfCards;
	private final int[] priority;
	private final int[] steps;

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
