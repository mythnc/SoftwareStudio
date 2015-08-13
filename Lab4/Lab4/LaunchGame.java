// Date: 2012/10/31 09:31:35   
// Problem: Design a 4 * 5 size of Black and White Chess Game by GUI.
// 0) All cells' background are white at first.
// 1) Randomize one cell's background to black.
// 2) When clicking one cell, this cell and the north, west, east, and south cells of it
//    have to changecolor.
// 3) When all cells become black, print out "You Win" message.
import javax.swing.JFrame;

public class LaunchGame {
    public static void main(String[] args) {
        // default row and column sizes
        final int row = 4;
        final int column = 5;

        GameWindow game = new GameWindow("Game", row, column);

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(column * 100, row * 100);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}

