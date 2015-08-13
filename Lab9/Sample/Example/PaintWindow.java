// PaintDemo.java - Simple painting program.
// Illustrates use of mouse and BufferedImage.
// Fred Swartz 2002-12-02
// Possible Enhancements:
//   * Clear drawing area
//   * Other shapes (line, outline shapes, text, ...)
//   * Color selection.
//   * An eraser.
//   * Create a real toobar.
//   * Save/Load 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.BufferedImage;

//////////////////////////////////////////////////////////// PaintWindow
class PaintWindow extends JFrame {
    PaintPanel canvas = new PaintPanel();
    //====================================================== constructor
    public PaintWindow() {
        //--- create the buttons
        JButton circleButton = new JButton("Circle");
        circleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setShape(PaintPanel.CIRCLE);
            }});
        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setShape(PaintPanel.RECTANGLE);
            }});

        //--- layout the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(circleButton);
        buttonPanel.add(rectangleButton);

        //--- layout the window
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(buttonPanel, BorderLayout.WEST);
        content.add(canvas     , BorderLayout.CENTER);
        this.setTitle("Paint Demo");
        this.pack();
    }//end constructor
}//endclass PaintWindow

