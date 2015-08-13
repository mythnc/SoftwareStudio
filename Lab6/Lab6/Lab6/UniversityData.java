// Date: 2012/11/23 15:41:33

import java.util.ArrayList;

// UniversityData: Save university data.
public class UniversityData extends StudentData {
    private ArrayList<CollegeData> collegeList;

    UniversityData() {
        collegeList = new ArrayList<CollegeData>();
    }

    public void setCollege(CollegeData c) {
        collegeList.add(c);
    }

    public ArrayList<CollegeData> getCollege() {
        return collegeList;
    }

    // getAverage: Calculating the average student number of colleges.
    public float getAverage() {
        int sum = 0;
        int i;

        for (i = 0; i < collegeList.size(); i++)
            sum += collegeList.get(i).getTotalNum();

        return sum / (float)i;
    }

    // output: For test use.
    public void output() {
        System.out.print("university nthu: ");
        super.output();

        for (CollegeData c: collegeList)
            c.output();
    }
}

