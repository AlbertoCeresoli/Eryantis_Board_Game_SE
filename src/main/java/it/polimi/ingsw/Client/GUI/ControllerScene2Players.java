package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Messages.PrintMessages.*;
import it.polimi.ingsw.Messages.UpdateMessages.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ControllerScene2Players implements ControllerInterface {
    private GUIPrinter printer;
    private ArrayList<ImageView> islands;
    private ArrayList<ImageView> clouds;
    private ArrayList<ImageView> assistantCards;
    private ArrayList<ImageView> boards;
    private ArrayList<ImageView> coins;
    private ArrayList<ImageView> characterCards;

    @FXML private AnchorPane anchorPane;
    @FXML private Label lblPlayer1Name;
    @FXML private Label lblPlayer2Name;
    @FXML private ImageView imgPlayer1Board;
    @FXML private ImageView imgPlayer2Board;
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

            characterCards = new ArrayList<>();
            characterCards.add(characterCard1);
            characterCards.add(characterCard2);
            characterCards.add(characterCard3);
        }
        else {
            imgCoin1.toBack();
            imgCoin2.toBack();
            characterCard1.toBack();
            characterCard2.toBack();
            characterCard3.toBack();
        }
    }

    public void setCC(int[] cardIndexes){
        printer.setCharacterCards(cardIndexes, anchorPane);
    }

    @Override
    public void updateGame(UpdateMessage message) {
        if (message instanceof BoardUpdateMessage){
            elaborateBoardUpdateMessage((BoardUpdateMessage) message);
        }
        if (message instanceof CloudsUpdateMessage){
            elaborateCloudsUpdateMessage((CloudsUpdateMessage) message);
        }
        if (message instanceof MotherNatureUpdateMessage){
            elaborateMotherNatureUpdateMessage((MotherNatureUpdateMessage) message);
        }
        if (message instanceof StudentMovedUpdateMessage){
            elaborateStudentMovedUpdateMessage((StudentMovedUpdateMessage) message);
        }
        if (message instanceof EriantysUpdateMessage){
            elaborateEriantysUpdateMessage((EriantysUpdateMessage) message);
        }
        if (message instanceof IslandsUpdateMessage){
            elaborateIslandsUpdateMessage((IslandsUpdateMessage) message);
        }
        if (message instanceof TeachersUpdateMessage){
            elaborateTeachersUpdateMessage((TeachersUpdateMessage) message);
        }
    }

    public void elaborateBoardUpdateMessage(BoardUpdateMessage message){
       Map<Colors, Integer> entrance = message.getEntrance();
       Map<Colors, Integer> hall = message.getHall();
       for(Colors c: Colors.values()) {
           printer.modifyStudInEntrance(message.getPlayerIndex(), c, entrance.get(c));
           printer.modifyStudInHall(message.getPlayerIndex(), c, hall.get(c));
       }
       printer.modifyTowersOnBoard(message.getPlayerIndex(), message.getTowers());
    }

    public void elaborateCloudsUpdateMessage(CloudsUpdateMessage message){
        PrintCloudsMessage cloudsMessage = message.getPrintCloudsMessage();
        for (int numCloud = 0; numCloud<Constants.getNumPlayers(); numCloud++){
            printer.modifyCloud(numCloud, cloudsMessage.getClouds().get(numCloud));
        }
    }

    public void elaborateIslandsUpdateMessage(IslandsUpdateMessage message){
        ArrayList<PrintIslandMessage> islandMessages = message.getPrintIslandsMessage().getIslandMessages();
        int[] towers = message.getTowers();
        Map<String, Integer> nickToIndex = message.getNickToIndex();

        for (int numIsland=0; numIsland < 12; numIsland++){
            if (islandMessages.get(numIsland).getNumberOfTowers()==0 || islandMessages.get(numIsland).getNumberOfTowers()==1){
                printer.printIsland(numIsland,
                        nickToIndex.get(islandMessages.get(numIsland).getIslandController()),
                        islandMessages.get(numIsland).getStudents(),
                        islandMessages.get(numIsland).isMotherNatureInHere(),
                        islandMessages.get(numIsland).getNumberOfTowers(),
                        islandMessages.get(numIsland).getInhibitionCards()
                );
            }
            else {
                printer.printIsland(numIsland,
                        nickToIndex.get(islandMessages.get(numIsland).getIslandController()),
                        islandMessages.get(numIsland).getStudents(),
                        islandMessages.get(numIsland).isMotherNatureInHere(),
                        islandMessages.get(numIsland).getNumberOfTowers(),
                        islandMessages.get(numIsland).getInhibitionCards()
                );
                for (int i=1; i<islandMessages.get(numIsland).getNumberOfTowers(); i++){
                    printer.hideIsland(numIsland + i);
                }
                numIsland += islandMessages.get(numIsland).getNumberOfTowers() - 1;
            }
        }

        for (int numPlayer=0; numPlayer<Constants.getNumPlayers(); numPlayer++){
            printer.modifyTowersOnBoard(numPlayer, towers[numPlayer]);
        }
    }

    public void elaborateMotherNatureUpdateMessage(MotherNatureUpdateMessage message){
        printer.modifyMNPosition(message.getPosition());
    }

    public void elaborateStudentMovedUpdateMessage(StudentMovedUpdateMessage message){
        if (Objects.equals(message.getFromWhere().toLowerCase(), "entrance")){
            printer.modifyStudInEntrance(message.getNickToIndex().get(message.getNickname()), message.getColor(), -1);
        }
        if (Objects.equals(message.getToWhere().toLowerCase(), "island")){
            printer.modifyIsland(message.getIslandIndex(), message.getColor(), + 1);
        }
        if (Objects.equals(message.getToWhere().toLowerCase(), "hall")){
            printer.modifyStudInHall(message.getNickToIndex().get(message.getNickname()), message.getColor(), +1);
        }
    }

    public void elaborateTeachersUpdateMessage(TeachersUpdateMessage message){
        PrintTeachersMessage teachersMessage = message.getPrintTeachersMessage();
        for (int numPlayer=0; numPlayer<Constants.getNumPlayers(); numPlayer++){
            boolean[] teachers = new boolean[5];
            for (Colors c: Colors.values()){
                if (teachersMessage.getNickToIndex().get(teachersMessage.getTeachers().get(c)) == numPlayer){
                    teachers[c.ordinal()]=true;
                }
                else {
                    teachers[c.ordinal()]=false;
                }
            }
            printer.modifyTeachers(numPlayer, teachers);
        }
    }

    public void elaborateEriantysUpdateMessage(EriantysUpdateMessage message){
        //set dei nicknames
        setNicknames(message.getPlayers());

        //set students on board
        PrintBoardMessage[] boards = message.getPrintBoardMessages();
        for (int numPlayer=0; numPlayer<Constants.getNumPlayers(); numPlayer++){
            Map<Colors, Integer> entrance = boards[numPlayer].getEntrance();
            Map<Colors, Integer> hall = boards[numPlayer].getHall();
            for(Colors c: Colors.values()){
                printer.modifyStudInEntrance(numPlayer, c, entrance.get(c));
                printer.modifyStudInHall(numPlayer, c, hall.get(c));
            }
            printer.modifyTowersOnBoard(numPlayer, boards[numPlayer].getNumberOfTowers());
        }

        //set students on islands
        PrintIslandsMessage islands = message.getPrintIslandsMessage();
        for (int numIsland=0; numIsland<12; numIsland++) {
            PrintIslandMessage island = islands.getIslandMessages().get(numIsland);

            printer.printIsland(island.getIslandIndex(), message.getNickToIndex().get(island.getIslandController()),
                    island.getStudents(), island.isMotherNatureInHere(), island.getNumberOfTowers(),
                    island.getInhibitionCards());
        }

        //set students on clouds
        PrintCloudsMessage clouds = message.getPrintCloudsMessage();
        for (int numCloud = 0; numCloud<Constants.getNumPlayers(); numCloud++){
            printer.modifyCloud(numCloud, clouds.getClouds().get(numCloud));
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
        cloud1.setOnMouseClicked(mouseEvent -> onClickClouds());
        cloud2.setOnMouseClicked(mouseEvent -> onClickClouds());
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

            Constants.moveObject(anchorPane, 150, 0, rectOpaqueBackground);
            Constants.zoomObject(anchorPane, 0.3, 0.4);


            rectOpaqueBackground.setOnMouseClicked(mouseEvent -> {
                Constants.zoomBackObject(anchorPane, -0.3, -0.4);
                Constants.moveBackObject(anchorPane, -150, 0);

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

    @Override
    public void printEasyMessage(String text) {
        txtAreaChat.appendText(text + "\n");
    }


}
