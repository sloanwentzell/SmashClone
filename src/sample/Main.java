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


import java.io.FileInputStream;

public class Main extends Application {
    Image image;
    Image title;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Super Intellij Bros");
        primaryStage.setScene(new Scene(root, 1440, 900));
        primaryStage.show();

        FileInputStream input = new FileInputStream("/Users/paxton_wentzell/Desktop/AdvancedCS/Smash Clone/src/Unknown-3.png");
        image = new Image(input);

        FileInputStream input1 = new FileInputStream("/Users/paxton_wentzell/Desktop/AdvancedCS/Smash Clone/out/production/Smash Clone/sample/Unknown.png");
        title = new Image(input1);
        //imageView.setImage(image);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
