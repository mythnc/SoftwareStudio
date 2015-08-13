// Date: 2012/10/04 21:09:31   
// Problem: guess number
// Algorithm: see readme.txt
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Lab1_4 {
    public static void main(String[] args) {
        Random generator = new Random();
        Scanner scanner = new Scanner(System.in);
        int[] answer, guess;
        int i, len, j, k, a, b;
        boolean mark;
        boolean[] visited_g, visited_a;
        String input;

        answer = new int[4];
        guess = new int[4];
        visited_g = new boolean[4];
        visited_a = new boolean[4];
        // generate 4 random numbers
        for (i = 0; i < 4; i++)
            answer[i] = generator.nextInt(10);

        // for test using: output answer
        //System.out.print("answer: ");
        //for (i = 0; i < 4; i++)
        //    System.out.print(answer[i]);
        //System.out.println();
        // test end

        System.out.println("******  Guess Number  ******\n");
        System.out.println("You have 10 times to guess number");
        System.out.println("input 4-digit number");
        // user input
        for (i = a = 0; i < 10 && a < 4; i++) {
            System.out.println((i + 1) + ".");
            // fool-proof
            while (true) {
                input = scanner.nextLine();
                for (j = 0, mark = false, len = input.length(); j < 4 && len == 4; j++) {
                    guess[j] = (int)input.charAt(j) - (int)'0';
                    if (!(guess[j] >= 0 && guess[j] < 10)) {
                        mark = true;
                        break;
                    }
                }
                if (mark || len != 4) {
                    System.out.println("input 4-digit number again");
                    continue;
                }
                break;
            }

            // initialize boolean arrays to false
            Arrays.fill(visited_a, false);
            Arrays.fill(visited_g, false);
            // find times of a and b
            for (j = a = 0; j < 4; j++)
                if (answer[j] == guess[j]) {
                    a++;
                    visited_a[j] = visited_g[j] = true;
                    continue;
                }
            for (j = b = 0; j < 4 && a < 4; j++) {
                if (visited_g[j])
                    continue;
                for (k = (j + 1) % 4; k != j; k = (k + 1) % 4) {
                    if (visited_a[k])
                        continue;
                    if (answer[k] == guess[j]) {
                        b++;
                        visited_a[k] = visited_g[j] = true;
                        break;
                    }
                }
            }
            // output a and b
            System.out.println(a + " A " + b + " B\n");
        }

        if (a == 4) {
            System.out.println("You guess the right number.");
            System.out.println("Congratulation!");
        }
        else {
            System.out.println("You lose!");
            System.out.print("the right answer is: ");
            for (i = 0; i < 4; i++)
                System.out.print(answer[i]);
            System.out.println();
        }
    }
}

