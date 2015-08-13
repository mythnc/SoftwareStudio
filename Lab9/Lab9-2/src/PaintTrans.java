// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/26 20:23:51   

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Date;

// Receive image by InputStream and set it to BufferedImage in ReceivePanel.
public class PaintTrans implements Runnable {
    private PaintWindow paint;
    private ObjectInputStream input;

    PaintTrans(PaintWindow paint, ObjectInputStream input) {
        this.paint = paint;
        this.input = input;
    }

    @Override
    public void run() {
        while (true) {
            ArrayList<TransData> dataSet;
            try {
                dataSet = (ArrayList<TransData>)input.readObject();
                //System.out.println("receive data:");
                //System.out.println(dataSet);
                paint.receive.panel.draw(dataSet);
            }
            catch (ClassNotFoundException ex) {
                System.err.println("No such class");
            }
            catch (EOFException ex) {
                System.out.println("Remote is closed at: " + new Date());
                break;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}

