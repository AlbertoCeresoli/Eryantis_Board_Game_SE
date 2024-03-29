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

public class BoardGUI {
    private final ImageView imageView;
    private final Image image;
    private final double positionX;
    private final double positionY;
    private final ArrayList<Image> studImages;
    private final ArrayList<Image> teacherImages;
    private Image tower;

    private final Map<Colors, Boolean> teachersOnBoard;
    private final Map<Colors, Integer> studInEntrance;
    private final Map<Colors, Integer> studInHall;
    private Integer numTower;

    private final ArrayList<ImageView> studColorsInEntrance;
    private final ArrayList<ImageView> studColorsInHall;
    private final ArrayList<ImageView> teachers;

    private final ArrayList<Label> lblColorsInEntrance;
    private final ArrayList<Label> lblColorsInHall;
    private final Label lblTower;


    /**
     * Constructor of the Board of a Player. Binds the ImageView and the Image and memorize the position of the ImageView.
     * It also creates all the components on the board (when it's not zoomed)
     * @param imageView imageView of the board
     * @param image image of the board
     * @param anchorPane anchorPane of the stage, used to add all the components of the board
     * @param numPlayer used to set the color of the towers
     */
    public BoardGUI(ImageView imageView, Image image, AnchorPane anchorPane, int numPlayer) {
        this.imageView = imageView;
        this.image = image;
        studInEntrance = new HashMap<>();
        studInHall = new HashMap<>();
        teachersOnBoard = new HashMap<>();

        studColorsInEntrance = new ArrayList<>();
        studColorsInHall = new ArrayList<>();
        teachers = new ArrayList<>();

        studImages = new ArrayList<>();
        teacherImages = new ArrayList<>();
        switch (numPlayer) {
            case 0 -> tower = new Image("file:../src/resources/Images/Other_objects/White_tower.png");
            case 1 -> tower = new Image("file:../src/resources/Images/Other_objects/Grey_tower.png");
            case 2 -> tower = new Image("file:../src/resources/Images/Other_objects/Black_tower.png");
            default -> {
            }
        }

        lblColorsInEntrance = new ArrayList<>();
        lblColorsInHall = new ArrayList<>();
        lblTower = new Label();

        for (Colors c: Colors.values()){
            studInEntrance.put(c, 0);
            studInHall.put(c,0);
            teachersOnBoard.put(c, false);
        }

        numTower=8;

        positionX = imageView.getLayoutX();
        positionY = imageView.getLayoutY();

        fillStudColors();
        fillTeachersAndTowers();

        imageView.setImage(image);
        for (Colors c: Colors.values()){
            anchorPane.getChildren().add(studColorsInEntrance.get(c.ordinal()));
            lblColorsInEntrance.get(c.ordinal()).setText(studInEntrance.get(c).toString());
            anchorPane.getChildren().add(lblColorsInEntrance.get(c.ordinal()));

            anchorPane.getChildren().add(studColorsInHall.get(c.ordinal()));
            lblColorsInHall.get(c.ordinal()).setText(studInHall.get(c).toString());
            anchorPane.getChildren().add(lblColorsInHall.get(c.ordinal()));

            anchorPane.getChildren().add(teachers.get(c.ordinal()));
        }
        anchorPane.getChildren().add(lblTower);
    }

    /**
     * method used to create all the ImageViews, images and labels of the teachers and the towers
     */
    public void fillTeachersAndTowers(){
        Image greenImageT = new Image("file:../src/resources/Images/Students and teachers/Green_T.png");
        teacherImages.add(greenImageT);

        Image redImageT = new Image("file:../src/resources/Images/Students and teachers/Red_T.png");
        teacherImages.add(redImageT);

        Image yellowImageT = new Image("file:../src/resources/Images/Students and teachers/Yellow_T.png");
        teacherImages.add(yellowImageT);

        Image pinkImageT = new Image("file:../src/resources/Images/Students and teachers/Pink_T.png");
        teacherImages.add(pinkImageT);

        Image blueImageT = new Image("file:../src/resources/Images/Students and teachers/Blue_T.png");
        teacherImages.add(blueImageT);

        for (Colors c: Colors.values()){
            ImageView stud = new ImageView();
            stud.setLayoutX(positionX+140); stud.setLayoutY(positionY + 5 + 15*c.ordinal());
            stud.setFitWidth(15); stud.setFitHeight(15);
            teachers.add(stud);
        }
        teachers.get(0).setImage(new Image("file:"));
        teachers.get(1).setImage(new Image("file:"));
        teachers.get(2).setImage(new Image("file:"));
        teachers.get(3).setImage(new Image("file:"));
        teachers.get(4).setImage(new Image("file:"));

        lblTower.setLayoutX(positionX + 170); lblTower.setLayoutY(positionY + 20);
        lblTower.setFont(Font.font(30));
        lblTower.setText(numTower.toString());
    }

