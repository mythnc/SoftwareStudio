// Date: 2012/10/26 19:57:38   
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.StringBuilder;

public class IntCalculator extends JFrame {
    private final int MAX_TEXT_LEN = 15;

    private StringBuilder text;
    private long ans;
    private int opd;
    private char opr = '+';
    private boolean isError;
    private boolean isOpr;
    private boolean preIsAns;
    private boolean is1stNum = true;
    private JButton[] numButton;
    private JButton addButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;
    private JButton clearButton;
    private JButton ansButton;
    private JTextField displayTF;

    IntCalculator (String title) {
        super(title);

        text = new StringBuilder(MAX_TEXT_LEN);
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        clearButton = new JButton("C");
        ansButton = new JButton("=");
        displayTF = new JTextField(11);
        displayTF.setText("0");
        displayTF.setHorizontalAlignment(JTextField.RIGHT);
        displayTF.setEditable(false);

        numButton = new JButton[10];
        for (int i = 0; i < 10; i++) {
            final String s = Integer.toString(i);
            numButton[i] = new JButton(s);

            // register numButtons: when numButtons be clicked,
            // append that number to text and copy text content
            // to displayTF
            numButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!lengthOverflow() && !isError) {
                        isOpr = false;

                        appendText(s);
                        displayTF.setText(text.toString());
                    }
                }
            });
        }

        // register clearButton: reset variables to default
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTF.setText("0");
                ans = opd = 0;
                text.delete(0, MAX_TEXT_LEN);
                isError = isOpr = preIsAns = false;
                opr = '+';
                is1stNum = true;
            }
        });

        // register operator Buttons
        addButton.addActionListener(new oprActionListener('+'));
        subButton.addActionListener(new oprActionListener('-'));
        mulButton.addActionListener(new oprActionListener('*'));
        divButton.addActionListener(new oprActionListener('/'));
        ansButton.addActionListener(new oprActionListener('='));

        // use GridLayout to contain buttons
        JPanel centerGrid = new JPanel(new GridLayout(4, 4, 5, 5));
        centerGrid.add(numButton[7]);
        centerGrid.add(numButton[8]);
        centerGrid.add(numButton[9]);
        centerGrid.add(divButton);
        centerGrid.add(numButton[4]);
        centerGrid.add(numButton[5]);
        centerGrid.add(numButton[6]);
        centerGrid.add(mulButton);
        centerGrid.add(numButton[1]);
        centerGrid.add(numButton[2]);
        centerGrid.add(numButton[3]);
        centerGrid.add(subButton);
        centerGrid.add(numButton[0]);
        centerGrid.add(clearButton);
        centerGrid.add(ansButton);
        centerGrid.add(addButton);

        // put TextField and Grid to Border
        setLayout(new BorderLayout());
        add(displayTF, BorderLayout.NORTH);
        add(centerGrid, BorderLayout.CENTER);
    }

    // appendText: append number n to text
    private void appendText(String n) {
        // if text = "0"
        if (text.length() == 1 && text.charAt(0) == '0') {
            // if n is zero, do not append it
            if (n.equals("0"))
                return;

            // if n is non-zero, delete text[0]
            text.deleteCharAt(0);
        }
        text.append(n);
    }

    // lengthOverflow: the digits of int number can't more than 9 digits
    private boolean lengthOverflow() {
        int len = text.length();

        // negative int
        if (len > 0 && text.charAt(0) == '-' && len == 10)
            return true;
        // positive int
        if (len == 9)
            return true;

        return false;
    }

    // oprActionListener: depend on char c to do correspond operation.
    // check 1)division can't be zero. 2) length <= 9
    // if no problem update displayTF, text, and opr.
    private class oprActionListener implements ActionListener {
        private char c;

        oprActionListener(char c) {
            this.c = c;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isError)
                return;

            if (is1stNum && c == '=') {
                return;
            }

            is1stNum = false;

            if (!isOpr) {
                // if no content in text, set opd to zero
                // otherwise copy text content to opd
                if (text.length() == 0)
                    opd = 0;
                else
                    opd = Integer.valueOf(text.toString());
            }
            // do not handle repeat operator
            else if (isOpr && c != '=') {
                opr = c;
                preIsAns = false;
                return;
            }
            else if (isOpr && !preIsAns && c == '=') {
                opd = (int)ans;
            }

            // do operations
            switch (opr) {
                case '+':
                    ans += opd;
                    break;
                case '-':
                    ans -= opd;
                    break;
                case '*':
                    ans *= opd;
                    break;
                case '/':
                    // divide by zero is not permitted
                    // show error message on displayTY
                    // and block all buttons exclusive clearbutton
                    if (opd == 0) {
                        isError = true;
                        displayTF.setText("ERROR");
                        return;
                    }
                    ans /= opd;
                    break;
                default:
                    ;
            }

            // if abs(ans) >= 10^9 (10 digits)
            // do not print it out
            if (ans > 999999999 || ans < -999999999) {
                isError = true;
                displayTF.setText("ERROR");
                return;
            }

            // output ans and set variables
            displayTF.setText(Integer.toString((int)ans));
            text.delete(0, MAX_TEXT_LEN);
            isOpr = true;
            if (c != '=') {
                opr = c;
                preIsAns = false;
            }
            else
                preIsAns = true;
        }
    }
}

