// Date: 2012/11/10 21:40:53   

// BubbleThread: Sort array by bubble sort.
public class BubbleThread extends SortThread {
    BubbleThread(SortPanel panel, int[] array, SortWindow window) {
        super(panel, array, window);
    }

    @Override
    public void run() {
        int i, j;
        boolean move;

        for (i = 0; i < array.length; i++) {
            move = false;
            for (j = array.length - 1; j > i; j--) {
                panel.setRedIndex(j);
                doSleep();
                if (array[j] < array[j - 1]) {
                    move = true;
                    swap(j, j - 1);
                    doSleep();
                }
            }
            if (!move)
                break;
            panel.setSorted(0, j);
            doSleep();
        }
        panel.setRedIndex(-1);
        panel.setSorted(0, array.length - 1);
        doSleep();
        window.increCompleteT();
    }
}

