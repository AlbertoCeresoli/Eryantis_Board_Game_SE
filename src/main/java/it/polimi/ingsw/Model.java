package it.polimi.ingsw;

import it.polimi.ingsw.Cards.*;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Exceptions.EndGameException;

import java.util.*;

public class Model {
    private final PlayerInteraction playerInteraction;
    private CharacterCards[] characterCards;
    private BagNClouds bagNClouds;
    private final IslandInteraction islandInteraction;
    public final int[] gameRules;

    /**
     * Model Constructor, it initializes everything
     *
     * @param gameRules     array with specific constants referred to the played gamemode
     */
    public Model(int[] gameRules) {
        this.gameRules = gameRules;
        // calls PlayerInteraction's constructor (input: nPlayer)
        playerInteraction = new PlayerInteraction(this.gameRules[0]);
        //calls bagNClouds' constructor(input: nPlayer)
        bagNClouds = new BagNClouds(this.gameRules[0]);
        //calls islandInteraction's constructor (input: nTowers)
        islandInteraction = new IslandInteraction(this.gameRules[2], this.gameRules[0]);
        // if the gamemode is hard: calls draw3CC
        if (this.gameRules[4] == 1) {
            this.characterCards = this.drawCharacterCards();
        }

    }

    /**
     *  initializeGame calls other initializer methods and instantiates bagNClouds
     * @return true if everything gone good
     */
    public boolean initializeGame() {
        //Calls initializeIsland
        initializeIsland();
        //Calls initializeEntrance
        initializeEntrance();
        return true;
    }

    /**
     *  initializeEntrance sets the entrances of all players' boards
     */
    private void initializeEntrance() {
        //creates a temporary HashMap
        Map<Colors, Integer> temp;
        //for the number of players:
        for (int i = 0; i < gameRules[0]; i++) {
            temp = bagNClouds.drawStudents(gameRules[1]);
            playerInteraction.getPlayers().get(i).getBoard().addToEntrance(temp);
        }
    }

    /**
     *  initializeIsland sets the first students in the islands,
     *  then fills the bags with the correct amount of remaining students
     */
    private void initializeIsland() {
        bagNClouds.fillBag(2);
        Map<Colors, Integer> temp;
        //for 10 times: calls islandInteraction and add the students drawn from the bag
        for (int i = 0; i < 10; i++) {
            temp = bagNClouds.drawStudents(1);
            islandInteraction.getIslands().get(i).addStudents(temp);
        }
        bagNClouds.fillBag(24);
    }

    /**
     *      this moves 1 student per time from the entrance to the hall
     *      then it checks whether the control of the teacher changes
     *      if the student placed is the 3rd, 6th or 9th of that color, it adds a coin to that player
     * @param studColor color of the student to be moved
     * @param player    player who moves the student
     */
    public boolean moveFromEntranceToHall(Colors studColor, int player) {
        //creates the map with the student to move
        Map<Colors, Integer> temp = new HashMap<>();
        for (Colors c : Colors.values()){
            temp.put(c, 0);
        }
        temp.put(studColor, 1);
        ArrayList<Integer> newTeacherController;

        //remove from entrance
        playerInteraction.getPlayers().get(player).getBoard().removeFromEntrance(temp);

        //add to hall
        playerInteraction.getPlayers().get(player).getBoard().addToHall(temp);
        newTeacherController = playerInteraction.checkTeacher(studColor, player);
        if (newTeacherController.size()==1){
            islandInteraction.setTeacher(newTeacherController.get(0), studColor);
        }

        //check for the addCoin
        if(playerInteraction.getPlayers().get(player).getBoard().getStudHall().get(studColor)%3 == 0) {
            playerInteraction.getPlayers().get(player).addCoin();
        }
        return true;
    }

    public PlayerInteraction getPlayerInteraction() {
        return playerInteraction;
    }

    /**
     *  this moves 1 student per time from the entrance to the island
     * @param studColor     color of the student to be moved
     * @param player    player who moves the student
     * @param island    index of the island where the student will be moved to
     */
    public boolean moveFromEntranceToIsland(Colors studColor, int player, int island) {
        //creates the map with the student to move
        Map<Colors, Integer> temp = new HashMap<>();
        for (Colors c : Colors.values()){
            temp.put(c, 0);
        }
        temp.put(studColor, 1);

        //remove from entrance
        playerInteraction.getPlayers().get(player).getBoard().removeFromEntrance(temp);
        //add to island
        islandInteraction.getIslands().get(island).addStudents(temp);

        return true;
    }

    public BagNClouds getBagNClouds() {
        return bagNClouds;
    }

    /**
     *  this move the entire array of students to the entrance of the caller
     * @param player    index of the player who moves the cloud
     * @param cloudIndex     index of th cloud he gets
     */
    public boolean studentsCloudToEntrance(int player, int cloudIndex) {
        //creates the map with the students to move
        Map<Colors, Integer> temp = new HashMap<>();
        for(Colors c : Colors.values()){
            temp.put(c, bagNClouds.getClouds().get(cloudIndex).get(c));
        }
        //remove the students from the cloud
        bagNClouds.resetCloud(cloudIndex);
        //calls addToEntrance(temp)
        playerInteraction.getPlayers().get(player).getBoard().addToEntrance(temp);
        return true;
    }

