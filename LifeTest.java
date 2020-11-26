import org.junit.Test;
import static org.junit.Assert.*;

public class LifeTest {

  @Test
  public void calculateNeighborsAlive() {
    Life firstGen = new Life(5, 5);
    firstGen.setAlive(1,1);
    firstGen.setAlive(1,2);
    firstGen.setAlive(1,3);
    firstGen.setAlive(2,1);
    firstGen.setAlive(2,2);
    firstGen.setAlive(2,3);
    firstGen.setAlive(3,1);
    firstGen.setAlive(3,2);
    firstGen.setAlive(3,3);

    firstGen.print();

    assertEquals( 1,firstGen.calculateNeighborsAlive(0,0));
    assertEquals( 2,firstGen.calculateNeighborsAlive(1,0));
    assertEquals( 3,firstGen.calculateNeighborsAlive(2,0));
    assertEquals( 2,firstGen.calculateNeighborsAlive(3,0));
    assertEquals( 1,firstGen.calculateNeighborsAlive(4,0));


    assertEquals( 2,firstGen.calculateNeighborsAlive(0,1));
    assertEquals( 3,firstGen.calculateNeighborsAlive(1,1));
    assertEquals( 5,firstGen.calculateNeighborsAlive(2,1));
    assertEquals( 3,firstGen.calculateNeighborsAlive(3,1));
    assertEquals( 2,firstGen.calculateNeighborsAlive(4,1));


    assertEquals( 3,firstGen.calculateNeighborsAlive(0,2));
    assertEquals( 5,firstGen.calculateNeighborsAlive(1,2));
    assertEquals( 8,firstGen.calculateNeighborsAlive(2,2));
    assertEquals( 5,firstGen.calculateNeighborsAlive(3,2));
    assertEquals( 3,firstGen.calculateNeighborsAlive(4,2));

    Life nextGen = (Life) firstGen.nextGeneration();
    nextGen.print();
}
    

    @Test
    public void createNewCell() {
        
        // Arrange: drei lebende Zellen
        Life firstGen = new Life(5, 5);
        firstGen.setAlive(2, 0);
        firstGen.setAlive(2, 1);
        firstGen.setAlive(2, 2);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = firstGen.nextGeneration();

        // Assert: Rasterpunkt mit drei Nachbarn sollte jetzt leben
        assertTrue(nextGen.isAlive(1, 1));
        assertTrue(nextGen.isAlive(3, 1));
    }


    @Test
    public void destroyLonelyCell() {

       // Arrange: vier lebende Zellen
      Life firstGen = new Life(5, 5);
        firstGen.setAlive(2, 2);
        firstGen.setAlive(2, 4);
     
        // Act: Berechnung der Folgegeneration
        ILife nextGen = firstGen.nextGeneration();

        // Assert: Rasterpunkt ohne Nachbarn sollte jetzt sterben
        assertTrue(nextGen.isDead(2, 2));
        assertTrue(nextGen.isDead(2, 4));

    }

    @Test
    public void keepAliveCell() {
         // Arrange: drei lebende Zellen
        Life firstGen = new Life(5, 5);
        firstGen.setAlive(1, 1);
        firstGen.setAlive(2, 2);
        firstGen.setAlive(3, 3);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = firstGen.nextGeneration();

        // Assert: Rasterpunkt bleibt am Leben
        assertTrue(nextGen.isAlive(2, 2));

    }


    @Test
    public void destroyCrowdedCell() {

         // Arrange: drei lebende Zellen
        Life firstGen = new Life(5, 5);
        firstGen.setAlive(1, 1);
        firstGen.setAlive(1, 2);
        firstGen.setAlive(1, 3);
        firstGen.setAlive(2, 1);
        firstGen.setAlive(2, 2);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = firstGen.nextGeneration();

        // Assert: Rasterpunkt stirbt wegen Überbevölkerung
        assertTrue(nextGen.isDead(1, 2));
        assertTrue(nextGen.isDead(2, 2));
    }


}
