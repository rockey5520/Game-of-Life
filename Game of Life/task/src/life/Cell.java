package life;

class Cell {
  private boolean state;

  public Cell() {
    this.state = false;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public void Die() {
    this.state = false;
  }

  public void Alive() {
    this.state = true;
  }

  public boolean isAlive() {
    return state;
  }
}