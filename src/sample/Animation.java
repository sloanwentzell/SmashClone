package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Animation<Private> implements Runnable{
    private Controller controller;
    private DataStructure inQueue;
    private DataStructure outQueue;
    private Image image2;
    private GraphicsContext gc1;

    Animation(Controller c) {
        controller = c;
        gc1 = controller.gc;

        String Stick = "sample/Stickman.png";
        image2 = new Image(Stick);
        controller.draw();
}
    public void run() {
        Thread.currentThread().setName("Animation Thread");


        while (!Thread.interrupted()) {
            controller.y1 =controller.y1+controller.yv1;
            controller.x1=controller.x1+controller.xv1;
            if (controller.jump1) {
                controller.yv1 = -5;
            } else {
                if (controller.y1 < 220) {
                    controller.yv1 = controller.yv1 +.5;
                } else {
                    controller.y1 = 220;
                    controller.yv1 = 0;

                }
            }
            if (controller.rl1 == 1) {
                controller.xv1 = controller.xv1 + 2;
                if (controller.xv1 > 5) {
                    controller.xv1 = 5;
                }
            }
            if (controller.rl1 == 2) {
                controller.xv1 = controller.xv1 - 2;
                if (controller.xv1 < -5) {
                    controller.xv1 = -5;
                }
            }
            if (controller.rl1 == 0) {
                if (controller.xv1 > 0) {
                    controller.xv1 = controller.xv1 -.5;
                }
                if (controller.xv1 < 0) {
                    controller.xv1 = controller.xv1 + .5;
                }
            }
                controller.draw();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        boolean putSuccess = outQueue.put(message);
        while (!putSuccess) {
            putSuccess = outQueue.put(message);
        }
    }
});
    }

    void draw() {
       gc1.drawImage(image2, controller.x1, controller.y1, 40, 40);
    }
}
