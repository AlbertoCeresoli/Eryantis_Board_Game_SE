package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Model.Cards.CharacterCards;
import it.polimi.ingsw.Model.Island.Island;
import it.polimi.ingsw.Model.Player.AssistantCard;
import it.polimi.ingsw.Model.Player.Board;

import java.util.ArrayList;
import java.util.Map;

/**
 * The class is used to create messages after a print request received from the client. Each method returns a string
 * that will be sent to the client then as server's response
 */
public class PrintMessageGenerator {

    public static String printAssistantCards(ArrayList<AssistantCard> assistantCards) {
        String msg = "";

        int availableCards = 0;

        for (int i = 0; i < assistantCards.size(); i++) {
            if (assistantCards.get(i).getCardState() == 2)
                availableCards++;
        }

        msg += availableCards + "\n";

        for (int i = 0; i < assistantCards.size() - 1; i++) {
            if (assistantCards.get(i).getCardState() == 2) {
                msg += (i + 1) + "\n";
                msg += assistantCards.get(i).getPriority() + "\n";
                msg += assistantCards.get(i).getSteps() + "\n";
            }
        }

        msg += (assistantCards.size() -1 + 1) + "\n";
        msg += assistantCards.get(assistantCards.size() - 1).getPriority() + "\n";
        msg += assistantCards.get(assistantCards.size() - 1).getSteps();

        return msg;
    }



    /**
     * Gets all information from objects in input in order to create a string (containing data of the island) that will be parsed,
     * elaborated and then printed on client side
     *
     * Data are: island index, mother nature is on the island or not, number of students on the island
     * for each color, island's controller (nobody if not conquered yet), number of towers and number of inhibition cards
     *
     * @param island that will be printed and for which message will be created
     * @param islandIndex is island's index with respect to array list of islands saved in model
     * @param MN is the island where mother nature is
     * @param players is the map that keep saved correspondence between player index saved in model and nickname of that player
     *
     * @return the string that will be sent to the client that made the request of printing and island and then printed
     */
    public static String printIsland(Island island, int islandIndex, boolean MN, Map<Integer, String> players) {
        //string where information will be added
        String msg = "";

        //adds island's index with respect to the array list saved in the model
        msg += islandIndex + "\n";

        //adds true or false depending on if mother nature is on the island or not
        msg += MN + "\n";

        //adds the number of students on the island for each color
        for (Colors c : Colors.values()) {
            msg += island.getStudents().get(c) + "\n";
        }

        //adds the nickname of island's controller. If it has not been conquered yet, "nobody" will be added
        if (island.getControllerIndex() == -1)
            msg += "Nobody" + "\n";
        else
            msg += players.get(island.getControllerIndex()) + "\n";

        //adds number of towers on the island
        msg += island.getnTowers() + "\n";

        //adds number of inhibition cards on the island
        msg += island.getInhibitionCards();

        return msg;
    }

    /**
     * Gets all information from objects in input in order to create a string (containing data of all islands)
     * that will be parsed, elaborated and then printed on client side
     *
     * Data are: data for each island (see printIsland method's javadoc for island's data)
     *
     * @param islands is an arraylist containing all islands
     * @param MNIndex is island's index where mother nature is at the moment
     * @param players is the map that keep saved correspondence between
     *                player index saved in model and nickname of that player
     * @return the string that will be sent to the client that made the request and then printed
     */
    public static String printAllIslands(ArrayList<Island> islands, int MNIndex, Map<Integer, String> players) {
        //string where information will be added
        String msg = "";

        msg += islands.size() + "\n";

        //will indicates if mother nature is on actual island or not
        boolean MN;

        //adds the string returned by printIsland for each island
        for (int i = 0; i < islands.size(); i++) {
            MN = MNIndex == i;
            msg += printIsland(islands.get(i), i, MN, players) + "\n";
        }

        return msg;
    }

