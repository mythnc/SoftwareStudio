// Date: 2012/10/22 22:31:42   
import java.util.ArrayList;

// NcFacebook: a medium to construct user and friend data
// save them in ArrayList<User> and ArrayList<Friend>
public class NcFacebook {
    private User u;
    private ArrayList<User> ulist;

    NcFacebook() {
        ulist = new ArrayList<User>();
    }

    // createUser: create user profile
    public void createUser(String n, String g, int y, int m, int d,
                           String r, String e, String p, String ma, int pos_x, int pos_y) {
        u = new User();
        u.setName(n);
        u.setGender(g);
        u.setBirth(y, m, d);
        u.setResidence(r);
        u.setEmail(e);
        u.setPhone(p);
        u.setMarriage(ma);
        u.setPositionX(pos_x);
        u.setPositionY(pos_y);
        u.setImage(n);
        u.setLabel();
        ulist.add(u);
    }

    // createUserFriend: create user's friend
    public void createUserFriend(String uname, String fname, String type, int pos_x, int pos_y) {
        for (User i: ulist)
            if (uname.equals(i.getName())) {
                i.setFriend(fname, type, pos_x, pos_y);
                return;
            }
    }

    // getData: return ulist
    public ArrayList<User> getData() {
        return ulist;
    }
}

