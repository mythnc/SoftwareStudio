// Date: 2012/10/21 19:47:12   
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

// MainTopPanel: handle all mainWindow compoments exclusive button
public class MainTopPanel extends JPanel {
    private JComboBox userList;
    private JLabel friendLabel;            // show "xxx's friends"
    private JLabel friendProfileLabel;     // show "xxx's profile"
    private JLabel userLabel;              // show user picture + text
    private JList friendList;
    private JLabel friendProfileContent;   // show friend profile content
    private DefaultListModel listModel;
    private String userName;
    private String friendName;

    MainTopPanel(final ArrayList<User> ulist) {
        // initilize instance variables and set their attribution
        userList = new JComboBox();
        friendLabel = new JLabel();
        friendLabel.setVerticalTextPosition(JLabel.CENTER);
        friendLabel.setHorizontalTextPosition(JLabel.CENTER);
        friendProfileLabel = new JLabel();
        userLabel = new JLabel();
        userLabel.setVerticalTextPosition(JLabel.BOTTOM);
        userLabel.setHorizontalTextPosition(JLabel.CENTER);
        userLabel.setHorizontalAlignment(JLabel.CENTER);
        listModel = new DefaultListModel();
        friendList = new JList(listModel);
        friendList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friendProfileContent = new JLabel();
        friendProfileContent.setVerticalTextPosition(JLabel.BOTTOM);
        friendProfileContent.setHorizontalTextPosition(JLabel.CENTER);
        friendProfileContent.setHorizontalAlignment(JLabel.CENTER);

        // make user list(combobox)
        for (User i: ulist)
            userList.addItem(i.getName());
        userList.setSelectedIndex(-1);

        // register UserCBListener: when select user from ComboBox,
        // initialize and update other components
        userList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                userName = (String)cb.getSelectedItem();

                // initialize and update other components
                updateUserLabel();
                // update userfriend label
                friendLabel.setText(userName + "'s friends");
                updateFriendList(ulist);
                friendProfileLabel.setText("");
                friendProfileContent.setText("");
                friendProfileContent.setIcon(null);
            }
        });

        // register FriendListListener: when select an item from friendList,
        // update friend label and its content
        friendList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    JList list = (JList)e.getSource();
                    friendName = (String)list.getSelectedValue();

                    // update user's friend profile label and profile content
                    if (friendName != null) {
                        friendProfileLabel.setText(friendName + "'s profile");
                        updateFProfileContent(ulist);
                    }
                }
            }
        });

        JPanel northGrid = new JPanel(new GridLayout(1, 0));
        JPanel southGrid = new JPanel(new GridLayout(1, 0));

        JPanel friendLabelFlow = new JPanel(new FlowLayout());
        JPanel friendPLFlow = new JPanel(new FlowLayout());
        // add friendLable and friendPRofileLabel to Flow each
        // then add north 3 components to northGrid
        friendLabelFlow.add(friendLabel);
        friendPLFlow.add(friendProfileLabel);
        northGrid.add(userList);
        northGrid.add(friendLabelFlow);
        northGrid.add(friendPLFlow);

        JPanel userFlow = new JPanel(new FlowLayout());
        JPanel listFlow = new JPanel(new FlowLayout());
        // add userLabel and friendList to Flow each
        // then add south 3 components to soutGrid
        userFlow.add(userLabel);
        listFlow.add(friendList);
        southGrid.add(userFlow);
        southGrid.add(listFlow);
        southGrid.add(friendProfileContent);

        // add Grid to Border
        setLayout(new BorderLayout());
        add(northGrid, BorderLayout.NORTH);
        add(southGrid, BorderLayout.CENTER);
    }

    // updateUserLabel: update the content of userLabel
    public void updateUserLabel() {
        BufferedImage userImage = null;

        // load image
        try {
            userImage = ImageIO.read(getClass().getClassLoader().getResource("images/" + userName + ".png"));
        }
        catch (IOException ex) {
            System.out.println("no such file.");
        }

        // set icon and text
        userLabel.setIcon(new ImageIcon(userImage));
        userLabel.setText("<html><br>" + userName);
    }

    // updateFriendList: update user's friends list
    public void updateFriendList(ArrayList<User> ulist) {
        // let listModel has no element
        listModel.clear();

        // add friends of user to listModel
        for (User i: ulist)
            if (i.getName().equals(userName))
                for (Friend j: i.getFriends())
                    if (!j.getType().equals("Stranger"))
                        listModel.addElement(j.getName());
    }

    // updateFProfileContent: update the content of friend profile
    public void updateFProfileContent(ArrayList<User> ulist) {
        BufferedImage friendImage = null;

        // update text
        for (User i: ulist)
            if (i.getName().equals(friendName))  // i: friend
                for (Friend j: i.getFriends())
                    if (j.getName().equals(userName)) // j: corresponsive user
                        friendProfileContent.setText(i.getProfile(j.getType()));

        // load image
        try {
            friendImage = ImageIO.read(getClass().getClassLoader().getResource("images/" + friendName + ".png"));
        }
        catch (IOException ex) {
            System.out.println(friendName + "no such file.");
        }

        // update icon
        friendProfileContent.setIcon(new ImageIcon(friendImage));
    }
}

