package sample;

import javafx.scene.image.Image;

import javax.xml.crypto.Data;

public class GUIUpdater implements Runnable{
    private DataStructure inQueue;

    Controller controller;

    GUIUpdater(DataStructure q, Controller c) {
        inQueue = q;
        controller = c;
    }

    public void run() {
        Thread.currentThread().setName("GUIUpdater Thread");
        String directionMoved = null;
        while (!Thread.interrupted()) {

        }

        if (directionMoved.equals("up")) {
            Controller.upAndDraw();
        }

    }
}
