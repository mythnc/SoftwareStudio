// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/26 16:17:16   

import javax.swing.JPanel;
import java.awt.BorderLayout;

// Show received image and save function in this panel.
public class Receiver extends JPanel {
    public ReceivePanel panel;
    private SouthPanel south;

    Receiver() {
        setLayout(new BorderLayout());

        panel = new ReceivePanel();
        add(panel, BorderLayout.CENTER);

        south = new SouthPanel(panel);
        south.add(south.save);

        add(south, BorderLayout.SOUTH);
    }
}

