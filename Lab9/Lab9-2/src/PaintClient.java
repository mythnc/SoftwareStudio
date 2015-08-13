// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/26 19:44:44   
// Problem: A basic Painter and network function.
// Usage: This is a Client edition.

import java.net.Socket;
import java.util.Date;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class PaintClient {
    public static void main(String[] args) {
        new PaintClient();
    }

    private final String name = "Client";
    private PaintWindow paint;
    private final int port = 13579;
    private final String ip = "127.0.0.1";
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private PaintTrans trans;

    PaintClient() {
        try {
            System.out.println("Client start at: " + new Date());
            Socket socket = new Socket(ip, port);
            System.out.println("Link is connected at: " + new Date());

            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            paint = new PaintWindow(name, output);
            trans = new PaintTrans(paint, input);
            new Thread(trans).start();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

