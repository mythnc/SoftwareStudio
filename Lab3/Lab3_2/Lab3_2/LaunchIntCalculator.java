// Date: 2012/10/26 19:54:29   
// Problem: Design a GUI Integer Calculator
// Constrait: 1) the size of int. 2) can't divide by zero
import javax.swing.JFrame;

public class LaunchIntCalculator {
    public static void main(String[] args) {
        IntCalculator cal = new IntCalculator("Calculator");

        cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cal.pack();
        cal.setResizable(false);
        cal.setVisible(true);
    }
}

