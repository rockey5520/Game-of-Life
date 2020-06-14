package life;


import java.util.Random;

abstract class UniverseGenerator {

  static boolean fillCell(Random randomizer) {
    return randomizer.nextBoolean();
  }

  static void createUniverse(Universe universe) {
    Random randomize = new Random();
    universe.setGeneration(0);
    int size = universe.getSize();
    for (int i = 0; i < size; ++i) {
      Cell[] cells = universe.at(i);
      for (Cell cell : cells) {
        cell.setState(fillCell(randomize));
      }
    }
  }

  static void evolve(Universe universe) {
    int size = universe.getSize();
    Universe tmp = new Universe(size);

    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        Cell cell = universe.at(i, j);
        byte aliveNeighbours = universe.countAliveNeighbours(i, j);

        if (cell.isAlive() && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
          tmp.at(i, j).Die();
        } else if (!cell.isAlive() && aliveNeighbours == 3) {
          tmp.at(i, j).Alive();
        } else {
          tmp.at(i, j).setState(universe.at(i, j).isAlive());
        }
      }
    }

    Universe.copy(tmp, universe);
  }
}