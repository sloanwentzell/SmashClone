package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
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


public class Controller  {
    public Canvas cc;

    private GraphicsContext gc;

    public void initialize() {
        gc = cc.getGraphicsContext2D();
        String stage = "stage.png";
        Image image = new Image(stage);
        gc.drawImage(image, 120, 100, 300,300);

        String Stick = "sample/Stickman.png";
        Image image1 = new Image(Stick);
        gc.drawImage(image1, 130, 220,30, 30);

    }
}
