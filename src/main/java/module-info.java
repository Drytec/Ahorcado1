module org.example.crapsgame {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.crapsgame to javafx.fxml;
    opens org.example.crapsgame.controller to javafx.fxml;

    exports org.example.crapsgame;
}