package org.example.crapsgame;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.crapsgame.view.WellcomeStage;

import java.io.IOException;

public class Main extends Application  {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        WellcomeStage.getInstance();
    }
}
