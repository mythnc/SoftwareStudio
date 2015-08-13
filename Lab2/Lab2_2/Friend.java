// Date: 2012/10/13 08:25:55   
public class Friend extends User {
    private String name;
    private enum Ftype {REGULAR, CLOSE};
    private Ftype type;

    // setType: set friend type
    public void setType(String t) {
        if (t.equals("Close"))
            type = Ftype.CLOSE;
        else if (t.equals("Regular"))
            type = Ftype.REGULAR;
    }
    // getType: return friend type
    public String getType() {
        if (type.equals(Ftype.CLOSE))
            return "Close";
        else
            return "Regular";
    }
}

