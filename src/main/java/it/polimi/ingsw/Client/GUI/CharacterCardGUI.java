package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Constants.Constants;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class CharacterCardGUI {
    private final ImageView imageView;
    private Image image;
    private double positionX;
    private double positionY;
    private String effect;

    /**
     * Constructor of the character card. memorize the ImageView and his position.
     * @param imgVw imageView of the character card
     */
    public CharacterCardGUI(ImageView imgVw) {
        imageView = imgVw;
        positionX = imgVw.getLayoutX();
        positionY = imgVw.getLayoutY();

        imageView.setImage(new Image("file:src/main/resources/Images/Character_Cards/CC1.jpg"));
        effect = Constants.MONK_EFFECT;
    }

    /**
     * Binds the ImageView with the correct image and effect
     * @param index of the characted card
     */
    public void setCC(int index){
        ArrayList<Image> CCImages = Constants.createArrayImagesCC();
        image = CCImages.get(index);
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
    }
}
