// Date: 2012/11/10 21:40:53   

// SelectThread: Sort array by selection sort.
public class SelectThread extends SortThread {
    SelectThread(SortPanel panel, int[] array, SortWindow window) {
        super(panel, array, window);
    }

    @Override
    public void run() {
        int i, j, min;

        for (i = 0; i < array.length - 1; i++) {
            panel.setRedIndex(i);
            doSleep();
            for (j = i + 1, min = i; j < array.length; j++) {
                panel.setRedIndex(j);
                doSleep();
                if (array[j] < array[min])
                    min = j;
            }
            panel.setSorted(0, i);
            swap(i, min);
            doSleep();
        }
        panel.setRedIndex(-1);
        panel.setSorted(0, i);
        doSleep();
        window.increCompleteT();
    }
}

