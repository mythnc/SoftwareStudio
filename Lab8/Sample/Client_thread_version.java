import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Wilber-Chao
 * Date: 2012/11/4
 * Time: 上午 2:00
 * To change this template use File | Settings | File Templates.
 */
public class Client_thread_version  extends JFrame {
    public static void main(String[] args) {
        new Client_thread_version();
    }

    private TransmissionThread transmissionThread;
    private JButton button;
    private JTextArea jta = new JTextArea();
    private PrintStream outputToClient;
    private DataInputStream inputFromClient;
    private Socket socket;

    public Client_thread_version() {
        button = new JButton("Client Send");
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);
        add(button, BorderLayout.NORTH);

        setTitle("Client");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        try {
            socket = new Socket("127.0.0.1",8000);
            jta.append("Client started at" + new Date() + '\n');

            inputFromClient = new DataInputStream(socket.getInputStream());
            outputToClient = new PrintStream(socket.getOutputStream());
            transmissionThread = new TransmissionThread(inputFromClient,jta);
            transmissionThread.start();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outputToClient.println("Client send data");
                    outputToClient.flush();
                }
            });
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

