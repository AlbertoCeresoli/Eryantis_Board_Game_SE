package it.polimi.ingsw;

public class Model {
    private static Model model;
    private PlayerInteraction playerInteraction;
    private CharacterCards[] characterCards;
    private BagNClouds bagNClouds;
    private IslandInteraction islandInteraction;
    private int[] gamerules;

    private Model(int[] gamerules) {
        this.gamerules = gamerules;
        /*
        Chiama il costruttore di:
	    PlayerInteraction(nPlayer)
	    bagNClouds(nPlayer)
	    islandInteraction(nTowers)
        se la gamemode è hard
        chiama draw3CC
         */
    }

    public static Model GetIstance(){
        if(model == null) {
            model = new Model(model.gamerules);
        }

        // returns the singleton object
        return model;
    }
}
