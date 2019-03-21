package sample;

import java.io.Serializable;

public class Message implements Serializable {
    // Message includes both sender ID and text data being sent
    private int sender;
    private double x;
    private double y;
    private double xv;
    private double yv;
    private double rl;

    // both fields are simple Strings, so default code is used to read/write these Strings

    Message(int who, double x1, double y1, double xv1, double yv1, double rl1) {
        sender = who;
        x = x1;
        y = y1;
        xv = xv1;
        yv = yv1;
        rl = rl1;

    }

    int sender() {
        return sender;
    }

    double getX() { return x; }

    double getY() {
        return y;
    }

    double getXv() {return xv;}

    double getYv() {return yv;}

    double getRl() {return rl;}

    public String toString() {
        return "(" + x + ", " + y + ", " + yv + ", "+ xv +", "+ rl +") from: " + sender;
    }
}
