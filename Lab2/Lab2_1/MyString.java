// Date: 2012/10/12 20:36:59   
import java.util.Scanner;

// MyString: the main class to find palindrome and output results
public class MyString {
    private String data;
    private String sub;
    private int len;
    private int sublen;
    private boolean subpalin = false;
    // set: set data and len by receiving user input string
    public void set() {
        Scanner scan = new Scanner(System.in);

        System.out.println("input your string (at least one character):");
        data = scan.nextLine();
        len = data.length();
    }
    // palindrome: check original string is a palindrome or not
    public void palindrome() {
        int i = 0;
        int j = 0;
        boolean mark = true;

        for (i = 0, j = len - 1; i < j; i++, j--)
            if (data.charAt(i) != data.charAt(j)) {
                mark = false;
                break;
            }
        // do not handle empty string
        if (len > 0) {
            if (mark) {
                output(true, data);
                return;
            }
            else {
                output(false, data);
                subPalin();
            }
        }
    }
    // palindrome(overloading): for substring use.
    // check substring is a palindrome or not
    private void palindrome(String sub) {
        int i = 0;
        int j = 0;
        boolean mark = true;

        for (i = 0, j = sublen - 1; i < j; i++, j--)
            if (sub.charAt(i) != sub.charAt(j)) {
                mark = false;
                break;
            }

        if (!subpalin && mark) {
            System.out.println("but its substring: ");
            subpalin = true;
            output(true, sub);
            return;
        }
        else if (mark)
            output(true, sub);
    }
    // subPalin: find substrings and check it is a palindrome or not
    private void subPalin() {
        int i = 0;
        int j = 0;

        // enumerate all substrings exclusive of one character string
        // ignore one character string
        for (i = 0; i < len - 1; i++)
            for (j = i + 2; j <= len; j++) {
                sub = data.substring(i, j);
                sublen = sub.length();
                palindrome(sub);
            }
    }
    // output: output results
    private void output(boolean i, String data) {
        if (i)
            System.out.println("\"" + data + "\"" + " is a palindrome.");
        else
            System.out.println("\"" + data + "\"" + " is not a palindrome.");
    }
}

