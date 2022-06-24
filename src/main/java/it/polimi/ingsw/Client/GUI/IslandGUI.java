package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class IslandGUI {
    private final ImageView imageView;
    private final Image image;
    private ImageView controller;
    private ImageView MN;
    private boolean MNPresent;
    private double positionX;
    private double positionY;
    private Map<Colors, Integer> students;
    private ArrayList<ImageView> studColors;
    private ArrayList<Image> studImages;
    private ArrayList<Label> lblColors;

    public IslandGUI(ImageView imageView, Image image, AnchorPane anchorPane) {
        this.imageView = imageView;
        this.image = image;
        students = new HashMap<>();
        studColors = new ArrayList<>();
        studImages = new ArrayList<>();
        lblColors = new ArrayList<>();
        MNPresent = true;

        for (Colors c: Colors.values()){
            students.put(c, 0);
        }

        positionX = imageView.getLayoutX();
        positionY = imageView.getLayoutY();

        fillStudColors();

        imageView.setImage(image);
        for (Colors c: Colors.values()){
            anchorPane.getChildren().add(studColors.get(c.ordinal()));
            lblColors.get(c.ordinal()).setText(students.get(c).toString());
            anchorPane.getChildren().add(lblColors.get(c.ordinal()));
        }

        controller = new ImageView();
        controller.setLayoutX(positionX + 70); controller.setLayoutY(positionY);
        controller.setFitWidth(20); controller.setFitHeight(40);
        controller.setImage(new Image("file:src/main/resources/Images/Other_objects/Grey_tower.png"));
        anchorPane.getChildren().add(controller);

        MN = new ImageView();
        MN.setLayoutX(positionX + 60); MN.setLayoutY(positionY + 20);
        MN.setFitWidth(20); MN.setFitHeight(40);
        MN.setImage(new Image("file:src/main/resources/Images/Other_objects/MN.png"));
        anchorPane.getChildren().add(MN);
    }

    public void fillStudColors(){
        ImageView green = new ImageView();
        green.setLayoutX(positionX + 25); green.setLayoutY(positionY + 50);
        green.setFitWidth(20); green.setFitHeight(20);
        Label lblGreen = new Label();
        lblGreen.setLayoutX(green.getLayoutX() + 5); lblGreen.setLayoutY(green.getLayoutY());
        lblGreen.setFont(Font.font(15));
        lblColors.add(lblGreen);
        Image greenImage = new Image("file:src/main/resources/Images/Students and teachers/Green_S.png");
        green.setImage(greenImage);
        studColors.add(green);
        studImages.add(greenImage);

        ImageView red = new ImageView();
        red.setLayoutX(positionX + 10); red.setLayoutY(positionY + 10);
        red.setFitWidth(20); red.setFitHeight(20);
        Label lblRed = new Label();
        lblRed.setLayoutX(red.getLayoutX() + 5); lblRed.setLayoutY(red.getLayoutY());
        lblRed.setFont(Font.font(15));
        lblColors.add(lblRed);
        Image redImage = new Image("file:src/main/resources/Images/Students and teachers/Red_S.png");
        red.setImage(redImage);
        studColors.add(red);
        studImages.add(redImage);

        ImageView yellow = new ImageView();
        yellow.setLayoutX(positionX + 40); yellow.setLayoutY(positionY + 10);
        yellow.setFitWidth(20); yellow.setFitHeight(20);
        Label lblYellow = new Label();
        lblYellow.setLayoutX(yellow.getLayoutX() + 5); lblYellow.setLayoutY(yellow.getLayoutY());
        lblYellow.setFont(Font.font(15));
        lblColors.add(lblYellow);
        Image yellowImage = new Image("file:src/main/resources/Images/Students and teachers/Yellow_S.png");
        yellow.setImage(yellowImage);
        studColors.add(yellow);
        studImages.add(yellowImage);

        ImageView pink = new ImageView();
        pink.setLayoutX(positionX + 10); pink.setLayoutY(positionY + 30);
        pink.setFitWidth(20); pink.setFitHeight(20);
        Label lblPink = new Label();
        lblPink.setLayoutX(pink.getLayoutX() + 5); lblPink.setLayoutY(pink.getLayoutY());
        lblPink.setFont(Font.font(15));
        lblColors.add(lblPink);
        Image pinkImage = new Image("file:src/main/resources/Images/Students and teachers/Pink_S.png");
        pink.setImage(pinkImage);
        studColors.add(pink);
        studImages.add(pinkImage);

        ImageView blue = new ImageView();
        blue.setLayoutX(positionX + 40); blue.setLayoutY(positionY + 30);
        blue.setFitWidth(20); blue.setFitHeight(20);
        Label lblBlue = new Label();
        lblBlue.setLayoutX(blue.getLayoutX() + 5); lblBlue.setLayoutY(blue.getLayoutY());
        lblBlue.setFont(Font.font(15));
        lblColors.add(lblBlue);
        Image blueImage = new Image("file:src/main/resources/Images/Students and teachers/Blue_S.png");
        blue.setImage(blueImage);
        studColors.add(blue);
        studImages.add(blueImage);
    }

    public void setIslandToFront(){
        imageView.toFront();
        for (Colors c: Colors.values()){
            studColors.get(c.ordinal()).toFront();
            lblColors.get(c.ordinal()).toFront();
            controller.toFront();
            MN.toFront();
        }
    }

    public void zoomIsland(Rectangle rectOpaqueBackground){
        rectOpaqueBackground.toFront();
        setIslandToFront();

        //transition of every object of the island
        Constants.moveObject(imageView, 500 - imageView.getFitWidth()/2 - imageView.getLayoutX(), 300 - imageView.getFitHeight()/2 - imageView.getLayoutY(), rectOpaqueBackground);
        for (Colors c: Colors.values()){
            Constants.moveObject(studColors.get(c.ordinal()), 300 + 100*c.ordinal() - studColors.get(c.ordinal()).getFitWidth()/2 - studColors.get(c.ordinal()).getLayoutX(), 400 - studColors.get(c.ordinal()).getFitHeight()/2 - studColors.get(c.ordinal()).getLayoutY(), rectOpaqueBackground);
            Constants.moveObject(lblColors.get(c.ordinal()), 290 + 100*c.ordinal() - lblColors.get(c.ordinal()).getLayoutX(), 390 - lblColors.get(c.ordinal()).getLayoutY(), rectOpaqueBackground);
        }
        Constants.moveObject(controller, 700 - controller.getLayoutX(), 200 - controller.getLayoutY(), rectOpaqueBackground);
        Constants.moveObject(MN, 300 - controller.getLayoutX(), 200 - controller.getLayoutY(), rectOpaqueBackground);

        //scale of every object of the island
        Constants.zoomObject(imageView, 3, 3);
        for (Colors c: Colors.values()){
            Constants.zoomObject(studColors.get(c.ordinal()), 3, 3);
            lblColors.get(c.ordinal()).setFont(Font.font(30));
        }
        Constants.zoomObject(controller, 3, 3);
        Constants.zoomObject(MN, 3, 3);
    }

    public void moveBackIsland(Rectangle rectOpaqueBackground){
        Constants.moveBackObject(imageView, -(500 - imageView.getFitWidth()/2 - imageView.getLayoutX()), -(300 - imageView.getFitHeight()/2 - imageView.getLayoutY()));
        for (Colors c: Colors.values()){
            Constants.moveBackObject(studColors.get(c.ordinal()), -(300 + 100*c.ordinal() - studColors.get(c.ordinal()).getFitWidth()/2 - studColors.get(c.ordinal()).getLayoutX()), -(400 - studColors.get(c.ordinal()).getFitHeight()/2 - studColors.get(c.ordinal()).getLayoutY()));
            Constants.moveBackObject(lblColors.get(c.ordinal()), -(290 + 100*c.ordinal() - lblColors.get(c.ordinal()).getLayoutX()), -(390 - lblColors.get(c.ordinal()).getLayoutY()));
        }
        Constants.moveBackObject(controller, -(700 - controller.getLayoutX()), -(200 - controller.getLayoutY()));
        Constants.moveBackObject(MN, -(300 - controller.getLayoutX()), -(200 - controller.getLayoutY()));

        Constants.zoomBackObject(imageView, -3, -3);
        for (Colors c: Colors.values()){
            Constants.zoomBackObject(studColors.get(c.ordinal()),-3,-3);
            lblColors.get(c.ordinal()).setFont(Font.font(15));
        }
        Constants.zoomBackObject(controller, -3, -3);
        Constants.zoomBackObject(MN, -3,-3);

        rectOpaqueBackground.setWidth(1);
        rectOpaqueBackground.setHeight(1);
    }

    public void setController (int PlayerIndex){
        switch (PlayerIndex) {
            case 0:
                controller.setImage(new Image("file:src/main/resources/Images/Other_objects/White_tower.png"));
            case 1:
                controller.setImage(new Image("file:src/main/resources/Images/Other_objects/Grey_tower.png"));
            case 2:
                controller.setImage(new Image("file:src/main/resources/Images/Other_objects/Black_tower.png"));
            default: break;
        }
    }

    public void setNumStudents(Colors color, int numStudents){
        students.put(color, numStudents);
        lblColors.get(color.ordinal()).setText(students.get(color).toString());
    }

    public void showMN(){
        if (!isMNPresent()){
            MN.setImage(new Image("file:"));
        }
        else if (isMNPresent()){
            MN.setImage(new Image("file:src/main/resources/Images/Other_objects/MN.png"));
        }
    }

    /**
     * GET and SET methods
     */
    public void setMNPresent(boolean MNPresent) {
        this.MNPresent = MNPresent;
    }

    public boolean isMNPresent() {
        return MNPresent;
    }
}