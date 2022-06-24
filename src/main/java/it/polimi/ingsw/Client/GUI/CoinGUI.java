package it.polimi.ingsw.Client.GUI;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class CoinGUI {
    private final ImageView imageView;
    private final Image image;
    private double positionX;
    private double positionY;
    private Integer numCoins;
    private Label lblCoin;

    public CoinGUI(ImageView imageView, Image image, AnchorPane anchorPane) {
        this.imageView = imageView;
        this.image = image;

        positionX = imageView.getLayoutX();
        positionY = imageView.getLayoutY();

        numCoins=0;

        lblCoin = new Label();
        lblCoin.setLayoutX(positionX + 65); lblCoin.setLayoutY(positionY + 50);
        lblCoin.setFont(Font.font(40));
        lblCoin.setText(numCoins.toString());

        imageView.setImage(image);
        anchorPane.getChildren().add(lblCoin);
    }

    public void updateCoins(int coinsAdded){
        numCoins+=coinsAdded;
        lblCoin.setText(numCoins.toString());
    }
}
