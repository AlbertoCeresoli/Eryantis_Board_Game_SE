package it.polimi.ingsw.Client.GUI;

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

    /**
     * Constructor of the cloud. Binds the ImageView and the Image and memorize the position of the ImageView.
     * Creates all the components on the cloud
     * @param imageView imageView of the cloud
     * @param image image of the cloud
     * @param anchorPane used to add all the components (students) of the cloud to the stage
     */
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

    /**
     * used to create all the imageViews of the students on the cloud
     */
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

    /**
     * used to fill the arrayList of the students images
     */
    public void setUpImages(){
        studImages.add(new Image("file:../resources/Images/Students and teachers/Green_S.png"));
        studImages.add(new Image("file:../resources/Images/Students and teachers/Red_S.png"));
        studImages.add(new Image("file:../resources/Images/Students and teachers/Yellow_S.png"));
        studImages.add(new Image("file:../resources/Images/Students and teachers/Pink_S.png"));
        studImages.add(new Image("file:../resources/Images/Students and teachers/Blue_S.png"));

    }

    /**
     * used to put the cloud and all the components to the front of the stage
     */
    public void setCloudToFront(){
        imageView.toFront();
        for (int numStud=0; numStud<Constants.getNumPlayers() + 1; numStud++){
            studColors.get(numStud).toFront();
        }
    }

    /**
     * zooms the cloud and puts it in the center of the stage
     * @param numCloud used to move the cloud in the correct position
     * @param rectOpaqueBackground rectangle put under the clouds to increase the opacity of the rest of the table
     */
    public void zoomClouds(int numCloud, Rectangle rectOpaqueBackground){
        Constants.moveObject(imageView, 150 + 260 * numCloud - imageView.getLayoutX(), 300 - imageView.getLayoutY(), rectOpaqueBackground);
        Constants.zoomObject(imageView, 2, 2);

        for (int numStud=0; numStud<Constants.getNumPlayers() + 1; numStud++){
            Constants.moveObject(studColors.get(numStud), 100 + (260 * numCloud) + 50*numStud - studColors.get(numStud).getLayoutX(), 400 - studColors.get(numStud).getLayoutY(), rectOpaqueBackground);
            Constants.zoomObject(studColors.get(numStud), 2, 2);
        }
    }

    /**
     * zooms back the clouds to the original position
     * @param numCloud used to move the cloud in the correct original position
     */
    public void zoomBackClouds(int numCloud){
        Constants.moveBackObject(imageView, -(150 + 260 * numCloud - imageView.getLayoutX()), -(300 - imageView.getLayoutY()));
        Constants.zoomObject(imageView, -2, -2);

        for (int numStud=0; numStud<Constants.getNumPlayers() + 1; numStud++){
            Constants.moveBackObject(studColors.get(numStud), -(100 + (260 * numCloud) + 50*numStud - studColors.get(numStud).getLayoutX()),-(400 - studColors.get(numStud).getLayoutY()));
            Constants.zoomObject(studColors.get(numStud), -2, -2);
        }
    }

    /**
     * method used to set the images of the students on the clouds
     * @param cloud map of the students to put on the cloud
     */
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