    /**
     * method used to create all the ImageViews, images and labels of the students in the hall and the entrance
     */
    public void fillStudColors(){
        Image greenImage = new Image("file:../src/resources/Images/Students and teachers/Green_S.png");
        studImages.add(greenImage);

        Image redImage = new Image("file:../src/resources/Images/Students and teachers/Red_S.png");
        studImages.add(redImage);

        Image yellowImage = new Image("file:../src/resources/Images/Students and teachers/Yellow_S.png");
        studImages.add(yellowImage);

        Image pinkImage = new Image("file:../src/resources/Images/Students and teachers/Pink_S.png");
        studImages.add(pinkImage);

        Image blueImage = new Image("file:../src/resources/Images/Students and teachers/Blue_S.png");
        studImages.add(blueImage);

        for (Colors c: Colors.values()){
            ImageView stud = new ImageView();
            stud.setLayoutX(positionX); stud.setLayoutY(positionY + 5 + 15*c.ordinal());
            stud.setFitWidth(15); stud.setFitHeight(15);
            Label lblStudInEntrance = new Label();
            lblStudInEntrance.setLayoutX(stud.getLayoutX() + 3); lblStudInEntrance.setLayoutY(stud.getLayoutY());
            lblStudInEntrance.setFont(Font.font(13));
            lblColorsInEntrance.add(lblStudInEntrance);
            studColorsInEntrance.add(stud);
        }
        studColorsInEntrance.get(0).setImage(greenImage);
        studColorsInEntrance.get(1).setImage(redImage);
        studColorsInEntrance.get(2).setImage(yellowImage);
        studColorsInEntrance.get(3).setImage(pinkImage);
        studColorsInEntrance.get(4).setImage(blueImage);

        for (Colors c: Colors.values()){
            ImageView stud = new ImageView();
            stud.setLayoutX(positionX + 40); stud.setLayoutY(positionY + 5 + 15*c.ordinal());
            stud.setFitWidth(15); stud.setFitHeight(15);
            Label lblStudInHall = new Label();
            lblStudInHall.setLayoutX(stud.getLayoutX() + 3); lblStudInHall.setLayoutY(stud.getLayoutY());
            lblStudInHall.setFont(Font.font(13));
            lblColorsInHall.add(lblStudInHall);
            studColorsInHall.add(stud);
        }
        studColorsInHall.get(0).setImage(greenImage);
        studColorsInHall.get(1).setImage(redImage);
        studColorsInHall.get(2).setImage(yellowImage);
        studColorsInHall.get(3).setImage(pinkImage);
        studColorsInHall.get(4).setImage(blueImage);
    }

    /**
     * sets the board (and all the components) to the front of the scene
     */
    public void setBoardToFront(){
        imageView.toFront();
        for(Colors c: Colors.values()){
            studColorsInHall.get(c.ordinal()).toBack();
            studColorsInEntrance.get(c.ordinal()).toBack();
            lblColorsInHall.get(c.ordinal()).toBack();
            lblColorsInEntrance.get(c.ordinal()).toBack();
            teachers.get(c.ordinal()).toBack();
            lblTower.toBack();
        }
    }

