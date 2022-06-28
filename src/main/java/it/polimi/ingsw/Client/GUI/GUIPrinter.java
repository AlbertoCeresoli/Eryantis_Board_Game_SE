package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Map;

public class GUIPrinter {
    private ArrayList<IslandGUI> islands;
    private ArrayList<CloudGUI> clouds;
    private ArrayList<AssistantCardGUI> assistants;
    private ArrayList<BoardGUI> boards;
    private ArrayList<CoinGUI> coins;
    private ArrayList<CharacterCardGUI> characterCards;

    /**
     * prints all the boards on the stage
     * @param boardImageViews ArrayList of the ImageViews of the boards
     * @param anchorPane anchorPane of the stage
     */
    public void printBoards(ArrayList<ImageView> boardImageViews, AnchorPane anchorPane){
        ArrayList<Image> boardImages = new ArrayList<>();
        boardImages.add(new Image("file:src/main/resources/Images/BOARD.png"));
        boardImages.add(new Image("file:src/main/resources/Images/BOARD.png"));
        if (Constants.getNumPlayers() == 3){
            boardImages.add(new Image("file:src/main/resources/Images/BOARD.png"));
        }

        boards = new ArrayList<>();
        for (int i = 0; i< Constants.getNumPlayers(); i++){
            boards.add(new BoardGUI(boardImageViews.get(i), boardImages.get(i), anchorPane, i));
        }
    }

    /**
     * prints all the islands on the stage
     * @param islandImageViews ArrayList of the ImageViews of the islands
     * @param anchorPane anchorPane of the stage
     */
    public void printIslandsOnTable(ArrayList<ImageView> islandImageViews, AnchorPane anchorPane){
        ArrayList<Image> islandImages = Constants.createArrayImagesIslands();

        islands = new ArrayList<>();
        for (int numIsland = 0; numIsland<12; numIsland++){
            islands.add(new IslandGUI(islandImageViews.get(numIsland), islandImages.get(numIsland), anchorPane));
        }
    }

