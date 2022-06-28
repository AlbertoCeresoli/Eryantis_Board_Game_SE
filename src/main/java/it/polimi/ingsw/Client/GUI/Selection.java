package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Constants;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class Selection {
    private final GUI gui;

    public Selection(GUI gui) {
        this.gui = gui;
    }

    /**
     * method used to ask to the player to select a color
     */
    public void selectColor(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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
            gui.removeStage(stageSel);
            stageSel.close();
        });
        redImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("red");
            gui.removeStage(stageSel);
            stageSel.close();
        });
        yellowImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("yellow");
            gui.removeStage(stageSel);
            stageSel.close();
        });
        pinkImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("pink");
            gui.removeStage(stageSel);
            stageSel.close();
        });
        blueImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("blue");
            gui.removeStage(stageSel);
            stageSel.close();
        });
    }

    /**
     * method used to ask to the player to select an assistant card
     */
    public void selectAC(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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

        acImgVw.forEach((image)-> image.setOnMouseClicked(mouseEvent -> {
            gui.print(String.valueOf(acImgVw.indexOf(image)));
            gui.removeStage(stageSel);
            stageSel.close();
        }));

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    /**
     * method used to ask to the player to select an island index
     */
    public void selectIsland(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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

        islandsImgVw.forEach((image)-> image.setOnMouseClicked(mouseEvent -> {
            gui.print(String.valueOf(islandsImgVw.indexOf(image)));
            gui.removeStage(stageSel);
            stageSel.close();
        }));

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    /**
     * method used to ask to the player to select a cloud index
     */
    public void selectCloud(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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

        cloudsImgVw.forEach((image)-> image.setOnMouseClicked(mouseEvent -> {
            gui.print(String.valueOf(cloudsImgVw.indexOf(image)));
            gui.removeStage(stageSel);
            stageSel.close();
        }));

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    /**
     * method used to ask to the player to select the number of steps of MN
     */
    public void selectSteps(int maxSteps){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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

        cloudsImgVw.forEach((image)-> image.setOnMouseClicked(mouseEvent -> {
            gui.print(String.valueOf(cloudsImgVw.indexOf(image) + 1));
            gui.removeStage(stageSel);
            stageSel.close();
        }));

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    /**
     * method used to ask to the player to select between island or board
     */
    public void selectPlace(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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
            gui.removeStage(stageSel);
            stageSel.close();
        });

        boardImgView.setOnMouseClicked(mouseEvent -> {
            gui.print("board");
            gui.removeStage(stageSel);
            stageSel.close();
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    /**
     * method used to ask to the player to insert the IP address of the server
     */
    public void selectIP(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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
        lblSelection.setText("Insert the IP address");
        anchorPaneSel.getChildren().add(lblSelection);

        TextField txtSelection = new TextField();
        txtSelection.setLayoutX(30);
        txtSelection.setLayoutY(50);
        txtSelection.setMaxWidth(220);
        anchorPaneSel.getChildren().add(txtSelection);

        stageSel.setScene(sceneSel);
        stageSel.show();

        txtSelection.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                gui.print(txtSelection.getText());
                gui.removeStage(stageSel);
                stageSel.close();
            }
        });
    }

    /**
     * method used to ask to the player to insert the nickname
     */
    public void selectNickname(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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
                gui.removeStage(stageSel);
                stageSel.close();
            }
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    /**
     * method used to ask to the player to insert the server port
     */
    public void selectServerPort(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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
                gui.removeStage(stageSel);
                stageSel.close();
            }
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    /**
     * method used to ask to the player to select the number of players of the match
     */
    public void selectNumPlayers(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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
        btn3PLayers.setText("3 players");
        anchorPaneSel.getChildren().add(btn3PLayers);

        btn2PLayers.setOnMouseClicked(mouseEvent -> {
            gui.print("2");
            gui.removeStage(stageSel);
            stageSel.close();
        });

        btn3PLayers.setOnMouseClicked(mouseEvent -> {
            gui.print("3");
            gui.removeStage(stageSel);
            stageSel.close();
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }

    /**
     * method used to ask to the player to select the gamemode of the match
     */
    public void selectGamemode(){
        Stage stageSel = new Stage();
        gui.addStage(stageSel);
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
            gui.removeStage(stageSel);
            stageSel.close();
        });

        btnHard.setOnMouseClicked(mouseEvent -> {
            gui.print("1");
            gui.removeStage(stageSel);
            stageSel.close();
        });

        stageSel.setScene(sceneSel);
        stageSel.show();
    }
}
