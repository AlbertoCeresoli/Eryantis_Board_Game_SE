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
import java.util.Map;

public class CharacterCardGUI {
    private final ImageView imageView;
    private Image image;
    private double positionX;
    private double positionY;
    private int index;
    private String effect;
    private ArrayList<ImageView> students;
    private ArrayList<Image> studImages;

    /**
     * Constructor of the character card. memorize the ImageView and his position.
     * @param imgVw imageView of the character card
     */
    public CharacterCardGUI(ImageView imgVw) {
        imageView = imgVw;
        positionX = imgVw.getLayoutX();
        positionY = imgVw.getLayoutY();

        studImages = new ArrayList<>();
        Image greenImage = new Image("file:../resources/Images/Students and teachers/Green_S.png");
        studImages.add(greenImage);

        Image redImage = new Image("file:../resources/Images/Students and teachers/Red_S.png");
        studImages.add(redImage);

        Image yellowImage = new Image("file:../resources/Images/Students and teachers/Yellow_S.png");
        studImages.add(yellowImage);

        Image pinkImage = new Image("file:../resources/Images/Students and teachers/Pink_S.png");
        studImages.add(pinkImage);

        Image blueImage = new Image("file:../resources/Images/Students and teachers/Blue_S.png");
        studImages.add(blueImage);
    }

    /**
     * Binds the ImageView with the correct image and effect
     * @param index of the characted card
     */
    public void setCC(int index, AnchorPane anchorPane){
        this.index=index;
        ArrayList<Image> CCImages = Constants.createArrayImagesCC();
        image = CCImages.get(index - 1);
        imageView.setImage(image);

        switch (index){
            case 0:
                effect = Constants.MONK_EFFECT;
                break;
            case 1:
                effect = Constants.FARMER_EFFECT;
                break;
            case 2:
                effect = Constants.HERALD_EFFECT;
                break;
            case 3:
                effect = Constants.MAGIC_POSTMAN_EFFECT;
                break;
            case 4:
                effect = Constants.GRANDMA_HERBS_EFFECT;
                break;
            case 5:
                effect = Constants.CENTAUR_EFFECT;
                break;
            case 6:
                effect = Constants.JOKER_EFFECT;
                break;
            case 7:
                effect = Constants.KNIGHT_EFFECT;
                break;
            case 8:
                effect = Constants.MUSHROOMS_MAN_EFFECT;
                break;
            case 9:
                effect = Constants.MINSTREL_EFFECT;
                break;
            case 10:
                effect = Constants.SPOILED_PRINCESS_EFFECT;
                break;
            case 11:
                effect = Constants.THIEF_EFFECT;
                break;
            default:
                break;
        }

        if (index == 1){
            createStudImageViews(4, anchorPane);
        }
        else if (index == 7){
            createStudImageViews(6, anchorPane);
        }
        else if (index == 11){
            createStudImageViews(4, anchorPane);
        }
    }

    /**
     * creates the imageViews of the students if requested
     * @param numStuds number of the students
     * @param anchorPane used to add the imageViews of the students
     */
    public void createStudImageViews(int numStuds, AnchorPane anchorPane){
        students = new ArrayList<>();

        for (int numStud=0; numStud<numStuds; numStud++){
            ImageView s = new ImageView();
            s.setLayoutX(positionX + 30 * (numStud % 2));
            s.setLayoutY(positionY +  30 * (numStud / 2));
            s.setFitWidth(25); s.setFitHeight(25);
            anchorPane.getChildren().add(s);
            students.add(s);
        }
    }

    public void setStudents(Map<Colors, Integer> studs){
        int counter = 0;
        for (Colors c: Colors.values()){
            if (studs.get(c)>0){
                for (int i=0; i<studs.get(c); i++) {
                    students.get(counter).setImage(studImages.get(c.ordinal()));
                    counter++;
                }
            }
        }
    }

    /**
     * puts the character card to the front of the stage
     */
    public void setCCToFront(){
        imageView.toFront();
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

        for (int numStud=0; numStud<students.size(); numStud++){
            students.get(numStud).toFront();
        }
    }

    public int getIndex() {
        return index;
    }
}
