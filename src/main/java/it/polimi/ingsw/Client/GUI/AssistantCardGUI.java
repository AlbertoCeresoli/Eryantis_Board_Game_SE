package it.polimi.ingsw.Client.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AssistantCardGUI {
    private final ImageView imageView;
    private final Image image;
    private double positionX;
    private double positionY;

    /**
     * Constructor of an assistant card in the GUI. Keeps memorized the image set in the ImageView and the ImageView position
     * @param imageView imageView
     * @param image image of the assistant card
     */
    public AssistantCardGUI(ImageView imageView, Image image) {
        this.imageView = imageView;
        this.image = image;

        positionX = imageView.getLayoutX();
        positionY = imageView.getLayoutY();

        imageView.setImage(image);
    }

    /**
     * remove the image of the assistant card when used
     */
    public void assistantUsed(){
        imageView.setImage(new Image("file:"));
    }

    /**
     * get and set methods
     */
    public Image getImage() {
        return image;
    }
}
