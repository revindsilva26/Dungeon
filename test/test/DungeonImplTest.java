package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import dungeon.model.Direction;
import dungeon.model.Dungeon;
import dungeon.model.DungeonImpl;
import dungeon.model.HealthStatus;
import dungeon.model.Position;
import dungeon.randomizer.IRandomNumberGenerator;
import dungeon.randomizer.PseudoRandomNumberGenerator;
import dungeon.randomizer.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test functionality of Dungeon.
 */
public class DungeonImplTest {

  private Dungeon dungeonWrap;
  private Dungeon dungeonNotWrap;

  /**
   * Set up objects for testing.
   */
  @Before
  public void setUp() throws Exception {
    IRandomNumberGenerator rng = new PseudoRandomNumberGenerator();
    dungeonWrap = new DungeonImpl(true,
            7,
            6,
            11,
            30,
            10,
            rng);
    dungeonNotWrap = new DungeonImpl(false,
            7,
            6,
            10,
            50,
            10,
            rng);
  }

  /**
   * Test if exception thrown when invalid
   * no. of monsters given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionForInvalidMonsters() {
    IRandomNumberGenerator rng = new RandomNumberGenerator();
    Dungeon dungeon1 = new DungeonImpl(false,
            7,
            6,
            1000,
            50,
            -1,
            rng);
  }

  /**
   * Test if exception thrown when interconnectivity
   * is not in range required.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionForBadInterconnectivity() {
    IRandomNumberGenerator rng = new RandomNumberGenerator();
    Dungeon dungeon1 = new DungeonImpl(false,
            7,
            6,
            1000,
            50,
            20,
            rng);
  }

  /**
   * Test if exception thrown when rows/columns
   * is not in range required.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionForBadRowsOrColumns() {
    IRandomNumberGenerator rng = new RandomNumberGenerator();
    Dungeon dungeon1 = new DungeonImpl(false,
            4,
            6,
            10,
            50,
            20,
            rng);
  }

  /**
   * Test if exception thrown when interconnectivity
   * is not in range required.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionForBadPercentTreasures() {
    IRandomNumberGenerator rng = new RandomNumberGenerator();
    Dungeon dungeon1 = new DungeonImpl(false,
            7,
            6,
            10,
            500,
            25,
            rng);
  }

  /**
   * Test if correct values of start generated.
   */
  @Test
  public void getStart() {
    assertEquals(5, dungeonNotWrap.getStart().getRow());
    assertEquals(3, dungeonNotWrap.getStart().getColumn());

    assertEquals(3, dungeonWrap.getStart().getRow());
    assertEquals(3, dungeonWrap.getStart().getColumn());
  }

  /**
   * Test if correct values of end generated.
   */
  @Test
  public void getEnd() {
    assertEquals(0, dungeonNotWrap.getEnd().getRow());
    assertEquals(4, dungeonNotWrap.getEnd().getColumn());

    assertEquals(6, dungeonWrap.getEnd().getRow());
    assertEquals(1, dungeonWrap.getEnd().getColumn());
  }

