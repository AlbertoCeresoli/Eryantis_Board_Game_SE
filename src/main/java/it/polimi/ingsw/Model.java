package it.polimi.ingsw;

public class Model {
    private static Model model;
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
        //[!!!]bagNClouds = new BagNClouds(gamerules[0]); [!!!]
	    //islandInteraction(nTowers)
        islandInteraction = new IslandInteraction(gamerules[2], gamerules[0]);
        //se la gamemode è hard
        //chiama draw3CC
        if(gamerules[4] == 1){
            this.drawCharacterCard();
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
            temp = BagNClouds.drawStudents(gamerules[1]);
            playerInteraction.getPlayers().get(i).board.addStudents(temp);
        }
    }

    private void initializeIsland() {
        BagNClouds.fillBag(2);
        int[] temp;
        temp = new int[5];
        //Per 10 volte:
        for(int i = 0; i < 10; i++){
            temp = BagNClouds.drawStudents(1);
            islandInteraction.getIslands().get(i).addStudents(temp);
        }
        //chiama islandInteracrion... passando in ingresso il vettore restituito da drawstudents
        bagNClouds.fillBag(24);
    }

    private void drawCharacterCard() {

    }

}
