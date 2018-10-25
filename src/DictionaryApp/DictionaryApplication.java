package DictionaryApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryApplication extends Application {
    public static DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../UIUX/Home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("UET Translate");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        dictionaryManagement.insertFromFile();
        launch(args);
    }
}