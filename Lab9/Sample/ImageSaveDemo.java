import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageSaveDemo extends JFrame {
    BufferedImage bi;
    JButton btn;

    public ImageSaveDemo() {
        super("ImageSaveDemo");
        bi = createImage();
        this.add(btn = new JButton("Save"), BorderLayout.NORTH);
        this.add(new JLabel(new ImageIcon(bi)), BorderLayout.CENTER);
        this.setSize(200, 180);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveImage(bi, "savefile.png");
            }
        });
    }

    public void saveImage(BufferedImage img, String filename) {
        try {
            String format = (filename.endsWith(".png")) ? "png" : "jpg";
            ImageIO.write(img, format, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage createImage() {
        BufferedImage bi = new BufferedImage(100, 100,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setPaint(Color.YELLOW);
        g2.fillRect(0, 0, 100, 100);
        g2.setPaint(Color.red);
        g2.fillRect(10, 10, 60, 60);
        g2.setPaint(Color.CYAN);
        g2.fillOval(30, 30, 60, 60);
        return bi;
    }

    public static void main(String[] args) {        
        new ImageSaveDemo();
    }

}
