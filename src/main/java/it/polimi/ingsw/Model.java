package it.polimi.ingsw;

import it.polimi.ingsw.Cards.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Model {
    private final PlayerInteraction playerInteraction;
    private CharacterCards[] characterCards;
    private BagNClouds bagNClouds;
    private final IslandInteraction islandInteraction;
    private final int[] gamerules;

    /**
     * Model Constructor, it initilialize everything
     *
     * @param gamerules     array with specific constants referred to the played gamemode
     */
    public Model(int[] gamerules) {
        this.gamerules = gamerules;
        // Chiama il costruttore di:
        //PlayerInteraction(nPlayer)
        playerInteraction = new PlayerInteraction(gamerules[0]);
        //bagNClouds(nPlayer)
        bagNClouds = new BagNClouds(gamerules[0]);
        //islandInteraction(nTowers)
        islandInteraction = new IslandInteraction(gamerules[2], gamerules[0]);
        //se la gamemode è hard
        //chiama draw3CC
        if (gamerules[4] == 1) {
            this.characterCards = this.drawCharacterCards();
        }

    }

    /**
     *  initializeGame calls other initializer methods and instantiates bagNClouds
     * @return true if everything gone good
     */
    public boolean initializeGame() {
        //Chiama initializeIsland
        initializeIsland();
        //Chiama initializeEntrance
        initializeEntrance();
        //Chiama il costruttore bagNClouds(numero di giocatori)

        return true;
    }

    /**
     *  initializeEntrance sets the entrances of all players' boards
     */
    private void initializeEntrance() {
        //crea un array temporaneo di 5 interi
        int[] temp;
        //per il numero dei giocatori:
        for (int i = 0; i < gamerules[0]; i++) {
            temp = bagNClouds.drawStudents(gamerules[1]);
            playerInteraction.getPlayers().get(i).getBoard().addToEntrance(temp);
        }
    }

    /**
     *  initializeIsland sets the first students in the islands,
     *  then fills the bags with the correct amount of remaining students
     */
    private void initializeIsland() {
        bagNClouds.fillBag(2);
        int[] temp;
        //Per 10 volte:
        for (int i = 0; i < 10; i++) {
            temp = bagNClouds.drawStudents(1);
            islandInteraction.getIslands().get(i).addStudents(temp);
        }
        //chiama islandInteracrion... passando in ingresso il vettore restituito da drawstudents
        bagNClouds.fillBag(24);
    }

    /**
     *      this moves 1 student per time from the entrance to the hall
     *      then it checks whether the control of the teacher changes
     *      if the student placed is the 3rd, 6th or 9th of that color, it adda a coin to that player
     * @param studColor color of the student to be moved
     * @param player    player who moves the student
     */
    public void moveFromEntranceToHall(int studColor, int player) {

        //preparo il vettore corrispondente allo studente da spostare
        int[] temp;
        temp = new int[5];
        temp[studColor]++;
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
        if(playerInteraction.getPlayers().get(player).getBoard().getStudHall()[studColor]%3 == 0) {
            playerInteraction.getPlayers().get(player).addCoin();
        }
    }

    /**
     *  this moves 1 student per time from the entrance to the island
     * @param studColor     color of the student to be moved
     * @param player    player who moves the student
     * @param island    index of the island wher the student will be moved to
     */
    public void moveFromEntranceToIsland(int studColor, int player, int island) {

        //preparo il vettore corrispondente allo studente da spostare
        int[] temp;
        temp = new int[5];
        temp[studColor]++;
        //remove from entrance
        playerInteraction.getPlayers().get(player).getBoard().removeFromEntrance(temp);
        //add to island
        islandInteraction.getIslands().get(island).addStudents(temp);
    }

    /**
     *  this move the entire array of students to the entrance of the caller
     * @param player    index of the player who moves the cloud
     * @param cloud     index of th cloud he gets
     */
    public void studentsCloudToEntrance(int player, int cloud) {

        //prepara il vettore di studenti da trasportere
        int[] temp;
        temp = bagNClouds.getCloud(cloud);
        //svuoto la nuvola
        bagNClouds.resetCloud(cloud);
        //add to Entrance di temp
        playerInteraction.getPlayers().get(player).getBoard().addToEntrance(temp);

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
                if (playerInteraction.getPlayers().get(i).getAssistants()[1][j] != 0) {
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
        int winnerTowers = 8;
        for (int i = 0; i < playerInteraction.getPlayers().size(); i++) {
            if (islandInteraction.getTowersByPlayer()[i] < winnerTowers) {
                winner = i;
                winnerTowers = islandInteraction.getTowersByPlayer()[i];
            }
            if (islandInteraction.getTowersByPlayer()[i] == winnerTowers) {
                Arrays.sort(islandInteraction.getTowersByPlayer());
                int current = 0;
                int maxFrequency = 1;
                int count = 1;
                for (int j = 1; j < 5; j++) {
                    if (islandInteraction.getTowersByPlayer()[j] == islandInteraction.getTowersByPlayer()[j - 1]) {
                        count++;
                        current = islandInteraction.getTowersByPlayer()[j];
                    } else if (count > maxFrequency) {
                        winner = current;
                        maxFrequency = count;
                        count = 1;
                    }
                }
            }
        }
        return winner;
    }

    /**
     *  draw and instantiates 3 random character cards out of the 12 available
     * @return  a CharacterCards[] array containing the 3 drawn cards
     */
    public CharacterCards[] drawCharacterCards() {
        CharacterCards[] cards;
        cards = new CharacterCards[3];
        int[] studs;
        Random random = new Random();
        int rnd;
        ArrayList<Integer> bucket;
        bucket = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            bucket.add(i);
        }

        for (int i = 0; i < 3; i++) {
            rnd = random.nextInt(bucket.size());
            rnd = bucket.get(rnd);
            bucket.remove(rnd);
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
                    cards[i] = new Card10(1, playerInteraction);
                    break;
                case 10:
                    studs = bagNClouds.drawStudents(Constants.CARD11_STUDENTS_CAPACITY);
                    cards[i] = new Card11(2, playerInteraction, bagNClouds, studs);
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
    public void moveMN(int steps) {
        int MN = islandInteraction.getMotherNature();
        MN += steps;
        MN %= (islandInteraction.getIslands().size());
        islandInteraction.setMotherNature(MN);
        if(islandInteraction.getIslands().get(MN).getInhibitionCards() > 0){
            islandInteraction.getIslands().get(MN).removeInhibitionCard();
            islandInteraction.addInhibitionCard();
            return;
        }
        if (islandInteraction.getIslands().get(MN).getInhibitionCards() == 0){
            islandInteraction.calculateInfluence(MN);
        }
    }
}
