// Date: 2012/10/12 20:39:19
// Problem: improved Lab1_2_i
// 1) check palindrome
// 2) if not, check whether its substrings are palindrome or not
// Algorithm:
// 1) same as Lab1_2_i
// 2) enumerate all substrings and check whether they are palindrome or not
//    (time complexity is O(N^2))

public class PalindromeFinder {
    public static void main(String[] args) {
        MyString s = new MyString();

        s.set();
        s.palindrome();
    }
}

