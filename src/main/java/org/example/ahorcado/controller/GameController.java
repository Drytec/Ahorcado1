package org.example.ahorcado.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import java.io.IOException;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.ahorcado.model.player.Player;
import org.example.ahorcado.model.alert.AlertBox;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.*;


public class GameController {
    @FXML
    private AnchorPane anchorPane,anchorPane1;
    @FXML
    private ImageView ahorcado;
    @FXML
    private TextField letterToCheck;
    @FXML
    private TextField wordToCheck;
    @FXML
    private HBox HBoxLetters;
    @FXML
    private Label warningWordLabel;
    @FXML
    private Label warningLetterLabel;
    private static String wordToFind;
    private char[] wordLetters;
    private int imageNumber = 0;
    private int questionCounter = 0;
    private int clueCounter = 3;

    List<Character> wordLettersClue = new ArrayList<>();
    List<Integer> positionList = new ArrayList<>();
    List<Integer> positionClue = new ArrayList<>();

    @FXML
    void initialize() {
        descomponerPalabra(wordToFind.toLowerCase());//Se descompone la palabra en un Array de letras Char y se muestra cantidad de letras
    }

    @FXML
    void OnHandleButtonVerifyWord(ActionEvent event) {
        wordToCheck.requestFocus();
        boolean sameWord = comprobarPalabra(wordToCheck.getText(), wordToFind);
        if (sameWord){
            for(int i = 0; i < wordLetters.length; i++){
                Label wordLabel = (Label) HBoxLetters.getChildren().get(i);
                wordLabel.setText(String.valueOf(wordLetters[i]));
            }
            winningMessage();
        }
        else {
            imageNumber++;
            ahorcado.setImage(new Image(String.valueOf(getClass().getResource("/org/example/ahorcado/images/" +imageNumber+".png"))));
            warningWordLabel.setVisible(true);
            if(imageNumber == 6){
                losingMessage();
            }

        }
    }

    @FXML
    void OnHandleButtonClue(ActionEvent event) {
        if (clueCounter > 0) {
            Random random = new Random();
            int randomLetterIndex = random.nextInt(wordLettersClue.size());
            int randomIndexOldList = indexOfElement(wordLetters, wordLettersClue.get(randomLetterIndex));
            positionClue = checkLetter(wordLetters, wordLetters[randomIndexOldList]);
            for (int i = 0; i < positionClue.size(); i++) {
                questionCounter++;
                Label lettersLabel = (Label) HBoxLetters.getChildren().get(positionClue.get(i));
                lettersLabel.setText(String.valueOf(wordLetters[positionClue.get(i)]));
            }
            wordLettersClue = updateClueList(wordLettersClue, wordLetters[randomIndexOldList]);
            clueCounter--;
            if(wordLetters.length - questionCounter == 0){
                winningMessage();
            }
        }
        else {
            clueWarning();
        }
    }

