package life;

import java.util.Random;

public class Earth {
  private char[][] array;
  private int earthSize;
  public int aliveCount;
  private Random rand;

  public Earth(int earthSize, long seed){
    this.array = new char[earthSize][earthSize];
    this.earthSize = earthSize;
    this.rand = new Random(seed);
    this.initialize();
  }

  public Earth(int earthSize){
    this.array = new char[earthSize][earthSize];
    this.earthSize = earthSize;
  }

  public void initialize(){
    aliveCount = 0;
    for(int y=0; y<earthSize; y++){
      for(int x=0;x<earthSize; x++){
        if(rand.nextBoolean()){
          array[y][x] = 'O';
          aliveCount++;
        }
        else{
          array[y][x] =  ' ';
        }
      }
    }
  }

  private void setArrayPoint(int y, int x, char value){
    this.array[y][x] = value;
  }

  public Earth evolute(){
    Earth nextGen = new Earth(this.earthSize);
    for(int y=0; y<earthSize; y++){
      for(int x=0;x<earthSize; x++){
        Boolean alive = deadOrAlive(y,x);
        int neighbours = countNeighbours(x,y);
        if((neighbours==2 && alive)||neighbours==3){
          nextGen.setArrayPoint(y,x,'O');
          nextGen.aliveCount++;
        }
        else{
          nextGen.setArrayPoint(y,x,' ');
        }
      }
    }
    return nextGen;
  }

  public int getAliveCount() {
    return aliveCount;
  }

  private Boolean deadOrAlive(int y, int x){
    if(array[y][x] == 'O'){
      return true;
    }
    else{
      return false;
    }
  }

  private int countNeighbours(int y, int x){
    int neighbours = 0;
    if(this.array[proper(x-1)][proper(y-1)]=='O'){
      neighbours +=1;
    }
    if(this.array[proper(x-1)][proper(y)]=='O'){
      neighbours +=1;
    }
    if(this.array[proper(x-1)][proper(y+1)]=='O'){
      neighbours +=1;
    }
    if(this.array[proper(x)][proper(y-1)]=='O'){
      neighbours +=1;
    }
    if(this.array[proper(x)][proper(y+1)]=='O'){
      neighbours +=1;
    }
    if(this.array[proper(x+1)][proper(y-1)]=='O'){
      neighbours +=1;
    }
    if(this.array[proper(x+1)][proper(y)]=='O'){
      neighbours +=1;
    }
    if(this.array[proper(x+1)][proper(y+1)]=='O'){
      neighbours +=1;
    }
    return neighbours;
  }

  private int proper(int input){
    if(input<0) {
      input = earthSize - Math.abs(input);
    }
    if (input>=earthSize){
      input = input - earthSize;
    }
    return input;
  }

  public char[][] getArray(){
    return array;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(char[] row:this.array){
      sb.append(String.copyValueOf(row));
      sb.append("\n");
    }
    return sb.toString();
  }
}