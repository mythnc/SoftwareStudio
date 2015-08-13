//Painter.java   
import java.io.*;   
import java.awt.*;   
import java.awt.event.*;   
import javax.swing.*;   
import javax.imageio.*;   

class FaceMenuItem extends JRadioButtonMenuItem   
{   
    private String face;   

    public FaceMenuItem(String text, String face,boolean selected){   
        super(text,selected);   
        this.face = face;   
    }   
    public FaceMenuItem(String text, String face){   
        this(text, face, false);   
    }   

    public String   getFace(){return face;}   
}   

interface WidgeMediator   
{   
    void    widgeChanged(Component widge);   
}   

public class Painter extends JFrame implements ActionListener,WidgeMediator   
{   
    private String      filename = null;   
    private Picture     picture = new Picture();   

    private Container   c = getContentPane();   
    private DrawPanel   drawPanel = new DrawPanel();   
    private ColorPanel  colorPanel = new ColorPanel(this);   
    private ToolPanel   toolPanel = new ToolPanel(this,drawPanel);   
    private JToolBar    toolBar = new JToolBar();   

    private JButton     newBtn = new JButton(new ImageIcon("res/new.gif")),   
            openBtn = new JButton(new ImageIcon("res/open.gif")),   
            saveBtn = new JButton(new ImageIcon("res/save.gif")),   
            unDoBtn = new JButton(new ImageIcon("res/undo.gif")),   
            reDoBtn = new JButton(new ImageIcon("res/redo.gif")),   
            clearBtn = new JButton(new ImageIcon("res/clear.gif")),   
            abtBtn = new JButton(new ImageIcon("res/about.gif"));   
    private JButton     btns[] = {newBtn,openBtn,saveBtn,unDoBtn,reDoBtn,clearBtn,abtBtn};   
    private ButtonGroup faceGrp = new ButtonGroup();   

    private JMenuBar    menuBar1 = new JMenuBar();   
    private JMenu       fileMenu = new JMenu("File"),   
            editMenu = new JMenu("Edit"),   
            faceMenu = new JMenu("Look&Feel"),   
            helpMenu = new JMenu("Help");   
    private JMenu       menus[] = {fileMenu,editMenu,faceMenu,helpMenu};   

    private JMenuItem   newf = new JMenuItem("New"),   
            open = new JMenuItem("Open"),   
            save = new JMenuItem("Save"),   
            saveas = new JMenuItem("Save As"),   
            exit = new JMenuItem("Exit");   
    private JMenuItem   fileItems[] = {newf,open,save,saveas,exit};   

    private JMenuItem   unDo = new JMenuItem("Undo"),   
            reDo = new JMenuItem("Redo"),   
            clear = new JMenuItem("Clear");   
    private JMenuItem   editItems[] = {unDo,reDo,clear};   


    private FaceMenuItem    metal = new FaceMenuItem("Metal","javax.swing.plaf.metal.MetalLookAndFeel",true),   
            motif = new FaceMenuItem("Motif","com.sun.java.swing.plaf.motif.MotifLookAndFeel"),   
            windows = new FaceMenuItem("Windows","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");   
    private JMenuItem   faceItems[] = {metal,motif,windows};   

    private JMenuItem   help = new JMenuItem("Help"),   
            about = new JMenuItem("About");   
    private JMenuItem   helpItems[] = {help,about};   

    private JMenuItem   menuItems[][] = {fileItems,editItems,faceItems,helpItems};   

