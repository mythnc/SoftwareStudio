// Date: 2012/11/08 23:47:05   
// Problem: Visualize bubble, selection, and insertion sort.

import javax.swing.JFrame;

public class VisualSort {
    public static void main(String[] args ) {
        SortWindow sort = new SortWindow("Sort Visualization");

        sort.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sort.setSize(600, 320);
        sort.setLocationRelativeTo(null);
        sort.setVisible(true);
    }
}

