package it.polimi.ingsw;

import it.polimi.ingsw.Teacher.NormalCheck;
import it.polimi.ingsw.Teacher.TeacherInterface;

import java.util.ArrayList;

public class PlayerInteraction {
    private ArrayList<Player> players;
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
     * test cases: TODO
     */

    /**
     * Creates a temporary arraylist of integers
     * it collects in temp all the students with the specified color in the hall of each player
     * calls the correct checkTeacher following the TeacherInterface
     */
    public ArrayList<Integer> checkTeacher(int studColor){
        ArrayList<int[]> temp = new ArrayList<>();
        for (int i=0; i < players.size(); i++){
            temp.add((players.get(i)).getBoard().getStudHall());
        }
        return teacherInterface.checkTeacher(temp, studColor);
    }
    /**
     * Test cases: TODO
     */

    /**
     * Per ogni giocatore:
     *     player[i].FixHand[Cards[i]]
     *     Calcola l’ordine di gioco dei players e lo restituisce in output
     */
    public int[] playAssistantCard(int[] cards){
        int[] playerOrder = new int[players.size()];
        int temp;

        for (int i=0; i< players.size(); i++){
            players.get(i).fixHand(cards[i]);
        }

        // riempire e ordinare il vettore (programmazione funzionale???) TODO

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

    public void setTeacherInterface(TeacherInterface teacherInterface) {
        this.teacherInterface = teacherInterface;
    }
}
