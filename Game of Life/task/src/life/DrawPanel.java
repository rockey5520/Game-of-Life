package life;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawPanel extends JPanel {
  int size;
  public DrawPanel(int size){
    super();
    this.size = size;
  }

  private void doDrawing(Graphics g) {

    var g2d = (Graphics2D) g;
    for(int i=1; i<=size; i++) {
      g2d.drawLine(0, i*(getHeight()/size), getWidth(), i*(getHeight()/size));
      g2d.drawLine(i*(getWidth()/size), 0, i*(getWidth()/size), getHeight());
    }
  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    doDrawing(g);
  }
}