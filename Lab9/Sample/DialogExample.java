import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DialogExample extends JFrame implements MouseListener {
    private JButton bu1;
    private JPanel jp;

    public DialogExample() {
        super("Dialog Example");
        Container c = getContentPane();
        jp = new JPanel();

        bu1 = new JButton("Press me");
        bu1.setSize(100, 100);
        bu1.addMouseListener(this);
        jp.add(bu1);
        c.add(jp);
    }

    public void mousePressed(MouseEvent e) {
        if (JOptionPane.showConfirmDialog(jp,
                    "showConfirmDialog Message context!", "Message Title!",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(jp, "Press Yes!!", "Message Title",
                    JOptionPane.WARNING_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(jp, "Press No!", "Message Title",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public static void main(String[] args) {
        DialogExample win = new DialogExample();
        win.setSize(300, 200);
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
