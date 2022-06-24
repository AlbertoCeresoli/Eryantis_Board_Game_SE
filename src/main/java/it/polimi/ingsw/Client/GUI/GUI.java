package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.TypesOfUpdate;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Objects;

public class GUI extends Application {
    private ControllerInterface controller;

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //the stage (first layer) is already created by the start method
        stageSettings(stage); //method that implements all the stage proprieties
        Constants.setNumPlayers(2);

        //creation of the scene (we must add at least a root node at the scene, the basic root node is the Group)
        FXMLLoader loader;

        if (Constants.getNumPlayers()==3){
            loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Scene3Players.fxml")));
        }
        else{
            loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Scene2Players.fxml")));
        }
        Parent root = loader.load();
        Scene scene = new Scene(root, Color.LIGHTSKYBLUE);

        //creation of the controller and of the printer
        controller = loader.getController();
        controller.setUp();
        controller.printTable();
        controller.startEventHandling(); //event handling for the click on the images

        //set the Scene to the stage and make the stage visible, this lines must be at the end of the start method
        stage.setScene(scene);
        stage.show();



        Stage stage2 = new Stage();
        Group group = new Group();
        Scene scene2 = new Scene(group, Color.LIGHTSKYBLUE);
        stage2.setScene(scene2);
        stage2.show();
    }


    public void stageSettings(Stage stage){
        //set the icon of the stage
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stage.getIcons().add(cranioLogo);

        stage.setTitle("Eriantys"); //change the title of the stage
        //stage dimensions are equals to the scene dimensions
        stage.setResizable(false); //make the stage not resizable
    }

    public void showGameChanges(TypesOfUpdate selection, int index, Colors c, int numAdded, HashMap<Colors, Integer> map, int newController){
        controller.updateGame(selection, index, c, numAdded, map, newController);
    }
}
