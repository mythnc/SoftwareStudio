// Date: 2012/11/24 18:24:01   

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;

// VisualData: Visualize the student number relatively.
public class VisualData extends PApplet {
    private UniversityData university;
    private ArrayList<CollegeData> college;
    private ArrayList<DepartmentData> department;
    private ArrayList<GradeData> grade;
    private int total;
    private int female;
    private PFont f;

    VisualData(UniversityData u) {
        university = u;
        total = u.getTotalNum();
        female = u.getFemaleNum();
    }

    @Override
    public void setup() {
        size(750, 600);
        smooth();
        noLoop();
        background(255.0f * female / total);
        f = createFont("mingliu", 12, true);
        //String[] fontList = PFont.list();
        //println(fontList);
        textFont(f);
    }

    @Override
    public void draw() {
        drawCollegeRect();
    }

    private void drawCollegeRect() {
        college = university.getCollege();
        float avg = university.getAverage();
        int count = 0;

        for (int row = 0; row < 2; row++)
            for (int col = 0; col < 4; col++)
                collegeRect(college.get(count++), avg, row, col);
    }

    // collegeRect: the prototype of college rectugular
    private void collegeRect(CollegeData c, float avg, int row, int col) {
        int total = c.getTotalNum();
        int female = c.getFemaleNum();
        int male = c.getMaleNum();
        String name = c.getName();
        final float scale = total / avg;
        final float len = 50 * scale;
        final int x = 70;
        final int y = 100;
        final int tranX = 160 * col;
        final int tranY = 290 * row;

        // Translate the size and position of college rectangular
        pushMatrix();
        translate(tranX, tranY);
        // Set stroke
        if (male > female)
            stroke(0, 0, 255);
        else if (male == female)
            noStroke();
        else
            stroke(255, 0, 0);
        fill(255.0f * female / total);
        rect(x, y, len, len);

        // Show the name of college and its deparments
        fill(0);
        text(name + " " + Integer.toString(total), x, y + len + 15);
        stroke(0);
        line(x, y + len + 15 + 4, x + textWidth(name), y + len + 15 + 4);
        department = c.getDepartment();
        for (int i = 0; i < department.size(); i++) {
            DepartmentData d = department.get(i);
            name = d.getName();
            total = d.getTotalNum();
            fill(0);
            text(name + " " + Integer.toString(total), x,
                       y + len + 15 * (i + 2));
            // Draw Department rect
            drawDepartmentRect(d, c.getAverage(), x, y, i);
        }
        popMatrix();
    }

    private void drawDepartmentRect(DepartmentData d, float avg,
                             int x, int y, int n) {
        int total = d.getTotalNum();
        int female = d.getFemaleNum();
        int male = d.getMaleNum();
        String name = d.getName();
        final float scale = total / avg;
        final int w = 8;
        final float h = 30 * scale;
        final int rangeX = w + 20;
        final int rangeY = 3;

        // Set stroke
        if (male > female)
            stroke(0, 0, 255);
        else if (male == female)
            noStroke();
        else
            stroke(255, 0, 0);
        fill(255.0f * female / total);
        pushMatrix();
        translate(x + n * rangeX, y - rangeY - h);
        rect(0, 0, w, h);
        // Draw grade
        grade = d.getGrade();
        for (int i = 0; i < grade.size(); i++)
            drawGradeCirc(grade.get(i), d.getAverage(), w, h, i);
        popMatrix();
    }

    private void drawGradeCirc(GradeData g, float avg, int w,
                            float h, int n) {
        int total = g.getTotalNum();
        int female = g.getFemaleNum();
        int male = g.getMaleNum();
        String name = g.getName();
        final float scale = total / avg;
        final float r = 5f * scale;
        // Set stroke
        if (male > female)
            stroke(0, 0, 255);
        else if (male == female)
            noStroke();
        else
            stroke(255, 0, 0);
        fill(255.0f * female / total);
        pushMatrix();
        translate(w + 10, h - n * 15 - 5);
        ellipse(0, 0, r, r);
        popMatrix();
    }
}

