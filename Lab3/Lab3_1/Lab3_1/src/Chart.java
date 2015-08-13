// Date: 2012/10/24 10:50:37   
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

// Chart: draw user pictures and lines in JPanel
public class Chart extends JPanel {
    private ArrayList<User> ulist;
    private User chosenUser;
    private final int shiftX = 75;
    private final int shiftY = 50;

    Chart(ArrayList<User> ulist) {
        this.ulist = ulist;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drw images and lines
        for (User u: ulist) {
            int uX = u.getPositionX();
            int uY = u.getPositionY();
            g.drawImage(u.getImage(), uX, uY, null);
            for (Friend f: u.getFriends()) {
                int midX = (f.getPositionX() + uX) / 2;
                int midY = (f.getPositionY() + uY) / 2;

                if (f.getType().equals("Close"))
                    g.setColor(Color.blue);
                else if (f.getType().equals("Regular"))
                    g.setColor(Color.red);
                else if (f.getType().equals("Stranger"))
                    continue;

                g.drawLine(uX + shiftX, uY + shiftY, midX + shiftX, midY + shiftY);
            }
        }
    }
}

