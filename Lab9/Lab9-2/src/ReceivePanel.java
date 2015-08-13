// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/30 12:21:31   

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// Receive drawing data from remote, and draw it.
public class ReceivePanel extends NorthPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void draw(ArrayList<TransData> dataSet) {
        int w = getWidth();
        int h = getHeight();
        BufferedImage b = (BufferedImage)createImage(w, h);
        Graphics2D g = b.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, w, h);

        g.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        for (TransData d: dataSet) {
            g.setColor(d.getColor());
            ArrayList<Point> pointSet = d.getPointSet();
            for (int i = 0; i < pointSet.size() - 1; i++) {
                Point p1 = pointSet.get(i);
                Point p2 = pointSet.get(i + 1);
                g.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
            }
        }
        bufImg = b;
        repaint();
    }
}

