// Date: 2012/11/24 18:06:18

import java.net.URL;
import java.io.*;

import javax.swing.JFrame;

// MakeData: Parse the input file.
public class MakeData {
    private URL url;
    private String path = "data/regb100_1.txt";
    private String[] dataset;
    private UniversityData university;
    private CollegeData college;
    private DepartmentData department;
    private GradeData grade;
    private boolean inCollege;
    private final String[] gradeLevel = {"1", "2", "3","4", "5", "6"};

    MakeData(String s) {
        if (s != null)
            path = s;
        url = this.getClass().getClassLoader().getResource(path);
        university = new UniversityData();
        try {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(url.openStream(), "UTF-8"));
            String line = null;
            int count = 0;
            while ((line = br.readLine()) != null) {
                // The first 4 lines are uesless
                if (count < 4) {
                    count++;
                    continue;
                }

                dataset = line.split("\t+");
                int len = dataset[0].length();

                // Find a new college
                if (dataset[0].charAt(1) != ' ' && !inCollege) {
                    college = new CollegeData(dataset[0]);
                    inCollege = true;
                }
                else {
                    char c = dataset[0].charAt(0);

                    // The last line data
                    if (c == '全' && dataset[0].charAt(len - 1) == '計') {
                        university.setTotalNum((dataset[1]));
                        university.setMaleNum((dataset[2]));
                        university.setFemaleNum((dataset[3]));
                        break;
                    }

                    // Find a new Department
                    if (c != '本' && c != '全') {
                        department = new DepartmentData(dataset[0]);
                        department.setTotalNum((dataset[1]));
                        department.setMaleNum((dataset[2]));
                        department.setFemaleNum((dataset[3]));
                        for (int i = 4; i < dataset.length; i++) {
                            // Find a new Grade
                            grade = new GradeData(gradeLevel[(i - 4) / 2]);
                            grade.setMaleNum((dataset[i++]));
                            grade.setFemaleNum((dataset[i]));
                            // Add grade to department
                            department.setGrade(grade);
                        }
                        // Add department to college
                        college.setDepartment(department);
                    }
                    else {
                        college.setTotalNum((dataset[1]));
                        college.setMaleNum((dataset[2]));
                        college.setFemaleNum((dataset[3]));
                        // Add college to university
                        university.setCollege(college);
                        inCollege = false;
                    }
                }
            }
            br.close();
        }
        catch (IOException e) {
            System.err.println("no such file.");
        }

    }

    public void visualize() {
        VisualData applet = new VisualData(university);
        applet.init();
        applet.start();

        JFrame frame = new JFrame("Visualization of student population");
        frame.add(applet);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // read: For test use.
    // Output parse data to stdin.
    public void read() {
        university.output();
    }
}

