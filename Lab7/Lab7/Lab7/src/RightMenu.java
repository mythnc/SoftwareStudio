// Programmer: Wu, En-Hsin 100062273 Lab7
// Date: 2012/12/01 21:18:21   
// Revised this code which TA wrote for Lab7 use.
// 1. Move the ActionListener of draw to LeftDraw.java
// 2. Make the ActionListener of send to anonymous class

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;

public class RightMenu extends JPanel {
    public JButton load;
    public JButton draw;
    public JTextPane content;
    public JTextPane newInput;
    public JButton send;
    public String file;
    private int[] buttonSize = {285, 30};

    public RightMenu() {
        setPreferredSize(new Dimension(300, 600));

        load = new JButton("LOAD");
        load.setPreferredSize(new Dimension(buttonSize[0], buttonSize[1]));
        ActionListener loadFile = new loadListener();
        load.addActionListener(loadFile);
        add(load);

        content = new JTextPane();
        content.setPreferredSize(new Dimension(280,450));
        content.setSize(new Dimension(280,450));
        content.setEditable(false);
        content.setText("");

        JScrollPane scrollText = new JScrollPane(content);
        scrollText.setPreferredSize(new Dimension(280, 450));
        scrollText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrollText);

        draw = new JButton("DRAW");
        draw.setPreferredSize(new Dimension(buttonSize[0], buttonSize[1]));
        add(draw);

        newInput = new JTextPane();
        newInput.setPreferredSize(new Dimension(200,50));
        JScrollPane scrollInput = new JScrollPane(newInput);
        scrollInput.setPreferredSize(new Dimension(200, 50));
        scrollInput.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(scrollInput);

        send = new JButton("SEND");
        // Use Document to append string to content
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String s = newInput.getText();
                newInput.setText("");
                try {
                    Document doc = content.getDocument();
                    doc.insertString(doc.getLength(), s, null);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        add(send);
    }

    public class loadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedReader reader = null;
            try {
                //Create a file chooser
                File f = new File(new File(".").getCanonicalPath());
                //open current directory
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(f);

                //In response to a button click:
                int returnVal = fc.showOpenDialog(load);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    FileInputStream inputFile = new FileInputStream(file);
                    DataInputStream inputData = new DataInputStream(inputFile);
                    StringBuffer contents = new StringBuffer();
                    reader = new BufferedReader(new InputStreamReader(inputData, "UTF-8"));
                    String text = null;
                    while ((text = reader.readLine()) != null)
                        contents.append(text).append(System.getProperty("line.separator"));

                    System.out.println("Opening: " + file.getName() + ".");
                    content.setText(contents.toString());
                } 
                else
                    System.out.println("Open command cancelled by user.");
            } catch (FileNotFoundException e1) {
                content.setText("file not found");
                file = "";
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            finally {
                try {
                    if (reader != null)
                        reader.close();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

