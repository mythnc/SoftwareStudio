// Programmer: Wu, En-Hsin 100062273 Lab8
// Date: 2012/12/16 11:26:20
// Problem: Make a Chatroom.
// This is Server version.

import java.net.Socket;
import java.io.IOException;
import java.util.Date;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// ChatClient: For client use.
public class ChatClient {
    public static void main(String[] args) {
        new ChatClient();
    }

    private ChatWindow chat;
    private String name = "Client";
    private String IP = "127.0.0.1";
    private int port = 13579;
    private DataInputStream input;
    private DataOutputStream output;

    ChatClient() {
        chat = new ChatWindow(name);

        try {
            chat.append(name + " start at: " + new Date());
            Socket socket = new Socket(IP, port);
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

