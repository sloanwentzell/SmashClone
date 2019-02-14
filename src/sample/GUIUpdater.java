package sample;

import javax.xml.crypto.Data;

public class GUIUpdater implements Runnable{
    private DataStructure inQueue;
    private int image1X;
    private int image1Y;
    private int image2X;
    private int image2Y;

    Controller controller;

    GUIUpdater(DataStructure q, Controller c) {
        inQueue = q;
        controller = c;
    }

    public void run() {
        Thread.currentThread().setName("GUIUpdater Thread");
        String directionMoved;
        while (!Thread.interrupted()) {

        }

        if (directionMoved.equals("up")) {
            Controller.upAndDraw();
        }

    }
}
