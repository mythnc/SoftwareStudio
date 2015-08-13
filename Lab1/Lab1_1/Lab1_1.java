// date: 2012/10/03 17:47:55
// problem: counting character x times in string line
// algorithm: directly using for loop to count
// character times.
import java.util.Scanner;

public class Lab1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line, x;
        int i, sum, len;
        char c;

        // let user input string and search character
        System.out.println("input string:");
        line = scanner.nextLine();
        do {
            System.out.println("input \"only\" one character:");
            x = scanner.nextLine();
            len = x.length();
        } while (len != 1);

        c = x.charAt(0);
        // count times of character c
        for (i = sum = 0; i < line.length(); i++)
            if (c == line.charAt(i))
                sum++;

        // output result
        System.out.println("Your input character is \'" + c + '\'');
        if (sum > 1)
            System.out.println("It appears " + sum + " times.");
        else if (sum == 1)
            System.out.println("It appears " + sum + " time.");
        else
            System.out.println("It never appears.");
    }
}

