package org.example.ahorcado.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.ahorcado.model.player.Player;
import org.example.ahorcado.view.GameStage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WelcomeController {
    @FXML
    private TextField nicknameTextField;
    @FXML
    private TextField wordTextField;

    @FXML
    public void onHandleButtonPlay(ActionEvent event) throws IOException {
        if(nicknameTextField.getText().equals("") || wordTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Warning");
            alert.setContentText("\n\nUpss, something went wrong:\nNickname and key word have been given.");
            alert.setGraphic(new ImageView(this.getClass().getResource("/org/example/ahorcado/images/eyeFavIcon.png").toString()));
            alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            alert.showAndWait();

        }else{
            if(isCharacter()){
                GameController.setWord(wordTextField.getText());
                String nickname = nicknameTextField.getText();
                Player player=new Player(1, nickname );
                GameStage.getInstance().getGameController().setPlayer(player);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please check this out:\nThe word must be only letters.");
                alert.setGraphic(new ImageView(this.getClass().getResource("/org/example/ahorcado/images/eyeFavIcon.png").toString()));
                alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                alert.showAndWait();
            }
        }
    }

    public boolean isCharacter(){
        // pattern with regular expresion desired
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");

        // Matcher to compare the String with the pattern
        Matcher matcher = pattern.matcher(wordTextField.getText());

        // verifying if the String is contained in the pattern
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void onKeyPressedUser(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            wordTextField.requestFocus();
        }
    }

    @FXML
    void onKeyPressedWord(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            try {
                onHandleButtonPlay(new ActionEvent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
