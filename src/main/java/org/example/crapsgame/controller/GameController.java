package org.example.crapsgame.controller;

import com.sun.javafx.charts.Legend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.example.crapsgame.model.player.Player;
import org.example.crapsgame.model.alert.AlertBox;


public class GameController {
    @FXML
    private AnchorPane anchorPane,anchorPane1;
    @FXML
    private ImageView ahorcado;
    @FXML
    private TextField letraParaComprobar;
    private static String wordToFind;
    private char[] wordLetters;

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
        //centra la imagen

        int totalDiceValue = diceValue1;
        descomponerPalabra(wordToFind);//Hay que hacer el get y ponerlo en la funcion luego hacer if y ya gg
        comprobarTexto(wordLetters, letraParaComprobar.getText().charAt(0));
    }

    @FXML
    void question(ActionEvent event) {
        String title = "Información de la Partida";
        String header = "La palabra contiene X letras";//hay que configurar eso despues
        String content = "Te quedan X intentos";
        new AlertBox().showMessage(title,header,content);


    }

    public static void setWord(String text){
        wordToFind = text;
    }

    public void setPlayer(Player player){

        this.player = player;
    }
    private Player player;

    public void descomponerPalabra(String word) {
        HBox hbox= new HBox();
        hbox.setSpacing(10);
        wordLetters = word.toCharArray();
        for (char c : wordLetters) {
            Label label = new Label(String.valueOf(c));
            label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Sans Serif';");
            hbox.getChildren().add(label);

        }
        anchorPane1.getChildren().add(hbox);
    }

    public boolean comprobarTexto(char[] charArray, char letter) {
        boolean matchingLetter = false;
        for(char c : charArray) {
            if(c == letter ){
                matchingLetter = true;
                break;
            }
        }
        return matchingLetter;
    }

}
