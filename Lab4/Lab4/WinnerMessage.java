// Date: 2012/10/31 13:40:33   
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// WinnerMessage: Draw a win message by paintComponent.
// When player wins show it.
public class WinnerMessage extends JPanel {
    private String text;
    private final int swiftX = 160;

    WinnerMessage (String text) {
        this.text = text;

        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Serif", Font.BOLD, 66));
        g.drawString(text, this.getWidth() / 2 - swiftX, this.getHeight() / 2);
    }
}

