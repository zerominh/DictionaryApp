package com.zero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        String css = this.getClass().getResource("style.css").toExternalForm();

        primaryStage.setTitle("Dictionary");
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void runApplication(String[] args) {
        launch(args);
    }
}
