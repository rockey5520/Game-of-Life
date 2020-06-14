package life;


class Universe {
  static private int[] shift = {-1, 0, 1};

  private int generation = 0;
  private int size;
  Cell[][] universe;

  public Universe(int size) {
    this.size = size;
    universe = new Cell[this.size][this.size];

    for (int i = 0; i < this.size; ++i) {
      for (int j = 0; j < this.size; ++j) {
        universe[i][j] = new Cell();
      }
    }
  }

  public int getSize() {
    return size;
  }

  public int getGeneration() {
    return generation;
  }

  public void setGeneration(int generation) {
    this.generation = generation;
  }

  public void increaseGeneration() {
    ++generation;
  }

  public Cell[][] getUniverse() {
    return universe;
  }

  public Cell[] at(int i) {
    return universe[i];
  }

  public Cell at(int i, int j) {
    return universe[i][j];
  }

  public byte countAliveNeighbours(int i, int j) {
    byte alive = 0;

    for (int i_idx : shift) {
      for (int j_idx : shift) {
        if (i_idx == j_idx && i_idx == 0) {
          continue;
        }

        int x = (i + i_idx) % size;
        if (x < 0) {
          x = size - 1;
        }

        int y = (j + j_idx) % size;
        if (y < 0) {
          y = size - 1;
        }
        if (universe[x][y].isAlive()) {
          ++alive;
        }
      }
    }

    return alive;
  }

  static void copy(Universe from, Universe to) {
    int size = from.getSize();
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        Cell cell = to.at(i, j);
        cell.setState(from.at(i, j).isAlive());
      }
    }
  }

  public int getNumberOfAliveCells() {
    int alive = 0;

    for (Cell[] row : universe) {
      for (Cell cell : row) {
        alive += cell.isAlive() ? 1 : 0;
      }
    }

    return alive;
  }
}