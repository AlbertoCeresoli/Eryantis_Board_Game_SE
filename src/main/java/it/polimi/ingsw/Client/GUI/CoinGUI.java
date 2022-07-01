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

    /**
     * constructor of the Coin in the GUI. binds the ImageView and the Image and creates the label with the number of coins
     * @param imageView imageView of the coin
     * @param image image of the coin
     * @param anchorPane used to add the label of the number of coins
     */
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

    /**
     * updates the number of coins and the label
     * @param n number of coins to add/remove
     */
    public void updateCoins(int n){
        numCoins = n;
        lblCoin.setText(numCoins.toString());
    }
}
