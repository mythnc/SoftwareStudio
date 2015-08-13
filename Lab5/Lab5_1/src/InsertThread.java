// Date: 2012/11/10 21:40:53   

// InsertThread: Sort array by insertion sort.
public class InsertThread extends SortThread {
    InsertThread(SortPanel panel, int[] array, SortWindow window) {
        super(panel, array, window);
    }

    @Override
    public void run() {
        int i, j;

        for (i = 1; i < array.length; i++) {
            panel.setSorted(0, i);
            doSleep();
            for (j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(j, j - 1);
                panel.setRedIndex(j - 1);
                doSleep();
            }
        }
        panel.setRedIndex(-1);
        panel.setSorted(0, i - 1);
        doSleep();
        window.increCompleteT();
    }
}

