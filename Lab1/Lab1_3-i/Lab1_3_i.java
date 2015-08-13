// Date: 2012/10/04 19:23:45   
// Problem: print out binomial coefficient
// Algorithm: using two arrays and only handle
// [1] to [mid] (see Readme.txt for more detailed explanation)
import java.util.Scanner;
import java.util.Arrays;

public class Lab1_3_i {
    public static final int MAXSIZE = 500;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x, i, j, mid;
        int[] ary, ary_pre;
        ary = new int[MAXSIZE];
        ary_pre = new int[MAXSIZE];

        // input
        System.out.println("please input a number");
        x = scanner.nextInt();

        // init
        Arrays.fill(ary, 1);
        Arrays.fill(ary_pre, 1);

        // find & print
        for (i = 0; i <= x; i++) {
            /* mid: the mid of each array
             * use i represent x, mid = (x + 1) / 2
             * i + 1 == x, so mid = (i + 2) / 2 */
            mid = (i + 2) / 2;
            /* ary[0] is always 1, so j starts from 1 */
            for (j = 1; j < mid; j++)
                ary[j] = ary_pre[j - 1] + ary_pre[j];
            /* if the row is an even row, ary[j] is same as ary[j - 1] */
            if (i % 2 == 1)
                ary[j] = ary[j - 1];

            /* copy row to row - 1 */
            ary_pre = Arrays.copyOf(ary, MAXSIZE);

            /* print out each row 
             * mid mean x / 2 now,
             * so (i + 1) / 2 == mid */
            mid = (i + 1) / 2;
            for (j = 0; j < mid; j++)
                System.out.printf("%d ", ary[j]);
            /* odd row have to print mid */
            if (i % 2 == 0)
                System.out.printf("%d ", ary[j]);
            for (--j; j > -1; j--)
                System.out.printf("%d ", ary[j]);
            System.out.printf("\n");
        }
    }
}

