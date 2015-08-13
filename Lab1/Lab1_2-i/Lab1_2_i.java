// Date: 2012/10/04 13:21:38
// Problem: Detecting palindrome
// Algorithm: using for loop compare string directly
// see readme.txt for more detailed explain.
import java.util.Scanner;

public class Lab1_2_i {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int i, j, len;
        boolean mark;

        while (true) {
            System.out.println("input your string: (type \"exit\" to leave)");
            input = scanner.nextLine();

            if (input.compareTo("exit") == 0)
                break;

            len = input.length();
            for (i = 0, mark = false, j = len - 1; i < j && len > 0; i++, j--)
                if (input.charAt(i) != input.charAt(j)) {
                    mark = true;
                    break;
                }

            // output
            if (!mark && len > 0)
                System.out.println("this is a palindrom.\n");
            else if (len == 0)
                System.out.println("you must input one character at least.\n");
            else
                System.out.println("this isn't a palindrom.\n");
        }
    }
}

