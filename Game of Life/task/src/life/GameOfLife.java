package life;


import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;


public class GameOfLife extends JFrame {
  private JPanel[][] cells;

  private final int defaultSpeed = 1000;
  private int evolutionSpeed = defaultSpeed;
  private boolean needToRestart = false;

  private JPanel info = new JPanel();
  private JPanel grid = new JPanel();
  private JPanel labels = new JPanel();

  private JLabel generationLabel = new JLabel();
  private JLabel aliveLabel = new JLabel();

  private JToggleButton play;
  private JButton restart;

  private JSlider speedControl;

  private Color aliveColor = Color.BLACK;

  private void initCells(int size) {
    cells = new JPanel[size][size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        cells[i][j] = new JPanel();
        cells[i][j].setBackground(Color.BLACK);
      }
    }
  }

  private void initGrid(int size) {
    grid.setLayout(new GridLayout(size, size, 1, 1));
    grid.setBackground(Color.DARK_GRAY);
    add(grid, BorderLayout.CENTER);
    grid.setPreferredSize(new Dimension(labels.getWidth(), getHeight()));

    for (JPanel[] row : cells) {
      for (JPanel cell : row) {
        grid.add(cell);
      }
    }
  }

  private void initLabels() {
    labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
    generationLabel.setName("GenerationLabel");
    aliveLabel.setName("AliveLabel");
    recalcLabels(0, 0);
    labels.add(generationLabel);
    labels.add(aliveLabel);
    info.add(labels);
  }

  public void init(int size) {
    initCells(size);
    initGrid(size);
  }

  public GameOfLife() {
    super("Game of Life");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setSize(1200, 800);
    setLayout(new BorderLayout());
    Dimension infoSize = new Dimension(260, getHeight());
    info.setSize(infoSize);
    info.setPreferredSize(infoSize);
    add(info, BorderLayout.WEST);
    initLabels();
    addButtons();
    addColorChanger();
    setVisible(true);
  }

  private void remainOnlySwatches(JColorChooser colorChooser) {
    for (AbstractColorChooserPanel accp : colorChooser.getChooserPanels()) {
      if (!accp.getDisplayName().equals("Swatches")) {
        colorChooser.removeChooserPanel(accp);
      }
    }
  }

  private void addColorChanger() {
    JColorChooser colorChooser = new JColorChooser();
    colorChooser.setPreferredSize(new Dimension(info.getWidth(), 200));
    colorChooser.setPreviewPanel(new JPanel());
    remainOnlySwatches(colorChooser);
    colorChooser.getSelectionModel().addChangeListener(e ->
        aliveColor = colorChooser.getSelectionModel().getSelectedColor());

    info.add(colorChooser);
  }

  private void addButtons() {
    JPanel buttons = new JPanel();

    play = new JToggleButton("▶");
    play.setName("PlayToggleButton");
    play.addActionListener(e -> {
      if (play.isSelected()) {
        evolutionSpeed = 0;
      } else {
        evolutionSpeed = defaultSpeed;
      }
    });
    buttons.add(play);

    restart = new JButton("Restart");
    restart.setName("ResetButton");
    restart.addActionListener(event -> needToRestart = true);
    buttons.add(restart);

    speedControl = new JSlider(1, 20);
    buttons.add(speedControl);
    speedControl.setValue(1);
    speedControl.setMajorTickSpacing(10);
    speedControl.setMinorTickSpacing(1);
    speedControl.setPaintTicks(true);
    speedControl.setPaintLabels(true);
    buttons.setPreferredSize(new Dimension(info.getWidth(), 100));
    info.add(buttons);
  }

  public void processUniverse(Universe universe) {
    recalcLabels(universe.getGeneration(), universe.getNumberOfAliveCells());
    repaintGrid(universe.getUniverse());
  }

  private void recalcLabels(int generationNumber, int aliveNumber) {
    String generation = "Generation #";
    generationLabel.setText(generation + generationNumber);
    String alive = "Alive: ";
    aliveLabel.setText(alive + aliveNumber);
  }

  private void repaintGrid(Cell[][] cells) {
    int size = cells.length;

    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        Cell cell = cells[i][j];
        Color color = cell.isAlive() ? aliveColor : Color.WHITE;
        this.cells[i][j].setBackground(color);
      }
    }
  }

  public int getEvolutionSpeed() {
    if (!play.isSelected()) {
      evolutionSpeed = defaultSpeed / speedControl.getValue();
    }
    return evolutionSpeed;
  }

  public boolean isNeedToRestart() {
    return needToRestart;
  }

  public void setNeedToRestart(boolean needToRestart) {
    this.needToRestart = needToRestart;
  }
}