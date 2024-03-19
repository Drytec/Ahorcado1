package org.example.crapsgame.controller;


import javafx.event.ActionEvent;

import java.util.Objects;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.crapsgame.model.alert.AlertBox;
import org.example.crapsgame.model.player.Player;

public class GameController {
    private Player player;
    @FXML
     ImageView myImageView;
    @FXML
    ImageView myImageView1;
     @FXML
     Button myButton;
    String[] imagePaths = {
            "/org/example/crapsgame/images/Dado1.png",
            "/org/example/crapsgame/images/Dado2.png",
            "/org/example/crapsgame/images/Dado3.png",
            "/org/example/crapsgame/images/Dado4.png",
            "/org/example/crapsgame/images/Dado5.png",
            "/org/example/crapsgame/images/Dado6.png",
    };
    String[] imagePaths1= {
            "/org/example/crapsgame/images/Dado1.png",
            "/org/example/crapsgame/images/Dado2.png",
            "/org/example/crapsgame/images/Dado3.png",
            "/org/example/crapsgame/images/Dado4.png",
            "/org/example/crapsgame/images/Dado5.png",
            "/org/example/crapsgame/images/Dado6.png",
    };




    public void onHandleButtonRollTheDice(ActionEvent event) {
        int randomIndex = new Random().nextInt(imagePaths.length);
        int diceValue = randomIndex + 1;
        int random = new Random().nextInt(imagePaths1.length);
        int diceValue1 = random + 1;
        int result;


        Image myImage = new Image(getClass().getResourceAsStream(imagePaths[randomIndex]));
        Image myImage1 = new Image((getClass().getResourceAsStream(imagePaths[random])));


        myImageView.setImage(myImage);
        myImageView1.setImage(myImage1);
        result=diceValue1+diceValue;
        new AlertBox().showMessage("Hola "+ player.getNickname(),"La suma de los dados es", String.valueOf(result));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
