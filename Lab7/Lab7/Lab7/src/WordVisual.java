// Programmer: Wu, En-Hsin 100062273 Lab7 
// Date: 2012/11/27 22:27:44

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class WordVisual extends JFrame {
    public WordVisual() {
        super("Word Visualization");

        LeftDraw drawData = new LeftDraw();
        add(drawData, BorderLayout.WEST);
        drawData.init();

        RightMenu dataInput = new RightMenu();
        dataInput.draw.addActionListener(drawData);
        add(dataInput, BorderLayout.EAST);

        drawData.sp = dataInput;

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

