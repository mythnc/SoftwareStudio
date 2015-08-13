// Date: 2012/11/23 15:30:24   

// StudentData: Prototype data class. Save student data.
public class StudentData {
    private String name;
    private int male;
    private int female;
    private int total;

    protected void setName(String name) {
        this.name = name;
    }

    protected void setMaleNum(String n) {
        male = Integer.valueOf(n);
    }

    protected void setFemaleNum(String n) {
        female = Integer.valueOf(n);
    }
    
    protected void setTotalNum(String n) {
        total = Integer.valueOf(n);
    }

    protected String getName() {
        return name;
    }

    protected int getFemaleNum() {
        return female;
    }

    protected int getMaleNum() {
        return male;
    }

    protected int getTotalNum() {
        if (total == 0)
            return female + male;
        return total;
    }

    // output: For test use.
    // Output data to stdin.
    protected void output() {
        System.out.print("Male: " + male);
        System.out.print(" Female: " + female);
        System.out.print(" Total: " + getTotalNum() + "\n");
    }
}