  /**
   * Test if grid is correct.
   */
  @Test
  public void getDungeonGrid() {
    assertEquals("[[Tunnel, #0, T: [], E: [[1, EAST], " +
                    "[6, SOUTH]], W: [], M: Otyugh - Dead, Cave, " +
                    "#1, T: [RUBY, DIAMOND, RUBY], E: [[0, WEST], " +
                    "[2, EAST], [7, SOUTH]], W: [], M: Otyugh - Alive, " +
                    "Cave, #2, T: [], E: [[1, WEST], [3, EAST], [8, SOUTH]], " +
                    "W: [CROOKEDARROW, CROOKEDARROW], M: Otyugh - " +
                    "Dead, Cave, #3, T: [SAPPHIRE, DIAMOND, RUBY], " +
                    "E: [[2, WEST], [4, EAST], [9, SOUTH]], W: [], " +
                    "M: Otyugh - Dead, Cave, #4, T: [], E: [[3, WES" +
                    "T], [5, EAST], [10, SOUTH]], W: [], M: Otyugh " +
                    "- Alive, Tunnel, #5, T: [], E: [[4, WEST], " +
                    "[11, SOUTH]], W: [], M: Otyugh - Dead], [Cave," +
                    " #6, T: [RUBY, SAPPHIRE, DIAMOND], E: [[0, NORTH" +
                    "], [7, EAST], [12, SOUTH]], W: [], M: Otyugh -" +
                    " Dead, Cave, #7, T: [], E: [[1, NORTH], [6, WEST" +
                    "], [13, SOUTH]], W: [CROOKEDARROW], M: Otyugh - " +
                    "Alive, Cave, #8, T: [], E: [[2, NORTH], [9, EAST]" +
                    ", [14, SOUTH]], W: [CROOKEDARROW, CROOKEDARROW, " +
                    "CROOKEDARROW], M: Otyugh - Dead, Cave, #9, T: [RU" +
                    "BY], E: [[3, NORTH], [8, WEST], [15, SOUTH]], W: [" +
                    "CROOKEDARROW, CROOKEDARROW], M: Otyugh - Dead, Tun" +
                    "nel, #10, T: [], E: [[4, NORTH], [16, SOUTH]], W: [" +
                    "], M: Otyugh - Dead, Tunnel, #11, T: [], E: [[5, NOR" +
                    "TH], [17, SOUTH]], W: [], M: Otyugh - Dead], [Tunnel," +
                    " #12, T: [], E: [[6, NORTH], [18, SOUTH]], W: [], M: " +
                    "Otyugh - Dead, Cave, #13, T: [], E: [[7, NORTH], " +
                    "[14, EAST], [19, SOUTH]], W: [CROOKEDARROW, " +
                    "CROOKEDARROW, CROOKEDARROW], M: Otyugh - Alive, " +
                    "Cave, #14, T: [RUBY], E: [[8, NORTH], [13, WEST], " +
                    "[20, SOUTH]], W: [CROOKEDARROW, CROOKEDARROW, CROOK" +
                    "EDARROW], M: Otyugh - Alive, Tunnel, #15, T: [], " +
                    "E: [[9, NORTH], [21, SOUTH]], W: [CROOKEDARROW, CR" +
                    "OOKEDARROW, CROOKEDARROW], M: Otyugh - Dead, " +
                    "Tunnel, #16, T: [], E: [[10, NORTH], [22, SOUTH]], " +
                    "W: [CROOKEDARROW, CROOKEDARROW], M: Otyugh - " +
                    "Dead, Tunnel, #17, T: [], E: [[11, NORTH], [23, " +
                    "SOUTH]], W: [], M: Otyugh - Dead], [Tunnel, #18, " +
                    "T: [], E: [[12, NORTH], [24, SOUTH]], W: [], M: " +
                    "Otyugh - Dead, Tunnel, #19, T: [], E: [[13, NORTH]," +
                    " [25, SOUTH]], W: [], M: Otyugh - Dead, Cave, #20, " +
                    "T: [SAPPHIRE, SAPPHIRE], E: [[14, NORTH], [21, EAST" +
                    "], [26, SOUTH]], W: [CROOKEDARROW, CROOKEDARROW], " +
                    "M: Otyugh - Alive, Cave, #21, T: [SAPPHIRE, RUBY, " +
                    "RUBY], E: [[15, NORTH], [20, WEST], [27, SOUTH]], " +
                    "W: [CROOKEDARROW, CROOKEDARROW, CROOKEDARROW], M: " +
                    "Otyugh - Alive, Cave, #22, T: [], E: [[16, NORTH], " +
                    "[23, EAST], [28, SOUTH]], W: [CROOKEDARROW, CROOK" +
                    "EDARROW, CROOKEDARROW], M: Otyugh - Dead, Cave," +
                    " #23, T: [], E: [[17, NORTH], [22, WEST], [29, S" +
                    "OUTH]], W: [CROOKEDARROW], M: Otyugh - Dead], [T" +
                    "unnel, #24, T: [], E: [[18, NORTH], [30, SOUTH]], " +
                    "W: [], M: Otyugh - Dead, Cave, #25, T: [], E: [[" +
                    "19, NORTH], [26, EAST], [31, SOUTH]], W: [], M: " +
                    "Otyugh - Alive, Cave, #26, T: [], E: [[20, NORTH]," +
                    " [25, WEST], [32, SOUTH]], W: [CROOKEDARROW, " +
                    "CROOKEDARROW, CROOKEDARROW], M: Otyugh - Alive, " +
                    "Tunnel, #27, T: [], E: [[21, NORTH], [33, SOUTH]], " +
                    "W: [], M: Otyugh - Dead, Tunnel, #28, T: [], " +
                    "E: [[22, NORTH], [34, SOUTH]], W: [], M: Otyugh - " +
                    "Dead, Tunnel, #29, T: [], E: [[23, NORTH], [35, SOUT" +
                    "H]], W: [CROOKEDARROW], M: Otyugh - Dead], [Tunnel, " +
                    "#30, T: [], E: [[24, NORTH], [36, SOUTH]], W: [], M: " +
                    "Otyugh - Dead, Tunnel, #31, T: [], E: [[25, NORTH], [3" +
                    "7, SOUTH]], W: [CROOKEDARROW], M: Otyugh - Dead, Tunnel" +
                    ", #32, T: [], E: [[26, NORTH], [38, SOUTH]], W: " +
                    "[CROOKEDARROW], M: Otyugh - Dead, Cave, #33, T: " +
                    "[DIAMOND], E: [[27, NORTH], [34, EAST], [39, SOUTH]], " +
                    "W: [], M: Otyugh - Dead, Cave, #34, T: [], E: " +
                    "[[28, NORTH], [33, WEST], [40, SOUTH]], W: [], " +
                    "M: Otyugh - Dead, Tunnel, #35, T: [], E: [[29, " +
                    "NORTH], [41, SOUTH]], W: [CROOKEDARROW], M: " +
                    "Otyugh - Dead], [Tunnel, #36, T: [], E: [[30, " +
                    "NORTH], [37, EAST]], W: [CROOKEDARROW, CROOKED" +
                    "ARROW], M: Otyugh - Dead, Cave, #37, T: [RUBY, " +
                    "SAPPHIRE], E: [[31, NORTH], [36, WEST], [38, EAST]]" +
                    ", W: [CROOKEDARROW, CROOKEDARROW], M: Otyugh -" +
                    " Dead, Tunnel, #38, T: [], E: [[32, NORTH], [37, " +
                    "WEST]], W: [], M: Otyugh - Dead, Tunnel, #39, " +
                    "T: [], E: [[33, NORTH], [40, EAST]], W: [CROOKEDA" +
                    "RROW], M: Otyugh - Dead, Tunnel, #40, T: [], E: " +
                    "[[34, NORTH], [39, WEST]], W: [CROOKEDARROW], " +
                    "M: Otyugh - Dead, Cave, #41, T: [RUBY, RUBY], " +
                    "E: [[35, NORTH]], W: [], M: Otyugh - Alive]]",
            dungeonNotWrap.getDungeonGrid().toString());

    assertEquals("[[Cave, #0, T: [], E: [[1, EAST], [5, WEST], " +
                    "[6, SOUTH], [36, NORTH]], W: [], M: Otyugh - Dead, " +
                    "Cave, #1, T: [DIAMOND], E: [[0, WEST], [2, EAST], " +
                    "[7, SOUTH], [37, NORTH]], W: [], M: Otyugh - Dead, " +
                    "Cave, #2, T: [], E: [[1, WEST], [3, EAST], [8, SOUTH]" +
                    ", [38, NORTH]], W: [], M: Otyugh - Alive, Cave, " +
                    "#3, T: [], E: [[2, WEST], [4, EAST], [9, SOUTH]," +
                    " [39, NORTH]], W: [], M: Otyugh - Dead, Cave, " +
                    "#4, T: [RUBY, DIAMOND], E: [[3, WEST], [10, SOU" +
                    "TH], [40, NORTH]], W: [CROOKEDARROW], M: Otyugh" +
                    " - Alive, Cave, #5, T: [], E: [[0, EAST], [11, " +
                    "SOUTH], [41, NORTH]], W: [], M: Otyugh - Dead], " +
                    "[Tunnel, #6, T: [], E: [[0, NORTH], [12, SOUTH]]" +
                    ", W: [CROOKEDARROW], M: Otyugh - Dead, Tunnel, " +
                    "#7, T: [], E: [[1, NORTH], [13, SOUTH]], W: [], " +
                    "M: Otyugh - Dead, Tunnel, #8, T: [], E: [[2, NORT" +
                    "H], [14, SOUTH]], W: [], M: Otyugh - Dead, Tunnel," +
                    " #9, T: [], E: [[3, NORTH], [15, SOUTH]], W: [CROOK" +
                    "EDARROW, CROOKEDARROW], M: Otyugh - Dead, Cave, #10," +
                    " T: [], E: [[4, NORTH], [11, EAST], [16, SOUTH]], W:" +
                    " [], M: Otyugh - Alive, Cave, #11, T: [], E: [[5, NOR" +
                    "TH], [10, WEST], [17, SOUTH]], W: [], M: Otyugh - Dea" +
                    "d], [Tunnel, #12, T: [], E: [[6, NORTH], [18, SOUTH]]," +
                    " W: [], M: Otyugh - Dead, Tunnel, #13, T: [], E: [[7, " +
                    "NORTH], [19, SOUTH]], W: [], M: Otyugh - Dead, Tunnel, " +
                    "#14, T: [], E: [[8, NORTH], [20, SOUTH]], W: [CROOKEDARR" +
                    "OW], M: Otyugh - Dead, Tunnel, #15, T: [], E: [[9, NORTH" +
                    "], [21, SOUTH]], W: [], M: Otyugh - Dead, Cave, #16, T: [" +
                    "], E: [[10, NORTH], [17, EAST], [22, SOUTH]], W: [], M: " +
                    "Otyugh - Dead, Cave, #17, T: [], E: [[11, NORTH], [16, W" +
                    "EST], [23, SOUTH]], W: [], M: Otyugh - Alive], [Cave, #" +
                    "18, T: [SAPPHIRE, RUBY], E: [[12, NORTH], [19, EAST], " +
                    "[24, SOUTH]], W: [], M: Otyugh - Dead, Cave, #19, T: []" +
                    ", E: [[13, NORTH], [18, WEST], [20, EAST], [25, SOUTH]]" +
                    ", W: [], M: Otyugh - Dead, Cave, #20, T: [DIAMOND], E: " +
                    "[[14, NORTH], [19, WEST], [21, EAST], [26, SOUTH]], W: " +
                    "[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW], M: Otyugh - " +
                    "Dead, Cave, #21, T: [], E: [[15, NORTH], [20, WEST], [22" +
                    ", EAST], [27, SOUTH]], W: [CROOKEDARROW, CROOKEDARROW], M" +
                    ": Otyugh - Dead, Cave, #22, T: [], E: [[16, NORTH], [21" +
                    ", WEST], [28, SOUTH]], W: [], M: Otyugh - Dead, Tunnel, " +
                    "#23, T: [], E: [[17, NORTH], [29, SOUTH]], W: [], M: Oty" +
                    "ugh - Dead], [Tunnel, #24, T: [], E: [[18, NORTH], [30, " +
                    "SOUTH]], W: [CROOKEDARROW, CROOKEDARROW], M: Otyugh - Dea" +
                    "d, Cave, #25, T: [RUBY, DIAMOND, DIAMOND], E: [[19, NORTH" +
                    "], [26, EAST], [31, SOUTH]], W: [CROOKEDARROW, CROOKEDARR" +
                    "OW, CROOKEDARROW], M: Otyugh - Dead, Cave, #26, T: [], E:" +
                    " [[20, NORTH], [25, WEST], [32, SOUTH]], W: [], M: Otyugh " +
                    "- Dead, Cave, #27, T: [], E: [[21, NORTH], [28, EAST], " +
                    "[33, SOUTH]], W: [], M: Otyugh - Alive, Cave, #28, T: []" +
                    ", E: [[22, NORTH], [27, WEST], [34, SOUTH]], W: [], M: " +
                    "Otyugh - Alive, Tunnel, #29, T: [], E: [[23, NORTH], [35" +
                    ", SOUTH]], W: [], M: Otyugh - Dead], [Tunnel, #30, T: []" +
                    ", E: [[24, NORTH], [31, EAST]], W: [], M: Otyugh - Dead, " +
                    "Tunnel, #31, T: [], E: [[25, NORTH], [30, WEST]], W: [CR" +
                    "OOKEDARROW], M: Otyugh - Dead, Cave, #32, T: [], E: [[26" +
                    ", NORTH]], W: [], M: Otyugh - Alive, Tunnel, #33, T: [], " +
                    "E: [[27, NORTH], [34, EAST]], W: [], M: Otyugh - Dead, Tu" +
                    "nnel, #34, T: [], E: [[28, NORTH], [33, WEST]], W: [CROOK" +
                    "EDARROW, CROOKEDARROW], M: Otyugh - Dead, Cave, #35, T: " +
                    "[SAPPHIRE], E: [[29, NORTH]], W: [], M: Otyugh - Alive], " +
                    "[Tunnel, #36, T: [], E: [[0, SOUTH], [41, WEST]], W: [], " +
                    "M: Otyugh - Dead, Cave, #37, T: [], E: [[1, SOUTH]], W: [" +
                    "], M: Otyugh - Alive, Cave, #38, T: [SAPPHIRE], E: [[2, S" +
                    "OUTH]], W: [CROOKEDARROW, CROOKEDARROW, CROOKEDARROW], M:" +
                    " Otyugh - Alive, Cave, #39, T: [], E: [[3, SOUTH]], W: []," +
                    " M: Otyugh - Dead, Cave, #40, T: [RUBY, DIAMOND, SAPPHIRE]," +
                    " E: [[4, SOUTH]], W: [CROOKEDARROW, CROOKEDARROW], M: Otyugh" +
                    " - Dead, Tunnel, #41, T: [], E: [[5, SOUTH], [36, EAST]], W: [" +
                    "CROOKEDARROW, CROOKEDARROW], M: Otyugh - Dead]]",
            dungeonWrap.getDungeonGrid().toString());
  }

