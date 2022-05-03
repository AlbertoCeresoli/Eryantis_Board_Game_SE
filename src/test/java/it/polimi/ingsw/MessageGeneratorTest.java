package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.MessageType;
import it.polimi.ingsw.Model.BagNClouds.BagNClouds;
import it.polimi.ingsw.Model.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 Unit test for BagNClouds class. Tests:
 - colorSelection
 - placeSelection
 - islandSelection
 - stepsSelection
 - playerIndexSelection
 - assistantsSelection
 - cloudSelection
 - characterCardSelection
 */

public class MessageGeneratorTest {
    MessageGenerator messageGenerator;

    @BeforeEach
    void setUp() {
        messageGenerator=new MessageGenerator();
    }

    /**
     * selectColor:
     * - simpleCases
     * - notValidInput
     */
    @Test
    @DisplayName("simple cases colorSelection")
    void testCorrectColor() {
        Map<MessageType, String> message;

        for (Colors c: Colors.values()) {
            message = messageGenerator.colorSelection(c.toString());
            assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectColor failed");
            assertEquals("The color selected is " + c.toString().toLowerCase(), message.get(MessageType.CORRECT_INPUT), "testCorrectColor failed");
        }
    }

    @Test
    @DisplayName("notValidInput colorSelection")
    void testNotValidColor() {
        Map<MessageType, String> message;
        String s= "ciao";

        message = messageGenerator.colorSelection(s);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INPUT), "testNotValidColor failed");
        assertEquals("Not valid input, the color must be yellow, blue, green, red or pink", message.get(MessageType.NOT_VALID_INPUT), "testNotValidColor failed");
    }

    /**
     * placeSelection:
     * - simpleCases
     * - notValidInput
     */
    @Test
    @DisplayName("simple cases placeSelection")
    void testCorrectPlace() {
        Map<MessageType, String> message;
        String s1 = "Island";
        String s2 = "Hall";

        message = messageGenerator.placeSelection(s1);
        assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectPlace failed");
        assertEquals("You have selected island", message.get(MessageType.CORRECT_INPUT), "testCorrectPlace failed");

        message = messageGenerator.placeSelection(s2);
        assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectPlace failed");
        assertEquals("You have selected hall", message.get(MessageType.CORRECT_INPUT), "testCorrectPlace failed");
    }

    @Test
    @DisplayName("notValidInput placeSelection")
    void testNotValidPlace() {
        Map<MessageType, String> message;
        String s= "ciao";

        message = messageGenerator.placeSelection(s);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INPUT), "testNotValidPlace failed");
        assertEquals("not valid Input, you can select Island or Hall", message.get(MessageType.NOT_VALID_INPUT), "testNotValidPlace failed");
    }

    /**
     * islandSelection:
     * - simpleCases
     * - notValidIndex
     * - notValidInput
     */
    @Test
    @DisplayName("simple cases islandSelection")
    void testCorrectIsland() {
        Map<MessageType, String> message;
        int islandSize = 12;
        String s= "0";

        message = messageGenerator.islandSelection(s, islandSize);
        assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectIsland failed");
        assertEquals("You have selected the island 0", message.get(MessageType.CORRECT_INPUT), "testCorrectIsland failed");
    }

    @Test
    @DisplayName("notValidIndex islandSelection")
    void testNotValidIndexIsland() {
        Map<MessageType, String> message;
        int islandSize = 12;
        String s= "13";

        message = messageGenerator.islandSelection(s, islandSize);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INDEX), "testNotValidIndexIsland failed");
        assertEquals("not valid index, the input must be in (0 - " + islandSize + ")", message.get(MessageType.NOT_VALID_INDEX), "testNotValidIndexIsland failed");
    }

    @Test
    @DisplayName("notValidInput islandSelection")
    void testNotValidIsland() {
        Map<MessageType, String> message;
        int islandSize = 12;
        String s= "ciao";

        message = messageGenerator.islandSelection(s, islandSize);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INPUT), "testNotValidIsland failed");
        assertEquals("not valid format, the input must be an number in (0 - " + islandSize + ")", message.get(MessageType.NOT_VALID_INPUT), "testNotValidIsland failed");
    }

    /**
     * StepsSelection:
     * - simpleCases
     * - notValidIndex
     * - notValidInput
     */
    @Test
    @DisplayName("simple cases stepsSelection")
    void testCorrectSteps() {
        Map<MessageType, String> message;
        int maxSteps = 3;
        String s= "1";

        message = messageGenerator.stepsSelection(s, maxSteps);
        assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectSteps failed");
        assertEquals("You have selected " + s +" steps", message.get(MessageType.CORRECT_INPUT), "testCorrectSteps failed");
    }

    @Test
    @DisplayName("notValidIndex stepsSelection")
    void testNotValidIndexSteps() {
        Map<MessageType, String> message;
        int maxSteps = 3;
        String s= "4";

        message = messageGenerator.stepsSelection(s, maxSteps);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INDEX), "testNotValidIndexSteps failed");
        assertEquals("not valid index, the input must be in (1 - " + maxSteps + ")", message.get(MessageType.NOT_VALID_INDEX), "testNotValidIndexSteps failed");
    }

    @Test
    @DisplayName("notValidInput stepsSelection")
    void testNotValidSteps() {
        Map<MessageType, String> message;
        int maxSteps = 3;
        String s= "ciao";

        message = messageGenerator.stepsSelection(s, maxSteps);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INPUT), "testNotValidSteps failed");
        assertEquals("not valid format, the input must be an number in (0 - " + maxSteps + ")", message.get(MessageType.NOT_VALID_INPUT), "testNotValidSteps failed");
    }

    /**
     * PlayerSelection:
     * - simpleCases
     * - notValidIndex
     * - notValidInput
     */
    @Test
    @DisplayName("simple cases playerIndexSelection")
    void testCorrectPlayer() {
        Map<MessageType, String> message;
        int numPlayers = 3;
        String s= "1";

        message = messageGenerator.playerIndexSelection(s, numPlayers);
        assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectPlayer failed");
        assertEquals("you have selected player" + s, message.get(MessageType.CORRECT_INPUT), "testCorrectPlayer failed");
    }

    @Test
    @DisplayName("notValidIndex playerSelection")
    void testNotValidIndexPlayer() {
        Map<MessageType, String> message;
        int numPlayers = 3;
        String s= "5";

        message = messageGenerator.playerIndexSelection(s, numPlayers);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INDEX), "testNotValidIndexPlayer failed");
        assertEquals("not valid index, the input must be in (0 - " + (numPlayers - 1) + ")", message.get(MessageType.NOT_VALID_INDEX), "testNotValidIndexPlayer failed");
    }

    @Test
    @DisplayName("notValidInput playerSelection")
    void testNotValidPlayer() {
        Map<MessageType, String> message;
        int numPlayers = 3;
        String s= "ciao";

        message = messageGenerator.playerIndexSelection(s, numPlayers);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INPUT), "testNotValidPlayer failed");
        assertEquals("not valid format, the input must be an number in (0 - " + numPlayers + ")", message.get(MessageType.NOT_VALID_INPUT), "testNotValidPlayer failed");
    }

    /**
     * CloudSelection:
     * - simpleCases
     * - emptyCloud
     * - notValidIndex
     * - notValidInput
     */
    @Test
    @DisplayName("simple cases cloudSelection")
    void testCorrectCloud() {
        Map<MessageType, String> message;
        int numPlayers = 3;
        BagNClouds bagNClouds = new BagNClouds(numPlayers);
        bagNClouds.fillBag(10);
        bagNClouds.studentsBagToCloud();
        String s= "1";

        message = messageGenerator.cloudSelection(s, numPlayers, bagNClouds);
        assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectCloud failed");
        assertEquals("Cloud number " + s + " selected", message.get(MessageType.CORRECT_INPUT), "testCorrectCloud failed");
    }

    @Test
    @DisplayName("already selected cloud cloudSelection")
    void testEmptyCloud() {
        Map<MessageType, String> message;
        int numPlayers = 3;
        BagNClouds bagNClouds = new BagNClouds(numPlayers);
        bagNClouds.fillBag(10);
        bagNClouds.studentsBagToCloud();
        String s= "1";
        bagNClouds.resetCloud(Integer.parseInt(s));

        message = messageGenerator.cloudSelection(s, numPlayers, bagNClouds);
        assertTrue(message.containsKey(MessageType.ALREADY_PLAYED), "testEmptyCloud failed");
        assertEquals("The cloud you have selected is already empty, select another one", message.get(MessageType.ALREADY_PLAYED), "testEmptyCloud failed");
    }

    @Test
    @DisplayName("notValidIndex cloudSelection")
    void testNotValidIndexCloud() {
        Map<MessageType, String> message;
        int numPlayers = 3;
        BagNClouds bagNClouds = new BagNClouds(numPlayers);
        bagNClouds.fillBag(10);
        bagNClouds.studentsBagToCloud();
        String s= "4";

        message = messageGenerator.cloudSelection(s, numPlayers, bagNClouds);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INDEX), "testNotValidIndexCloud failed");
        assertEquals("not valid index, the input must be in (0 - " + (numPlayers - 1) + ")", message.get(MessageType.NOT_VALID_INDEX), "testNotValidIndexCloud failed");
    }

    @Test
    @DisplayName("notValidInput cloudSelection")
    void testNotValidCloud() {
        Map<MessageType, String> message;
        int numPlayers = 3;
        BagNClouds bagNClouds = new BagNClouds(numPlayers);
        bagNClouds.fillBag(10);
        bagNClouds.studentsBagToCloud();
        String s= "ciao";

        message = messageGenerator.cloudSelection(s, numPlayers, bagNClouds);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INPUT), "testNotValidCloud failed");
        assertEquals("not valid Input, the input must be an int between (0 - " + (numPlayers - 1) + ")", message.get(MessageType.NOT_VALID_INPUT), "testNotValidCloud failed");
    }

    /**
     * CharacterCardSelection:
     * - simpleCases
     * - notValidIndex
     * - notValidInput
     */
    @Test
    @DisplayName("simple cases characterCardSelection")
    void testCorrectCC() {
        Map<MessageType, String> message;
        String s= "0";

        message = messageGenerator.characterCardSelection(s);
        assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectCC failed");
        assertEquals("Card " + s + " selected", message.get(MessageType.CORRECT_INPUT), "testCorrectCC failed");
    }

    @Test
    @DisplayName("notValidIndex characterCardSelection")
    void testNotValidIndexCC() {
        Map<MessageType, String> message;
        String s= "8";

        message = messageGenerator.characterCardSelection(s);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INDEX), "testNotValidIndexCC failed");
        assertEquals("Not valid index, the input must be 0 or 1 or 2", message.get(MessageType.NOT_VALID_INDEX), "testNotValidIndexCC failed");
    }

    @Test
    @DisplayName("notValidInput characterCardSelection")
    void testNotValidCC() {
        Map<MessageType, String> message;
        String s= "ciao";

        message = messageGenerator.characterCardSelection(s);
        assertTrue(message.containsKey(MessageType.NOT_VALID_INPUT), "testNotValidCC failed");
        assertEquals("Not valid input, the input must be 0 or 1 or 2", message.get(MessageType.NOT_VALID_INPUT), "testNotValidCC failed");
    }

    /**
     * AssistantSelection:
     * - simpleCases
     * - alreadyPlayedThisTurn
     * - alreadyPlayed
     * - notValidIndex
     * - notValidInput
     */
    @Test
    @DisplayName("simple cases assistantSelection")
    void testCorrectAssistant() {
        Map<MessageType, String> message;
        Player player = new Player();
        int[] cardsPlayed = {1, 2};
        int numPlayers = 2;
        String s= "0";

        message = messageGenerator.assistantSelection(s, 2, cardsPlayed, player.getAssistants());
        assertTrue(message.containsKey(MessageType.CORRECT_INPUT), "testCorrectAssistant failed");
        assertEquals(s + " card selected", message.get(MessageType.CORRECT_INPUT), "testCorrectAssistants failed");
    }

    @Test
    @DisplayName("already selected assistant this turn assistantSelection")
    void testSelectedThisTurnAssistant() {
        Map<MessageType, String> message;
        Player player = new Player();
        int[] cardsPlayed = {1, 2};
        int numPlayers = 2;
        String s= "1";

        message = messageGenerator.assistantSelection(s, 2, cardsPlayed, player.getAssistants());
        assertTrue(message.containsKey(MessageType.ALREADY_PLAYED_THIS_TURN), "testSelectedThisTurnAssistant failed");
        assertEquals("Another player has already played this card in this round", message.get(MessageType.ALREADY_PLAYED_THIS_TURN), "testSelectedThisTurnAssistant failed");
    }

    @Test
    @DisplayName("already selected assistant assistantSelection")
    void testSelectedAssistant() {
        Map<MessageType, String> message;
        Player player = new Player();
        int[] cardsPlayed = {5, 2};
        player.fixHand(1);
        int numPlayers = 2;
        String s= "1";

        message = messageGenerator.assistantSelection(s, 2, cardsPlayed, player.getAssistants());
        assertTrue(message.containsKey(MessageType.ALREADY_PLAYED), "testSelectedAssistant failed");
        assertEquals("You have already played this card", message.get(MessageType.ALREADY_PLAYED), "testSelectedAssistant failed");
    }

    @Test
    @DisplayName("notValidIndex assistantSelection")
    void testNotValidIndexAssistant() {
        Map<MessageType, String> message;
        Player player = new Player();
        int[] cardsPlayed = {5, 2};
        player.fixHand(1);
        int numPlayers = 2;
        String s= "16";

        message = messageGenerator.assistantSelection(s, 2, cardsPlayed, player.getAssistants());
        assertTrue(message.containsKey(MessageType.NOT_VALID_INDEX), "testNotValidIndexAssistant failed");
        assertEquals("Not valid card value", message.get(MessageType.NOT_VALID_INDEX), "testNotValidIndexAssistant failed");
    }

    @Test
    @DisplayName("notValidInput assistantSelection")
    void testNotValidAssistant() {
        Map<MessageType, String> message;
        Player player = new Player();
        int[] cardsPlayed = {5, 2};
        player.fixHand(1);
        int numPlayers = 2;
        String s= "ciao";

        message = messageGenerator.assistantSelection(s, 2, cardsPlayed, player.getAssistants());
        assertTrue(message.containsKey(MessageType.NOT_VALID_INPUT), "testNotValidAssistant failed");
        assertEquals("Not valid format, you must select an int between 0 and " + (Constants.NUMBER_OF_ASSISTANT_CARDS - 1), message.get(MessageType.NOT_VALID_INPUT), "testNotValidAssistant failed");
    }
}