    /**
     * Gets all information from objects in input in order to create a string
     * (containing data of the board) that will be parsed, elaborated and then printed on client side
     *
     * Data are: nickname, number of student in the entrance for each color,
     * number of students in the hall for each color, number of towers on the board
     *
     * @param board that will be printed and for which message will be created
     * @param nickname of the player that owns the board
     * @param towers on the board
     * @return the string that will be sent to the client that made the request and then printed
     */
    public static String printBoard(Board board, String nickname, int towers) {
        //string where information will be added
        String msg = "";

        //adds nickname of the player
        msg += nickname + "\n";

        //adds number of student in the entrance of each color
        for (Colors c : Colors.values()) {
            msg += board.getStudEntrance().get(c) + "\n";
        }

        //adds number of student in the hall of each color
        for (Colors c : Colors.values()) {
            msg += board.getStudHall().get(c) + "\n";
        }

        //adds number of towers on the board
        msg += towers + "\n";

        return msg;
    }

    /**
     * Gets all information from objects in input in order to create a string
     * (containing data of all boards) that will be parsed, elaborated and then printed on client side
     *
     * Data are: data for each board (see printBoard method's javadoc for board's data)
     *
     * @param boards is an arraylist containing all boards
     * @param players is the map that keep saved correspondence between
     *                player index saved in model and nickname of that player
     * @param towers is an array containing the number of towers for each player
     * @return the string that will be sent to the client that made the request and then printed
     */
    public static String printAllBoards(ArrayList<Board> boards, Map<Integer, String> players, int[] towers) {
        //string where information will be added
        String msg = "";

        //adds string return by printBoard for each player
        for (int i = 0; i < boards.size(); i++) {
            msg += printBoard(boards.get(i), players.get(i), towers[i]) + "\n";
        }

        return msg;
    }

    /**
     * Gets all information from objects in input in order to create a string
     * (containing data of all clouds) that will be parsed, elaborated and then printed on client side
     *
     * Data are: number of clouds, number of student of each color on the cloud (for each cloud)
     *
     * @param clouds is an arraylist of maps containing students on each cloud
     * @return the string that will be sent to the client that made the request and then printed
     */
    public static String printClouds(ArrayList<Map<Colors, Integer>> clouds) {
        //string where information will be added
        String msg = "";

        //adds number of clouds in the game
        msg += clouds.size() + "\n";

        //adds number of student of each color for each island
        for (Map<Colors, Integer> cloud : clouds) {
            for (Colors c : Colors.values()) {
                msg += cloud.get(c) + "\n";
            }
        }

        return msg;
    }

    /**
     * Gets all information from objects in input in order to create a string
     * (containing data of teachers) that will be parsed, elaborated and then printed on client side
     *
     * Association between player that control a teacher and teacher's color is implicit, follows enumeration's order
     *
     * Data are: nickname of player that controls the teacher (for each color)
     *
     * @param teachers is a map containing index of the player that controls the teacher for each color
     * @param players is the map that keep saved correspondence between
     *                player index saved in model and nickname of that player
     * @return the string that will be sent to the client that made the request and then printed
     */
    public static String printTeachers(Map<Colors, Integer> teachers, Map<Integer, String> players) {
        //string where information will be added
        String msg = "";

        //adds nickname of player that owns the teacher, for each color
        for (Colors c : Colors.values()) {
            msg += players.get(teachers.get(c)) + "\n";
        }

        return msg;
    }

    public static String printCharacterCards(CharacterCards[] characterCards) {
        String msg = "";
        int i;

        for (i = 0; i < Constants.NUMBER_OF_CHARACTER_CARDS; i++) {
            msg += characterCards[i].getName() + "\n";
            msg += characterCards[i].getCost() + "\n";

            if (characterCards[i].getStudents() != null) {
                msg += 1 + "\n";
                for (Colors c : Colors.values()) {
                    msg += characterCards[i].getStudents().get(c) + "\n";
                }
            }
            else {
                msg += 0 + "\n";
            }
        }

        return msg;
    }
}
