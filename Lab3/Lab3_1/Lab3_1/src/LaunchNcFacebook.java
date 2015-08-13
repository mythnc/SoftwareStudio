// Date: 2012/10/21 18:42:07   
// Problem: design a GUI facebook-like system by OO concept
// 1) switch user option (display personal picture and friend list)
// 2) read friends' profile option (unsymmetric: close v.s. regular, display picture and profile)
// 3) change friendships option
// 4) visualize 3)
import javax.swing.JFrame;

// LaunchNcFacebook: create default users and their friends, 
// then use a window to represent them
public class LaunchNcFacebook {
    public static void main(String[] args) {
        NcFacebook fb = new NcFacebook();

        // input default users
        fb.createUser("Allen", "male", 1987, 6, 5, "Taipei",
                     "allen@nthucs.com", "0912477123", "Single", 230, 0);
        fb.createUser("Peter", "male" ,1988, 7, 23, "Tainan",
                     "peter@microsoft.com", "0987654321", "Divorced", 380, 100);
        fb.createUser("Sam", "male", 1988, 9, 2, "Taichung",
                     "sam@apple.com", "0972377819", "Married", 80, 125);
        fb.createUser("Cathy", "female", 1989, 4, 21, "Changhua",
                     "cathy@yahoo.com", "0923456789", "Single", 330, 300);
        fb.createUser("Mike", "male", 1990, 12, 12, "Hsinchu",
                     "mike@goole.com", "0910110101", "Single", 105, 300);

        // input default friendships
        fb.createUserFriend("Allen", "Peter", "Regular", 380, 100);
        fb.createUserFriend("Allen", "Sam", "Close", 80, 125);
        fb.createUserFriend("Allen", "Cathy", "Stranger", 330, 300);
        fb.createUserFriend("Allen", "Mike", "Regular", 105, 300);
        fb.createUserFriend("Peter", "Allen", "Regular", 230, 0);
        fb.createUserFriend("Peter", "Sam", "Regular", 80, 125);
        fb.createUserFriend("Peter", "Cathy", "Close", 330, 300);
        fb.createUserFriend("Peter", "Mike", "Close", 105, 300);
        fb.createUserFriend("Sam", "Allen", "Regular", 230, 0);
        fb.createUserFriend("Sam", "Peter", "Regular", 380, 100);
        fb.createUserFriend("Sam", "Cathy", "Regular", 330, 300);
        fb.createUserFriend("Sam", "Mike", "Stranger", 105, 300);
        fb.createUserFriend("Cathy", "Allen", "Stranger", 230, 0);
        fb.createUserFriend("Cathy", "Peter", "Regular", 380, 100);
        fb.createUserFriend("Cathy", "Sam", "Close", 80, 125);
        fb.createUserFriend("Cathy", "Mike", "Regular", 105, 300);
        fb.createUserFriend("Mike", "Allen", "Close", 230, 0);
        fb.createUserFriend("Mike", "Peter", "Regular", 380, 100);
        fb.createUserFriend("Mike", "Sam", "Stranger", 80, 125);
        fb.createUserFriend("Mike", "Cathy", "Close", 330, 300);

        // create Main Window
        MainWindow frame = new MainWindow("NcFacebook", fb.getData());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(550, 420);
        frame.setVisible(true);
    }
}

