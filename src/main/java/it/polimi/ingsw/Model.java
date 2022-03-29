package it.polimi.ingsw;

public class Model {
    private static Model model;
    private PlayerInteraction playerInteraction;
    private CharacterCards[] characterCards;
    private BagNClouds bagNClouds;
    private IslandInteraction islandInteraction;
    private int[] gamerules;

    public Model(int[] gamerules) {
        this.gamerules = gamerules;
        // Chiama il costruttore di:
	    //PlayerInteraction(nPlayer)
        //playerInteraction = new PlayerInteraction();
	    //bagNClouds(nPlayer)
        //bagNClouds = new BagNClouds();
	    //islandInteraction(nTowers)
        islandInteraction = new IslandInteraction(gamerules[2], gamerules[0]);
        //se la gamemode è hard
        //chiama draw3CC

    }

}
