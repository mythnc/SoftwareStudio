// Date: 2012/10/22 22:22:35   
import java.util.ArrayList;
import java.lang.StringBuilder;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.FlowLayout;
import javax.swing.*;

// User: put all user data in this class
// set Friend data in ArrayList<Friend> individually
public class User {
    private String name;
    private enum Gtype {MALE, FEMALE};
    private Gtype gender;
    private int birth_year, birth_month, birth_day;
    private String residence;
    private String email;
    private String phone;
    private enum Mstate {SINGLE, MARRIED, DIVORCED};
    private Mstate marriage;
    private int pos_x, pos_y;
    private BufferedImage image;
    private JLabel label;
    private Friend f;
    private ArrayList<Friend> flist;
    private JPanel panel;

    User() {
        flist = new ArrayList<Friend>();
    }

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
    public void setFriend(String n, String t, int x, int y) {
        f = new Friend();
        f.setName(n);
        f.setType(t);
        f.setImage(n);
        f.setPositionX(x);
        f.setPositionY(y);
        f.setRadioButton();
        flist.add(f);
    }

    // setPositionX: set the position x of image in panel
    public void setPositionX(int x) {
        pos_x = x;
    }

    // setPositionY: set the position y of image in panel
    public void setPositionY(int y) {
        pos_y = y;
    }

    // setImage: get the image file from hard disk
    public void setImage(String picName) {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("images/" + picName + ".png"));
        }
        catch (IOException ex) {
            System.out.println("no such file");
        }
    }

    // setLabel: set label (use this label at 2nd windom mid panel top)
    // package it in a panel with FlowLayout
    public void setLabel() {
        label = new JLabel("set friendship of " + name + "'s friends");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.BOTTOM);

        panel = new JPanel(new FlowLayout());
        panel.add(label);
    }

    // getName: return name
    public String getName() {
        return name;
    }

    // getGender: return gender
    public String getGender() {
        if (gender.equals(Gtype.MALE))
            return "Male";
        return "Female";
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

    // getPositionX: return the image position x in the panel
    public int getPositionX() {
        return pos_x;
    }

    // getPositionY: return the image position y in the panel
    public int getPositionY() {
        return pos_y;
    }

    // getImage: return image file
    public BufferedImage getImage() {
        return image;
    }

    // getLabel: return label.
    public JPanel getLabel() {
        return panel;
    }

    // getProfile: print out friend profile depends on the friendship
    public String getProfile(String s) {
        String newLine = "<br>";
        StringBuilder profile = new StringBuilder(200);

        // put personal profile in profile by html syntax
        profile.append("<html>" + newLine);
        profile.append("Name: " + getName() + newLine);
        profile.append("Gender: " + getGender() + newLine);
        profile.append(String.format("Date of Birth: %d/%02d/%02d<br>",
                        getBirthYear(), getBirthMonth(), getBirthDay()));
        profile.append("Place of residence: " + getResidence() + newLine);
        profile.append("E-mail: " + getEmail() + newLine);

        if (s.equals("Close")) {
            profile.append("Phone: " + getPhone() + newLine);
            profile.append("Marriage: " + getMarriage());
        }

        return profile.toString();
    }

    // getFriends: return flist ArrayList;
    public ArrayList<Friend> getFriends() {
        return flist;
    }
}

