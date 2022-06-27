package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUIPrinter {
    private ArrayList<IslandGUI> islands;
    private ArrayList<CloudGUI> clouds;
    private ArrayList<AssistantCardGUI> assistants;
    private ArrayList<BoardGUI> boards;
    private ArrayList<CoinGUI> coins;
    private ArrayList<CharacterCardGUI> characterCards;

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

    public void printIslandsOnTable(ArrayList<ImageView> islandImageViews, AnchorPane anchorPane){
        ArrayList<Image> islandImages = Constants.createArrayImagesIslands();

        islands = new ArrayList<>();
        for (int numIsland = 0; numIsland<12; numIsland++){
            islands.add(new IslandGUI(islandImageViews.get(numIsland), islandImages.get(numIsland), anchorPane));
        }
    }

    public void printCloudsOnTable(ArrayList<ImageView> cloudImageViews, AnchorPane anchorPane){
        ArrayList<Image> cloudImages = Constants.createArrayImagesClouds();

        clouds = new ArrayList<>();
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.add(new CloudGUI(cloudImageViews.get(numCloud), cloudImages.get(numCloud), anchorPane));
        }
    }

    public void printAssistantCards(ArrayList<ImageView> assistantCardsImageViews, AnchorPane anchorPane){
        ArrayList<Image> assistantCards = Constants.createArrayImagesAC();

        assistants= new ArrayList<>();
        for (int numAC = 0; numAC<10; numAC++){
            assistants.add(new AssistantCardGUI(assistantCardsImageViews.get(numAC), assistantCards.get(numAC), anchorPane));
        }
    }

    public void printCoins(ArrayList<ImageView> coinsImageViews, AnchorPane anchorPane){
        coins = new ArrayList<>();
        Image coinImage = new Image("file:src/main/resources/Images/Other_objects/Coin.png");
        for(int numPlayer = 0; numPlayer < Constants.getNumPlayers(); numPlayer++){
            coins.add(new CoinGUI(coinsImageViews.get(numPlayer), coinImage, anchorPane));
        }
    }

    public void printCharacterCards(ArrayList<ImageView> ccImageViews, AnchorPane anchorPane){
        characterCards= new ArrayList<>();
        for(int numCC=0; numCC<3; numCC++){
            characterCards.add(new CharacterCardGUI(ccImageViews.get(numCC), anchorPane));
        }
    }

    public void islandsToFront(){
        for (int numIsland=0; numIsland<islands.size(); numIsland++){
            islands.get(numIsland).setIslandToFront();
        }
    }

    public void zoomIsland(int numIsland, Rectangle rectOpaqueBackground){
        islands.get(numIsland).zoomIsland(rectOpaqueBackground);
    }

    public void moveBackIsland(int numIsland, Rectangle rectOpaqueBackground){
        islands.get(numIsland).moveBackIsland(rectOpaqueBackground);
    }

    public void cloudsToFront(){
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.get(numCloud).setCloudToFront();
        }
    }

    public void zoomClouds(Rectangle rectOpaqueBackground){
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.get(numCloud).setCloudToFront();
            clouds.get(numCloud).zoomClouds(numCloud, rectOpaqueBackground);
        }
    }

    public void zoomBackClouds(){
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.get(numCloud).zoomBackClouds(numCloud);
        }
    }

    public void boardToFront(int numBoard){
        boards.get(numBoard).setBoardToFront();
    }

    public ArrayList<ImageView> zoomBoard(int numBoard, Rectangle rectOpaqueBackground, AnchorPane anchorPane){
        return boards.get(numBoard).zoomBoard(rectOpaqueBackground, anchorPane);
    }

    public void zoomBackBoard(int numBoard, ArrayList<ImageView> images, AnchorPane anchorPane){
        boards.get(numBoard).zoomBackBoard(images, anchorPane);
    }

    public ArrayList<TextArea> zoomCC(Rectangle rectOpaqueBackground, AnchorPane anchorPane){
        ArrayList<TextArea> labels = new ArrayList<>();

        for (int numCC = 0; numCC < 3; numCC++){
            characterCards.get(numCC).setCCToFront();
            labels.add(characterCards.get(numCC).zoomCC(numCC, rectOpaqueBackground, anchorPane));
        }

        return labels;
    }

    public void zoomBackCC(ArrayList<TextArea> effects, AnchorPane anchorPane){
        for (int numCC = 0; numCC < 3; numCC++){
            characterCards.get(numCC).zoomBackCC(numCC, effects, anchorPane);
        }
    }

    /**
     * methods used to update the GUI
     */
    public void modifyStudInEntrance(int numPlayer, Colors c, int numAdded){
        boards.get(numPlayer).updateStudEntrance(c, numAdded);
    }

    public void modifyStudInHall(int numPlayer, Colors c, int numAdded){
        boards.get(numPlayer).updateStudHall(c, numAdded);
    }

    public void modifyTowersOnBoard(int numPlayer, int numAdded){
        boards.get(numPlayer).updateTowers(numAdded);
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