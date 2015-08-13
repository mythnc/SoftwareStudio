// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/28 21:07:54

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// The prototype of south panel.
public class SouthPanel extends JPanel {
    protected JButton save;
    public NorthPanel panel;

    SouthPanel(NorthPanel panel) {
        this.panel = panel;
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(400, 60));

        // Save local image.
        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File f = new File(new File(".").getCanonicalPath());
                    JFileChooser fc = new JFileChooser();

                    // Set path to current direcotry.
                    fc.setCurrentDirectory(f);
                    if (fc.showSaveDialog(SouthPanel.this) ==
                        JFileChooser.APPROVE_OPTION) {
                        f = fc.getSelectedFile();
                        ImageIO.write(SouthPanel.this.panel.getBufImg(), "png", new File(f.getName() + ".png"));
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

