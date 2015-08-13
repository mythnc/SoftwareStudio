// Programmer: Wu, En-Hsin 100062273 Lab9-1
// Date: 2012/12/25 14:45:38   

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

// DrawPanel: Let user use mouse(press, drag, and release) to draw in it.
public class DrawPanel extends NorthPanel implements MouseListener,
                                                 MouseMotionListener {
    private Color color;
    private int x1 = -1;
    private int y1 = -1;
    private int x2 = -1;
    private int y2 = -1;
    private final int CAP = 10;
    // Circular stack
    private BufferedImage[] undo = new BufferedImage[CAP + 1];
    private int tail;
    private int head;
    private BufferedImage[] redo = new BufferedImage[CAP];
    private int top;

    DrawPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        color = Color.black;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw current image
        //drawCurrent(); this is unnecessary.
    }

    // clear: Let bufImg assign to null, so it will do clear by repaint()
    public void clear() {
        bufImg = null;
        x1 = y1 = x2 = y2 = -1;
        tail = head = top = 0;
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
    public void undoPush(BufferedImage b) {
        undo[tail++] = deepCopy(b);
        tail %= CAP + 1;
        if (tail == head) {
            head++;
            head %= CAP + 1;
        }
    }

    public BufferedImage undoPop() {
        if (tail == head)
            return null;
        tail--;
        if (tail < 0)
            tail += CAP + 1;
        return undo[tail];
    }

    public void redoPush(BufferedImage b) {
        redo[top++] = deepCopy(b);
    }

    public BufferedImage redoPop() {
        if (top == 0)
            return null;
        return redo[--top];
    }

    // Deepcoppy BufferedImage.
    private BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        undoPush(bufImg);
        // If add new content in panel, there is no redo.
        top = 0;

        x1 = (int)e.getX();
        y1 = (int)e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = (int)e.getX();
        y2 = (int)e.getY();

        drawCurrent();
        this.repaint();

        x1 = x2;
        y1 = y2;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = (int)e.getX();
        y2 = (int)e.getY();

        drawCurrent();
        this.repaint();

        x1 = x2 = y1 = y2 = -1;
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}

