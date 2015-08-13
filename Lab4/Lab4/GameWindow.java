// Date: 2012/10/31 09:40:51   
import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// GameWindow: The medium to load GameCell and WinnerMessage.
public class GameWindow extends JFrame {
    private JPanel panel;
    private GameCell[][] cell;
    private boolean[][] isBlack;

    GameWindow(String title, int row, int column) {
        super(title);

        panel = new JPanel();
        panel.setLayout(new GridLayout(row, column));

        // Use 2-D array to construct cells, register them
        // and put them into GridLayout.
        // Use another 2-D arrray to record their states
        isBlack = new boolean[row][column];
        cell = new GameCell[row][column];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++) {
                isBlack[i][j] = false;
                cell[i][j] = new GameCell();
                cell[i][j].setBackground(Color.white);
                cell[i][j].addMouseListener(new cellMouseListener(i, j, row, column));
                panel.add(cell[i][j]);
            }

        // Randomize one cell to black
        int x = (int)(row * Math.random());
        int y = (int)(column * Math.random());
        changeCell(x, y);

        add(panel);
    }

    // cellMouseListener: When user click one of the cells,
    // change it's and its adjacecy's(if exists) background and rect color.
    // After change, check the number of black cells.
    // If all cells are black, print out "You Win" message
    private class cellMouseListener implements MouseListener {
        // Coordinates of (x, y) + (moveRow, moveColumn),
        // including itself, north, east, west and south.
        private final int moveRow[] = {0, 0, 1, -1, 0};
        private final int moveColumn[] = {0, 1, 0, 0, -1};
        private int x;
        private int y;
        private int r;
        private int c;
        private int row;
        private int column;

        cellMouseListener(int r, int c, int row, int column) {
            this.r = r;
            this.c = c;
            this.row = row;
            this.column = column;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < 5; i++) {
                x = r + moveRow[i];
                y = c + moveColumn[i];
                if (x >= 0 && x < row && y >= 0 && y < column)
                    changeCell(x, y);
            }
            if (allIsBlack(row, column))
                youWin();
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
    private void changeCell(int x, int y) {
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
    private boolean allIsBlack(int r, int c) {
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (!isBlack[i][j])
                    return false;

        return true;
    }

    // youWin: change component to youWin panel
    private void youWin() {
        //removeAll(); this method is useless
        getContentPane().removeAll();
        add(new WinnerMessage("You Win!!!"));
        //invalidate(); useless too
        validate();
        //repaint(); uesless three
    }
}

