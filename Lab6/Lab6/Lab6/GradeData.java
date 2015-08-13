// Date: 2012/11/23 20:25:50   

// GradeData: Save grade data.
public class GradeData extends StudentData {
    GradeData (String name) {
        setName(name);
    }

    // output: For test use.
    public void output() {
        System.out.print("grade " + getName() + " ");
        super.output();
    }
}

