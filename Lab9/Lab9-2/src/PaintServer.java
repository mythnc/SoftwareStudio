// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/26 19:44:44   
// Problem: A basic Painter and network function.
// Usage: This is a Server edition.

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class PaintServer {
    public static void main(String[] args) {
        new PaintServer();
    }

    private final String name = "Server";
    private PaintWindow paint;
    private final int port = 13579;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private PaintTrans trans;

    PaintServer() {
        try {
            System.out.println("Server start at: " + new Date());
            System.out.println("Waiting for link ...");
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            System.out.println("Link is connected at: " + new Date());

            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            paint = new PaintWindow(name, output);
            trans = new PaintTrans(paint, input);
            new Thread(trans).start();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

