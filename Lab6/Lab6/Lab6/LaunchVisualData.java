// Date: 2012/11/24 18:19:40   
// Problem: Read file, parse the data,
// and display them by visualization.

public class LaunchVisualData {
    public static void main(String[] args) {
        String s;

        try {
            s = args[0];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            s = null;
        }

        MakeData student = new MakeData(s);
        //student.read();
        student.visualize();
    }
}

