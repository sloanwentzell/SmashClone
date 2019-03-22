package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Animation implements Runnable{
    private Controller controller;
    private DataStructure inQueue;
    private DataStructure outQueue;
    private Image image2;
    private GraphicsContext gc1;

    public int player;
    double x1 = 130;
    double xv1 = 220;
    double yv1 = 0;
    double y1 = 220;

    double x2 = 300;
    double y2 = 220;
    double xv2 = 220;
    double yv2 = 0;

    boolean jump1 = false;
    boolean jump2 =  false;
    double rl1 = 0;
    double rl2 = 0;

    Animation(Controller c) {
        controller = c;
        gc1 = controller.gc;

        String Stick = "sample/Stickman.png";
        image2 = new Image(Stick);
        controller.draw();
}
    public void run() {
        Thread.currentThread().setName("Animation Thread");
        Message message = new Message(player, x1, y1, xv1, yv1, rl1, jump1, x2, y2, xv2, yv2, rl2, jump2);

            while (!Thread.interrupted()) {
                if (player == 1) {
                    controller.y1 = controller.y1 + controller.yv1;
                    controller.x1 = controller.x1 + controller.xv1;
                } else {
                    if (player == 2) {
                        controller.x2 = controller.x2 + controller.xv2;
                        controller.y2 = controller.y2 + controller.yv2;
                    }
                }


                if (controller.jump2) {
                    controller.yv2 = -5;
                } else {
                    if (controller.y2 < 220) {
                        controller.yv2 = controller.yv2 + .5;
                    } else {
                        controller.y2 = 220;
                        controller.yv2 = 0;

                    }
                }
                if (controller.rl2 == 1) {
                    controller.xv2 = controller.xv2 + 2;
                    if (controller.xv2 > 5) {
                        controller.xv2 = 5;
                    }
                }
                if (controller.rl2 == 2) {
                    controller.xv2 = controller.xv2 - 2;
                    if (controller.xv2 < -5) {
                        controller.xv2 = -5;
                    }
                }
                if (controller.rl2 == 0) {
                    if (controller.xv2 > 0) {
                        controller.xv2 = controller.xv2 - .5;
                    }
                    if (controller.xv2 < 0) {
                        controller.xv2 = controller.xv2 + .5;
                    }
                }

                if (controller.jump2) {
                    controller.yv2 = -5;
                } else {
                    if (controller.y2 < 220) {
                        controller.yv2 = controller.yv2 + .5;
                    } else {
                        controller.y2 = 220;
                        controller.yv2 = 0;

                    }
                }
                if (controller.rl2 == 1) {
                    controller.xv2 = controller.xv1 + 2;
                    if (controller.xv2 > 5) {
                        controller.xv2 = 5;
                    }
                }
                if (controller.rl2 == 2) {
                    controller.xv2 = controller.xv2 - 2;
                    if (controller.xv2 < -5) {
                        controller.xv2 = -5;
                    }
                }
                if (controller.rl2 == 0) {
                    if (controller.xv2 > 0) {
                        controller.xv2 = controller.xv2 - .5;
                    }
                    if (controller.xv2 < 0) {
                        controller.xv2 = controller.xv2 + .5;
                    }
                }

                controller.draw();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            if (player == 1) {
                message = new Message(player, x1, y1, xv1, yv1, rl1);
            } else {
                message = new Message(player, x2, y2, xv2, yv2, rl2);
            }

            boolean putSuccess = outQueue.put(message);
            while (!putSuccess) {
                putSuccess = outQueue.put(message);
            }
    }

        void draw () {
            gc1.drawImage(image2, controller.x1, controller.y1, 40, 40);
        }
    }
