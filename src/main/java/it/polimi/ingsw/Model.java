package it.polimi.ingsw;

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

    private void drawCharacterCard() {

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

}
