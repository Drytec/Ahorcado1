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

import java.util.ArrayList;
import java.util.List;


public class GameController {
    @FXML
    private AnchorPane anchorPane,anchorPane1;
    @FXML
    private ImageView ahorcado;
    @FXML
    private TextField letraParaComprobar;
    @FXML
    private TextField palabraParaComprobar;
    @FXML
    private HBox HBoxLetters;
    private static String wordToFind;
    private char[] wordLetters;
    private int imageNumber = 0;
    List<Integer> positionList = new ArrayList<>();

    @FXML
    void initialize() {
        descomponerPalabra(wordToFind);//Se descompone la palabra en un Array de letras Char y se muestra cantidad de letras
    }

    @FXML
    void OnHandleButtonVerifyWord(ActionEvent event) {
        boolean sameWord = comprobarPalabra(palabraParaComprobar.getText(), wordToFind);
        if (sameWord){
            for(int i = 0; i < wordLetters.length; i++){
                Label wordLabel = (Label) HBoxLetters.getChildren().get(i);
                wordLabel.setText(String.valueOf(wordLetters[i]));
            }
        }
        else {
            imageNumber++;
            ahorcado.setImage(new Image(String.valueOf(getClass().getResource("/org/example/crapsgame/Images/"+imageNumber+".png"))));
        }
    }
    @FXML
    void OnHandleButtonVerifyLetter(ActionEvent event){
        positionList = comprobarLetra(wordLetters, letraParaComprobar.getText().charAt(0));
        if (positionList.isEmpty()){
            imageNumber++;
            ahorcado.setImage(new Image(String.valueOf(getClass().getResource("/org/example/crapsgame/Images/"+imageNumber+".png"))));
        }
        else {
            for (int i = 0; i < positionList.size(); i++ ) {
                Label lettersLabel = (Label) HBoxLetters.getChildren().get(positionList.get(i));
                lettersLabel.setText(String.valueOf(wordLetters[positionList.get(i)]));
            }
        }
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
        HBoxLetters.setSpacing(10);
        wordLetters = word.toCharArray();
        for (char c : wordLetters) {
            Label label = new Label(String.valueOf("__"));
            label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Sans Serif';");
            HBoxLetters.getChildren().add(label);
        }
    }

    public boolean comprobarPalabra(String word1, String word2){
        boolean sameWord = true;
        char[] word1Letters = word1.toCharArray();
        char[] word2Letters = word2.toCharArray();
        for(int i = 0; i < word1Letters.length; i++){
            if(word1Letters[i] != word2Letters[i]){
                sameWord = false;
            }
        }
        return sameWord;
    }


    public List<Integer> comprobarLetra(char[] charArray, char letter) {
        List<Integer> positionArray = new ArrayList<>();
        for(int i = 0; i < charArray.length; i++) {
            if(charArray[i] == letter ){
                positionArray.add(i);
            }
        }
        return positionArray;
    }
}
