// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/24 21:31:04   

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;

// Let user do things about painting in this panel.
public class Painter extends JPanel {
    private DrawPanel draw;
    private final int colorType = 3;
    private final String[] colorName = {"Red", "Green", "Blue"};
    private final Color[] colorMap = {Color.red, Color.green, Color.blue};
    private JButton[] color = new JButton[colorType];
    private JButton moreColor;
    private JButton clear;
    private JButton send;
    private ObjectOutputStream output;
    private SouthPanel south;

    Painter(ObjectOutputStream output) {
        this.output = output;
        setLayout(new BorderLayout());

        draw = new DrawPanel();
        add(draw, BorderLayout.CENTER);

        south = new SouthPanel(draw);

        // Setting the favorite three colors.
        for (int i = 0; i < colorType; i++) {
            final Color c = colorMap[i];
            color[i] = new JButton(colorName[i]);
            color[i].setForeground(c);
            color[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    draw.setColor(c);
                }
            });
            south.add(color[i]);
        }

        // Let user choose more colors from JColorChooser.
        moreColor = new JButton("More colors");
        moreColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(Painter.this,
                                                   "Colors", draw.getColor());
                if (c != null)
                    draw.setColor(c);
            }
        });
        south.add(moreColor);

        // Reset DrawPanel to default.
        clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.clear();
            }
        });
        south.add(clear);

        // Save local image.
        south.add(south.save);

        send = new JButton("Send");
        // Send local image to remote.
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //System.out.println("send data:");
                    //System.out.println(draw.dataSet);
                    Painter.this.output.reset();
                    Painter.this.output.writeObject(draw.dataSet);
                    Painter.this.output.reset();
                }
                catch (IOException ex) {
                    System.out.println("Remote is closed.");
                    //ex.printStackTrace();
                }
            }
        });
        south.add(send);

        add(south, BorderLayout.SOUTH);
    }

    // Test
    public static void main(String[] args) {
        JFrame frame = new JFrame("Painter");
        frame.add(new Painter(null));
        frame.setVisible(true);
        frame.setSize(400, 450);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

