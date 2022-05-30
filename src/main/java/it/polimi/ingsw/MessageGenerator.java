package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.MessageType;
import it.polimi.ingsw.Model.BagNClouds.BagNClouds;
import it.polimi.ingsw.Model.Player.AssistantCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageGenerator {
    public MessageGenerator() {

    }

    public Map<MessageType, String> colorSelection(String result){
        Map<MessageType, String> message = new HashMap<>();

        switch (result.toUpperCase()) {
            case "YELLOW", "BLUE", "GREEN", "RED", "PINK" ->
                    message.put(MessageType.CORRECT_INPUT, "The color selected is " + result.toLowerCase());
            default ->
                    message.put(MessageType.NOT_VALID_INPUT, "Not valid input, the color must be yellow, blue, green, red or pink");
        }
        return message;
    }

    public Map<MessageType, String> placeSelection(String result){
        Map<MessageType, String> message = new HashMap<>();

        if (result.equalsIgnoreCase("ISLAND")) {
            message.put(MessageType.CORRECT_INPUT, "You have selected island");
        }
        else if (result.equalsIgnoreCase("HALL")){
            message.put(MessageType.CORRECT_INPUT, "You have selected hall");
        }
        else {
            message.put(MessageType.NOT_VALID_INPUT, "not valid Input, you can select Island or Hall");
        }
        return message;
    }

    public Map<MessageType, String> islandSelection(String result, int islandSize){
        Map<MessageType, String> message = new HashMap<>();
        int index;
        try{
            index = Integer.parseInt(result);
            if (index < 0 || index > islandSize){
                message.put(MessageType.NOT_VALID_INDEX, "not valid index, the input must be in (0 - " + islandSize + ")");
            }
            else {
                message.put(MessageType.CORRECT_INPUT, "You have selected the island " + index);
            }
        }
        catch (NumberFormatException e) {
            message.put(MessageType.NOT_VALID_INPUT, "not valid format, the input must be an number in (0 - " + islandSize + ")");
        }
        return message;
    }

    public Map<MessageType, String> stepsSelection(String result, int maxSteps){
        Map<MessageType, String> message = new HashMap<>();
        int index;

        try{
            index = Integer.parseInt(result);
            if (index < 0 || index > maxSteps){
                message.put(MessageType.NOT_VALID_INDEX, "not valid index, the input must be in (1 - " + maxSteps + ")");
            }
            else {
                message.put(MessageType.CORRECT_INPUT, "You have selected " + index + " steps");
            }
        }
        catch (NumberFormatException e) {
            message.put(MessageType.NOT_VALID_INPUT, "not valid format, the input must be an number in (0 - " + maxSteps + ")");
        }

        return message;
    }

    public Map<MessageType, String> playerIndexSelection(String result, int numPlayers){
        Map<MessageType, String> message = new HashMap<>();
        int index;

        try{
            index = Integer.parseInt(result);
            if (index < 0 || index > numPlayers){
                message.put(MessageType.NOT_VALID_INDEX, "not valid index, the input must be in (0 - " + (numPlayers - 1) + ")");
            }
            else {
                message.put(MessageType.CORRECT_INPUT, "you have selected player" + index);
            }
        }
        catch (NumberFormatException e) {
            message.put(MessageType.NOT_VALID_INPUT, "not valid format, the input must be an number in (0 - " + numPlayers + ")");
        }

        return message;
    }

    public Map<MessageType, String> assistantSelection(String result, int numPlayers, int[] cardsPlayed, ArrayList<AssistantCard> assistants){
        Map<MessageType, String> message = new HashMap<>();
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
                    message.put(MessageType.CORRECT_INPUT, card + " card selected");
                }
                else if (alreadyPlayed){
                    message.put(MessageType.ALREADY_PLAYED_THIS_TURN, "Another player has already played this card in this round");
                }
                else if (assistants.get(card).getCardState()!=2){
                    message.put(MessageType.ALREADY_PLAYED, "You have already played this card");
                }
            }
            else {
                message.put(MessageType.NOT_VALID_INDEX, "Not valid card value");
            }
        } catch (NumberFormatException e) {
            message.put(MessageType.NOT_VALID_INPUT, "Not valid format, you must select an int between 0 and " + (Constants.NUMBER_OF_ASSISTANT_CARDS - 1));
        }

        return message;
    }

    public Map<MessageType, String> cloudSelection(String result, int numPlayers, BagNClouds bagNClouds){
        Map<MessageType, String> message = new HashMap<>();
        int index;

        try{
            index = Integer.parseInt(result);
            if (index < 0 || index > numPlayers){
                message.put(MessageType.NOT_VALID_INDEX, "not valid index, the input must be in (0 - " + (numPlayers - 1) + ")");
            }
            else if (!bagNClouds.emptyCloud(index))
                message.put(MessageType.CORRECT_INPUT, "Cloud number " + index + " selected");
            else{
                message.put(MessageType.ALREADY_PLAYED, "The cloud you have selected is already empty, select another one");
            }
        }
        catch (NumberFormatException e) {
            message.put(MessageType.NOT_VALID_INPUT, "not valid Input, the input must be an int between (0 - " + (numPlayers - 1) + ")");
        }

        return message;
    }

    public Map<MessageType, String> characterCardSelection(String result){
        Map<MessageType, String> message = new HashMap<>();
        int index;

        try{
            index = Integer.parseInt(result);
            if (index>=0 && index <3){
                message.put(MessageType.CORRECT_INPUT, "Card " + index + " selected");
            }
            else {
                message.put(MessageType.NOT_VALID_INDEX, "Not valid index, the input must be 0 or 1 or 2");
            }
        }
        catch (NumberFormatException e) {
            message.put(MessageType.NOT_VALID_INPUT, "Not valid input, the input must be 0 or 1 or 2");
        }

        return message;
    }
}
