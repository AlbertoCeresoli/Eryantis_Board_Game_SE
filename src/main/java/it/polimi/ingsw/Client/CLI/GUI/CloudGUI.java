package it.polimi.ingsw.Client.CLI.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloudGUI {
    private final ImageView imageView;
    private final Image image;
    private double positionX;
    private double positionY;
    private Map<Colors, Integer> students;
    private ArrayList<ImageView> studColors;
    private ArrayList<Image> studImages;

    public CloudGUI(ImageView imageView, Image image, AnchorPane anchorPane) {
        this.imageView = imageView;
        this.image = image;
        students = new HashMap<>();
        studColors = new ArrayList<>();
        studImages = new ArrayList<>();

        for (Colors c: Colors.values()) {
            students.put(c, 0);
        }

        positionX = imageView.getLayoutX();
        positionY = imageView.getLayoutY();

        setUpImageViews();
        setUpImages();

        imageView.setImage(image);
        for (int numCloud = 0; numCloud< Constants.getNumPlayers() + 1; numCloud++){
            anchorPane.getChildren().add(studColors.get(numCloud));
        }
    }

    public void setUpImageViews(){
        ImageView img1 = new ImageView();
        img1.setLayoutX(positionX + 5); img1.setLayoutY(positionY + 13);
        img1.setFitWidth(20); img1.setFitHeight(20);
        img1.setImage(new Image("file:"));
        studColors.add(img1);

        ImageView img2 = new ImageView();
        img2.setLayoutX(positionX + 35); img2.setLayoutY(positionY + 5);
        img2.setFitWidth(20); img2.setFitHeight(20);
        img2.setImage(new Image("file:"));
        studColors.add(img2);

        ImageView img3 = new ImageView();
        img3.setLayoutX(positionX + 13); img3.setLayoutY(positionY + 43);
        img3.setFitWidth(20); img3.setFitHeight(20);
        img3.setImage(new Image("file:"));
        studColors.add(img3);

        if (Constants.getNumPlayers() == 3) {
            ImageView img4 = new ImageView();
            img4.setLayoutX(positionX + 42);
            img4.setLayoutY(positionY + 36);
            img4.setFitWidth(20);
            img4.setFitHeight(20);
            img4.setImage(new Image("file:"));
            studColors.add(img4);
        }
    }

    public void setUpImages(){
        studImages.add(new Image("file:src/main/resources/Images/Students and teachers/Green_S.png"));
        studImages.add(new Image("file:src/main/resources/Images/Students and teachers/Red_S.png"));
        studImages.add(new Image("file:src/main/resources/Images/Students and teachers/Yellow_S.png"));
        studImages.add(new Image("file:src/main/resources/Images/Students and teachers/Pink_S.png"));
        studImages.add(new Image("file:src/main/resources/Images/Students and teachers/Blue_S.png"));

    }

    public void setCloudToFront(){
        imageView.toFront();
        for (int numStud=0; numStud<Constants.getNumPlayers() + 1; numStud++){
            studColors.get(numStud).toFront();
        }
    }

    public void zoomClouds(int numCloud, Rectangle rectOpaqueBackground){
        Constants.moveObject(imageView, 150 + 260 * numCloud - imageView.getLayoutX(), 300 - imageView.getLayoutY(), rectOpaqueBackground);
        Constants.zoomObject(imageView, 2, 2);

        for (int numStud=0; numStud<Constants.getNumPlayers() + 1; numStud++){
            Constants.moveObject(studColors.get(numStud), 100 + (260 * numCloud) + 50*numStud - studColors.get(numStud).getLayoutX(), 400 - studColors.get(numStud).getLayoutY(), rectOpaqueBackground);
            Constants.zoomObject(studColors.get(numStud), 2, 2);
        }
    }

    public void zoomBackClouds(int numCloud){
        Constants.moveBackObject(imageView, -(150 + 260 * numCloud - imageView.getLayoutX()), -(300 - imageView.getLayoutY()));
        Constants.zoomObject(imageView, -2, -2);

        for (int numStud=0; numStud<Constants.getNumPlayers() + 1; numStud++){
            Constants.moveBackObject(studColors.get(numStud), -(100 + (260 * numCloud) + 50*numStud - studColors.get(numStud).getLayoutX()),-(400 - studColors.get(numStud).getLayoutY()));
            Constants.zoomObject(studColors.get(numStud), -2, -2);
        }
    }

    public void setStudents(Map<Colors, Integer> cloud){
        int counter = 0;
        for (Colors c: Colors.values()){
            if (cloud.get(c)>0){
                for (int i=0; i<cloud.get(c); i++) {
                    students.put(c, cloud.get(c));
                    studColors.get(counter).setImage(studImages.get(c.ordinal()));
                    counter++;
                }
            }
        }
    }
}
