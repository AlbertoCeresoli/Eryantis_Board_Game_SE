package it.polimi.ingsw.Client.CLI.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AssistantCardGUI {
    private final ImageView imageView;
    private final Image image;
    private double positionX;
    private double positionY;

    public AssistantCardGUI(ImageView imageView, Image image, AnchorPane anchorPane) {
        this.imageView = imageView;
        this.image = image;

        positionX = imageView.getLayoutX();
        positionY = imageView.getLayoutY();

        imageView.setImage(image);
    }

    public void assistantUsed(){
        imageView.setOpacity(0.5);
    }
}
