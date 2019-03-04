package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application{
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Thread.currentThread().setName("Controller MainClient GUI Thread");
        primaryStage.setTitle("Super Intellij Bros");
        primaryStage.setScene(new Scene(root, 1440, 900));
        primaryStage.show();

        controller = loader.getController();
        controller.setClientMode();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
