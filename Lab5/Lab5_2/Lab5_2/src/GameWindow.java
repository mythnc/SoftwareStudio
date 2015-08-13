// Date: 2012/11/09 23:02:34   
import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

// GameWindow: The medium to load GameCell, WinnerMessage and GameThread.
public class GameWindow extends JFrame {
    private JPanel panel;
    private GameCell[][] cell;
    private boolean[][] isBlack;
    private GameThread thread;
    private int maxRow;
    private int maxCol;
    private boolean localIsWin;

    GameWindow(String title, int row, int column,
               DataInputStream input, DataOutputStream output) {
        super(title);
        this.maxRow = row;
        this.maxCol = column;

        panel = new JPanel();
        panel.setLayout(new GridLayout(row, column));

        // Use 2-D array to construct cells, register them
        // and put them into GridLayout.
        // Use another 2-D arrray to record their states
        isBlack = new boolean[maxRow][maxCol];
        cell = new GameCell[maxRow][maxCol];
        for (int i = 0; i < maxRow; i++)
            for (int j = 0; j < maxCol; j++) {
                isBlack[i][j] = false;
                cell[i][j] = new GameCell();
                cell[i][j].setBackground(Color.white);
                cell[i][j].addMouseListener(new cellMouseListener(i, j, output));
                panel.add(cell[i][j]);
            }

        // Randomize one cell to black
        int x = (int)(row * Math.random());
        int y = (int)(column * Math.random());
        changeCell(x, y);

        add(panel);

        // Thread
        thread = new GameThread(output, input, this);
        thread.start();
    }

    // cellMouseListener: When user click one of the cells,
    // change it's and its adjacecy's(if exists) background and rect color,
    // and send relative data to remote host.
    // If remote and local panel are all black,
    // print out "You Win" message.
    private class cellMouseListener implements MouseListener {
        // Coordinates of (x, y) + (moveRow, moveColumn),
        // including itself, north, east, west and south.
        private final int moveRow[] = {0, 0, 1, -1, 0};
        private final int moveColumn[] = {0, 1, 0, 0, -1};
        private int x;
        private int y;
        private int r;
        private int c;
        private DataOutputStream output;

        cellMouseListener(int r, int c, DataOutputStream output) {
            this.r = r;
            this.c = c;
            this.output = output;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < 5; i++) {
                x = r + moveRow[i];
                y = c + moveColumn[i];
                if (x >= 0 && x < maxRow && y >= 0 && y < maxCol) {
                    changeCell(x, y);
                    // Send (x, y) to remote computer
                    try {
                        output.writeInt(x);
                        output.writeInt(y);
                        // Always send "false" here.
                        output.writeBoolean(false);
                    }
                    catch(IOException ex) {
                        System.err.println(ex);
                    }
                }
            }
            if (allIsBlack())
                localIsWin = true;
            else
                localIsWin = false;
            // For Win tag, so there is no (x, y),
            // set them to (-1, -1)
            try {
                output.writeInt(-1);
                output.writeInt(-1);
                output.writeBoolean(localIsWin);
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            ;
        }
    }

    // changeCell: If the bgcolor of cell is black 
    // and color of cell is white, change them to white
    // and black, and vice versa.
    public void changeCell(int x, int y) {
        if (cell[x][y].getBackground() == Color.black) {
            cell[x][y].setBackground(Color.white);
            cell[x][y].setColor(Color.black);
            isBlack[x][y] = false;
        }
        else {
            cell[x][y].setBackground(Color.black);
            cell[x][y].setColor(Color.white);
            isBlack[x][y] = true;
        }
        cell[x][y].repaint();
    }

    // allIsBlack: If all cells are black,
    // return true, else return false.
    public boolean allIsBlack() {
        for (int i = 0; i < maxRow; i++)
            for (int j = 0; j < maxCol; j++)
                if (!isBlack[i][j])
                    return false;

        return true;
    }

    // youWin: change component to youWin panel
    public void youWin() {
        //removeAll(); this method is useless
        getContentPane().removeAll();
        add(new WinnerMessage("You Win!!!"));
        //invalidate(); useless too
        validate();
        //repaint(); uesless three
    }
}

