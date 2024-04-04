module org.example.crapsgame {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.ahorcado to javafx.fxml;
    opens org.example.ahorcado.controller to javafx.fxml;

    exports org.example.ahorcado;
}