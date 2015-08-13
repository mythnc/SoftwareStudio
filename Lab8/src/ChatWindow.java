// Programmer: Wu, En-Hsin 100062273 Lab8
// Date: 2012/12/15 22:08:38   

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// ChatWindow: The frame(window) of chatroom
public class ChatWindow extends JFrame {
    private JTextField text;
    private JTextArea show;
    private JLabel label;
    private String name;
    private DataInputStream input;
    private DataOutputStream output;
    private Thread thread;

    ChatWindow(String name) {
        super("Chatroom - " + name);
        this.name = name;
        this.name += ": ";

        JPanel south = new JPanel(new BorderLayout());

        label = new JLabel(" Input:");
        south.add(label, BorderLayout.WEST);

        text = new JTextField();
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = ChatWindow.this.name + text.getText();
                show.append(s + "\n");
                text.setText("");
                if (output != null) {
                    try {
                        output.writeUTF(s);
                    }
                    catch (IOException ex) {
                            System.err.println(ex);
                    }
                }
            }
        });
        south.add(text, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        show = new JTextArea();
        show.setEditable(false);
        JScrollPane scroll = new JScrollPane(show);
        add(scroll, BorderLayout.CENTER);

        setSize(500, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // append: Append s to show.
    public void append(String s) {
        show.append(s + "\n");
    }

    // setDeliverData: Set DataStream of input and output,
    // initialize thread and let it start.
    public void setDeliverData(DataInputStream input, DataOutputStream output) {
        this.output = output;

        thread = new Thread(new ChatThread(this, input));
        thread.start();
    }

    // For Test use.
    public static void main(String[] args) {
        ChatWindow chat = new ChatWindow("test");
        chat.append("test");
    }
}

