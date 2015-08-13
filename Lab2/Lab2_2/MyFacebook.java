// Date: 2012/10/13 13:33:58   
import java.util.Scanner;

public class MyFacebook {
    private int count;
    private User[] ulist = new User[5];
    private Scanner scan = new Scanner(System.in);
    // createUser: create user profile
    public void createUser(String n, String g, int y, int m, int d, String r, String e, String p, String ma) {
        ulist[count] = new User();
        ulist[count].setName(n);
        ulist[count].setGender(g);
        ulist[count].setBirth(y, m, d);
        ulist[count].setResidence(r);
        ulist[count].setEmail(e);
        ulist[count].setPhone(p);
        ulist[count].setMarriage(ma);
    }
    // createUserFriend: create user's friend
    public void createUserFriend(String n, String t) {
        ulist[count].setFriend(n, t);
    }
    // add: add one save space
    public void add() {
        count++;
    }
    // listUsers: list all users
    public void listUsers() {
        System.out.println("====== Users ======");
        for (int i = 0; i < count; i++)
            System.out.println((i + 1) + ". " + ulist[i].getName());
        System.out.println("------ Users ------");
        System.out.println("Choose Username(input number):");
    }
    // chooseUser: choose the user whom you want to login as
    public int chooseUser() {
        return scan.nextInt();
    }
    // userMenu: show menu of user and receive command
    public boolean userMenu(int unum) {
        unum--;
        while (true) {
            System.out.println("\nLogin as: " + ulist[unum].getName());
            System.out.println("====== Menu ======");
            System.out.println("1. Access personal profile");
            System.out.println("2. List friends");
            System.out.println("3. Inquire friend's profile");
            System.out.println("4. Switch User");
            System.out.println("5. Exit");
            System.out.println("------ Menu ------");
            System.out.println("enter the number you want to do:");

            switch (scan.nextInt()) {
                case 1:
                    ulist[unum].getProfile();
                    break;
                case 2:
                    ulist[unum].listFriends();
                    break;
                case 3:
                    ulist[unum].listFriends();
                    String fname = ulist[unum].findFriend();
                    if (fname.length() == 0)
                        break;
                    getFriendProfile(fname, ulist[unum].getName());
                    break;
                case 4:
                    listUsers();
                    return true;
                case 5:
                    return false;
            }
        }
    }
    // getFriendProfile: find the relationship of friend and user
    // then print out friend's profile
    public void getFriendProfile(String fname, String uname) {
        System.out.println("======== " + fname + "'s Personal Profile ========");
        for (int i = 0; i < count; i++)
            if (ulist[i].getName().equals(fname)) {
                for (int j = 0; j < ulist[i].getFriendNumber(); j++)
                    if (ulist[i].getFriend(j).getName().equals(uname))
                        ulist[i].getProfile(ulist[i].getFriend(j).getType());
                break;
            }
        System.out.println("-------- " + fname + "'s Personal Profile --------");
    }
}

