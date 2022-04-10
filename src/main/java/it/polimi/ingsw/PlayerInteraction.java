package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Teacher.NormalCheck;
import it.polimi.ingsw.Teacher.TeacherInterface;

import java.util.*;
import java.util.Map;


public class PlayerInteraction implements hasSetTeacherInterface, hasEntrance, hasHall {
    private final ArrayList<Player> players;
    private TeacherInterface teacherInterface;

    /**
     * playerInteraction's constructor
     * it creates a Player class for each player and
     * also creates teacherInterface and initialize it to normalCheck
     */
    public PlayerInteraction (int numPlayers){
        players = new ArrayList<>();
        teacherInterface=new NormalCheck();
        for (int i=0; i<numPlayers; i++){
            Player player = new Player();
            players.add(player);
        }
    }
    /**
     * test cases: TODO??
     */

    /**
     * Creates a temporary arraylist of integers
     * it collects in temp all the students with the specified color in the hall of each player
     * calls the correct checkTeacher following the TeacherInterface
     */
    public ArrayList<Integer> checkTeacher(Colors studColor, int actualPlayer){
        ArrayList<Integer> temp = new ArrayList<>();
        for (Player player : players) {
            temp.add(player.getBoard().getStudHall().get(studColor));
        }
        return teacherInterface.checkTeacher(temp, actualPlayer);
    }
    /**
     * Test cases:
     *      - simple cases
     *      - not acceptable color TODO
     */

    /**
     * for each player:
     *     player[i].FixHand[Cards[i]]
     *     it calculates the players order for this round
     */

    public int[] playAssistantCard(int[] cards){
        int[] playerOrder = new int[players.size()];
        ArrayList<Integer> playerIndex= new ArrayList<>();

        //Already played card controlled in the controller TODO
        for (int i=0; i< players.size(); i++){
            players.get(i).fixHand(cards[i]);
        }


        //fill the array of the played assistant cards
        for (int i=0; i< players.size(); i++){
            playerIndex.add(i);
        }

        // order the array (used SelectionSort)(functional programming TODO??)
        playerIndex.sort((Integer x, Integer y) ->
                                    players.get(x).getAssistants().get(cards[x]).getPriority()-
                                            players.get(y).getAssistants().get(cards[y]).getPriority());

        for(int i=0; i<playerIndex.size(); i++){
            playerOrder[i] = playerIndex.get(i);
        }

        return playerOrder;

    }

    /**
     * Test cases: TODO
     */

    /**
     * get and set methods
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int playerIndex){
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
}
