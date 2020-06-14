/*
package life;

public class GenerationAlgorithm {
  static Universe getNextGeneration(Universe currentUniverse) {
    Universe nextGeneration = new Universe(currentUniverse.size());

    for (int row = 0; row < currentUniverse.size(); ++row) {
      for (int col = 0; col < currentUniverse.size(); ++col) {
        final int neighbours = currentUniverse.getNeighboursCount(row, col);
        final var isLive = neighbours == 3 || neighbours == 2 && currentUniverse.isLive(row, col);
        nextGeneration.setCell(row, col, isLive);
      }
    }
    return nextGeneration;
  }
}*/
