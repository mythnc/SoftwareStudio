// Date: 2012/10/13 18:28:09   
// Problem: design a facebook-like system by OO concept
// 1) access personal profile option
// 2) switch user option
// 3) read friend's profile option(unsymmetric: close v.s. regular)
public class LaunchMyFacebook {
    public static void main(String[] args) {
        MyFacebook x = new MyFacebook();

        x.createUser("Allen", "male", 1987, 6, 5, "Taipei", "allen@nthucs.com", "0912477123", "Single");
        x.createUserFriend("Peter", "Regular");
        x.createUserFriend("Sam", "Close");
        x.createUserFriend("Mike", "Regular");
        x.add();
        x.createUser("Peter", "male" ,1988, 7, 23, "Tainan", "peter@microsoft.com", "0987654321", "Divorced");
        x.createUserFriend("Allen", "Regular");
        x.createUserFriend("Sam", "Regular");
        x.createUserFriend("Cathy", "Close");
        x.createUserFriend("Mike", "Close");
        x.add();
        x.createUser("Sam", "male", 1988, 9, 2, "Taichung", "sam@apple.com", "0972377819", "Married");
        x.createUserFriend("Allen", "Regular");
        x.createUserFriend("Peter", "Regular");
        x.createUserFriend("Cathy", "Regular");
        x.add();
        x.createUser("Cathy", "female", 1989, 4, 21, "Changhua", "cathy@yahoo.com", "0923456789", "Single");
        x.createUserFriend("Peter", "Regular");
        x.createUserFriend("Sam", "Close");
        x.createUserFriend("Mike", "Regular");
        x.add();
        x.createUser("Mike", "male", 1990, 12, 12, "Hsinchu", "mike@goole.com", "0910110101", "Single");
        x.createUserFriend("Allen", "Close");
        x.createUserFriend("Peter", "Regular");
        x.createUserFriend("Cathy", "Close");
        x.add();

        x.listUsers();
        while (x.userMenu(x.chooseUser()))
            ;
    }
}

