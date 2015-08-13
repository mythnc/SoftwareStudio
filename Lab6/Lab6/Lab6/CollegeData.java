// Date: 2012/11/23 15:30:24   

import java.util.ArrayList;

// CollegeData: Save each college data.
public class CollegeData extends StudentData {
    private ArrayList<DepartmentData> departmentList;

    public CollegeData(String name) {
        setName(name);
        departmentList = new ArrayList<DepartmentData>();
    }

    public void setDepartment(DepartmentData d) {
        departmentList.add(d);
    }

    public ArrayList<DepartmentData> getDepartment() {
        return departmentList;
    }

    // getAverage: Calculating the average student number of departments.
    public float getAverage() {
        int sum = 0;
        int i;

        for (i = 0; i < departmentList.size(); i++)
            sum += departmentList.get(i).getTotalNum();

        return sum / (float)i;
    }

    // output: For test use.
    public void output() {
        System.out.print("college " + getName() + " ");
        super.output();

        for (DepartmentData d: departmentList)
            d.output();
    }
}

