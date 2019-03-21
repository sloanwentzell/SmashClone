package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Controller {
    private DataStructure inQueue;
    private DataStructure outQueue;
    private boolean serverMode;
    static boolean connected;
    public TextField IPAddressText;
    public TextField portText;
    public Button startButton;
    public TextField statusText;
    public TextField yourNameText;
    public int player;
    public Canvas cc;
    public GraphicsContext gc;
    public String directionMoved;

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
    Image image;
    Image image1;
    Image image2;


    public void initialize() {

        inQueue = new DataStructure();
        outQueue = new DataStructure();
        connected = false;



//      Create and start the GUI updater thread
        GUIUpdater updater;
        updater = new GUIUpdater(inQueue, this);
        Thread updaterThread = new Thread(updater);
        updaterThread.start();

        cc.setFocusTraversable(true);

        gc = cc.getGraphicsContext2D();
        String stage = "sample/stage.png";
        image = new Image(stage);
        String Stick = "sample/Stickman.png";
        image1 = new Image(Stick);

        String stage1 = "sample/stage.png";
        image = new Image(stage1);
        String Stick1 = "sample/Stickman.png";
        image2 = new Image(Stick1);
        draw();
        cc.setFocusTraversable(true);

        cc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String direction = "none";
                KeyCode code = event.getCode();

                if (code == KeyCode.UP) {
                    if (player == 1) {
                        y1 = y1 - 5;
                    } else {
                        y2 = y2 - 5;
                    }
                    System.out.println("Moving Stick 1");
                    direction = "up";
                }

                if (code == KeyCode.DOWN) {
                    if (player == 1) {
                        y1 = y1 + 5;
                    } else {
                        y2 = y2 + 5;
                    }
                    System.out.println("Moving Stick 1");
                    direction = "down";
                }

                if (code == KeyCode.LEFT) {
                    if (player == 1) {
                        x1 = x1 - 5;
                    } else {
                        x2 = x2 - 5;
                }
                    System.out.println("Moving Stick 1");
                    direction = "left";
                }

                if (code == KeyCode.RIGHT) {
                    if (player == 1) {
                        x1 = x1 + 5;

                    } else {
                        x2 = x2 + 5;
                    }
                    System.out.println("Moving Stick 1");
                    direction = "right";
                }
                draw();
                cc.setFocusTraversable(true);

                Message message;
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
        });
    }

    void draw() {
        gc.clearRect(0, 0, cc.getWidth(), cc.getHeight());
        gc.drawImage(image, 120, 100, 300, 300);
        gc.drawImage(image1, x1, y1, 40, 40);
        gc.drawImage(image2, x2, y2, 40, 40);
    }

    void setServerMode() {
        serverMode = true;
        startButton.setText("Start");
        try {
            // display the computer's IP address
            IPAddressText.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception ex) {
            ex.printStackTrace();
            statusText.setText("Server start: getLocalHost failed. Exiting....");
        }
    }

    void setClientMode() {
        serverMode = false;
        startButton.setText("Connect");
        // display the IP address for the local computer
        IPAddressText.setText("127.0.0.1");
    }

    public void startButtonPressed() {
        // If we're already connected, start button should be disabled
        if (connected) {
            // don't do anything else; the threads will stop and everything will be cleaned up by them.
            return;
        }

        // We can't start network connection if Port number is unknown
        if (portText.getText().isEmpty()) {
            // user did not enter a Port number, so we can't connect.
            statusText.setText("Type a port number BEFORE connecting.");
            return;
        }

        // We're gonna start network connection!
        connected = true;
        startButton.setDisable(true);

        if (serverMode) {

            // We're a server: create a thread for listening for connecting clients
            ConnectToNewClients connectToNewClients = new ConnectToNewClients(Integer.parseInt(portText.getText()), inQueue, outQueue, statusText, yourNameText);
            Thread connectThread = new Thread(connectToNewClients);
            connectThread.start();

        } else {

            // We're a client: connect to a server
            try {
                Socket socketClientSide = new Socket(IPAddressText.getText(), Integer.parseInt(portText.getText()));
                statusText.setText("Connected to server at IP address " + IPAddressText.getText() + " on port " + portText.getText());

                // The socketClientSide provides 2 separate streams for 2-way communication
                //   the InputStream is for communication FROM server TO client
                //   the OutputStream is for communication TO server FROM client
                // Create data reader and writer from those stream (NOTE: ObjectOutputStream MUST be created FIRST)

                // Every client prepares for communication with its server by creating 2 new threads:
                //   Thread 1: handles communication TO server FROM client
                CommunicationOut communicationOut = new CommunicationOut(socketClientSide, new ObjectOutputStream(socketClientSide.getOutputStream()), outQueue, statusText);
                Thread communicationOutThread = new Thread(communicationOut);
                communicationOutThread.start();

                //   Thread 2: handles communication FROM server TO client
                CommunicationIn communicationIn = new CommunicationIn(socketClientSide, new ObjectInputStream(socketClientSide.getInputStream()), inQueue, null, statusText, yourNameText);
                Thread communicationInThread = new Thread(communicationIn);
                communicationInThread.start();

            } catch (Exception ex) {
                ex.printStackTrace();
                statusText.setText("Client start: networking failed. Exiting....");
            }
            // We connected!
        }
        cc.setFocusTraversable(true);
    }

    public void changeXY(int player, double x, double y, double xv, double yv, double rl) {

        if (player == 1) {
            x1 = x;
            y1 = y;
            xv1 = xv;
            yv1 = yv;
            rl1 = rl;
        }

        if (player == 2) {
            x2 = x;
            y2 = y;
            xv2 = xv;
            yv2 = yv;
            rl2 = rl;
        }
        draw();
    }
}