    /**
     * prints all the clouds on the stage
     * @param cloudImageViews ArrayList of the ImageViews of the clouds
     * @param anchorPane anchorPane of the stage
     */
    public void printCloudsOnTable(ArrayList<ImageView> cloudImageViews, AnchorPane anchorPane){
        ArrayList<Image> cloudImages = Constants.createArrayImagesClouds();

        clouds = new ArrayList<>();
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.add(new CloudGUI(cloudImageViews.get(numCloud), cloudImages.get(numCloud), anchorPane));
        }
    }

    /**
     * prints all the assistant cards on the stage
     * @param assistantCardsImageViews ArrayList of the ImageViews of the assistantCards
     */
    public void printAssistantCards(ArrayList<ImageView> assistantCardsImageViews){
        ArrayList<Image> assistantCards = Constants.createArrayImagesAC();

        assistants= new ArrayList<>();
        for (int numAC = 0; numAC<10; numAC++){
            assistants.add(new AssistantCardGUI(assistantCardsImageViews.get(numAC), assistantCards.get(numAC)));
        }
    }

    /**
     * prints all the coins of the players
     * @param coinsImageViews ArrayList of the ImageViews of the coins
     * @param anchorPane anchorPane of the stage
     */
    public void printCoins(ArrayList<ImageView> coinsImageViews, AnchorPane anchorPane){
        coins = new ArrayList<>();
        Image coinImage = new Image("file:src/main/resources/Images/Other_objects/Coin.png");
        for(int numPlayer = 0; numPlayer < Constants.getNumPlayers(); numPlayer++){
            coins.add(new CoinGUI(coinsImageViews.get(numPlayer), coinImage, anchorPane));
        }
    }

    /**
     * print all the character cards on the stage
     * @param ccImageViews arrayList of the ImageViews of the character cards
     */
    public void printCharacterCards(ArrayList<ImageView> ccImageViews){
        characterCards= new ArrayList<>();
        for(int numCC=0; numCC<3; numCC++){
            characterCards.add(new CharacterCardGUI(ccImageViews.get(numCC)));
        }
    }

    /**
     * puts all the islands to the front of the stage
     */
    public void islandsToFront(){
        for (IslandGUI island : islands) {
            island.setIslandToFront();
        }
    }

    /**
     * calls the methods to zoom an island
     * @param numIsland number of the island to zoom
     * @param rectOpaqueBackground rectangle put under the board to increase the opacity of the rest of the table
     */
    public void zoomIsland(int numIsland, Rectangle rectOpaqueBackground){
        islands.get(numIsland).zoomIsland(rectOpaqueBackground);
    }

    /**
     * calls the methods to zoom back an island
     * @param numIsland number of the island to zoom back
     * @param rectOpaqueBackground rectangle put under the island to increase the opacity of the rest of the table
     */
    public void moveBackIsland(int numIsland, Rectangle rectOpaqueBackground){
        islands.get(numIsland).moveBackIsland(rectOpaqueBackground);
    }

    /**
     * puts all the clouds to the front of the stage
     */
    public void cloudsToFront(){
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.get(numCloud).setCloudToFront();
        }
    }

    /**
     * calls the methods to zoom the clouds
     * @param rectOpaqueBackground rectangle put under the clouds to increase the opacity of the rest of the table
     */
    public void zoomClouds(Rectangle rectOpaqueBackground){
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.get(numCloud).setCloudToFront();
            clouds.get(numCloud).zoomClouds(numCloud, rectOpaqueBackground);
        }
    }

    /**
     * calls the methods to zoom back the clouds
     */
    public void zoomBackClouds(){
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.get(numCloud).zoomBackClouds(numCloud);
        }
    }

    /**
     * puts the selected board to the front of the stage
     * @param numBoard number of the board
     */
    public void boardToFront(int numBoard){
        boards.get(numBoard).setBoardToFront();
    }

    /**
     * calls all the methods to zoom the board
     * @param numBoard index of the board to zoom
     * @param rectOpaqueBackground rectangle put under the board to increase the opacity of the rest of the table
     * @param anchorPane used to add all the components created in the method
     * @return returns all the imageViews created
     */
    public ArrayList<ImageView> zoomBoard(int numBoard, Rectangle rectOpaqueBackground, AnchorPane anchorPane){
        return boards.get(numBoard).zoomBoard(rectOpaqueBackground, anchorPane);
    }

    /**
     * calls all the methods to zoom back a board in the original position
     * @param numBoard index of the board to zoom back
     * @param images images created during the zoom and will be removed
     * @param anchorPane used to remove all the images added during a zoom
     */
    public void zoomBackBoard(int numBoard, ArrayList<ImageView> images, AnchorPane anchorPane){
        boards.get(numBoard).zoomBackBoard(images, anchorPane);
    }

    /**
     * calls the methods to zoom the 3 character cards
     * @param rectOpaqueBackground rectangle put under the character cards to increase the opacity of the rest of the table
     * @param anchorPane used to add the textArea of the effects of the character cards
     * @return return the ArrayList of the textAreas created during the zoom
     */
    public ArrayList<TextArea> zoomCC(Rectangle rectOpaqueBackground, AnchorPane anchorPane){
        ArrayList<TextArea> labels = new ArrayList<>();

        for (int numCC = 0; numCC < 3; numCC++){
            characterCards.get(numCC).setCCToFront();
            labels.add(characterCards.get(numCC).zoomCC(numCC, rectOpaqueBackground, anchorPane));
        }

        return labels;
    }

    /**
     * calls all the methods to zoom back the character cards in the original positions
     * @param effects arrayList of the TextAreas to remove from the stage
     * @param anchorPane used to remove the textAreas
     */
    public void zoomBackCC(ArrayList<TextArea> effects, AnchorPane anchorPane){
        for (int numCC = 0; numCC < 3; numCC++){
            characterCards.get(numCC).zoomBackCC(numCC, effects, anchorPane);
        }
    }

    /**
     * methods used to update the GUI
     */
    public void modifyStudInEntrance(int numPlayer, Colors c, int numStud){
        boards.get(numPlayer).setStudEntrance(c, numStud);
    }

    public void modifyStudInHall(int numPlayer, Colors c, int numStud){
        boards.get(numPlayer).setStudHall(c, numStud);
    }

    public void modifyTowersOnBoard(int numPlayer, int numTowers){
        boards.get(numPlayer).updateTowers(numTowers);
    }

    public void modifyCloud(int cloudIndex, Map<Colors, Integer> cloud){
        clouds.get(cloudIndex).setStudents(cloud);
    }

    public void modifyIsland(int islandIndex, Colors c, int numAdded){
        islands.get(islandIndex).setNumStudents(c, numAdded);
    }

    public void modifyMNPosition(int position){
        islands.forEach((island) -> {
            if (island.isMNPresent()) {
                island.setMNPresent(false);
                island.showMN();
            }
        });
        islands.get(position).setMNPresent(true);
        islands.get(position).showMN();
    }

    public void modifyController(int islandIndex, int controller){
        islands.get(islandIndex).setController(controller);
    }

    public void modifyAssistantCards(int assistantCard){
        assistants.get(assistantCard).assistantUsed();
    }

    public void modifyCoins(int playerIndex, int coinsAdded){
        coins.get(playerIndex).updateCoins(coinsAdded);
    }
}