// Programmer: Wu, En-Hsin 100062273 Lab9-2
// Date: 2012/12/30 11:05:39   
// Problem: Send data without BufferedImage.

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

// The format of saving drawing data.
public class TransData implements Serializable {
    private Color color;
    private ArrayList<Point> pointSet;

    TransData(Color color) {
        pointSet = new ArrayList<Point>(0);
        this.color = color;
    }

    public void setPoint(int x, int y) {
        pointSet.add(new Point(x, y));
    }

    public ArrayList<Point> getPointSet() {
        return pointSet;
    }

    public Color getColor() {
        return color;
    }
}

