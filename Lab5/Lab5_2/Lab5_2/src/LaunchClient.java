// Date: 2012/11/09 07:51:23   
// Problem: Improve Lab4
// 1) Same game, but have their own initial random black cell and window
//    in Server and Client version.
// 2) Server and Client users have to cooperate to complete this game.
// 3) Use Thread.
// Usage: This is Client version.
import javax.swing.JFrame;

import java.net.Socket;
import java.io.IOException;
import java.util.Date;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LaunchClient {
    public static void main(String[] args) {
        new LaunchClient();
    }

    // default row and column sizes
    private final int row = 4;
    private final int column = 5;
    private GameWindow game;
    private DataInputStream inputFromServer;
    private DataOutputStream outputToServer;

    LaunchClient() {
        try {
            System.out.println("Client start at: " + new Date());

            // Connect to server
            Socket socket = new Socket("127.0.0.1", 13579);
            System.out.println("Link is connected at: " + new Date() + '\n');

            inputFromServer = new DataInputStream(socket.getInputStream());
            outputToServer = new DataOutputStream(socket.getOutputStream());

            // Open game window
            game = new GameWindow("Client Game", row, column, inputFromServer, outputToServer);
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game.setSize(column * 100, row * 100);
            game.setLocation(column * 100 + 50, 0);
            game.setVisible(true);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

