package it.polimi.ingsw.Constants;

public enum Cards {
    MONK("Monk"),
    HERALD("Herald"),
    MAGIC_POSTMAN("Magic Postman"),
    GRANDMA_HERBS("Grandma Herbs"),
    CENTAUR("Centaur"),
    JOKER("Joker"),
    KNIGHT("Knight"),
    MUSHROOMS_MAN("Mushrooms Man"),
    MINSTREL("Minstrel"),
    SPOILED_PRINCESS("Spoiled Princess"),
    THIEF("Thief"),
    FARMER("Farmer");

    private final String name;

    Cards(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
