package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class GUI extends Application {
    private ControllerInterface controller;
    private Selection selection;
    private GUINetworkConnection guiNetworkConnection;
    private ArrayList<Stage> stages;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //initial stage
        Constants.setNumPlayers(2);
        Constants.setGameMode(true);

        //the stage (first layer) is already created by the start method
        stageSettings(stage); //method that implements all the stage proprieties

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

        stages = new ArrayList<>();
        stages.add(stage);

        selection = new Selection(this);
        guiNetworkConnection = new GUINetworkConnection(this);

        //set the Scene to the stage and make the stage visible, this lines must be at the end of the start method
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            quitGUI();
        });
    }

    public void print(String selection){
        guiNetworkConnection.sendMessageToServer(selection);
    }

    public void stageSettings(Stage stage){
        //set the icon of the stage
        Image cranioLogo = new Image("file:src/resources/Images/LOGO.png");
        stage.getIcons().add(cranioLogo);

        stage.setTitle("Eriantys"); //change the title of the stage
        //stage dimensions are equals to the scene dimensions
        stage.setResizable(false); //make the stage not resizable
    }

    /**
     * add a stage to the ArrayList of open stages
     * @param stage stage to add
     */
    public void addStage(Stage stage){
        stages.add(stage);
    }

    /**
     * remove a stage to the ArrayList of open stages
     * @param stage stage to remove
     */
    public void removeStage(Stage stage){
        stages.remove(stage);
    }

    /**
     * close all the stages that are still open
     */
    public void quitGUI(){
        stages.forEach(Stage::close);
        guiNetworkConnection.exit();
    }

    public Selection getSelection() {
        return selection;
    }

    public GUINetworkConnection getGuiNetworkConnection() {
        return guiNetworkConnection;
    }

    public ControllerInterface getController() {
        return controller;
    }
}
