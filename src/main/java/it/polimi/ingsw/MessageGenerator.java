package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Messages.EasyMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyPlayedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyPlayedThisTurnErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotValidIndexErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotValidInputErrorMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Model.BagNClouds.BagNClouds;
import it.polimi.ingsw.Model.Player.AssistantCard;

import java.util.ArrayList;

public class MessageGenerator {
    public MessageGenerator() {

    }

    public Message colorSelection(String result){
        Message message;


        switch (result.toUpperCase()) {
            case "YELLOW", "BLUE", "GREEN", "RED", "PINK" ->
                    message = new EasyMessage("The color selected is " + result.toLowerCase());
            default ->
                    message = new NotValidInputErrorMessage("Not valid input, the color must be yellow, blue, green, red or pink");
        }
        return message;
    }

    public Message placeSelection(String result){
        Message message;

        if (result.equalsIgnoreCase("ISLAND")) {
            message = new EasyMessage("You have selected island");
        }
        else if (result.equalsIgnoreCase("HALL")){
            message = new EasyMessage("You have selected hall");
        }
        else {
            message = new NotValidInputErrorMessage("Not valid Input, you can select Island or Hall");
        }
        return message;
    }

    public Message islandSelection(String result, int islandSize){
        Message message;

        int index;

        try{
            index = Integer.parseInt(result);
            if (index < 0 || index > islandSize){
                message = new NotValidIndexErrorMessage("Not valid index, the input must be in (0 - " + islandSize + ")");
            }
            else {
                message = new EasyMessage("You have selected the island " + index);
            }
        }
        catch (NumberFormatException e) {
            message = new NotValidInputErrorMessage("Not valid format, the input must be an number in (0 - " + islandSize + ")");
        }

        return message;
    }

    public Message stepsSelection(String result, int maxSteps){
        Message message;

        int index;

        try{
            index = Integer.parseInt(result);
            if (index < 0 || index > maxSteps){
                message = new NotValidIndexErrorMessage("Not valid index, the input must be in (1 - " + maxSteps + ")");
            }
            else {
                message = new EasyMessage("You have selected " + index + " steps");
            }
        }
        catch (NumberFormatException e) {
            message = new NotValidInputErrorMessage("Not valid format, the input must be an number in (0 - " + maxSteps + ")");
        }

        return message;
    }

    public Message playerIndexSelection(String result, int numPlayers){
        Message message;

        int index;

        try{
            index = Integer.parseInt(result);
            if (index < 0 || index > numPlayers){
                message = new NotValidIndexErrorMessage("Not valid index, the input must be in (0 - " + (numPlayers - 1) + ")");
            }
            else {
                message = new EasyMessage("You have selected player" + index);
            }
        }
        catch (NumberFormatException e) {
            message = new NotValidInputErrorMessage("Not valid format, the input must be an number in (0 - " + numPlayers + ")");
        }

        return message;
    }

    public Message assistantSelection(String result, int numPlayers, int[] cardsPlayed, ArrayList<AssistantCard> assistants){
        Message message = null;

        boolean alreadyPlayed = false;

        int card;

        try {
            card = Integer.parseInt(result);

            for (int i = 0; i < numPlayers; i++) {
                if (card == cardsPlayed[i]) {
                    alreadyPlayed = true;
                    break;
                }
            }

            if(card >= 0 && card < Constants.NUMBER_OF_ASSISTANT_CARDS){
                if (assistants.get(card).getCardState()==2 && !alreadyPlayed){
                    message = new EasyMessage(card + " card selected");
                }
                else if (alreadyPlayed){
                    message = new AlreadyPlayedThisTurnErrorMessage("Another player has already played this card in this round");
                }
                else if (assistants.get(card).getCardState() != 2){
                    message = new AlreadyPlayedErrorMessage("You have already played this card");
                }
            }
            else {
                message = new NotValidIndexErrorMessage("Not valid card value");
            }
        } catch (NumberFormatException e) {
            message = new NotValidInputErrorMessage("Not valid format, you must select an int between 0 and " + (Constants.NUMBER_OF_ASSISTANT_CARDS - 1));
        }

        return message;
    }

    public Message cloudSelection(String result, int numPlayers, BagNClouds bagNClouds){
        Message message;

        int index;

        try{
            index = Integer.parseInt(result);
            if (index < 0 || index > numPlayers){
                message = new NotValidIndexErrorMessage("Not valid index, the input must be in (0 - " + (numPlayers - 1) + ")");
            }
            else if (!bagNClouds.emptyCloud(index))
                message = new EasyMessage("Cloud number " + index + " selected");
            else{
                message = new AlreadyPlayedErrorMessage("The cloud you have selected is already empty, select another one");
            }
        }
        catch (NumberFormatException e) {
            message = new NotValidInputErrorMessage("Not valid Input, the input must be an int between (0 - " + (numPlayers - 1) + ")");
        }

        return message;
    }

    public Message characterCardSelection(String result){
        Message message;

        int index;

        try{
            index = Integer.parseInt(result);
            if (index>=0 && index <3){
                message = new EasyMessage("Card " + index + " selected");
            }
            else {
                message = new NotValidIndexErrorMessage("Not valid index, the input must be 0 or 1 or 2");
            }
        }
        catch (NumberFormatException e) {
            message = new NotValidInputErrorMessage("Not valid input, the input must be 0 or 1 or 2");
        }

        return message;
    }
}
