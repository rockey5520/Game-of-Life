package life;

import javax.swing.*;
import java.awt.*;

public class DrawUI extends JFrame {

  private JLabel generation;
  private JLabel alive;
  private JPanel jp;
  private final int boardSize;

  public DrawUI(int boardSize){
    super("Game of Life");
    this.boardSize = boardSize;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700, 800);
    setLocationRelativeTo(null);
    paintComponent();
    generation = new JLabel("GenerationLabel");
    generation.setBounds(5,5, 490, 15);

    alive = new JLabel("AliveLabel");
    alive.setBounds(5, 25, 490, 15);

    add(generation);
    add(alive);
    {add(jp);}

    setLayout(null);
    setVisible(true);
  }

  public void setGeneration(int genCount){
    generation.setText("Generation #"+genCount);
  }

  public void setAlive(int aliveCount){
    alive.setText("Alive: "+aliveCount);
  }

  public void paintComponent(){
    this.jp = new DrawPanel(boardSize);
    jp.setBounds(100,100,500,500);
    jp.setLayout(new BorderLayout());
    jp.setBackground(Color.GRAY);
    jp.setLayout(new BorderLayout());
  }

  public void paintComponent(Earth earth){
    //paintComponent();

    Graphics g = jp.getGraphics();
    var g2d = (Graphics2D) g;

    char[][] array = earth.getArray();
    for(int y=0; y<boardSize; y++){
      for(int x=0;x<boardSize; x++){
        if(array[y][x]==' '){
          g2d.setColor(Color.BLACK);
          int recHeight = getHeight()/boardSize;
          int recWidth = getWidth()/boardSize;
          g2d.fillRect(x*recHeight, y*recWidth, (y+1)*recWidth, (y+1)*recHeight);
        }
      }
      g2d.dispose();


      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      jp.updateUI();
      repaint();
    }
  }
}