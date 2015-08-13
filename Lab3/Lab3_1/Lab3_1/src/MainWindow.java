// Date: 2012/10/21 19:21:20   
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

// MainWindow: create 2 panels and add them to this window.
// detailed information of north panel is handled by MainTopPanel.java
// south panel is only a JButton, and is implemented here
public class MainWindow extends JFrame {
    private JPanel downPanel;
    private JButton setButton;
    private SubWindow frame;

    MainWindow(String title, final ArrayList<User> ulist) {
        super(title);

        setButton = new JButton("Set Friendship");
        // register button: open a new window when press setButton
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // reset user images to unchosen picture (XXXX.png)
                for (User u: ulist)
                    u.setImage(u.getName());

                // open 2nd window
                frame = new SubWindow("Set FriendShip", ulist);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setSize(860, 520);
                frame.setVisible(true);
            }
        });

        downPanel = new JPanel(new FlowLayout());
        downPanel.add(setButton);

        add(new MainTopPanel(ulist), BorderLayout.NORTH);
        add(downPanel, BorderLayout.SOUTH);
    }
}

