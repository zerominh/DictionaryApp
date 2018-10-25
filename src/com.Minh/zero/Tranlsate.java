package com.zero;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Tranlsate  {

    public void runApplication(String[] args) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GoogleTranslate.fxml"));
            String css = this.getClass().getResource("translate.css").toExternalForm();
            Scene scene = new Scene(root, 600, 600);
            scene.getStylesheets().add(css);
            Stage stage = new Stage();
            stage.setTitle("Translate");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
