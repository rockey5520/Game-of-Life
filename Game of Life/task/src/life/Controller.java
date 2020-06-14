package life;

public class Controller {
  Universe universe;
  GameOfLife gameOfLife;

  public Controller(Universe universe, GameOfLife gameOfLife) {
    this.universe = universe;
    UniverseGenerator.createUniverse(this.universe);
    this.gameOfLife = gameOfLife;
    this.gameOfLife.init(this.universe.getSize());
  }

  public void showUniverse() {
    gameOfLife.processUniverse(universe);
  }

  public void evolve() {
    try {
      Thread.sleep(gameOfLife.getEvolutionSpeed());
    } catch (InterruptedException ignored) {
    }
    if (gameOfLife.isNeedToRestart()) {
      UniverseGenerator.createUniverse(universe);
      gameOfLife.setNeedToRestart(false);
    }

    if (gameOfLife.getEvolutionSpeed() != 0) {
      UniverseGenerator.evolve(universe);
      universe.increaseGeneration();
      gameOfLife.processUniverse(universe);
    }
  }
}

