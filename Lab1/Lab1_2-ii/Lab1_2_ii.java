// Date: 2012/10/04 15:57:51   
// Problem: improved Lab1_2_i
// Algorithm: see readme.txt for more detailed explanation.
import java.util.Scanner;

public class Lab1_2_ii {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int i, j, len, pos;
        char c = '0';
        boolean mark;

        while (true) {
            System.out.println("input your string: (type \"exit\" to leave)");
            input = scanner.nextLine();

            if (input.compareTo("exit") == 0)
                break;

            len = input.length();
            // check palindrome and improved
            for (i = 0, mark = false, pos = -1, j = len - 1; i < j && len > 0; i++, j--)
                if (input.charAt(i) != input.charAt(j)) {
                    if (pos == -1) {
                        if (input.charAt(i + 1) == input.charAt(j)) {
                            pos = j + 1;
                            c = input.charAt(i++);
                            continue;
                        }
                        if (input.charAt(i) == input.charAt(j - 1)) {
                            pos = i;
                            c = input.charAt(j--);
                            continue;
                        }
                    }
                    mark = true;
                    break;
                }
            // output
            if (!mark && len > 0 && pos == -1)
                System.out.println("this is a palindrom.\n");
            else if (mark)
                System.out.println("no result.\n");
            else if (len == 0)
                System.out.println("you must input one character at least.\n");
            else {
                System.out.printf("add \'%c\' to string we can get a new palindrome:\n", c);
                for (i = 0; i < len; i++) {
                    if (i == pos)
                        System.out.print(c);
                    System.out.print((char)input.charAt(i));
                }
                if (i == pos)
                    System.out.print(c);
                System.out.print("\n\n");
            }
        }
    }
}

