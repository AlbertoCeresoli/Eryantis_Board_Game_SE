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


    public void setUp(GUI gui, Stage primaryStage){
        printer = new GUIPrinter();
        this.gui = gui;
        stages = new ArrayList<>();
        stages.add(primaryStage);

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

    public void printTable(){
        printer.printIslandsOnTable(islands, anchorPane);
        printer.printCloudsOnTable(clouds, anchorPane);
        printer.printAssistantCards(assistantCards, anchorPane);
        printer.printBoards(boards, anchorPane);
        if (Constants.isGameMode()) {
            printer.printCoins(coins, anchorPane);
            printer.printCharacterCards(characterCards, anchorPane);
        }
    }

    public void setNicknames(Map<Integer, String> indexToNick){
        lblPlayer1Name.setText(indexToNick.get(0));
        lblPlayer2Name.setText(indexToNick.get(1));
        lblPlayer3Name.setText(indexToNick.get(2));
    }

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

    public void zoomIsland (int numIsland) {
        if (!Constants.isSomethingClicked()) {
            Constants.setSomethingClicked(true);
            printer.zoomIsland(numIsland, rectOpaqueBackground);
            rectOpaqueBackground.setOnMouseClicked(mouseEvent -> printer.moveBackIsland(numIsland, rectOpaqueBackground));
        }
    }

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

    public void selectColor(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(110);
        stageSel.setWidth(340);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(110);
        blueBackground.setWidth(340);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        ImageView greenImgView = new ImageView();
        greenImgView.setLayoutX(10);
        greenImgView.setLayoutY(10);
        greenImgView.setFitHeight(50);
        greenImgView.setFitWidth(50);
        greenImgView.setImage(new Image("file:src/main/resources/Images/Students and teachers/Green_S.png"));

        ImageView redImgView = new ImageView();
        redImgView.setLayoutX(80);
        redImgView.setLayoutY(10);
        redImgView.setFitHeight(50);
        redImgView.setFitWidth(50);
        redImgView.setImage(new Image("file:src/main/resources/Images/Students and teachers/Red_S.png"));

        ImageView yellowImgView = new ImageView();
        yellowImgView.setLayoutX(140);
        yellowImgView.setLayoutY(10);
        yellowImgView.setFitHeight(50);
        yellowImgView.setFitWidth(50);
        yellowImgView.setImage(new Image("file:src/main/resources/Images/Students and teachers/Yellow_S.png"));

        ImageView pinkImgView = new ImageView();
        pinkImgView.setLayoutX(200);
        pinkImgView.setLayoutY(10);
        pinkImgView.setFitHeight(50);
        pinkImgView.setFitWidth(50);
        pinkImgView.setImage(new Image("file:src/main/resources/Images/Students and teachers/Pink_S.png"));

        ImageView blueImgView = new ImageView();
        blueImgView.setLayoutX(260);
        blueImgView.setLayoutY(10);
        blueImgView.setFitHeight(50);
        blueImgView.setFitWidth(50);
        blueImgView.setImage(new Image("file:src/main/resources/Images/Students and teachers/Blue_S.png"));

        anchorPaneSel.getChildren().add(blueBackground);
        anchorPaneSel.getChildren().add(greenImgView);
        anchorPaneSel.getChildren().add(redImgView);
        anchorPaneSel.getChildren().add(yellowImgView);
        anchorPaneSel.getChildren().add(pinkImgView);
        anchorPaneSel.getChildren().add(blueImgView);

        stageSel.setScene(sceneSel);
        stageSel.show();

        greenImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("green");
            stageSel.close();
        });
        redImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("red");
            stageSel.close();
        });
        yellowImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("yellow");
            stageSel.close();
        });
        pinkImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("pink");
            stageSel.close();
        });
        blueImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("blue");
            stageSel.close();
        });
    }

    public void selectAC(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(150);
        stageSel.setWidth(630);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(150);
        blueBackground.setWidth(630);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        ArrayList<Image> assistantCards = Constants.createArrayImagesAC();
        ArrayList<ImageView> acImgVw = new ArrayList<>();
        for (int numAC=0; numAC<10; numAC++){
            ImageView imgVw = new ImageView();
            imgVw.setLayoutX(10 + numAC*60);
            imgVw.setLayoutY(10);
            imgVw.setFitHeight(100);
            imgVw.setFitWidth(50);
            acImgVw.add(imgVw);
            imgVw.setImage(assistantCards.get(numAC));
            anchorPaneSel.getChildren().add(imgVw);
        }

        acImgVw.forEach((image)->{
            image.setOnMouseClicked(mouseEvent -> {
                gui.print(String.valueOf(acImgVw.indexOf(image)));
                stageSel.close();
            });
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectIsland(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(190);
        stageSel.setWidth(450);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(190);
        blueBackground.setWidth(450);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        ArrayList<Image> islands = Constants.createArrayImagesIslands();
        ArrayList<ImageView> islandsImgVw = new ArrayList<>();
        for (int numIsland=0; numIsland<12; numIsland++){
            ImageView imgVw = new ImageView();
            imgVw.setLayoutX(10 + numIsland%6*70);
            imgVw.setLayoutY(10 + numIsland/6*70);
            imgVw.setFitHeight(60);
            imgVw.setFitWidth(60);
            islandsImgVw.add(imgVw);
            imgVw.setImage(islands.get(numIsland));
            anchorPaneSel.getChildren().add(imgVw);

            Label lbl = new Label();
            lbl.setText(String.valueOf(numIsland));
            lbl.setLayoutX(imgVw.getLayoutX());
            lbl.setLayoutY(imgVw.getLayoutY());
            lbl.setFont(Font.font(15));
            anchorPaneSel.getChildren().add(lbl);
        }

        islandsImgVw.forEach((image)->{
            image.setOnMouseClicked(mouseEvent -> {
                gui.print(String.valueOf(islandsImgVw.indexOf(image)));
                stageSel.close();
            });
        });


        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectCloud(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(130);
        stageSel.setWidth(20 + Constants.getNumPlayers()*90);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(130);
        blueBackground.setWidth(20 + Constants.getNumPlayers()*90);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        ArrayList<Image> clouds = Constants.createArrayImagesClouds();
        ArrayList<ImageView> cloudsImgVw = new ArrayList<>();
        for (int numCloud=0; numCloud<Constants.numPlayers; numCloud++){
            ImageView imgVw = new ImageView();
            imgVw.setLayoutX(10 + numCloud*90);
            imgVw.setLayoutY(10);
            imgVw.setFitHeight(80);
            imgVw.setFitWidth(80);
            cloudsImgVw.add(imgVw);
            imgVw.setImage(clouds.get(numCloud));
            anchorPaneSel.getChildren().add(imgVw);

            Label lbl = new Label();
            lbl.setText(String.valueOf(numCloud));
            lbl.setLayoutX(imgVw.getLayoutX());
            lbl.setLayoutY(imgVw.getLayoutY());
            lbl.setFont(Font.font(15));
            anchorPaneSel.getChildren().add(lbl);
        }

        cloudsImgVw.forEach((image)->{
            image.setOnMouseClicked(mouseEvent -> {
                gui.print(String.valueOf(cloudsImgVw.indexOf(image)));
                stageSel.close();
            });
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectSteps(int maxSteps){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(110);
        stageSel.setWidth(20 + maxSteps*40);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(110);
        blueBackground.setWidth(20 + maxSteps*40);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        Image mn = new Image("file:src/main/resources/Images/Other_objects/MN.png");
        ArrayList<ImageView> cloudsImgVw = new ArrayList<>();
        for (int numStep=0; numStep<maxSteps; numStep++){
            ImageView imgVw = new ImageView();
            imgVw.setLayoutX(10 + numStep*40);
            imgVw.setLayoutY(10);
            imgVw.setFitHeight(60);
            imgVw.setFitWidth(30);
            cloudsImgVw.add(imgVw);
            imgVw.setImage(mn);
            anchorPaneSel.getChildren().add(imgVw);

            Label lbl = new Label();
            lbl.setText(String.valueOf(numStep + 1));
            lbl.setLayoutX(imgVw.getLayoutX());
            lbl.setLayoutY(imgVw.getLayoutY());
            lbl.setFont(Font.font(15));
            anchorPaneSel.getChildren().add(lbl);
        }

        cloudsImgVw.forEach((image)->{
            image.setOnMouseClicked(mouseEvent -> {
                gui.print(String.valueOf(cloudsImgVw.indexOf(image) + 1));
                stageSel.close();
            });
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectPlace(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(150);
        stageSel.setWidth(300);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(150);
        blueBackground.setWidth(300);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        Image island = new Image("file:src/main/resources/Images/Islands/Island1.png");

        ImageView islandImgView = new ImageView();
        islandImgView.setLayoutX(10);
        islandImgView.setLayoutY(10);
        islandImgView.setFitHeight(100);
        islandImgView.setFitWidth(100);
        islandImgView.setImage(island);

        anchorPaneSel.getChildren().add(islandImgView);


        Image board = new Image("file:src/main/resources/Images/BOARD.png");

        ImageView boardImgView = new ImageView();
        boardImgView.setLayoutX(120);
        boardImgView.setLayoutY(10);
        boardImgView.setFitHeight(90);
        boardImgView.setFitWidth(160);
        boardImgView.setImage(board);

        anchorPaneSel.getChildren().add(boardImgView);

        islandImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("island");
            stageSel.close();
        });

        boardImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("board");
            stageSel.close();
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectIP(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(150);
        stageSel.setWidth(240);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(150);
        blueBackground.setWidth(240);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        Label lblSelection = new Label();
        lblSelection.setLayoutX(30);
        lblSelection.setLayoutY(20);
        lblSelection.setText("Insert the IP address:");
        anchorPaneSel.getChildren().add(lblSelection);

        TextField txtSelection = new TextField();
        txtSelection.setLayoutX(30);
        txtSelection.setLayoutY(50);
        txtSelection.setMaxWidth(220);
        anchorPaneSel.getChildren().add(txtSelection);

        txtSelection.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                gui.print(txtSelection.getText());
                stageSel.close();
            }
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectNickname(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(150);
        stageSel.setWidth(240);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(150);
        blueBackground.setWidth(240);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        Label lblSelection = new Label();
        lblSelection.setLayoutX(30);
        lblSelection.setLayoutY(20);
        lblSelection.setText("Insert your nickname:");
        anchorPaneSel.getChildren().add(lblSelection);

        TextField txtSelection = new TextField();
        txtSelection.setLayoutX(30);
        txtSelection.setLayoutY(50);
        txtSelection.setMaxWidth(220);
        anchorPaneSel.getChildren().add(txtSelection);

        txtSelection.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                gui.print(txtSelection.getText());
                stageSel.close();
            }
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectServerPort(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(150);
        stageSel.setWidth(240);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(150);
        blueBackground.setWidth(240);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        Label lblSelection = new Label();
        lblSelection.setLayoutX(30);
        lblSelection.setLayoutY(20);
        lblSelection.setText("Insert the server port:");
        anchorPaneSel.getChildren().add(lblSelection);

        TextField txtSelection = new TextField();
        txtSelection.setLayoutX(30);
        txtSelection.setLayoutY(50);
        txtSelection.setMaxWidth(220);
        anchorPaneSel.getChildren().add(txtSelection);

        txtSelection.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                gui.print(txtSelection.getText());
                stageSel.close();
            }
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectNumPlayers(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(150);
        stageSel.setWidth(240);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(150);
        blueBackground.setWidth(240);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        Label lblSelection = new Label();
        lblSelection.setLayoutX(30);
        lblSelection.setLayoutY(20);
        lblSelection.setText("Select the number of players:");
        anchorPaneSel.getChildren().add(lblSelection);

        Button btn2PLayers = new Button();
        btn2PLayers.setLayoutX(30);
        btn2PLayers.setLayoutY(50);
        btn2PLayers.maxWidth(80);
        btn2PLayers.setText("2 players");
        anchorPaneSel.getChildren().add(btn2PLayers);

        Button btn3PLayers = new Button();
        btn3PLayers.setLayoutX(140);
        btn3PLayers.setLayoutY(50);
        btn3PLayers.maxWidth(80);
        btn2PLayers.setText("3 players");
        anchorPaneSel.getChildren().add(btn3PLayers);

        btn2PLayers.setOnMouseClicked(mouseEvent -> {
            gui.print("2");
            stageSel.close();
        });

        btn3PLayers.setOnMouseClicked(mouseEvent -> {
            gui.print("3");
            stageSel.close();
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void selectGamemode(){
        Stage stageSel = new Stage();
        stages.add(stageSel);
        AnchorPane anchorPaneSel = new AnchorPane();
        Scene sceneSel = new Scene(anchorPaneSel);
        stageSel.setHeight(150);
        stageSel.setWidth(240);
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stageSel.getIcons().add(cranioLogo);

        Rectangle blueBackground = new Rectangle();
        blueBackground.setHeight(150);
        blueBackground.setWidth(240);
        blueBackground.setLayoutX(0);
        blueBackground.setLayoutY(0);
        blueBackground.setFill(Paint.valueOf("#69bae9"));

        anchorPaneSel.getChildren().add(blueBackground);

        Label lblSelection = new Label();
        lblSelection.setLayoutX(30);
        lblSelection.setLayoutY(20);
        lblSelection.setText("Select the gamemode:");
        anchorPaneSel.getChildren().add(lblSelection);

        Button btnEasy = new Button();
        btnEasy.setLayoutX(30);
        btnEasy.setLayoutY(50);
        btnEasy.maxWidth(80);
        btnEasy.setText("EASY");
        anchorPaneSel.getChildren().add(btnEasy);

        Button btnHard = new Button();
        btnHard.setLayoutX(140);
        btnHard.setLayoutY(50);
        btnHard.maxWidth(80);
        btnHard.setText("HARD");
        anchorPaneSel.getChildren().add(btnHard);

        btnEasy.setOnMouseClicked(mouseEvent -> {
            gui.print("0");
            stageSel.close();
        });

        btnHard.setOnMouseClicked(mouseEvent -> {
            gui.print("1");
            stageSel.close();
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    public void quitGUI(){
        stages.forEach(Stage::close);
    }

    public GUIPrinter getPrinter() {
        return printer;
    }
}