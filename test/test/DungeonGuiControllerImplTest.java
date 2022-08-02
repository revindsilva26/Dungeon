package test;

import static org.junit.Assert.assertEquals;

import dungeon.controller.DungeonGuiController;
import dungeon.controller.DungeonGuiControllerImpl;
import dungeon.model.Direction;
import dungeon.model.Dungeon;
import dungeon.model.Position;
import dungeon.view.DungeonGuiView;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Testing the GUI Controller using a mock model
 * and mock view.
 */
public class DungeonGuiControllerImplTest {

  /**
   * Test for invalid view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidView() {
    Appendable appendableModel = new StringWriter();
    Appendable appendableView = new StringWriter();
    Dungeon model = new MockModel(appendableModel);
    DungeonGuiView view = new MockView(appendableView);
    int seed = 123;
    DungeonGuiController c = new DungeonGuiControllerImpl(model, null, seed);
  }

  /**
   * Test for invalid model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    Appendable appendableModel = new StringWriter();
    Appendable appendableView = new StringWriter();
    Dungeon model = new MockModel(appendableModel);
    DungeonGuiView view = new MockView(appendableView);
    int seed = 123;
    DungeonGuiController c = new DungeonGuiControllerImpl(null, view, seed);
  }

  /**
   * Test for correct working of move for mouse clicks.
   */
  @Test
  public void testMoveToPosition() {
    Appendable appendableModel = new StringWriter();
    Appendable appendableView = new StringWriter();
    Dungeon model = new MockModel(appendableModel);
    DungeonGuiView view = new MockView(appendableView);
    int seed = 123;
    DungeonGuiController c = new DungeonGuiControllerImpl(model, view, seed);
    c.move(new Position(0, 0));
    assertEquals("Called isWrapping()" +
            "Called getRows" +
            "Called getColumns()" +
            "Called getInterconnectivity()" +
            "Called getPercentageTreasures()" +
            "Called getNumberOfMonsters()" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received input: (0, 0)", appendableModel.toString());
    assertEquals("Called refresh()", appendableView.toString());
    c.move(new Position(1, 5));
    assertEquals("Called isWrapping()" +
            "Called getRows" +
            "Called getColumns()" +
            "Called getInterconnectivity()" +
            "Called getPercentageTreasures()" +
            "Called getNumberOfMonsters()" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received input: (0, 0)" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received input: (1, 5)", appendableModel.toString());
    assertEquals("Called refresh()Called refresh()", appendableView.toString());
  }

  /**
   * Test for correct working of move for keypad clicks.
   */
  @Test
  public void testMoveToDirection() {
    Appendable appendableModel = new StringWriter();
    Appendable appendableView = new StringWriter();
    Dungeon model = new MockModel(appendableModel);
    DungeonGuiView view = new MockView(appendableView);
    int seed = 123;
    DungeonGuiController c = new DungeonGuiControllerImpl(model, view, seed);
    c.move(Direction.NORTH);
    assertEquals("Called isWrapping()" +
            "Called getRows" +
            "Called getColumns()" +
            "Called getInterconnectivity()" +
            "Called getPercentageTreasures()" +
            "Called getNumberOfMonsters()" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received input: NORTH", appendableModel.toString());
    assertEquals("Called refresh()", appendableView.toString());
    c.move(Direction.SOUTH);
    assertEquals("Called isWrapping()" +
            "Called getRows" +
            "Called getColumns()" +
            "Called getInterconnectivity()" +
            "Called getPercentageTreasures()" +
            "Called getNumberOfMonsters()" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received input: NORTH" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received input: SOUTH", appendableModel.toString());
    assertEquals("Called refresh()" +
            "Called refresh()", appendableView.toString());
  }

  /**
   * Test for correct working of pickup.
   */
  @Test
  public void testPickup() {
    Appendable appendableModel = new StringWriter();
    Appendable appendableView = new StringWriter();
    Dungeon model = new MockModel(appendableModel);
    DungeonGuiView view = new MockView(appendableView);
    int seed = 123;
    DungeonGuiController c = new DungeonGuiControllerImpl(model, view, seed);
    c.pickup();
    assertEquals("Called isWrapping()" +
            "Called getRows" +
            "Called getColumns()" +
            "Called getInterconnectivity()" +
            "Called getPercentageTreasures()" +
            "Called getNumberOfMonsters()" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Called collectItems()", appendableModel.toString());
    assertEquals("Called refresh()", appendableView.toString());
  }

  /**
   * Test for correct working of attack.
   */
  @Test
  public void testAttack() {
    Appendable appendableModel = new StringWriter();
    Appendable appendableView = new StringWriter();
    Dungeon model = new MockModel(appendableModel);
    DungeonGuiView view = new MockView(appendableView);
    int seed = 123;
    DungeonGuiController c = new DungeonGuiControllerImpl(model, view, seed);
    c.attack(Direction.WEST, 4);
    assertEquals("Called isWrapping()" +
            "Called getRows" +
            "Called getColumns()" +
            "Called getInterconnectivity()" +
            "Called getPercentageTreasures()" +
            "Called getNumberOfMonsters()" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received inputs: West and 4", appendableModel.toString());
    assertEquals("Called refresh()", appendableView.toString());
    c.attack(Direction.EAST, 1);
    assertEquals("Called isWrapping()" +
            "Called getRows" +
            "Called getColumns()" +
            "Called getInterconnectivity()" +
            "Called getPercentageTreasures()" +
            "Called getNumberOfMonsters()" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received inputs: West and 4" +
            "Called isPlayerAlive()" +
            "Called getPlayerLocation()" +
            "Called getStart()" +
            "Received inputs: East and 1", appendableModel.toString());
    assertEquals("Called refresh()Called refresh()", appendableView.toString());
  }
}