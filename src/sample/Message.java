package sample;

import java.io.Serializable;

public class Message implements Serializable {
    // Message includes both sender ID and text data being sent
    private String sender;
    private int x;
    private int y;

    // both fields are simple Strings, so default code is used to read/write these Strings

    Message(String who, int x1, int y1) {
        sender = who;
        x = x1;
        y = y1;
    }

    String sender() {
        return sender;
    }

    int getX() { return x; }

    int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ") from: " + sender;
    }
}
