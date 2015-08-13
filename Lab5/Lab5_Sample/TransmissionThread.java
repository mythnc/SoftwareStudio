import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Wilber-Chao
 * Date: 2012/11/4
 * Time: 上午 2:04
 * To change this template use File | Settings | File Templates.
 */
public class TransmissionThread extends Thread {
    public DataInputStream in;
    private JTextArea jta;
    public boolean stop = false;

    public TransmissionThread(DataInputStream in, JTextArea jta) {
        this.in = in;
        this.jta = jta;
    }

    public void run() {
        while (!stop) {
            try {
                String result = in.readLine();
                jta.append(result + "\n");
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
