// Programmer: Wu, En-Hsin 100062273 Lab8
// Date: 2012/12/15 23:39:33
// Problem: Make a Chatroom.
// This is Server version.

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Date;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// ChatServer: For server use.
public class ChatServer {
    public static void main(String[] args) {
        new ChatServer();
    }

    private ChatWindow chat;
    private String name = "Server";
    private int port = 13579;
    private DataInputStream input;
    private DataOutputStream output;

    ChatServer() {
        chat = new ChatWindow(name);

        try {
            chat.append(name + " start at: " + new Date());
            chat.append("Waiting for link ...");
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            chat.append("Link is connected at: " + new Date() + "\n");

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            chat.setDeliverData(input, output);
        }
        catch(IOException ex) {
            System.err.println(ex);
        }
    }
}

