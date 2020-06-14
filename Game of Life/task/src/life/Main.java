package life;

import static java.lang.System.in;

import java.io.IOException;


import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    //stage 4
    Controller controller = new Controller(new Universe(50), new GameOfLife());
    controller.showUniverse();
    while (true) {
      controller.evolve();
    }
/*//stage 3
  Scanner scanner = new Scanner(in);
    int universeSize = scanner.nextInt();
    final int generations = 11;
    scanner.close();

    Universe universe = new Universe(universeSize).initialise();

    for (int i = 0; i < generations; ++i) {
      System.out.printf("Generation #%d%nAlive: %d%n", universe.getGeneration(),
          universe.getAliveCount());
      universe.printMap();
      universe.nextGeneration();
      Thread.sleep(1000);
      clearScreen();

    }
  }

  static void clearScreenOld() {
    try {
      if (System.getProperty("os.name").contains("Windows"))
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      else
        Runtime.getRuntime().exec("clear");
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
    // stage 2
    Scanner scanner = new Scanner(in);
    final int UniverseSize = scanner.nextInt();
    final long seed = scanner.nextLong();
    final int generations = scanner.nextInt();
    scanner.close();

    Universe universe = new Universe(UniverseSize, seed);
    for (int i = 0; i < generations; ++i) {
      universe = getNextGeneration(universe);
    }
    universe.print();



//*
/stage 1
    Scanner scanner = new Scanner(in);
    int arraySize = scanner.nextInt();
    int seed = scanner.nextInt();
    Random random = new Random(seed);

    for (int i = 0; i < arraySize; i++) {
      for (int j = 0; j < arraySize; j++) {
        if (random.nextBoolean()) {
          System.out.print("O");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }*/
  }
}



