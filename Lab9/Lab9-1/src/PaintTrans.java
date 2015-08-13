// Programmer: Wu, En-Hsin 100062273 Lab9-1
// Date: 2012/12/26 20:23:51   

import java.io.InputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

// Receive image by InputStream and set it to BufferedImage in ReceivePanel.
public class PaintTrans implements Runnable {
    private PaintWindow paint;
    private InputStream input;

    PaintTrans(PaintWindow paint, InputStream input) {
        this.paint = paint;
        this.input = input;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Have to make a BufferedImage first.
                // You can't just send it.
                // Because the return value of ImageIO.read(input) maybe equal to "null"
                BufferedImage b = ImageIO.read(input);
                if (b != null) {
                    paint.receive.panel.setBufImg(b);
                    //paint.receive.panel.setBufImg(ImageIO.read(input)); Wrong Way
                    paint.receive.panel.repaint();
                }
                //else
                //   System.out.println("b == null!");
            }
            catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}

