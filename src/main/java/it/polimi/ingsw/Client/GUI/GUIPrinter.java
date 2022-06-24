package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class GUIPrinter {
    private ArrayList<IslandGUI> islands;
    private ArrayList<CloudGUI> clouds;
    private ArrayList<AssistantCardGUI> assistants;
    private ArrayList<BoardGUI> boards;
    private ArrayList<CoinGUI> coins;

    public void printBoards(ArrayList<ImageView> boardImageViews, AnchorPane anchorPane){
        ArrayList<Image> boardImages = new ArrayList<>();
        boardImages.add(new Image("file:src/main/resources/Images/PLANCIA.png"));
        boardImages.add(new Image("file:src/main/resources/Images/PLANCIA.png"));
        if (Constants.getNumPlayers() == 3){
            boardImages.add(new Image("file:src/main/resources/Images/PLANCIA.png"));
        }

        boards = new ArrayList<>();
        for (int i = 0; i< Constants.getNumPlayers(); i++){
            boards.add(new BoardGUI(boardImageViews.get(i), boardImages.get(i), anchorPane, i));
        }
    }

    public void printIslandsOnTable(ArrayList<ImageView> islandImageViews, AnchorPane anchorPane){
        ArrayList<Image> islandImages = new ArrayList<>();
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola1.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola2.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola3.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola4.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola5.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola6.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola7.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola8.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola9.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola10.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola11.png"));
        islandImages.add(new Image("file:src/main/resources/Images/Islands/Isola12.png"));

        islands = new ArrayList<>();
        for (int numIsland = 0; numIsland<12; numIsland++){
            islands.add(new IslandGUI(islandImageViews.get(numIsland), islandImages.get(numIsland), anchorPane));
        }
    }

    public void printCloudsOnTable(ArrayList<ImageView> cloudImageViews, AnchorPane anchorPane){
        ArrayList<Image> cloudImages = new ArrayList<>();
        cloudImages.add(new Image("file:src/main/resources/Images/Clouds/2_Players/3_Players/3P_Cloud1.png"));
        cloudImages.add(new Image("file:src/main/resources/Images/Clouds/2_Players/3_Players/3P_Cloud2.png"));
        cloudImages.add(new Image("file:src/main/resources/Images/Clouds/2_Players/3_Players/3P_Cloud3.png"));

        clouds = new ArrayList<>();
        for (int numCloud = 0; numCloud < Constants.getNumPlayers(); numCloud++){
            clouds.add(new CloudGUI(cloudImageViews.get(numCloud), cloudImages.get(numCloud), anchorPane));
        }
    }

    public void printAssistantCards(ArrayList<ImageView> assistantCardsImageViews, AnchorPane anchorPane){
        ArrayList<Image> assistantCards = new ArrayList<>();
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (1).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (2).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (3).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (4).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (5).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (6).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (7).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (8).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (9).png"));
        assistantCards.add(new Image ("file:src/main/resources/Images/Assistants/Assistente (10).png"));

        assistants= new ArrayList<>();
        for (int numAC = 0; numAC<10; numAC++){
            assistants.add(new AssistantCardGUI(assistantCardsImageViews.get(numAC), assistantCards.get(numAC), anchorPane));
        }
        assistants.get(4).assistantUsed(); //TODO
    }

    public void printCoins(ArrayList<ImageView> coinsImageViews, AnchorPane anchorPane){
        coins = new ArrayList<>();
        Image coinImage = new Image("file:src/main/resources/Images/Other_objects/Coin.png");
        for(int numPlayer = 0; numPlayer < Constants.getNumPlayers(); numPlayer++){
            coins.add(new CoinGUI(coinsImageViews.get(numPlayer), coinImage, anchorPane));
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

    public void modifyCloud(int cloudIndex, HashMap<Colors, Integer> cloud){
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
