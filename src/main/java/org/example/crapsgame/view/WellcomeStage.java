package org.example.crapsgame.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class WellcomeStage extends Stage {
    public  WellcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/crapsgame/welcome-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setScene(scene);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/org/example/crapsgame/images/favicon.png"))));

        setResizable(false);
        show();

    }
    public static void getInstance() throws IOException {
        WellcomeStageHolder.INTANCE = new WellcomeStage();
    }
    private static class WellcomeStageHolder{

        private static WellcomeStage INTANCE;
    }
}
