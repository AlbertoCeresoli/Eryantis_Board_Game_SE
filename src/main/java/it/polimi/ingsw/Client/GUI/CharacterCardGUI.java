package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Colors;
import it.polimi.ingsw.Constants.Constants;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CharacterCardGUI {
    private final ImageView imageView;
    private Image image;
    private final double positionX;
    private final double positionY;
    private int index;
    private String effect;
    private Map<Colors, Integer> students;
    private ArrayList<ImageView> studColors;
    private final ArrayList<Image> studImages;

    /**
     * Constructor of the character card. memorize the ImageView and his position.
     * @param imgVw imageView of the character card
     */
    public CharacterCardGUI(ImageView imgVw) {
        imageView = imgVw;
        positionX = imgVw.getLayoutX();
        positionY = imgVw.getLayoutY();

        studImages = new ArrayList<>();
        studImages.add(new Image("file:src/resources/Images/Students and teachers/Green_S.png"));
        studImages.add(new Image("file:src/resources/Images/Students and teachers/Red_S.png"));
        studImages.add(new Image("file:src/resources/Images/Students and teachers/Yellow_S.png"));
        studImages.add(new Image("file:src/resources/Images/Students and teachers/Pink_S.png"));
        studImages.add(new Image("file:src/resources/Images/Students and teachers/Blue_S.png"));
    }

    /**
     * Binds the ImageView with the correct image and effect
     * @param index of the character card
     */
    public void setCC(int index, String effect, AnchorPane anchorPane){
        this.index= index;
        this.effect = effect;
        ArrayList<Image> CCImages = Constants.createArrayImagesCC();
        image = CCImages.get(index - 1);
        imageView.setImage(image);

        if (this.index == 1){
            students = new HashMap<>();
            studColors = new ArrayList<>();
            createStudImageViews(4, anchorPane);
        }
        else if (this.index == 7){
            students = new HashMap<>();
            studColors = new ArrayList<>();
            createStudImageViews(6, anchorPane);
        }
        else if (this.index == 11){
            students = new HashMap<>();
            studColors = new ArrayList<>();
            createStudImageViews(4, anchorPane);
        }
    }

    /**
     * creates the imageViews of the students if requested
     * @param numStuds number of the students
     * @param anchorPane used to add the imageViews of the students
     */
    public void createStudImageViews(int numStuds, AnchorPane anchorPane){
        for (int numStud=0; numStud<numStuds; numStud++){
            ImageView s = new ImageView();
            s.setLayoutX(positionX + 30 * (numStud % 2));
            s.setLayoutY(positionY +  30 * (numStud / 2));
            s.setFitWidth(25); s.setFitHeight(25);
            anchorPane.getChildren().add(s);
            studColors.add(s);
        }
    }

    /**
     * set the student's images of the cards
     * @param studs Map of the students on the card
     */
    public void setStudents(Map<Colors, Integer> studs){
        students = studs;

        int counter = 0;
        for (Colors c: Colors.values()){
            if (studs.get(c)>0){
                for (int i=0; i<studs.get(c); i++) {
                    studColors.get(counter).setImage(studImages.get(c.ordinal()));
                    counter++;
                }
            }
        }

        if (counter == 0) {
            for (Colors c : Colors.values()) {
                students.put(c, 0);
            }
            for (int i = 0; i < Constants.getNumPlayers() + 1; i++) {
                studColors.get(i).setImage(new Image("file:"));
            }
        }
    }

    /**
     * puts the character card to the front of the stage
     */
    public void setCCToFront(){
        imageView.toFront();
        if (studColors!=null) {
            for (ImageView studColor : studColors) {
                studColor.toFront();
            }
        }
    }

    /**
     * zooms the imageView of the character card and creates the TextArea of the effect
     * @param numCC used to set the position of the card on the stage
     * @param rectOpaqueBackground rectangle put under the character cards to increase the opacity of the rest of the table
     * @param anchorPane used to add the text area
     * @return returns the text area created
     */
    public TextArea zoomCC(int numCC, Rectangle rectOpaqueBackground, AnchorPane anchorPane){
        Constants.moveObject(imageView, 150 + 260 * numCC - imageView.getLayoutX(), 300 - imageView.getLayoutY(), rectOpaqueBackground);
        Constants.zoomObject(imageView, 2, 2);

        TextArea txtEffect = new TextArea();
        txtEffect.setLayoutX(90 + 260 * numCC);
        txtEffect.setLayoutY(360);
        txtEffect.setMaxSize(180, 120);
        txtEffect.setWrapText(true);
        txtEffect.setFont(Font.font(10));
        txtEffect.setText(effect);
        anchorPane.getChildren().add(txtEffect);

        if (studColors!=null) {
            for (int numStud = 0; numStud < studColors.size(); numStud++) {
                Constants.moveObject(studColors.get(numStud), 100 + (260 * numCC) + 50 * numStud - studColors.get(numStud).getLayoutX(), 500 - studColors.get(numStud).getLayoutY(), rectOpaqueBackground);
                Constants.zoomObject(studColors.get(numStud), 2, 2);
            }
        }

        return txtEffect;
    }

    /**
     * zooms back the character card to the original position and delete the text area of the character card zoomed
     * @param numCC used to move to the correct position the character card
     * @param effects arraylist of all the textAreas created during the zoom
     * @param anchorPane used to remove the textArea
     */
    public void zoomBackCC(int numCC, ArrayList<TextArea> effects, AnchorPane anchorPane){
        Constants.moveBackObject(imageView, -(150 + 260 * numCC - imageView.getLayoutX()), -(300 - imageView.getLayoutY()));
        Constants.zoomObject(imageView, -2, -2);

        anchorPane.getChildren().remove(effects.get(numCC));

        if (studColors!=null) {
            for (int numStud = 0; numStud < studColors.size(); numStud++) {
                Constants.moveBackObject(studColors.get(numStud), -(100 + (260 * numCC) + 50 * numStud - studColors.get(numStud).getLayoutX()), -(500 - studColors.get(numStud).getLayoutY()));
                Constants.zoomObject(studColors.get(numStud), -2, -2);
            }
        }
    }

    /**
     * get and set methods
     */
    public int getIndex() {
        return index;
    }

    public Image getImage() {
        return image;
    }
}
