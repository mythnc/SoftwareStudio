// Date: 2012/11/09 00:07:57

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

// SortPanel: Visualize array in panel.
public class SortPanel extends JPanel {
    private int[] array;
    // baseX and baseY are the position of array[0].
    // Other positions of elements is built on it.
    private final int baseX = 10;
    private final int baseY = 200;
    private final int x = 10;
    private final int y = 20;
    private final int width = 8;
    private final int baseH = 20;
    private int move;
    private int sortedFrom;
    private int sortedTo;

    SortPanel(int[] array) {
        this.array = array;
        setDefault();
    }

    // setRedIndex: Tag move to index x
    public void setRedIndex(int x) {
        move = x;
    }

    // setSorted: Tag sortedFrom and sortedTo to from and to
    public void setSorted(int from, int to) {
        sortedFrom = from;
        sortedTo = to;
    }

    // setDefault: Set 3 tag variables to -1
    public void setDefault() {
        move = sortedFrom = sortedTo = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Correspond each element to a rectangule
        for (int i = 0; i < array.length; i++) {
            // Set unsorted list to gray color
            g.setColor(Color.gray);
            // Set moving index to red color
            if (i == move)
                g.setColor(Color.red);
            // Set sorted list to black color
            if (i >= sortedFrom && i <= sortedTo && i != move)
                g.setColor(Color.black);

            g.fillRect(baseX + i * x, baseY - array[i] * y,
                       width, baseH + array[i] * y);
        }
    }
}

