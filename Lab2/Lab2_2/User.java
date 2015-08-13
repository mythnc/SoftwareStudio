// Date: 2012/10/13 08:25:30   
import java.util.Scanner;

public class User {
    private String name;
    private enum Gtype {MALE, FEMALE};
    private Gtype gender;
    private int birth_year;
    private int birth_month;
    private int birth_day;
    private String residence;
    private String email;
    private String phone;
    private enum Mstate {SINGLE, MARRIED, DIVORCED};
    private Mstate marriage;
    private Friend[] flist = new Friend[4];
    private int count;
    private enum Ftype {REGULAR, CLOSE};

    // setName: set Name to n
    public void setName(String n) {
        name = n;
    }
    // setGender: set Gender
    public void setGender(String g) {
        if (g.equals("male"))
            gender = Gtype.MALE;
        else if(g.equals("female"))
            gender = Gtype.FEMALE;
    }
    // setBirth: set Birthday y, m, d correspond to birth_year, birth_month, birth_day
    public void setBirth(int y, int m, int d) {
        birth_year = y;
        birth_month = m;
        birth_day = d;
    }
    // setResidence: set Residence = r
    public void setResidence(String r) {
        residence = r;
    }
    // setEmail: set email = e
    public void setEmail(String e) {
        email = e;
    }
    // setPhone: set phone = p
    public void setPhone(String p) {
        phone = p;
    }
    // setMarriage: set marriage
    public void setMarriage(String m) {
        if (m.equals("Single"))
            marriage = Mstate.SINGLE;
        else if (m.equals("Married"))
            marriage = Mstate.MARRIED;
        else if (m.equals("Divorced"))
            marriage = Mstate.DIVORCED;
    }
    // setFriend: add friend name and it's type to flist
    public void setFriend(String n, String t) {
        flist[count] = new Friend();
        flist[count].setName(n);
        flist[count].setType(t);
        count++;
    }
    // getName: return name
    public String getName() {
        return name;
    }
    // getGender: return gender
    public String getGender() {
        if (gender.equals(Gtype.MALE))
            return "male";
        return "female";
    }
    // getBirth_y: return birthday year
    public int getBirthYear() {
        return birth_year;
    }
    // getBirth_m: return birthday month
    public int getBirthMonth() {
        return birth_month;
    }
    // getBirth_d: return birthday day
    public int getBirthDay() {
        return birth_day;
    }
    // getResidence: return residence
    public String getResidence() {
        return residence;
    }
    // getEmail: return email 
    public String getEmail() {
        return email;
    }
    // getPhone: return phone
    public String getPhone() {
        return phone;
    }
    // getMarr: return marriage state 
    public String getMarriage() {
        if (marriage.equals(Mstate.SINGLE))
            return "Single";
        else if (marriage.equals(Mstate.MARRIED))
            return "Married";
        return "Divorced";
    }
    // getProfile: print out personal profile
    public void getProfile() {
        System.out.println("======== Your Personal Profile ========");
        System.out.println("Name: " + getName());
        System.out.println("Gender: " + getGender());
        System.out.printf("Date of Birth: %d/%02d/%02d\n", getBirthYear(), getBirthMonth(), getBirthDay());
        System.out.println("Place of residence: " + getResidence());
        System.out.println("E-mail: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Marriage: " + getMarriage());
        System.out.println("======== Your Personal Profile ========");
    }
    // getProfile(overloading): print out friend profile depends friendship
    public void getProfile(String s) {
        System.out.println("Name: " + getName());
        System.out.println("Gender: " + getGender());
        System.out.printf("Date of Birth: %d/%02d/%02d\n", getBirthYear(), getBirthMonth(), getBirthDay());
        System.out.println("Place of residence: " + getResidence());
        System.out.println("E-mail: " + getEmail());
        if (s.equals("Close")) {
            System.out.println("Phone: " + getPhone());
            System.out.println("Marriage: " + getMarriage());
        }
    }
    // listFriends: print out friends' name
    public void listFriends() {
        System.out.println("====== Friends ======");
        for (int i = 0; i < count; i++)
            System.out.println(flist[i].getName());
        System.out.println("------ Friends ------");
    }
    // findFriend: find friends of user.
    // if found return friend name, else return empty string
    public String findFriend() {
        Scanner scan = new Scanner(System.in);
        String fname;

        System.out.println("enter the name you want to inquire:");
        
        fname = scan.nextLine();
        for (int i = 0; i < count; i++)
            if (flist[i].getName().equals(fname))
                return fname;
        System.out.println("can't find this friend");
        return "";
    }
    // 
    public int getFriendNumber() {
        return count;
    }
    //
    public Friend getFriend(int i) {
        return flist[i];
    }
}

