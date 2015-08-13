// Date: 2012/11/11 10:49:20   

// SortThread: The prototype(superclass) of other sorting threads.
// Whenever array is changed, repaint it in panel.
// Each sorting threads have their own run() method.
public class SortThread extends Thread {
    private final int sleepTime = 150;
    protected int[] array;
    protected SortPanel panel;
    protected SortWindow window;

    SortThread(SortPanel panel, int[] array, SortWindow window) {
        this.panel = panel;
        this.array = array;
        this.window = window;
    }

    // doSleep: Wrap sleep() and repaint() in this method.
    protected void doSleep() {
        try {
            panel.repaint();
            sleep(sleepTime);
        }
        catch (Exception ex) {}
    }

    // swap: Exchange value of index x and y.
    protected void swap(int x, int y) {
        int temp;

        temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}

