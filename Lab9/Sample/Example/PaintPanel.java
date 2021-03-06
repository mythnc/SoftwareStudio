import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.BufferedImage;

///////////////////////////////////////////////////////////// PaintPanel
class PaintPanel extends JPanel implements MouseListener, 
      MouseMotionListener {
          //--- Public constants used to specify shape being drawn.          
          public static final int NONE      = 0;
          public static final int LINE      = 1;
          public static final int RECTANGLE = 2;
          public static final int CIRCLE    = 3;

          //--- Variables to store the current figure info
          private int _shape = NONE;
          private int _currentStartX = 0;  // where mouse first pressed
          private int _currentStartY = 0;
          private int _currentEndX   = 0;  // where dragged to or released
          private int _currentEndY   = 0;

          //--- BufferedImage to store the underlying saved painting.
          //    Will be initialized first time paintComponent is called.
          private BufferedImage _bufImage = null;

          //--- Private constant for size of paint area.
          private static final int SIZE = 600; // size of paint area


          //====================================================== constructor
          public PaintPanel() {
              setPreferredSize(new Dimension(SIZE, SIZE));
              setBackground(Color.white);
              //--- Add the mouse listeners.
              this.addMouseListener(this); 
              this.addMouseMotionListener(this);
          }//endconstructor


          //========================================================= setShape
          public void setShape(int shape) {
              //--- Provided so users can set the shape.
              _shape = shape;
          }//end setShape


          //=================================================== paintComponent
          public void paintComponent(Graphics g) {
              super.paintComponent(g);

              Graphics2D g2 = (Graphics2D)g;  // downcast to Graphics2D
              if (_bufImage == null) {
                  //--- This is the first time, initialize _bufImage
                  int w = this.getWidth();
                  int h = this.getHeight();
                  _bufImage = (BufferedImage)this.createImage(w, h);
                  Graphics2D gc = _bufImage.createGraphics();
                  gc.setColor(Color.white);
                  gc.fillRect(0, 0, w, h); // fill in background
              }
              g2.drawImage(_bufImage, null, 0, 0);  // draw previous shapes

              drawCurrentShape(g2);
          }//end paintComponent


          //================================================= drawCurrentShape
          private void drawCurrentShape(Graphics2D g2) {
              //--- Draws current shape on a graphics context, either
              //    on the context passed to paintComponent, or the
              //    context for the BufferedImage.
              switch (_shape) {
                  case NONE  :
                      break;

                  case CIRCLE:
                      g2.fillOval(_currentStartX, _currentStartY,
                              _currentEndX - _currentStartX, 
                              _currentEndY - _currentStartY);
                      break;

                  case RECTANGLE:
                      g2.fillRect(_currentStartX, _currentStartY,
                              _currentEndX - _currentStartX, 
                              _currentEndY - _currentStartY);
                      break;

                  default:  // should never happen
                      g2.drawString("Huh?", 10, 20);
                      break;
              }
          }//end paintComponent

          //===================================================== mousePressed
          public void mousePressed(MouseEvent e) {
              _currentStartX = e.getX(); // save x coordinate of the click
              _currentStartY = e.getY(); // save y
              _currentEndX   = _currentStartX;   // set end to same pixel
              _currentEndY   = _currentStartY;
          }//end mousePressed

          //===================================================== mouseDragged
          public void mouseDragged(MouseEvent e) {
              _currentEndX = e.getX();   // save new x and y coordinates
              _currentEndY = e.getY();
              this.repaint();            // show new shape
          }//end mouseDragged

          //==================================================== mouseReleased
          public void mouseReleased(MouseEvent e) {
              // This will save the shape that has been dragged by
              // drawing it onto the bufferedImage where all shapes
              // are written.
              _currentEndX = e.getX(); // save ending coordinates
              _currentEndY = e.getY();

              //--- Draw the current shape onto the buffered image.
              Graphics2D grafarea = _bufImage.createGraphics();
              drawCurrentShape(grafarea);

              this.repaint();
          }//end mouseReleased

          //========================================== ignored mouse listeners
          public void mouseMoved   (MouseEvent e) {}
          public void mouseEntered (MouseEvent e) {}
          public void mouseExited  (MouseEvent e) {}
          public void mouseClicked (MouseEvent e) {}
}//endclass PaintPanel
