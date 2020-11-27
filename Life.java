public class Life implements ILife {
  
  public static void main(String[] args) {
    Life game = new Life(new String[] {  
                                      "     ",
                                      "     ",
                                      " *** ",
                                      "     ",
                                      "     " });
  
    game.print();
    game = (Life) game.nextGeneration();
    game.print();
  }


  public Life(int dim_x, int dim_y) {
    this.dim_x = dim_x;
    this.dim_y = dim_y;
    cells = new CellValue[dim_x][dim_y];
    nukeAll();
  }

  public Life(String[] setup) {
    this( setup[0].length(), setup.length);
    for (int y = 0; y < dim_y; y++){
      for (int x = 0; x < dim_x; x++){
        if (setup[y].charAt(x) != ' ') {
          setAlive(x,y);
        }
      }
    }
  }


  @Override
  public void nukeAll() {
    for( int y = 0; y < dim_y; y++){
      for( int x = 0; x < dim_x; x++){
         setDead(x,y);
      }
    }
  }

  @Override
  public void setAlive(int x, int y) {
    cells[x][y] = CellValue.Alive;
  }

  @Override
  public void setDead(int x, int y) {
    cells[x][y] = CellValue.Dead;
  }

  @Override
  public boolean isAlive(int x, int y) {
    return cells[x][y] == CellValue.Alive;
  }
  @Override
  public boolean isDead(int x, int y) {
    return cells[x][y] == CellValue.Dead;
  }

  @Override
  public ILife nextGeneration() {
    Life newGeneration = new Life( dim_x, dim_y);
    for (int y=0; y< dim_y; y++) {
      for(int x=0; x < dim_x; x++) {
        int neighborsAlive = calculateNeighborsAlive(x,y);
        if( neighborsAlive == 3) {
          newGeneration.setAlive(x,y);
        }
        if( neighborsAlive < 2){
          newGeneration.setDead(x,y);
        }
        if( isAlive(x,y) && (neighborsAlive == 2 || neighborsAlive == 3)){
          newGeneration.setAlive(x,y);
        }
        if( isAlive(x,y) && neighborsAlive > 3) {
           newGeneration.setDead(x,y);
        }
     }
  }
  return newGeneration;
}

  public void print() {
    for( int x= 0; x < dim_x; x++) {
      for( int y = 0; y < dim_y; y++) {
        if( cells[x][y] == CellValue.Alive) {
          System.out.print( "*" );
        }else{
          System.out.print( " " );
        }
      }
      System.out.print( "\n");
    }
  }

  public int calculateNeighborsAlive( int x, int y) {
    int minX = x-1;
    int maxX = x+1;
    int minY = y-1;
    int maxY = y+1;

    if( minX < 0) minX = 0;
    if( maxX >= dim_x) maxX = dim_x-1;
    if( minY < 0) minY = 0;
    if( maxY >= dim_x) maxY = dim_y-1;

    int sum = 0;
    for( int dx = minX; dx <= maxX; dx++){
      for( int dy = minY; dy <= maxY; dy++){
        if( dx != x || dy != y) {
          if( cells [dx][dy] == CellValue.Alive ){
            sum++;
          }
        }
      }
    }
    return sum;
  }

  private final int dim_x;
  private final int dim_y;

  public enum CellValue {Dead, Alive}
  private final CellValue[][] cells;
}