    public void clueWarning() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setContentText("No puedes utilizar más pistas, "+getPlayer().getNickname());
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.showAndWait();
    }

    @FXML
    void setOnMouseClickedTextArea(MouseEvent event) {
        warningWordLabel.setVisible(false);
    }

    @FXML
    void OnHandleButtonVerifyLetter(ActionEvent event){
        warningLetterLabel.setVisible(false);
        letterToCheck.requestFocus();
        String letterToLowerCase = letterToCheck.getText().toLowerCase();
        System.out.println(letterToCheck.getText().toLowerCase());

        if (isCharacter(letterToLowerCase)){
            positionList = checkLetter(wordLetters, letterToLowerCase.charAt(0));
            if (positionList.isEmpty()){
                imageNumber++;
                ahorcado.setImage(new Image(String.valueOf(getClass().getResource("/org/example/ahorcado/images/" +imageNumber+".png"))));
                if(imageNumber == 6){
                    losingMessage();
                }

            }
            else {
                for (int i = 0;i < positionList.size(); i++ ) {
                    questionCounter++;
                    Label lettersLabel = (Label) HBoxLetters.getChildren().get(positionList.get(i));
                    lettersLabel.setText(String.valueOf(wordLetters[positionList.get(i)]));
                }
                wordLettersClue = updateClueList(wordLettersClue, letterToLowerCase.charAt(0));

                if(wordLetters.length - questionCounter == 0){
                    winningMessage();
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Warning");
            alert.setContentText("\n\nSomething went wrong:\nOnly join letters");
            alert.setGraphic(new ImageView(this.getClass().getResource("/org/example/ahorcado/images/eyeFavIcon.png").toString()));
            alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            alert.showAndWait();

        }
        letterToCheck.setText("");


    }

    @FXML
    void setOnKeyPressed(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER && letterToCheck.getLength() >= 1) {
            warningLetterLabel.setVisible(true);
        } else {
            warningLetterLabel.setVisible(false);
            OnHandleButtonVerifyLetter(new ActionEvent());
        }
    }

    private Player player;
    public void setPlayer(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return this.player;
    }


    public void winningMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ganaste!!!");
        alert.setHeaderText("Felicitaciones");
        alert.setContentText("Eres un gran jugador, "+getPlayer().getNickname());
        alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        alert.setGraphic(new ImageView(this.getClass().getResource("/org/example/ahorcado/images/win.png").toString()));
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.showAndWait();
        restarGame();
    }


    public void losingMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over!!!");
        alert.setHeaderText("Puedes hacerlo mejor");
        alert.setContentText("Perdiste, "+getPlayer().getNickname()+". La palabra era: "+wordToFind);
        alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        alert.setGraphic(new ImageView(this.getClass().getResource("/org/example/ahorcado/images/lose.jpg").toString()));
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.showAndWait();
        restarGame();
    }


    public boolean isCharacter(String letterToLowerCase){
        int asciiValue = 0;
        char character;
        character = letterToLowerCase.charAt(0);
        asciiValue = (int) character;

        if (asciiValue < 97 || asciiValue > 122){
            return false;

        }else{
            return true;

        }

    }

    @FXML
    void question(ActionEvent event) {
        String title = "Información de la Partida";
        String header = "La palabra contiene "+wordLetters.length+" letras";
        String content = "Te faltan "+(wordLetters.length - questionCounter)+" casillas\nTienes "+(6 - imageNumber)+" vidas\nTe quedan " +clueCounter+ " pistas";

        new AlertBox().showMessage(title,header,content);

    }

    public static void setWord(String text){
        wordToFind = text;
    }

    public void descomponerPalabra(String word) { //word = wordToFind (static), target word entered by user
        HBoxLetters.setSpacing(10); //space between boxes
        wordLetters = word.toCharArray();
        for (char c : wordLetters) {
            Label label = new Label(String.valueOf("__"));
            label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Sans Serif';");
            HBoxLetters.getChildren().add(label);
            wordLettersClue.add(c);
        }
    } //this mtd creates all labels(num of labels are defined by length of the target word) and add them to hbox


    public boolean comprobarPalabra(String wordToTry, String wordToFind){
        boolean sameWord = false;
        if(wordToTry.equalsIgnoreCase(wordToFind)){
            sameWord = true;
        }

        return sameWord;

    }


    public List<Integer> checkLetter(char[] charArray, char letter) {
        List<Integer> positionArray = new ArrayList<>();
        for(int i = 0; i < charArray.length; i++) {
            if(charArray[i] == letter ){
                positionArray.add(i);
            }
        }
        return positionArray;
    }

    public List<Character> updateClueList(List<Character> clueList, char letterToRemove) {
        Iterator<Character> iterator = clueList.iterator();
        while (iterator.hasNext()) {
            char letter = iterator.next();
            if (letter == letterToRemove) {
                iterator.remove(); // Safely remove the element using the iterator
            }
        }
        return clueList;
    }

    public int indexOfElement(char[] array, char element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i; // Return the index if the element is found
            }
        }
        return -1; // Return -1 if the element is not found
    }
    public void restarGame(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reinicio");
        alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        alert.setHeaderText("¿Quieres reiniciar el juego o salir?");

        ButtonType buttonTypeOne = new ButtonType("Reiniciar");
        ButtonType buttonTypeTwo = new ButtonType("Salir");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ahorcado/welcome-view.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Ahorcado");
                stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/org/example/ahorcado/images/favicon.png"))));
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (result.get() == buttonTypeTwo) {
            Platform.exit();
        }
    }
}