    //////////////////////////////////////////////////////////////////////////////   
    public Painter(){   
        super("Painter");
        c.setLayout(new BorderLayout());   
        c.add(toolBar,BorderLayout.NORTH);         
        c.add(toolPanel,BorderLayout.WEST);   
        c.add(drawPanel,BorderLayout.CENTER);          
        c.add(colorPanel,BorderLayout.SOUTH);          

        for(int i=0; i<btns.length; ++i){   
            toolBar.add(btns[i]);   
            btns[i].addActionListener(this);   
        }   

        for(int i=0; i<menus.length; ++i)   
            menuBar1.add(menus[i]);   

        for(int i=0; i<fileItems.length; ++i)   
            fileMenu.add(fileItems[i]);   
        for(int i=0; i<editItems.length; ++i)   
            editMenu.add(editItems[i]);   
        for(int i=0; i<faceItems.length; ++i){   
            faceMenu.add(faceItems[i]);   
            faceGrp.add(faceItems[i]);   
        }   
        for(int i=0; i<helpItems.length; ++i)   
            helpMenu.add(helpItems[i]);   

        for(int i=0; i<menuItems.length; ++i)   
            for(int j=0; j<menuItems[i].length; ++j)   
                menuItems[i][j].addActionListener(this);   

        newBtn.setToolTipText("create a new picture");   
        openBtn.setToolTipText("open a picture");   
        saveBtn.setToolTipText("save the current picture");   
        unDoBtn.setToolTipText("undo an operation");   
        reDoBtn.setToolTipText("redo an operation");   
        clearBtn.setToolTipText("clear the picture");   
        abtBtn.setToolTipText("about painter");   

        setJMenuBar(menuBar1);   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        ImageIcon icon = new ImageIcon("res/painter.gif");   
        setIconImage(icon.getImage());   

        setSize(800, 600);   
        setVisible(true);   
    }   

    public void actionPerformed(ActionEvent e){   
        Object src = e.getSource();   
        if(src instanceof FaceMenuItem)   
            setFace(((FaceMenuItem)src).getFace());   
        else if(src==newf || src==newBtn){   
            filename = null;   
            drawPanel.newPicture();   
            setTitle("Painter--New");   
        }   
        else if(src==open || src==openBtn){   
            if(showDlg(JFileChooser.OPEN_DIALOG) != false)   
                drawPanel.open(filename);   
        }          
        else if(src==save || src==saveBtn){   
            if (filename!=null || showDlg(JFileChooser.SAVE_DIALOG)!=false)   
                drawPanel.save(filename);   
        }          
        else if(src == saveas){   
            if (showDlg(JFileChooser.SAVE_DIALOG) != false)   
                drawPanel.save(filename);   
        }   
        else if(src==unDo || src==unDoBtn)   
            drawPanel.unDo();   
        else if(src==reDo || src==reDoBtn)   
            drawPanel.reDo();   
        else if(src==clear || src==clearBtn)   
            drawPanel.clear();   
        else if(src==about || src==abtBtn)   
            new AboutDlg();   
        else if(src==exit) {   
            System.exit(0);   
        }   
    }   

    public void widgeChanged(Component widge){   
        if(widge == colorPanel)   
            toolPanel.setColor(colorPanel.getColor());   
        else if(widge == toolPanel)   
            drawPanel.setTool(toolPanel.getTool());   
    }   

    private boolean showDlg(int dlgType) {   
        JFileChooser chooser = new JFileChooser();   

        chooser.setDialogType(dlgType);   
        if((chooser.showDialog(null,null) != JFileChooser.APPROVE_OPTION))   
            return  false;   
        filename = chooser.getSelectedFile().getPath();   
        setTitle("Painter--" + filename);   
        return  true;   
    }   

    private void    setFace(String face){   
        try{   
            UIManager.setLookAndFeel(face);   
        }catch(Exception e){return;}   
        SwingUtilities.updateComponentTreeUI(toolBar);   
        SwingUtilities.updateComponentTreeUI(toolPanel);   
        SwingUtilities.updateComponentTreeUI(colorPanel);   
        SwingUtilities.updateComponentTreeUI(drawPanel);   
        SwingUtilities.updateComponentTreeUI(menuBar1);   
    }   

    public static void main(String args[]){   
        new Painter();   
    }   
}  

