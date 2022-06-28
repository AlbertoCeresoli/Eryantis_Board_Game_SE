package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.TypesOfUpdate;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerScene3Players implements ControllerInterface {
    private GUIPrinter printer;
    private GUI gui;
    private ArrayList<Stage> stages;
    private ArrayList<ImageView> islands;
    private ArrayList<ImageView> clouds;
    private ArrayList<ImageView> assistantCards;
    private ArrayList<ImageView> boards;
    private ArrayList<ImageView> coins;
    private ArrayList<ImageView> characterCards;

    @FXML private AnchorPane anchorPane;
    @FXML private Label lblPlayer1Name;
    @FXML private Label lblPlayer2Name;
    @FXML private Label lblPlayer3Name;
    @FXML private ImageView imgPlayer1Board;
    @FXML private ImageView imgPlayer2Board;
    @FXML private ImageView imgPlayer3Board;
    @FXML private ImageView island1;
    @FXML private ImageView island2;
    @FXML private ImageView island3;
    @FXML private ImageView island4;
    @FXML private ImageView island5;
    @FXML private ImageView island6;
    @FXML private ImageView island7;
    @FXML private ImageView island8;
    @FXML private ImageView island9;
    @FXML private ImageView island10;
    @FXML private ImageView island11;
    @FXML private ImageView island12;
    @FXML private ImageView cloud1;
    @FXML private ImageView cloud2;
    @FXML private ImageView cloud3;
    @FXML private ImageView assistantCard0;
    @FXML private ImageView assistantCard1;
    @FXML private ImageView assistantCard2;
    @FXML private ImageView assistantCard3;
    @FXML private ImageView assistantCard4;
    @FXML private ImageView assistantCard5;
    @FXML private ImageView assistantCard6;
    @FXML private ImageView assistantCard7;
    @FXML private ImageView assistantCard8;
    @FXML private ImageView assistantCard9;
    @FXML private TextArea txtAreaChat;
    @FXML private TextField txtField;
    @FXML private Rectangle rectOpaqueBackground;
    @FXML private ImageView zoomSymbol;
    @FXML private Rectangle rctBlue;
    @FXML private ImageView imgCoin1;
    @FXML private ImageView imgCoin2;
    @FXML private ImageView imgCoin3;
    @FXML private ImageView characterCard1;
    @FXML private ImageView characterCard2;
    @FXML private ImageView characterCard3;

    /**
     * method used to set the labels of the nicknames
     * @param indexToNick map of the nicknames of the players
     */
    public void setNicknames(Map<Integer, String> indexToNick){
        lblPlayer1Name.setText(indexToNick.get(0));
        lblPlayer2Name.setText(indexToNick.get(1));
        lblPlayer3Name.setText(indexToNick.get(2));
    }

    /**
     * creates all the arrayList of images of the GUI
     */
    public void setUp(){
        printer = new GUIPrinter();

        //creation of the arrayList of island, cloud, characterCard and boards imageViews
        boards = new ArrayList<>();
        boards.add(imgPlayer1Board);
        boards.add(imgPlayer2Board);
        boards.add(imgPlayer3Board);

        islands = new ArrayList<>();
        islands.add(island1);
        islands.add(island2);
        islands.add(island3);
        islands.add(island4);
        islands.add(island5);
        islands.add(island6);
        islands.add(island7);
        islands.add(island8);
        islands.add(island9);
        islands.add(island10);
        islands.add(island11);
        islands.add(island12);

        clouds = new ArrayList<>();
        clouds.add(cloud1);
        clouds.add(cloud2);
        clouds.add(cloud3);

        assistantCards = new ArrayList<>();
        assistantCards.add(assistantCard0);
        assistantCards.add(assistantCard1);
        assistantCards.add(assistantCard2);
        assistantCards.add(assistantCard3);
        assistantCards.add(assistantCard4);
        assistantCards.add(assistantCard5);
        assistantCards.add(assistantCard6);
        assistantCards.add(assistantCard7);
        assistantCards.add(assistantCard8);
        assistantCards.add(assistantCard9);

        if (Constants.isGameMode()) {
            coins = new ArrayList<>();
            coins.add(imgCoin1);
            coins.add(imgCoin2);
            coins.add(imgCoin3);

            characterCards = new ArrayList<>();
            characterCards.add(characterCard1);
            characterCards.add(characterCard2);
            characterCards.add(characterCard3);
        }
        else {
            imgCoin1.toBack();
            imgCoin2.toBack();
            imgCoin3.toBack();
            characterCard1.toBack();
            characterCard2.toBack();
            characterCard3.toBack();
        }

        //setUp of the nicknames
        lblPlayer1Name.setText("ciao1");
        lblPlayer2Name.setText("ciao2");
        lblPlayer3Name.setText("ciao3");
    }

    //TODO ancora da modificare dopo l'aggiunta dei messaggi di update
    public void updateGame(TypesOfUpdate selection, int index, Colors c, int num, HashMap<Colors, Integer> cloudStudents, int newController){
        switch (selection) {
            case STUDENTS_IN_ENTRANCE -> printer.modifyStudInEntrance(index, c, num);
            case STUDENTS_IN_HALL -> printer.modifyStudInHall(index, c, num);
            case TOWERS_ON_BOARD -> printer.modifyTowersOnBoard(index, num);
            case CLOUDS -> printer.modifyCloud(index, cloudStudents);
            case STUDENTS_ON_ISLAND -> printer.modifyIsland(index, c, num);
            case MN -> printer.modifyMNPosition(index);
            case CONTROLLER_ISLAND -> printer.modifyController(index, newController);
            case ASSISTANT_CARD_USED -> printer.modifyAssistantCards(num);
            case COINS -> printer.modifyCoins(index, num);
            default -> {
            }
        }
    }

    /**
     * calls all the methods needed to show the imageViews of the components of the game
     */
    public void printTable(){
        printer.printIslandsOnTable(islands, anchorPane);
        printer.printCloudsOnTable(clouds, anchorPane);
        printer.printAssistantCards(assistantCards);
        printer.printBoards(boards, anchorPane);
        if (Constants.isGameMode()) {
            printer.printCoins(coins, anchorPane);
            printer.printCharacterCards(characterCards);
        }
    }

    /**
     * handles all the events on the primary stage
     */
    public void startEventHandling(){
        imgPlayer1Board.setOnMouseClicked(mouseEvent -> onClickBoard(0));
        imgPlayer2Board.setOnMouseClicked(mouseEvent -> onClickBoard(1));
        imgPlayer3Board.setOnMouseClicked(mouseEvent -> onClickBoard(2));
        cloud1.setOnMouseClicked(mouseEvent -> onClickClouds());
        cloud2.setOnMouseClicked(mouseEvent -> onClickClouds());
        cloud3.setOnMouseClicked(mouseEvent -> onClickClouds());
        assistantCard0.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard1.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard2.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard3.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard4.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard5.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard6.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard7.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard8.setOnMouseClicked(mouseEvent -> onClickACP1());
        assistantCard9.setOnMouseClicked(mouseEvent -> onClickACP1());
        island1.setOnMouseClicked(mouseEvent -> zoomIsland(0));
        island2.setOnMouseClicked(mouseEvent -> zoomIsland(1));
        island3.setOnMouseClicked(mouseEvent -> zoomIsland(2));
        island4.setOnMouseClicked(mouseEvent -> zoomIsland(3));
        island5.setOnMouseClicked(mouseEvent -> zoomIsland(4));
        island6.setOnMouseClicked(mouseEvent -> zoomIsland(5));
        island7.setOnMouseClicked(mouseEvent -> zoomIsland(6));
        island8.setOnMouseClicked(mouseEvent -> zoomIsland(7));
        island9.setOnMouseClicked(mouseEvent -> zoomIsland(8));
        island10.setOnMouseClicked(mouseEvent -> zoomIsland(9));
        island11.setOnMouseClicked(mouseEvent -> zoomIsland(10));
        island12.setOnMouseClicked(mouseEvent -> zoomIsland(11));
        zoomSymbol.setOnMouseClicked(mouseEvent -> onClickTable());
        characterCard1.setOnMouseClicked(mouseEvent -> onClickCharacterCards());
        characterCard2.setOnMouseClicked(mouseEvent -> onClickCharacterCards());
        characterCard3.setOnMouseClicked(mouseEvent -> onClickCharacterCards());

        txtField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAreaChat.appendText(txtField.getText() + "\n");
                txtField.clear();
            }
        });
    }

    /**
     * method that handle the click of a board of a player
     * @param numBoard number of the board clicked (it's also the index of the player)
     */
    public void onClickBoard(int numBoard){
        if (!Constants.isSomethingClicked()) {
            Constants.setSomethingClicked(true);
            rectOpaqueBackground.toFront();
            printer.boardToFront(numBoard);

            ArrayList<ImageView> images;
            images = printer.zoomBoard(numBoard, rectOpaqueBackground, anchorPane);

            rectOpaqueBackground.setOnMouseClicked(mouseEvent -> {
                printer.zoomBackBoard(numBoard, images, anchorPane);

                rectOpaqueBackground.setWidth(1);
                rectOpaqueBackground.setHeight(1);
            });
        }
    }

    /**
     * method that handle the click of an island
     * @param numIsland number of the island clicked
     */
    public void zoomIsland (int numIsland) {
        if (!Constants.isSomethingClicked()) {
            Constants.setSomethingClicked(true);
            printer.zoomIsland(numIsland, rectOpaqueBackground);
            rectOpaqueBackground.setOnMouseClicked(mouseEvent -> printer.moveBackIsland(numIsland, rectOpaqueBackground));
        }
    }

    /**
     * method that handle the click of an assistant card
     */
    public void onClickACP1(){
        if (!Constants.isSomethingClicked()) {
            Constants.setSomethingClicked(true);
            rectOpaqueBackground.toFront();

            for (int numAC = 0; numAC < 10; numAC++) {
                assistantCards.get(numAC).toFront();
                Constants.moveObject(assistantCards.get(numAC), 50 + 150 * (numAC % 5) - assistantCards.get(numAC).getLayoutX(), 100 + (numAC/ 5) * 200 - assistantCards.get(numAC).getLayoutY(), rectOpaqueBackground);
            }

            rectOpaqueBackground.setOnMouseClicked(mouseEvent -> {
                for (int numAC = 0; numAC < 10; numAC++) {
                    Constants.moveBackObject(assistantCards.get(numAC), - (50 + 150 * (numAC % 5) - assistantCards.get(numAC).getLayoutX()), - (100 + (numAC/ 5) * 200 - assistantCards.get(numAC).getLayoutY()));
                }
                rectOpaqueBackground.setWidth(1);
                rectOpaqueBackground.setHeight(1);
            });
        }
    }

    /**
     * method that handles the click of the zoom symbol on the table
     */
    public void onClickTable(){
        if (!Constants.isSomethingClicked()) {
            Constants.setSomethingClicked(true);
            rectOpaqueBackground.toFront();
            rctBlue.toFront();
            printer.islandsToFront();
            printer.cloudsToFront();

            Constants.moveObject(anchorPane, 150, -50, rectOpaqueBackground);
            Constants.zoomObject(anchorPane, 0.3, 0.5);


            rectOpaqueBackground.setOnMouseClicked(mouseEvent -> {
                Constants.zoomBackObject(anchorPane, -0.3, -0.5);
                Constants.moveBackObject(anchorPane, -150, 50);

                rectOpaqueBackground.setWidth(1);
                rectOpaqueBackground.setHeight(1);

                zoomSymbol.toFront();
            });
        }
    }

    /**
     * method that handle the click of a cloud
     */
    public void onClickClouds(){
        if (!Constants.isSomethingClicked()) {
            Constants.setSomethingClicked(true);
            rectOpaqueBackground.toFront();
            printer.zoomClouds(rectOpaqueBackground);

            rectOpaqueBackground.setOnMouseClicked(mouseEvent -> {
                printer.zoomBackClouds();

                rectOpaqueBackground.setWidth(1);
                rectOpaqueBackground.setHeight(1);
            });
        }
    }

    /**
     * method that handle the click of a character card
     */
    public void onClickCharacterCards(){
        ArrayList<TextArea> effects;

        if (!Constants.isSomethingClicked()) {
            Constants.setSomethingClicked(true);
            rectOpaqueBackground.toFront();
            effects = printer.zoomCC(rectOpaqueBackground, anchorPane);

            rectOpaqueBackground.setOnMouseClicked(mouseEvent -> {
                printer.zoomBackCC(effects, anchorPane);

                rectOpaqueBackground.setWidth(1);
                rectOpaqueBackground.setHeight(1);
            });
        }
    }

    /**
     * Set and get methods
     */
    public GUIPrinter getPrinter() {
        return printer;
    }
}