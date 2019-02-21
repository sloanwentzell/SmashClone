package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class MainClient extends Application{
    private Controller myController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        myController = loader.getController();
        myController.setClientMode();

        Thread.currentThread().setName("Controller MainClient GUI Thread");
        primaryStage.setTitle("Super Intellij Bros");
        primaryStage.setScene(new Scene(root, 1440, 900));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