    /**
     *  check if one of the statements which determine the end of the game are satisfied
     *  -no students left in the bag
     *  -a player used every assistant card in his hand
     * @return true if the game need to finish
     */
    public boolean endGame() {
        if (bagNClouds.isEmpty())
            return true;
        for (int i = 0; i < playerInteraction.getPlayers().size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (playerInteraction.getPlayers().get(i).getAssistants().get(j).getCardState() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *  finds the player with the less amount of towers left in his board
     *  in case of draw, finds the one who controls more teachers
     * @return the index of the winner player
     */
    public int getWinner() {
        int winner = -1;
        int winnerTowers = gameRules[2];
        for (int i = 0; i < playerInteraction.getPlayers().size(); i++) {
            if (islandInteraction.getTowersByPlayer()[i] < winnerTowers) {
                winner = i;
                winnerTowers = islandInteraction.getTowersByPlayer()[i];
            }
        }
        int[] nTeachers = new int[playerInteraction.getPlayers().size()];
        for (Colors c : Colors.values()) {
            nTeachers[islandInteraction.getTeachers().get(c)]++;
        }
        int i = 0;
        OptionalInt maxT;
        while (i >= 0 && i < nTeachers.length) {
            if(i != winner) {
                if (islandInteraction.getTowersByPlayer()[i] == winnerTowers) {
                    maxT = Arrays.stream(nTeachers).max();
                    for (int j = 0; j < nTeachers.length; j++) {
                        if(j != winner) {
                            if (nTeachers[j] == maxT.getAsInt() && nTeachers[winner] != maxT.getAsInt()) {
                                return j;
                            }
                            if (nTeachers[j] == maxT.getAsInt() && nTeachers[winner] == maxT.getAsInt()) {
                                return -1;
                            }
                        }
                    }
                }
            }
            i++;
        }
        return winner;
    }

    public IslandInteraction getIslandInteraction() {
        return islandInteraction;
    }

    /**
     *  draw and instantiates 3 random character cards out of the 12 available
     * @return  a CharacterCards[] array containing the 3 drawn cards
     */
    public CharacterCards[] drawCharacterCards() {
        CharacterCards[] cards;
        cards = new CharacterCards[3];
        Map<Colors, Integer> studs;

        Random random = new Random();
        int rnd;
        ArrayList<Integer> bucket;
        bucket = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            bucket.add(i);
        }

        for (int i = 0; i < 3; i++) {
            int position = random.nextInt(bucket.size());
            rnd = bucket.get(position);
            bucket.remove(position);
            switch (rnd) {
                case 0:
                    studs = bagNClouds.drawStudents(Constants.CARD1_STUDENTS_CAPACITY);
                    cards[i] = new Card1(1, islandInteraction, bagNClouds, studs);
                    break;
                case 1:
                    cards[i] = new Card2(2, playerInteraction);
                    break;
                case 2:
                    cards[i] = new Card3(3, islandInteraction);
                    break;
                case 3:
                    cards[i] = new Card4(1, playerInteraction);
                    break;
                case 4:
                    cards[i] = new Card5(2, islandInteraction);
                    break;
                case 5:
                    cards[i] = new Card6(3, islandInteraction);
                    break;
                case 6:
                    studs = bagNClouds.drawStudents(Constants.CARD7_STUDENTS_CAPACITY);
                    cards[i] = new Card7(1, playerInteraction, studs);
                    break;
                case 7:
                    cards[i] = new Card8(2, islandInteraction);
                    break;
                case 8:
                    cards[i] = new Card9(3, islandInteraction);
                    break;
                case 9:
                    cards[i] = new Card10(1, playerInteraction, islandInteraction);
                    break;
                case 10:
                    studs = bagNClouds.drawStudents(Constants.CARD11_STUDENTS_CAPACITY);
                    cards[i] = new Card11(2, playerInteraction, islandInteraction, bagNClouds, studs);
                    break;
                case 11:
                    cards[i] = new Card12(3, playerInteraction, bagNClouds);
                    break;
            }
        }
        return cards;
    }
    /**
     *  moves mother nature adding the steps choose by the player [mod n] where n is the number of islands "left"
     * @param steps number of steps Mother Nature has to do
     */
    public boolean moveMN(int steps) throws EndGameException {
        int MN = islandInteraction.getMotherNature();
        MN += steps;
        MN %= (islandInteraction.getIslands().size());
        islandInteraction.setMotherNature(MN);
        if(islandInteraction.getIslands().get(MN).getInhibitionCards() > 0){
            islandInteraction.getIslands().get(MN).removeInhibitionCard();
            islandInteraction.addInhibitionCard();
            return true;
        }
        if (islandInteraction.getIslands().get(MN).getInhibitionCards() == 0){
            islandInteraction.calculateInfluence(MN, gameRules[0]);
        }
        return true;
    }
}
