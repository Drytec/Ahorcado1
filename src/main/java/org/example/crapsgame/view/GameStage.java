package org.example.crapsgame.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.crapsgame.controller.GameController;

import java.io.IOException;

public class GameStage extends Stage {
    private GameController gameController;
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/crapsgame/game-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        setTitle("Ahorcado");
        Scene scene = new Scene(root);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/org/example/crapsgame/Images/favicon.png"))));
        setResizable(false);
        setScene(scene);
        show();
    }

    public GameController getGameController(){
        return gameController;
    }

    public static GameStage getInstance() throws IOException{
        return GameStage.GameStageHolder.INSTANCE = new GameStage();
    }

    private static class GameStageHolder {
        public static GameStage INSTANCE;
    }
}