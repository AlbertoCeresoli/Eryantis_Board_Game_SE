package it.polimi.ingsw;

import it.polimi.ingsw.Cards.*;

import java.util.Arrays;
import java.util.Random;

public class Model {
    private PlayerInteraction playerInteraction;
    private CharacterCards[] characterCards;
    private BagNClouds bagNClouds;
    private IslandInteraction islandInteraction;
    private int[] gamerules;

        //CONSTRUCTOR: MODEL
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
        if(gamerules[4] == 1){
            this.characterCards = this.drawCharacterCards();
        }

    }

    public void initializeGame(){
        //Chiama initializeIsland
        initializeIsland();
        //Chiama initializeEntrance
        initializeEntrance();
        //Chiama il costruttore bagNClouds(numero di giocatori)
        bagNClouds = new BagNClouds(gamerules[0]);
    }

    private void initializeEntrance() {
        //crea un array temporaneo di 5 interi
        int[] temp;
        temp = new int[5];
        //per il numero dei giocatori:
        for (int i = 0; i < gamerules[0]; i++) {
            temp = bagNClouds.drawStudents(gamerules[1]);
            playerInteraction.getPlayers().get(i).getBoard().addToEntrance(temp);
        }
    }

    private void initializeIsland() {
        bagNClouds.fillBag(2);
        int[] temp;
        temp = new int[5];
        //Per 10 volte:
        for(int i = 0; i < 10; i++){
            temp = bagNClouds.drawStudents(1);
            islandInteraction.getIslands().get(i).addStudents(temp);
        }
        //chiama islandInteracrion... passando in ingresso il vettore restituito da drawstudents
        bagNClouds.fillBag(24);
    }

    public void moveFromEntranceToHall(int studColor, int player){

        //preparo il vettore corrispondente allo studente da spostare
        int[] temp;
        temp = new int[5];
        temp[studColor]++;
        //remove from entrance
        playerInteraction.getPlayers().get(player).getBoard().removeStudent(temp);
        //add to hall
        playerInteraction.getPlayers().get(player).getBoard().addToHall(temp);
        //TODO checkteacher
        playerInteraction.checkTeacher(studColor);
        //TODO aggiungi coin se multiplo di 3
    }

    public void moveFromEntranceToIsland(int studColor, int player, int island){

        //preparo il vettore corrispondente allo studente da spostare
        int[] temp;
        temp = new int[5];
        temp[studColor]++;
        //remove from entrance
        playerInteraction.getPlayers().get(player).getBoard().removeStudent(temp);
        //add to island
        islandInteraction.getIslands().get(island).addStudents(temp);
    }

    public void studentsCloudToEntrance(int player, int cloud){

        //prepara il vettore di studenti da trasportere
        int[] temp;
        temp = new int[5];
        temp = bagNClouds.getCloud(cloud);
        //svuoto la nuvola
        bagNClouds.resetCloud(cloud);
        //add to Entrance di temp
        playerInteraction.getPlayers().get(player).getBoard().addToEntrance(temp);

    }

    public boolean endGame(){
        if(bagNClouds.isEmpty())
            return true;
        for (int i = 0; i < playerInteraction.getPlayers().size(); i++){
            for(int j = 0; j < 10; j++){
                if(playerInteraction.getPlayers().get(i).getAssistants()[1][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public int getWinner(){
        int winner = -1;
        int winnerTowers = 8;
        for (int i = 0; i < playerInteraction.getPlayers().size(); i++){
            if(islandInteraction.getNtowers()[i] < winnerTowers){
                winner = i;
                winnerTowers = islandInteraction.getNtowers()[i];
            }
            if(islandInteraction.getNtowers()[i] == winnerTowers){
                Arrays.sort(islandInteraction.getNtowers());
                int current = 0;
                int maxFrequency = 1;
                int count = 1;
                for (int j = 1; j < 5; j++){
                    if(islandInteraction.getNtowers()[j] == islandInteraction.getNtowers()[j-1]){
                        count++;
                        current = islandInteraction.getNtowers()[j];
                    }else if(count > maxFrequency){
                        winner = current;
                        maxFrequency = count;
                        count = 1;
                    }
                }
            }
        }
        return winner;
    }

    public CharacterCards[] drawCharacterCards(){
        CharacterCards[] cards;
        cards = new CharacterCards[3];
        int capacity;
        int[] studs;
        studs = new int[5];
        Random random = new Random();
        int rnd;

        for(int i = 0; i < 3; i++){
             rnd = random.nextInt(12);
             switch(rnd){
                 case 1:
                     studs = bagNClouds.drawStudents(Constants.CARD1_STUDENTS_CAPACITY);
                     cards[i] = new Card1(1, islandInteraction, bagNClouds, studs);
                     break;
                 case 0:
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
                     cards[i] = new Card12(3,playerInteraction, bagNClouds);
                     break;
             }
        }
        return cards;
    }

    public void moveMN(int steps){
        int MN = islandInteraction.getMotherNature();
        MN += steps;
        MN %= (islandInteraction.getIslands().size());
        islandInteraction.setMotherNature(MN);
    }
}
