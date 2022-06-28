package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Client.FromServerMessagesReader;
import it.polimi.ingsw.Client.UI;
import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Constants.TypesOfUpdate;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.PrintMessages.PrintBoardMessage;
import it.polimi.ingsw.Messages.PrintMessages.PrintCloudsMessage;
import it.polimi.ingsw.Messages.PrintMessages.PrintIslandMessage;
import it.polimi.ingsw.Messages.PrintMessages.PrintIslandsMessage;
import it.polimi.ingsw.Messages.UpdateMessages.TableUpdateMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private Selection selection;
    private ArrayList<Stage> stages;

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

        selection = new Selection(this);
        stages = new ArrayList<>();
        stages.add(stage);

        //set the Scene to the stage and make the stage visible, this lines must be at the end of the start method
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            //chiusura del socket TODO
            quitGUI();
        });
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

    public void initializeGUI(TableUpdateMessage message){
        //set dei nicknames
        controller.setNicknames(message.getPlayers());

        //set students on board
        PrintBoardMessage[] boards = message.getPrintBoardMessages();
        for (int numPlayer=0; numPlayer<Constants.getNumPlayers(); numPlayer++){
            Map<Colors, Integer> entrance = boards[numPlayer].getEntrance();
            Map<Colors, Integer> hall = boards[numPlayer].getHall();
            for(Colors c: Colors.values()){
                controller.getPrinter().modifyStudInEntrance(numPlayer, c, entrance.get(c));
                controller.getPrinter().modifyStudInHall(numPlayer, c, hall.get(c));
            }
            controller.getPrinter().modifyTowersOnBoard(numPlayer, boards[numPlayer].getNumberOfTowers());
        }

        //set students on islands
        PrintIslandsMessage islands = message.getPrintIslandsMessage();
        for (int numIsland=0; numIsland<12; numIsland++){
            PrintIslandMessage island = islands.getIslandMessages().get(numIsland);

            Map<Colors, Integer> students = island.getStudents();
            for (Colors c: Colors.values()) {
                controller.getPrinter().modifyIsland(numIsland, c, students.get(c));
            }

            //controller? numTowers? inibitionCard? TODO

            if (island.isMotherNatureInHere()) {
                controller.getPrinter().modifyMNPosition(numIsland);
            }
        }

        //set students on clouds
        PrintCloudsMessage clouds = message.getPrintCloudsMessage();
        for (int numCloud = 0; numCloud<Constants.getNumPlayers(); numCloud++){
            controller.getPrinter().modifyCloud(numCloud, clouds.getClouds().get(numCloud));
        }
    }

    public void addStage(Stage stage){
        stages.add(stage);
    }

    public void removeStage(Stage stage){
        stages.remove(stage);
    }

    /**
     * close all the stages that are still open
     */
    public void quitGUI(){
        stages.forEach(Stage::close);
    }
}
