// Date: 2012/11/09 07:51:23   
// Problem: Improve Lab4
// 1) Same game, but have their own initial random black cell and window
//    in Server and Client version.
// 2) Server and Client users have to cooperate to complete this game.
// 3) Use Thread.
// Usage: This is Server version.
import javax.swing.JFrame;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Date;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LaunchServer {
    public static void main(String[] args) {
        new LaunchServer();
    }

    // default row and column sizes
    private final int row = 4;
    private final int column = 5;
    private GameWindow game;
    private DataInputStream inputFromClient;
    private DataOutputStream outputToClient;

    LaunchServer() {
        try {
            System.out.println("Server start at: " + new Date() + '\n');

            // Waiting client 
            System.out.println("Waiting for link..." + '\n');
            ServerSocket server = new ServerSocket(13579);
            Socket socket = server.accept();
            System.out.println("Link is connected at: " + new Date() + '\n');

            inputFromClient = new DataInputStream(socket.getInputStream());
            outputToClient = new DataOutputStream(socket.getOutputStream());

            // Open game window
            game = new GameWindow("Server Game", row, column, inputFromClient, outputToClient);
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game.setSize(column * 100, row * 100);
            game.setLocation(0, 0);
            game.setVisible(true);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

