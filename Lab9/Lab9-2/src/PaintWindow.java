// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/26 18:50:17   

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.ObjectOutputStream;

// Merge all components in this frame.
public class PaintWindow extends JFrame {
    public Receiver receive;

    PaintWindow(String title, ObjectOutputStream output) {
        super(title);

        add(new Painter(output), BorderLayout.WEST);

        // Add a black line between Painter and Receiver
        JPanel line = new JPanel();
        line.setBackground(Color.gray);
        add(line, BorderLayout.CENTER);

        receive = new Receiver();
        add(receive, BorderLayout.EAST);

        setVisible(true);
        setSize(802, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Test
    public static void main(String[] args) {
        new PaintWindow("test", null);
    }
}