  /**
   * Test if moving the player works as expected.
   */
  @Test
  public void movePlayer() {
    Position start1 = dungeonNotWrap.getStart();
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    Position start2 = (Position) dungeonNotWrap.describeLocation().get(0);
    assertNotEquals(start1, start2);
    assertEquals(4, start2.getRow());
    assertEquals(3, start2.getColumn());

    final Position start11 = dungeonWrap.getStart();
    assertTrue(dungeonWrap.movePlayer(Direction.WEST));
    assertTrue(dungeonWrap.movePlayer(Direction.WEST));
    assertTrue(dungeonWrap.movePlayer(Direction.WEST));
    Position start22 = (Position) dungeonWrap.describeLocation().get(0);
    assertNotEquals(start11, start22);
    assertEquals(3, start22.getRow());
    assertEquals(0, start22.getColumn());
  }

  /**
   * Test if the items at location get collected.
   */
  @Test
  public void collectItems() {
    assertEquals("[(5, 3), Cave, #33, " +
                    "T: [DIAMOND], E: [[27, NORTH], " +
                    "[34, EAST], [39, SOUTH]], " +
                    "W: [], M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
    assertEquals("[LaraCroft, [], [CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]]",
            dungeonNotWrap.describePlayer().toString());
    dungeonNotWrap.collectItems();
    assertEquals("[(5, 3), Cave, #33, T: [], " +
                    "E: [[27, NORTH], [34, EAST], [39, SOUTH]], " +
                    "W: [], M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
    assertEquals("[LaraCroft, [DIAMOND], " +
                    "[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]]",
            dungeonNotWrap.describePlayer().toString());

    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[(3, 3), Cave, #21, T: [SAPPHIRE, RUBY, RUBY], " +
                    "E: [[15, NORTH], [20, WEST], [27, SOUTH]], " +
                    "W: [CROOKEDARROW, CROOKEDARROW, CROOKEDARROW], " +
                    "M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
    assertEquals("[LaraCroft, [DIAMOND], [CROOKEDARROW]]",
            dungeonNotWrap.describePlayer().toString());
    dungeonNotWrap.collectItems();
    assertEquals("[(3, 3), Cave, #21, T: [], " +
                    "E: [[15, NORTH], [20, WEST], [27, SOUTH]], " +
                    "W: [], M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
    assertEquals("[LaraCroft, [DIAMOND, SAPPHIRE, RUBY, RUBY]," +
                    " [CROOKEDARROW, CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]]",
            dungeonNotWrap.describePlayer().toString());
  }

  /**
   * Test if player's current location is valid.
   */
  @Test
  public void describeLocation() {
    assertEquals("[(5, 3), Cave, " +
                    "#33, T: [DIAMOND], E: [[27, NORTH], " +
                    "[34, EAST], [39, SOUTH]], W: [], M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertEquals("[(6, 3), Tunnel, " +
                    "#39, T: [], E: [[33, NORTH], " +
                    "[40, EAST]], W: [CROOKEDARROW], " +
                    "M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
  }

  /**
   * Test if a Player description is accurate.
   */
  @Test
  public void describePlayer() {
    assertEquals("[LaraCroft, [], " +
                    "[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]]",
            dungeonNotWrap.describePlayer().toString());
    dungeonNotWrap.collectItems();
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertEquals("[LaraCroft, [DIAMOND], " +
                    "[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]]",
            dungeonNotWrap.describePlayer().toString());
    dungeonNotWrap.movePlayer(Direction.SOUTH);
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.collectItems();
    assertEquals("[LaraCroft, " +
            "[DIAMOND, SAPPHIRE, RUBY, RUBY], " +
            "[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW, " +
            "CROOKEDARROW]]", dungeonNotWrap.describePlayer().toString());
  }

  /**
   * Test if length from start to end is at least 5.
   */
  @Test
  public void testIfReachedEndAndForLengthStartToEnd() {
    int count = 0;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.EAST);
    count += 1;

    assertEquals(dungeonNotWrap.describeLocation().get(0), dungeonNotWrap.getEnd());
    assertTrue(count >= 5);
  }

  /**
   * Test moving in a direction where no path exists.
   */
  @Test
  public void testIfMoveInNonExistentDirection() {
    Position start1 = dungeonNotWrap.getStart();
    assertFalse(dungeonNotWrap.movePlayer(Direction.WEST));
    Position start2 = (Position) dungeonNotWrap.describeLocation().get(0);
    assertEquals(start1, start2);
  }

  /**
   * Test if all Dungeon nodes can be visited from Start.
   */
  @Test
  public void testIfAllNodesCanBeVisited() {
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.WEST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.WEST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.WEST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.EAST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.EAST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.EAST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.EAST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.EAST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
  }

  /**
   * Test to verify that Tunnel does not have treasure.
   */
  @Test
  public void testIfTunnelHasNoTreasure() {
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[(4, 3), Tunnel, #27, T: [], " +
                    "E: [[21, NORTH], [33, SOUTH]], " +
                    "W: [], M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
  }

  /**
   * Check if Player's weapons kill a Monster
   * and return false when not striking a monster.
   */
  @Test
  public void usePlayerWeapon() {
    assertTrue(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
    assertTrue(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
    assertFalse(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
  }

  /**
   * Check if Player's weapons kill a Monster
   * by going to that location after using weapon.
   */
  @Test
  public void testSlayingMonster() {
    // if assertTrue works then weapon hit a monster
    assertTrue(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
    assertTrue(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
    // if assertFalse works, then monster at distance 1 has died
    assertFalse(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertEquals("[(3, 3), Cave, #21, " +
            "T: [SAPPHIRE, RUBY, RUBY], E: [[15, NORTH], " +
            "[20, WEST], [27, SOUTH]], W: [CROOKEDARROW, " +
            "CROOKEDARROW, CROOKEDARROW], M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
    assertTrue(dungeonNotWrap.isPlayerAlive());
  }

  /**
   * Check usage of player weapon when invalid
   * direction is provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void usePlayerWeaponInvalidDirection() {
    assertTrue(dungeonNotWrap.usePlayerWeapon(Direction.WEST, 2));
  }

  /**
   * Check usage of player weapon when no
   * weapons available.
   */
  @Test(expected = IllegalArgumentException.class)
  public void usePlayerWeaponWhenNoWeapons() {
    assertTrue(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
    assertTrue(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
    assertFalse(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1));
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
  }

  /**
   * Check for behaviour of using weapon when
   * distance is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void usePlayerWeaponInvalidDistance() {
    assertTrue(dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 0));
  }

  /**
   * Check if correct number of weapons returned.
   */
  @Test
  public void getWeaponsAtLocation() {
    assertEquals("[]",
            dungeonNotWrap.getWeaponsAtLocation().toString());
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]",
            dungeonNotWrap.getWeaponsAtLocation().toString());
  }

  /**
   * Test if correct no. of weapons held
   * by player.
   */
  @Test
  public void getWeaponsOfPlayer() {
    assertEquals("[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]",
            dungeonNotWrap.getWeaponsOfPlayer().toString());
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    assertEquals("[CROOKEDARROW]",
            dungeonNotWrap.getWeaponsOfPlayer().toString());
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.collectItems();
    assertEquals("[CROOKEDARROW, CROOKEDARROW, " +
                    "CROOKEDARROW, CROOKEDARROW]",
            dungeonNotWrap.getWeaponsOfPlayer().toString());
  }

  /**
   * Test if accurate output obtained
   * when checking for treasures at location.
   */
  @Test
  public void getTreasureAtLocation() {
    assertEquals("[DIAMOND]",
            dungeonNotWrap.getTreasureAtLocation().toString());
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[]",
            dungeonNotWrap.getTreasureAtLocation().toString());
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[SAPPHIRE, RUBY, RUBY]",
            dungeonNotWrap.getTreasureAtLocation().toString());
  }

  /**
   * Check if player is having correct
   * treasures upon collecting.
   */
  @Test
  public void getTreasureOfPlayer() {
    assertEquals("[]",
            dungeonNotWrap.getTreasureOfPlayer().toString());
    dungeonNotWrap.collectItems();
    assertEquals("[DIAMOND]",
            dungeonNotWrap.getTreasureOfPlayer().toString());
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.collectItems();
    assertEquals("[DIAMOND, SAPPHIRE, RUBY, RUBY]",
            dungeonNotWrap.getTreasureOfPlayer().toString());
  }

  /**
   * Check if player location
   * is being reported correctly.
   */
  @Test
  public void getPlayerLocation() {
    assertEquals("(5, 3)",
            dungeonNotWrap.getPlayerLocation().toString());
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("(4, 3)",
            dungeonNotWrap.getPlayerLocation().toString());
    dungeonNotWrap.movePlayer(Direction.SOUTH);
    dungeonNotWrap.movePlayer(Direction.EAST);
    assertEquals("(5, 4)",
            dungeonNotWrap.getPlayerLocation().toString());
  }

  /**
   * Test if smell being reported at a
   * location is accurate.
   */
  @Test
  public void describeSmellAtLocation() {
    assertEquals("Slight Pungent",
            dungeonNotWrap.describeSmellAtLocation().getValue());
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    assertEquals("No Smell",
            dungeonNotWrap.describeSmellAtLocation().getValue());
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("Strong Pungent",
            dungeonNotWrap.describeSmellAtLocation().getValue());
  }

  /**
   * Check if isPLayerAlive method is
   * working correctly.
   */
  @Test
  public void isPlayerAlive() {
    assertEquals("[(5, 3), Cave, #33, T: [DIAMOND], " +
            "E: [[27, NORTH], [34, EAST], " +
            "[39, SOUTH]], W: [], M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
    assertTrue(dungeonNotWrap.isPlayerAlive());
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[(3, 3), Cave, #21, " +
            "T: [SAPPHIRE, RUBY, RUBY], E: [[15, NORTH], " +
            "[20, WEST], [27, SOUTH]], W: [CROOKEDARROW, " +
            "CROOKEDARROW, CROOKEDARROW], M: Otyugh - Alive]",
            dungeonNotWrap.describeLocation().toString());
    assertFalse(dungeonNotWrap.isPlayerAlive());
  }

  /**
   * Test if player name can be obtained.
   */
  @Test
  public void getPlayerName() {
    assertEquals("LaraCroft", dungeonNotWrap.getPlayerName());
  }

  /**
   * Test if exits at location are accurate.
   */
  @Test
  public void getExitsFromPlayerLocation() {
    assertEquals("[NORTH, EAST, SOUTH]",
            dungeonNotWrap.getExitsFromPlayerLocation().toString());
    dungeonNotWrap.movePlayer(Direction.SOUTH);
    assertEquals("[NORTH, EAST]",
            dungeonNotWrap.getExitsFromPlayerLocation().toString());
    dungeonNotWrap.movePlayer(Direction.EAST);
    assertEquals("[NORTH, WEST]",
            dungeonNotWrap.getExitsFromPlayerLocation().toString());
  }

  /**
   * Check if location is a cave
   * or a tunnel.
   */
  @Test
  public void isPlayerLocationCave() {
    assertTrue(dungeonNotWrap.isPlayerLocationCave());
    dungeonNotWrap.movePlayer(Direction.SOUTH);
    assertFalse(dungeonNotWrap.isPlayerLocationCave());
  }

  /**
   * Check if Monster is not found in tunnel
   * but seen in Tunnel.
   */
  @Test
  public void checkIfMonsterNotInTunnelAndInCave() {
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[(4, 3), " +
                    "Tunnel, #27, T: [], " +
                    "E: [[21, NORTH], [33, SOUTH]], " +
                    "W: [], M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());
    assertFalse(dungeonNotWrap.isPlayerLocationCave());

    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[(3, 3), Cave, #21, " +
                    "T: [SAPPHIRE, RUBY, RUBY], E: [[15, NORTH], " +
                    "[20, WEST], [27, SOUTH]], W: [CROOKEDARROW, " +
                    "CROOKEDARROW, CROOKEDARROW], M: Otyugh - Alive]",
            dungeonNotWrap.describeLocation().toString());
    assertTrue(dungeonNotWrap.isPlayerLocationCave());
  }

  /**
   * Check if player begins in
   * Dungeon at start location.
   */
  @Test
  public void checkBeginAtStart() {
    assertEquals(dungeonNotWrap.getPlayerLocation(),
            dungeonNotWrap.getStart());
  }

  /**
   * Test if monster exists at end
   * and not at start.
   */
  @Test
  public void testIfMonsterAtEndAndNotStart() {
    assertEquals("[(5, 3), " +
                    "Cave, #33, T: [DIAMOND], E: [[27, NORTH], " +
                    "[34, EAST], [39, SOUTH]], W: [], " +
                    "M: Otyugh - Dead]",
            dungeonNotWrap.describeLocation().toString());

    int count = 0;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.NORTH);
    count += 1;
    dungeonNotWrap.movePlayer(Direction.EAST);
    count += 1;

    assertEquals(dungeonNotWrap.describeLocation().get(0),
            dungeonNotWrap.getEnd());
    assertTrue(count >= 5);

    assertEquals("[(0, 4), Cave, #4, T: [], " +
                    "E: [[3, WEST], [5, EAST], [10, SOUTH]], " +
                    "W: [], M: Otyugh - Alive]",
            dungeonNotWrap.describeLocation().toString());
  }

  /**
   * Check if player begins in
   * Dungeon with 3 arrows.
   */
  @Test
  public void checkBeginWithThreeArrows() {
    assertEquals(dungeonNotWrap.getPlayerLocation(),
            dungeonNotWrap.getStart());
    assertEquals("[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]",
            dungeonNotWrap.getWeaponsOfPlayer().toString());
    assertEquals(3, dungeonNotWrap.getWeaponsOfPlayer().size());
  }

  /**
   * Test if weapons found in caves and tunnels.
   */
  @Test
  public void checkWeaponsFoundLocation() {
    dungeonNotWrap.movePlayer(Direction.SOUTH);
    assertEquals("[CROOKEDARROW]",
            dungeonNotWrap.getWeaponsAtLocation().toString());
    assertFalse(dungeonNotWrap.isPlayerLocationCave());
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals("[CROOKEDARROW, CROOKEDARROW, CROOKEDARROW]",
            dungeonNotWrap.getWeaponsAtLocation().toString());
    assertTrue(dungeonNotWrap.isPlayerLocationCave());
  }

  /**
   * Test if HealthStatus of Otyugh at a location
   * is being reported accurately.
   */
  @Test
  public void testOtyughHealthStatus() {
    assertEquals(HealthStatus.DEAD, dungeonNotWrap.getMonsterHealth());
    dungeonNotWrap.usePlayerWeapon(Direction.NORTH, 1);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    dungeonNotWrap.movePlayer(Direction.NORTH);
    assertEquals(HealthStatus.WOUNDED, dungeonNotWrap.getMonsterHealth());
  }

  /**
   * Test if move method works when
   * we provide Position as argument.
   */
  @Test
  public void testMoveWithPositionAsArgument() {
    assertEquals("(5, 3)", dungeonNotWrap.getStart().toString());
    assertTrue(dungeonNotWrap.movePlayer(new Position(6, 3)));
    assertEquals("(6, 3)", dungeonNotWrap.getPlayerLocation().toString());
    assertFalse(dungeonNotWrap.movePlayer(new Position(0, 0)));
  }

  /**
   * Test if getVisited() method functions as expected.
   */
  @Test
  public void testVisited() {
    assertEquals("[(5, 3)]", dungeonNotWrap.getVisitedNodes().toString());
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertEquals("[(4, 3), (5, 3)]", dungeonNotWrap.getVisitedNodes().toString());
  }

  /**
   * Test for whether Thief is present at a location
   * same as Player.
   */
  @Test
  public void testIfThiefPresent() {
    assertFalse(dungeonNotWrap.isThiefPresent());
    dungeonNotWrap.collectItems();
    assertEquals("[DIAMOND]", dungeonNotWrap.getTreasureOfPlayer().toString());
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.NORTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.WEST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.WEST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.WEST));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.movePlayer(Direction.SOUTH));
    assertTrue(dungeonNotWrap.isThiefPresent());
    assertEquals("[]", dungeonNotWrap.getTreasureOfPlayer().toString());
  }

}