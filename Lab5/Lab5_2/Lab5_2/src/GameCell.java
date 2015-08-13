// Date: 2012/10/31 09:46:05   
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

// GameCell: Draw a rectangular by paintComponent.
// The default bgcolor is white and color is black.
public class GameCell extends JPanel {
    private Color color;

    GameCell() {
        color = Color.black;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
}

