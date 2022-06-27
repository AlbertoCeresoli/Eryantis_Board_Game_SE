package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Client.FromServerMessagesReader;
import it.polimi.ingsw.Client.UI;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.TypesOfUpdate;
import it.polimi.ingsw.Messages.Message;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Objects;

public class GUI extends Application {
    /*
    private final Socket socket;
    private final ObjectInputStream fromServerInput;
    private final ObjectOutputStream toServerOutput;
    private final FromServerMessagesReader fromServerMessagesReader;
    private boolean activeGame;

     */
    private ControllerInterface controller;

    public static void main(String[] args) throws IOException {
        System.out.println("[CLIENT] Waiting for server connection...");
        //Socket socket = new Socket("localhost", 1234);
        System.out.println("[CLIENT] Connected to server! [localhost, 1234]");

        //GUI gui = new GUI(socket);

        launch();
    }

    /*public GUI(Socket socket) throws IOException {
        this.socket = socket;
        this.toServerOutput = new ObjectOutputStream(socket.getOutputStream());
        this.toServerOutput.flush();
        this.fromServerInput = new ObjectInputStream(socket.getInputStream());
        this.activeGame = true;

        this.fromServerMessagesReader = new FromServerMessagesReader(socket, this);
        new Thread(this.fromServerMessagesReader).start();
    }

     */

    @Override
    public void start(Stage stage) throws Exception {
        //initial stage


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
        controller.setUp(this, stage);
        controller.printTable();
        controller.startEventHandling(); //event handling for the click on the images

        //set the Scene to the stage and make the stage visible, this lines must be at the end of the start method
        stage.setScene(scene);
        stage.show();
    }

    //TODO sostituire con un metodo che invia il messaggio al server
    public void print(String selection){
        System.out.println(selection);
    }


    public void stageSettings(Stage stage){
        //set the icon of the stage
        Image cranioLogo = new Image("file:src/main/resources/Images/LOGO.png");
        stage.getIcons().add(cranioLogo);

        stage.setTitle("Eriantys"); //change the title of the stage
        //stage dimensions are equals to the scene dimensions
        stage.setResizable(false); //make the stage not resizable
    }

    //@Override
    public void elaborateMessage(Message message) {

    }

    public void showGameChanges(TypesOfUpdate selection, int index, Colors c, int numAdded, HashMap<Colors, Integer> map, int newController){
        controller.updateGame(selection, index, c, numAdded, map, newController);
    }

    /*public ObjectInputStream getFromServerInput() {
        return fromServerInput;
    }

     */
}
