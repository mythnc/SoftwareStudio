// Date: 2012/11/08 23:51:08   

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// SortWindow: Window of this program.
// Put all components in it.
public class SortWindow extends JFrame {
    private final int sortType = 3;
    private final int sortNum = 10;
    // 0 for bubble, 1 for insertion, 2 for selection
    private JLabel[] label = new JLabel[sortType];
    private SortPanel[] panel = new SortPanel[sortType];
    private int[][] array = new int[sortType][sortNum];
    private SortThread sortThread[] = new SortThread[sortType];
    private JButton startButton;
    private JButton resetButton;
    private JButton modeButton;
    private int completeThread = 3;
    private boolean diffRandom = true;

    SortWindow(String title) {
        super(title);

        // Initialize label and put them in northGrid.
        // Put them in north of Border at last.
        label[0] = new JLabel("Bubble Sort");
        label[1] = new JLabel("Insertion Sort");
        label[2] = new JLabel("Selection Sort");
        JPanel northGrid = new JPanel(new GridLayout(1, 0));
        for (JLabel i: label)
            northGrid.add(i);
        add(northGrid, BorderLayout.NORTH);

        // Initialize array and panel.
        // Put array as parameter in panel,
        // and put panels in centerGrid.
        // Put them in center of Border at last. 
        JPanel centerGrid = new JPanel(new GridLayout(1, 0));
        for (int i = 0; i < sortType; i++) {
            initArray(array[i]);
            randomArray(array[i]);
            panel[i] = new SortPanel(array[i]);
            centerGrid.add(panel[i]);
        }
        add(centerGrid, BorderLayout.CENTER);

        // Initialize button and put them in southFlow.
        // Put them in south of Border at last. 
        startButton = new JButton("Start");
        // When press startButton, create 3 threads to do sort at same time.
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (completeThread == sortType) {
                    completeThread = 0;
                    // Set color to default color(gray)
                    for (SortPanel i: panel) {
                        i.setDefault();
                        i.repaint();
                    }
                    // Initialize and run 3 threads
                    sortThread[0] = new BubbleThread(panel[0], array[0], SortWindow.this);
                    sortThread[1] = new InsertThread(panel[1], array[1], SortWindow.this);
                    sortThread[2] = new SelectThread(panel[2], array[2], SortWindow.this);
                    for (SortThread i: sortThread)
                        i.start();
                }
            }
        });
        resetButton = new JButton("Reset");
        // When press resetButton, randomize all arrays and repaint panels.
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (completeThread == sortType) {
                    if (diffRandom)
                        for (int i = 0; i < sortType; i++) {
                            randomArray(array[i]);
                            panel[i].setDefault();
                            panel[i].repaint();
                        }
                    else {
                        randomArray(array[0]);
                        panel[0].setDefault();
                        panel[0].repaint();
                        // copy the content of array[0] to other arrays
                        for (int i = 1; i < sortType; i++) {
                            for (int j = 0; j < sortNum; j++)
                                array[i][j] = array[0][j];
                            panel[i].setDefault();
                            panel[i].repaint();
                        }
                    }
                }
            }
        });
        modeButton = new JButton("Diff Random");
        // Use diffRandom variable to tag current mode
        modeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (completeThread == sortType) {
                    JButton b = (JButton)e.getSource();
                    if (diffRandom) {
                        b.setText("Same Random");
                        diffRandom = false;
                    }
                    else {
                        b.setText("Diff Random");
                        diffRandom = true;
                    }
                }
            }
        });
        JPanel southFlow = new JPanel(new FlowLayout());
        southFlow.add(startButton);
        southFlow.add(resetButton);
        southFlow.add(modeButton);
        add(southFlow, BorderLayout.SOUTH);
    }

    // initArray: Initialize array to an ordered int array.
    private void initArray(int[] array) {
        for (int i = 0; i < sortNum; i++)
            array[i] = i;
    }

    // randomArray: Generate a random ordered int array.
    private void randomArray(int[] array) {
        for (int i = 0; i < sortNum; i++) {
            int j = (int)(Math.random() * sortNum);
            swap(array, i, j);
        }
    }
    
    // swap: Exchange the value of index i and j of array
    private void swap(int[] a, int x, int y) {
        int temp;

        temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    // increCompleteT: When a sorting thread finish, 
    // increase completeThread + 1;
    public void increCompleteT() {
        completeThread++;
    }
}

