package org.example.crapsgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.crapsgame.model.player.Player;
import org.example.crapsgame.view.GameStage;

import java.io.IOException;

public class WellcomeController {
    @FXML
    private TextField nicknameTextField;
    @FXML
    private TextField wordTextField;

    @FXML
    public void onHandleButtonPlay(ActionEvent event) throws IOException {
        String nickname = nicknameTextField.getText();
        Player player=new Player(1, nickname );
        GameStage.getInstance().getGameController().setPlayer(player);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public String getWordText() {
        return wordTextField.getText();
    }

}
