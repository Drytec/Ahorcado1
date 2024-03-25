package org.example.crapsgame.controller;

import com.sun.javafx.charts.Legend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.example.crapsgame.model.player.Player;
import org.example.crapsgame.model.alert.AlertBox;

public class GameController {
    @FXML
    private AnchorPane anchorPane;
    @FXML private ImageView ahorcado;
    private Player player;
    String palabra;

    @FXML
    void initialize() {
        ahorcado = new ImageView();
        anchorPane.getChildren().addAll(ahorcado);
    }


    @FXML
    void OnHandleButtonThrowDice(ActionEvent event) {
        Dice diceOne = new Dice();


        int diceValue1 = diceOne.throwDice() + 1;


        ahorcado.setImage(new Image(String.valueOf(getClass().getResource("/org/example/crapsgame/Images/"+diceValue1+".png"))));


        int totalDiceValue = diceValue1;


        String title = "Información de la Partida";
        String header = "A continuación se mostrará tu resultado: ";
        String content = "Este es tu puntaje: " + totalDiceValue;
        new AlertBox().showMessage(title,header,content);
    }



public void setPlayer(Player player){
        this.player = player;
    }}
