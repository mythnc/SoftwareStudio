// Date: 2012/10/23 00:11:49   
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

// SubWindow: let user choose user to set friendship and show it by Chart
public class SubWindow extends JFrame {
    private DefaultListModel model;
    private JList userList;
    private JPanel midPanel;
    private Chart chart;

    SubWindow(String title, final ArrayList<User> ulist) {
        super(title);

        model = new DefaultListModel();
        userList = new JList(model);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        chart = new Chart(ulist);
        chart.setPreferredSize(new Dimension(580, 500));

        // initialize ulist
        for (User i: ulist)
            model.addElement(i.getName());

        // register UserListListener: update other components when select a different user
        userList.addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                JList list = (JList)e.getSource();
                String userName = (String)list.getSelectedValue();

                // let midPanel has no component
                midPanel.removeAll();

                // change display image of user and
                // add radiobutton panel to midpanel
                for (User u: ulist) {
                    if (u.getName().equals(userName)) {
                        u.setImage("choose" + userName);
                        midPanel.add(u.getLabel());
                        for (Friend f: u.getFriends()) {
                            f.setChart(chart);
                            midPanel.add(f.getRadioButton());
                        }
                    }
                    else
                        u.setImage(u.getName());
                }
                // update midPanel for display
                midPanel.updateUI();
                chart.repaint();
            }
        }
    });

        // add components to Flow
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(userList);
        add(midPanel);
        add(chart);
    }
}

