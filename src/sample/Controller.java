package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;


public class Controller {
    public Canvas cc;

    private GraphicsContext gc;
    int image1X = 130;
    int image1Y = 220;
    int image2X = 300;
    int image2Y = 220;
    Image image;
    Image image1;
    Image image2;

    public void initialize() {

        cc.setFocusTraversable(true);

        gc = cc.getGraphicsContext2D();
        String stage = "stage.png";
        image = new Image(stage);

        String Stick = "sample/Stickman.png";
        image1 = new Image(Stick);

        String Stick1 = "sample/Stickman.png";
        image2 = new Image(Stick1);
        draw();

        cc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();

                if (code == KeyCode.UP){
                    System.out.println("UP");
                    image1Y = image1Y - 5;
                }

                if (code == KeyCode.DOWN){
                    image1Y = image1Y + 5;
                }

                if (code == KeyCode.LEFT){
                    image1X = image1X - 5;
                }

                if (code == KeyCode.RIGHT){
                    image1X = image1X + 5;
                }
                if (code == KeyCode.W){
                    System.out.println("UP");
                    image2Y = image2Y - 5;
                }

                if (code == KeyCode.S){
                    image2Y = image2Y + 5;
                }

                if (code == KeyCode.A){
                    image2X = image2X - 5;
                }

                if (code == KeyCode.D){
                    image2X = image2X + 5;
                }
                draw();
            }
        });
    }

    void draw() {
        gc.clearRect(0,0, cc.getWidth(), cc.getHeight());
        gc.drawImage(image, 120, 100, 300, 300);
        gc.drawImage(image1, image1X, image1Y, 40, 40);
        gc.drawImage(image2, image2X, image2Y, 40, 40);

    }
}
