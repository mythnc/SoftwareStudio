// Date: 2012/10/13 08:25:55   
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

// Friend: save Friend data and some components
public class Friend extends User {
    private enum Ftype {REGULAR, CLOSE, STRANGER};
    private Ftype type;
    private JPanel panel;
    private JRadioButton closeRB, regularRB, strangerRB;
    private ButtonGroup group;
    private Chart chart;

    // setType: set friend type
    public void setType(String t) {
        if (t.equals("Close"))
            type = Ftype.CLOSE;
        else if (t.equals("Regular"))
            type = Ftype.REGULAR;
        else
            type = Ftype.STRANGER;
    }

    // setChart: make a reference to chart obj in SubPanel
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    // getType: return friend type
    public String getType() {
        if (type.equals(Ftype.CLOSE))
            return "Close";
        else if (type.equals(Ftype.REGULAR))
            return "Regular";
        else
            return "Stranger";
    }

    // setRadioButton: set the content of JRadioButton for 2nd window mid panel use
    // package it with a title border and a panel
    public void setRadioButton() {
        panel = new JPanel(new GridLayout(0, 1));

        // set title
        Border border = BorderFactory.createTitledBorder(getName());
        panel.setBorder(border);

        group = new ButtonGroup();

        if (type.equals(Ftype.STRANGER)) {
            strangerRB = new JRadioButton("Stranger");

            strangerRB.setSelected(true);

            group.add(strangerRB);
            // package it
            panel.add(strangerRB);
        }
        else {
            closeRB = new JRadioButton("Close");
            regularRB = new JRadioButton("Regular");

            group.add(closeRB);
            group.add(regularRB);

            if (type.equals(Ftype.CLOSE))
                closeRB.setSelected(true);
            else
                regularRB.setSelected(true);

            // register close and regular
            closeRB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setType("Close");
                    chart.repaint();
                }
            });

            regularRB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setType("Regular");
                    chart.repaint();
                }
            });

            // package it
            panel.add(closeRB);
            panel.add(regularRB);
        }
    }

    // getRadioButton: return Radiobutton with a title border and package it as a panel
    public JPanel getRadioButton() {
        return panel;
    }
}

