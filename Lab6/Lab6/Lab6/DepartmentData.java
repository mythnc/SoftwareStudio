// Date: 2012/11/23 15:30:24   

import java.util.ArrayList;

// DepartmentData: Save department data.
public class DepartmentData extends StudentData {
    private ArrayList<GradeData> gradeList;

    DepartmentData(String name) {
        setName(name);
        gradeList = new ArrayList<GradeData>();
    }

    public void setGrade(GradeData g) {
        gradeList.add(g);
    }

    public ArrayList<GradeData> getGrade() {
        return gradeList;
    }

    // getAverage: Calculating the average student number of grades.
    public float getAverage() {
        int sum = 0;
        int i;

        for (i = 0; i < gradeList.size(); i++)
            sum += gradeList.get(i).getTotalNum();

        return sum / (float)i;
    }

    // output: For test use.
    public void output() {
        System.out.print("department " + getName() + " ");
        super.output();

        for (GradeData g: gradeList)
            g.output();
    }
}

