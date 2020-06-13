package life;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class Universe {
  private final List<BitSet> map;
  private int generation = 1;

  Universe(final int size, final long seed) {
    this(size);
    initialise(seed);
  }

  Universe(final int size) {
    map = new ArrayList<>(size);
    range(0, size).mapToObj(i -> new BitSet(size)).forEach(map::add);
  }

  Universe initialise(long seed) {
    final var random = new Random(seed);
    map.forEach(row -> range(0, map.size()).filter(i -> random.nextBoolean()).forEach(row::set));
    return this;
  }

  Universe initialise() {
    final var random = new Random();
    map.forEach(row -> range(0, map.size()).filter(i -> random.nextBoolean()).forEach(row::set));
    return this;
  }

  private String getRowAsString(BitSet row) {
    return range(0, map.size()).mapToObj(i -> row.get(i) ? "O" : " ").collect(joining());
  }

  boolean isLive(int row, int col) {
    return map.get(normalise(row)).get(normalise(col));
  }

  int getValue(int row, int col) {
    return isLive(row, col) ? 1 : 0;
  }

  int getNeighboursCount(int row, int col) {
    return getValue(row - 1, col - 1)
        + getValue(row - 1, col)
        + getValue(row - 1, col + 1)
        + getValue(row, col - 1)
        + getValue(row, col + 1)
        + getValue(row + 1, col - 1)
        + getValue(row + 1, col)
        + getValue(row + 1, col + 1);
  }

  int normalise(int i) {
    if (i == -1) {
      return map.size() - 1;
    }
    if (i == map.size()) {
      return 0;
    }
    return i;
  }

  public int size() {
    return map.size();
  }

  public void printMap() {
    map.stream().map(this::getRowAsString).forEach(System.out::println);
  }

  public void setCell(final int row, final int col, final boolean isLive) {
    map.get(row).set(col, isLive);
  }

  int getAliveCount() {
    return map.stream().mapToInt(BitSet::cardinality).sum();
  }

  int getGeneration() {
    return generation;
  }

  void nextGeneration() {
    List<BitSet> next = new ArrayList<>(map.size());

    for (int row = 0; row < map.size(); ++row) {
      final var nextRow = new BitSet(map.size());
      for (int col = 0; col < map.size(); ++col) {
        final int neighbours = getNeighboursCount(row, col);
        final var isLive = neighbours == 3 || neighbours == 2 && isLive(row, col);
        nextRow.set(col, isLive);
      }
      next.add(nextRow);
    }
    for (int i = 0; i < map.size(); ++i) {
      map.set(i, next.get(i));
    }
    ++generation;
  }
}