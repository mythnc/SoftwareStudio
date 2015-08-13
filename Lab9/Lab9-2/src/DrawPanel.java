// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/25 14:45:38   

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// DrawPanel: Let user use mouse(press, drag, and release) to draw in it.
public class DrawPanel extends NorthPanel implements MouseListener,
                                                 MouseMotionListener {
    private Color color;
    private int x1 = -1;
    private int y1 = -1;
    private int x2 = -1;
    private int y2 = -1;
    private final int CAP = 10;
    public ArrayList<TransData> dataSet;
    private TransData data;

    DrawPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        color = Color.black;
        dataSet = new ArrayList<TransData>(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw current image
        drawCurrent();
    }

    // clear: Let bufImg assign to null, so it will do clear by repaint()
    public void clear() {
        bufImg = null;
        x1 = y1 = x2 = y2 = -1;
        dataSet = new ArrayList<TransData>(0);
        repaint();
    }

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    // Draw current line.
    private void drawCurrent() {
        Graphics2D gc = bufImg.createGraphics();
        gc.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        gc.setColor(color);

        if (x1 >= 0)
            gc.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        data = new TransData(color);
        data.setPoint(x1, y1);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();

        drawCurrent();
        this.repaint();

        x1 = x2;
        y1 = y2;
        data.setPoint(x1, y1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        data.setPoint(x2, y2);
        dataSet.add(data);

        drawCurrent();
        this.repaint();

        x1 = x2 = y1 = y2 = -1;
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}

