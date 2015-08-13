import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Wilber-Chao
 * Date: 2012/11/4
 * Time: 上午 1:59
 * To change this template use File | Settings | File Templates.
 */
public class Server_thread_version extends JFrame {
    public static void main(String[] args) {
        new Server_thread_version();
        System.out.print("ok");
    }

    private TransmissionThread transmissionThread;
    private JButton button;
    private JTextArea jta = new JTextArea();
    private PrintStream outputToClient;
    private DataInputStream inputFromClient;

    public Server_thread_version() {
        button = new JButton("Server Send");
        setLayout(new BorderLayout());
        add(button, BorderLayout.NORTH);
        add(new JScrollPane(jta), BorderLayout.CENTER);

        setTitle("Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("Server started at" + new Date() + '\n');

            Socket socket = serverSocket.accept();
            inputFromClient = new DataInputStream(socket.getInputStream());
            outputToClient = new PrintStream(socket.getOutputStream());
            transmissionThread = new TransmissionThread(inputFromClient, jta);
            transmissionThread.start();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outputToClient.println("Server send data");
                    outputToClient.flush();
                }
            });
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
