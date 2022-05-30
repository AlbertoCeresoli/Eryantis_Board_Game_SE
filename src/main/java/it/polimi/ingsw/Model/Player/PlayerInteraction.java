package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Model.Teacher.NormalCheck;
import it.polimi.ingsw.Model.Teacher.TeacherInterface;

import java.util.*;
import java.util.Map;


public class PlayerInteraction implements hasSetTeacherInterface, hasEntrance, hasHall, hasCard12Effect, hasCheckTeacher, hasAddStepsToMNMovement {
    private final ArrayList<Player> players;
    private TeacherInterface teacherInterface;

    /**
     * preset: nPlayers==2 or nPlayers==3 TODO
     * <p>
     * playerInteraction's constructor
     * it creates a Player class for each player and
     * also creates teacherInterface and initialize it to normalCheck
     */
    public PlayerInteraction(int numPlayers) {
        players = new ArrayList<>();
        teacherInterface = new NormalCheck();
        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();
            players.add(player);
        }
    }

    /**
     * preset: 0 <= actualPlayer < nPlayers TODO
     * <p>
     * Creates a temporary arraylist of integers
     * it collects in temp all the students with the specified color in the hall of each player
     * calls the correct checkTeacher following the TeacherInterface
     */
    @Override
    public ArrayList<Integer> checkTeacher(Colors studColor, int actualPlayer) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (Player player : players) {
            temp.add(player.getBoard().getStudHall().get(studColor));
        }
        return teacherInterface.checkTeacher(temp, actualPlayer);
    }

    /**
     * preset: 0 <= cards[i] < NUMBER_OF_ASSISTANT_CARDS TODO
     * <p>
     * for each player:
     * player[i].FixHand[Cards[i]]
     * it calculates the players order for this round
     */
    public int[] playAssistantCard(int[] cards) {
        int[] playerOrder = new int[players.size()];
        ArrayList<Integer> playerIndex = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            players.get(i).fixHand(cards[i]);
        }

        //fill the array playerIndex
        for (int i = 0; i < players.size(); i++) {
            playerIndex.add(i);
        }

        // order the array
        playerIndex.sort((Integer x, Integer y) ->
                players.get(x).getAssistants().get(cards[x]).getPriority() -
                        players.get(y).getAssistants().get(cards[y]).getPriority());
        for (int i = 0; i < playerIndex.size(); i++) {
            playerOrder[i] = playerIndex.get(i);
        }

        return playerOrder;

    }

    /**
     * get and set methods
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public TeacherInterface getTeacherInterface() {
        return teacherInterface;
    }


    @Override
    public void setTeacherInterface(TeacherInterface teacherInterface) {
        this.teacherInterface = teacherInterface;
    }

    @Override
    public void addToEntrance(int playerIndex, Map<Colors, Integer> students) {
        this.players.get(playerIndex).getBoard().addToEntrance(students);
    }

    @Override
    public void removeFromEntrance(int playerIndex, Map<Colors, Integer> students) {
        this.players.get(playerIndex).getBoard().removeFromEntrance(students);
    }

    @Override
    public void addToHall(int playerIndex, Map<Colors, Integer> students) {
        this.players.get(playerIndex).getBoard().addToHall(students);
    }

    @Override
    public void removeFromHall(int playerIndex, Map<Colors, Integer> students) {
        this.players.get(playerIndex).getBoard().removeFromHall(students);
    }

    @Override
    public int card12Effect(Colors color) {
        int count = 0;

        for (Player player : this.players) {
            if (player.getBoard().getStudHall().get(color) <= Constants.THIEF_MAX_STUDENTS_TO_MOVE) {
                count += player.getBoard().getStudHall().get(color);
                player.getBoard().getStudHall().put(color, 0);
            } else {
                count += 3;
                player.getBoard().getStudHall().put(color, player.getBoard().getStudHall().get(color) - Constants.THIEF_MAX_STUDENTS_TO_MOVE);
            }
        }

        return count;
    }

    /**
     * implements the card-4 effect
     * @param playerIndex who uses this effect
     */
    @Override
    public void addStepsToMNMovement(int playerIndex) {
        Player player = getPlayer(playerIndex);
        ArrayList<AssistantCard> assistantCards = player.getAssistants();
        int lastUsedCardIndex = -1;
        for (int i = 0; i < Constants.NUMBER_OF_ASSISTANT_CARDS; i++) {
            if (assistantCards.get(i).getCardState() == 1) {
                lastUsedCardIndex = i;
                i = Constants.NUMBER_OF_ASSISTANT_CARDS;
            }
        }

        assistantCards.get(lastUsedCardIndex).setSteps(assistantCards.get(lastUsedCardIndex).steps + Constants.MAGIC_POSTMAN_ADDITION_MOVEMENT);
    }
}
