package org.example.ahorcado.model.alert;

import javafx.scene.control.Alert;

public class AlertBox implements IAlertBox {


public  void showMessage(String title, String header, String content){
    Alert alert =new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}}
