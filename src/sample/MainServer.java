package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainServer extends Application {
    private Controller controller;
    // Change multicastMode to enable multi-cast
    static boolean multicastMode = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load View from xml description
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        Thread.currentThread().setName("Controller MainServer GUI Thread");

        // Display the scene
        if (multicastMode) {
            primaryStage.setTitle("Controller SERVER Multi-cast");
        } else {
            primaryStage.setTitle("Controller SERVER");
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        controller = loader.getController();
        controller.player = 2;
        controller.setServerMode();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
