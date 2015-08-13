// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/28 20:43:05

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

// The prototype of north panel.
public class NorthPanel extends JPanel {
    protected BufferedImage bufImg;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        if (bufImg == null) {
            int w = getWidth();
            int h = getHeight();
            bufImg = (BufferedImage)createImage(w, h);
            // Use Grahpic2D component to change the content of bufImg
            Graphics2D gc = bufImg.createGraphics();
            gc.setColor(Color.white);
            gc.fillRect(0, 0, w, h);
        }

        // Draw previous image
        g2d.drawImage(bufImg, null, 0, 0);
    }

    public BufferedImage getBufImg() {
        return bufImg;
    }

    public void setBufImg(BufferedImage b) {
        bufImg = b;
    }

    // Test
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.add(new NorthPanel());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

