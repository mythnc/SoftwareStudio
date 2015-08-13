// Date: 2012/11/09 17:31:04

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.EOFException;

import java.util.Date;

// GameThread: Call Window.changeCell(x, y)
// to change the color of (x, y).
public class GameThread extends Thread {
    private DataInputStream input;
    private DataOutputStream output;
    private GameWindow game;
    private int x;
    private int y;
    private boolean remoteIsWin;

    GameThread(DataOutputStream output, DataInputStream input, GameWindow game) {
        this.output = output;
        this.input = input;
        this.game = game;
    }

    public void run() {
        while(true) {
            try {
                x = input.readInt();
                y = input.readInt();
                remoteIsWin = input.readBoolean();
                // If local and remote panel are all black,
                // send win tag.
                if (remoteIsWin && game.allIsBlack()) {
                    output.writeInt(-1);
                    output.writeInt(-1);
                    output.writeBoolean(true);
                    break;
                }
                if (x >= 0 && y >= 0)
                    game.changeCell(x, y);
            }
            // There is no data, if remote host is closed.
            catch (EOFException ex) {
                System.err.println("Remote host is closed at: " + new Date() + '\n');
                break;
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        if (remoteIsWin && game.allIsBlack())
            game.youWin();
    }
}