    /**
     * zooms the Board and puts it in the center of the stage
     * @param rectOpaqueBackground rectangle put under the board to increase the opacity of the rest of the table
     * @param anchorPane used to add all the ImageViews and Images of the zoomed components
     * @return ArrayList of the ImageViews created
     */
    public ArrayList<ImageView> zoomBoard(Rectangle rectOpaqueBackground, AnchorPane anchorPane){
        Constants.moveObject(imageView, 500 - imageView.getFitWidth()/2 - imageView.getLayoutX(), 300 - imageView.getFitHeight()/2 - imageView.getLayoutY(), rectOpaqueBackground);
        Constants.zoomObject(imageView, 3, 3);

        ArrayList<ImageView> images = new ArrayList<>();

        int studCounter=0;
        for (Colors c: Colors.values()) {
            for (int i = 0; i < studInEntrance.get(c); i++) {
                    ImageView img = new ImageView();
                    img.setLayoutX(120 + 50 * (studCounter/5)); img.setLayoutY(131 + studCounter % 5 * 57);
                    img.setFitWidth(40); img.setFitHeight(40);
                    img.setImage(studImages.get(c.ordinal()));
                    anchorPane.getChildren().add(img);
                    images.add(img);

                    studCounter++;
            }
        }

        studCounter=0;
        for (Colors c: Colors.values()) {
            for (int i = 0; i < studInHall.get(c); i++) {
                ImageView img = new ImageView();
                img.setLayoutX(248 + 38 * i); img.setLayoutY(134 + c.ordinal() * 57);
                img.setFitWidth(37); img.setFitHeight(37);
                img.setImage(studImages.get(c.ordinal()));
                anchorPane.getChildren().add(img);
                images.add(img);

                studCounter++;
            }
        }

        for (Colors c: Colors.values()){
            if (teachersOnBoard.get(c)){
                ImageView img = new ImageView();
                img.setLayoutX(666); img.setLayoutY(134 + c.ordinal() * 57);
                img.setFitWidth(37); img.setFitHeight(37);
                img.setImage(teacherImages.get(c.ordinal()));
                anchorPane.getChildren().add(img);
                images.add(img);
            }
        }

        for (int numT=0; numT< numTower; numT++){
            ImageView img = new ImageView();
            img.setLayoutX(750 + 80*(numT/4)); img.setLayoutY(150 + (numT%4) * 57);
            img.setFitWidth(37); img.setFitHeight(50);
            img.setImage(tower);
            anchorPane.getChildren().add(img);
            images.add(img);
        }

        return images;
    }

    /**
     * zooms back the board to the original position and puts to front all the components of the board
     * @param images arrayList of the imageViews created during the zoom, this method will delete them
     * @param anchorPane used to remove the imageViews
     */
    public void zoomBackBoard(ArrayList<ImageView> images, AnchorPane anchorPane){
        Constants.zoomBackObject(imageView, -3, -3);
        Constants.moveBackObject(imageView, -(500 - imageView.getFitWidth()/2 - imageView.getLayoutX()), -(300 - imageView.getFitHeight()/2 - imageView.getLayoutY()));

        while (!images.isEmpty()){
            anchorPane.getChildren().remove(images.get(0));
            images.remove(0);
        }

        for(Colors c: Colors.values()){
            studColorsInHall.get(c.ordinal()).toFront();
            studColorsInEntrance.get(c.ordinal()).toFront();
            lblColorsInHall.get(c.ordinal()).toFront();
            lblColorsInEntrance.get(c.ordinal()).toFront();
            teachers.get(c.ordinal()).toFront();
            lblTower.toFront();
        }
    }

    /**
     * changes the number of the students in the Entrance
     * @param c color of the student to update
     * @param n number of the students to add/remove
     */
    public void setStudEntrance (Colors c, int n){
        studInEntrance.put(c, n);
        lblColorsInEntrance.get(c.ordinal()).setText(studInEntrance.get(c).toString());
    }

    /**
     * changes the number of the students in the Hall
     * @param c color of the student to update
     * @param n number of the students
     */
    public void setStudHall (Colors c, int n){
        studInHall.put(c, n);
        lblColorsInHall.get(c.ordinal()).setText(studInHall.get(c).toString());
    }

    /**
     * changes the number of the towers
     * @param n number of the towers
     */
    public void updateTowers (int n){
        numTower = n;
        lblTower.setText(numTower.toString());
    }

    /**
     * changes the teachers on the board
     * @param colors array of booleans that indicates the teachers on the board
     */
    public void updateTeachers (boolean[] colors){
        for (Colors c: Colors.values()){
            if (colors[c.ordinal()]){
                teachersOnBoard.put(c, true);
                teachers.get(c.ordinal()).setImage(teacherImages.get(c.ordinal()));
            }
            else {
                teachersOnBoard.put(c, false);
                teachers.get(c.ordinal()).setImage(new Image("file:"));
            }
        }
    }
}
