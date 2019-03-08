package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
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
            // Try to get a Message from the inputQueue
            Message message = (Message) inQueue.get();
            while (message == null) {
                Thread.currentThread().yield();
                message = (Message) inQueue.get();
            }
            Message finalMessage = message; // needed for Platform.runLater()

            controller.changeXY(finalMessage.sender(), finalMessage.getX(), finalMessage.getY());


        }
    }
}
