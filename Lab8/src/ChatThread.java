// Programmer: Wu, En-Hsin 100062273 Lab8
// Date: 2012/12/16 11:40:56   

import java.io.DataInputStream;
import java.io.IOException;
import java.io.EOFException;

import java.util.Date;

// ChatThread: Deliver messages to others.
public class ChatThread implements Runnable {
    private ChatWindow chat;
    private DataInputStream input;

    ChatThread(ChatWindow chat, DataInputStream input) {
        this.chat = chat;
        this.input = input;
    }

    @Override
    public void run() {
        while (true) {
            try {
                chat.append(input.readUTF());
            }
            catch (EOFException ex) {
                chat.append("Remote host is closed at: " + new Date());
                break;
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

