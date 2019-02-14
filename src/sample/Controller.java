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
import javafx.scene.Group;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Controller {
    private DataStructure inQueue;
    private DataStructure outQueue;
    private boolean serverMode;
    static boolean connected;

    public Canvas cc;
    public Canvas cd;

    private GraphicsContext gc;
    private GraphicsContext gd;
    int image1X = 130;
    int image1Y = 220;
    int image2X = 300;
    int image2Y = 220;
    Image image;
    Image image1;
    Image image2;

    Controller() {

    }

    public void initialize() {

        inQueue = new DataStructure();
        outQueue = new DataStructure();
        connected = false;

//      Create and start the GUI updater thread
        GUIUpdater updater = new GUIUpdater(inQueue, Controller);
        Thread updaterThread = new Thread(updater);
        updaterThread.start();

        cc.setFocusTraversable(true);

        gc = cc.getGraphicsContext2D();
        String stage = "stage.png";
        image = new Image(stage);
        String Stick = "sample/Stickman.png";
        image1 = new Image(Stick);

        cd = new Canvas();
        gd = cd.getGraphicsContext2D();
        String stage1 = "stage.png";
        image = new Image(stage1);
        String Stick1 = "sample/Stickman.png";
        image2 = new Image(Stick1);
        draw();

        cc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String direction = "none";
                KeyCode code = event.getCode();

                if (code == KeyCode.UP){
                    image1Y = image1Y - 5;
                    System.out.println("Moving Stick 1");
                    direction = "up";
                }

                if (code == KeyCode.DOWN){
                    image1Y = image1Y + 5;
                    System.out.println("Moving Stick 1");
                    direction = "down";
                }

                if (code == KeyCode.LEFT){
                    image1X = image1X - 5;
                    System.out.println("Moving Stick 1");
                    direction = "left";
                }

                if (code == KeyCode.RIGHT){
                    image1X = image1X + 5;
                    System.out.println("Moving Stick 1");
                    direction = "right";
                }
                draw();

                boolean putSuccess = outQueue.put(direction);
                while (!putSuccess) {
                    putSuccess = outQueue.put(direction);
                }
            }
        });

        cd.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.UP){
                    System.out.println("UP");
                    image2Y = image2Y - 5;
                    System.out.println("Moving Stick 2");
                }

                if (code == KeyCode.DOWN){
                    image2Y = image2Y + 5;
                    System.out.println("Moving Stick 2");
                }

                if (code == KeyCode.LEFT){
                    image2X = image2X - 5;
                    System.out.println("Moving Stick 2");
                }

                if (code == KeyCode.RIGHT){
                    image2X = image2X + 5;
                    System.out.println("Moving Stick 2");
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
        //gc.re
    }

    void upAndDraw() {

    }


//    void setServerMode() {
//        serverMode = true;
//        startButton.setText("Start");
//        try {
//            // display the computer's IP address
//            IPAddressText.setText(InetAddress.getLocalHost().getHostAddress());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            statusText.setText("Server start: getLocalHost failed. Exiting....");
//        }
//    }
//
//    void setClientMode() {
//        serverMode = false;
//        startButton.setText("Connect");
//        // display the IP address for the local computer
//        IPAddressText.setText("127.0.0.1");
//    }
//
//    public void startButtonPressed() {
//        // If we're already connected, start button should be disabled
//        if (connected) {
//            // don't do anything else; the threads will stop and everything will be cleaned up by them.
//            return;
//        }
//
//        // We can't start network connection if Port number is unknown
//        if (portText.getText().isEmpty()) {
//            // user did not enter a Port number, so we can't connect.
//            statusText.setText("Type a port number BEFORE connecting.");
//            return;
//        }
//
//        // We're gonna start network connection!
//        connected = true;
//        startButton.setDisable(true);
//
//        if (serverMode) {
//
//            // We're a server: create a thread for listening for connecting clients
//            ConnectToNewClients connectToNewClients = new ConnectToNewClients(Integer.parseInt(portText.getText()), inQueue, outQueue, statusText, yourNameText);
//            Thread connectThread = new Thread(connectToNewClients);
//            connectThread.start();
//
//        } else {
//
//            // We're a client: connect to a server
//            try {
//                Socket socketClientSide = new Socket(IPAddressText.getText(), Integer.parseInt(portText.getText()));
//                statusText.setText("Connected to server at IP address " + IPAddressText.getText() + " on port " + portText.getText());
//
//                // The socketClientSide provides 2 separate streams for 2-way communication
//                //   the InputStream is for communication FROM server TO client
//                //   the OutputStream is for communication TO server FROM client
//                // Create data reader and writer from those stream (NOTE: ObjectOutputStream MUST be created FIRST)
//
//                // Every client prepares for communication with its server by creating 2 new threads:
//                //   Thread 1: handles communication TO server FROM client
//                CommunicationOut communicationOut = new CommunicationOut(socketClientSide, new ObjectOutputStream(socketClientSide.getOutputStream()), outQueue, statusText);
//                Thread communicationOutThread = new Thread(communicationOut);
//                communicationOutThread.start();
//
//                //   Thread 2: handles communication FROM server TO client
//                CommunicationIn communicationIn = new CommunicationIn(socketClientSide, new ObjectInputStream(socketClientSide.getInputStream()), inQueue, null, statusText, yourNameText);
//                Thread communicationInThread = new Thread(communicationIn);
//                communicationInThread.start();
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                statusText.setText("Client start: networking failed. Exiting....");
//            }
//
//            // We connected!
//        }
//    }
}
