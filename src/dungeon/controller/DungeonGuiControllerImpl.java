package dungeon.controller;

import dungeon.model.Direction;
import dungeon.model.Dungeon;
import dungeon.model.DungeonImpl;
import dungeon.model.Position;
import dungeon.randomizer.IRandomNumberGenerator;
import dungeon.randomizer.PseudoRandomNumberGenerator;
import dungeon.randomizer.RandomNumberGenerator;
import dungeon.view.DungeonGuiView;
import dungeon.view.DungeonGuiViewImpl;

/**
 * Implementation of the Controller interface
 * for Graphical User interface that a user
 * can use to play Dungeon game. This class
 * delegates the model and view to perform
 * operations on the basis of events listened
 * to in the view.
 */
public class DungeonGuiControllerImpl
        implements DungeonGuiController {

  private DungeonGuiView view;
  private Dungeon model;
  private boolean wrapping;
  private int seed;
  private int rows;
  private int cols;
  private int interconnectivity;
  private int percentageTreasures;
  private int numMonsters;

  /**
   * Constructor for the GUI controller where we
   * set the default values to be used for first game.
   */
  public DungeonGuiControllerImpl() {

    this.seed = 123;
    this.wrapping = false;
    this.rows = 6;
    this.cols = 6;
    this.interconnectivity = 10;
    this.percentageTreasures = 50;
    this.numMonsters = 3;

    IRandomNumberGenerator rng = new PseudoRandomNumberGenerator(seed);

    this.model = new DungeonImpl(
            wrapping,
            rows,
            cols,
            interconnectivity,
            percentageTreasures,
            numMonsters,
            rng);

  }

  /**
   * Constructor for the GUI controller where we
   * pass the model and view.
   */
  public DungeonGuiControllerImpl(Dungeon model, DungeonGuiView view, int seed) {

    if (model == null) {
      throw new IllegalArgumentException("Null model");
    }

    if (view == null) {
      throw new IllegalArgumentException("Null view");
    }

    this.model = model;
    this.view = view;
    this.seed = seed;
    this.wrapping = model.isWrapping();
    this.rows = model.getRows();
    this.cols = model.getColumns();
    this.interconnectivity = model.getInterconnectivity();
    this.percentageTreasures = model.getPercentageTreasures();
    this.numMonsters = model.getNumberOfMonsters();
  }

  @Override
  public void playGame() {
    this.view = new DungeonGuiViewImpl(this.model);
    this.view.addKeypadListener(this);
    this.view.addMouseClickListener(this);
    this.view.refresh();
    this.view.makeVisible();
  }

  @Override
  public void playGame(int seed) {
    this.seed = seed;

    IRandomNumberGenerator rng = new PseudoRandomNumberGenerator(seed);

    this.model = new DungeonImpl(wrapping,
            rows,
            cols,
            interconnectivity,
            percentageTreasures,
            numMonsters,
            rng);

    this.playGame();
  }

  @Override
  public boolean move(Direction d) {

    boolean flag = false;
    if (model.isPlayerAlive()
            && !model.getPlayerLocation().equals(model.getEnd())) {
      try {
        flag = this.model.movePlayer(d);
      } catch (IllegalArgumentException iae) {
        System.out.println("error: " + iae);
      }
    }

    this.view.refresh();
    return flag;
  }

  @Override
  public boolean move(Position p) {
    boolean flag = false;
    if (model.isPlayerAlive()
            && !model.getPlayerLocation().equals(model.getEnd())) {
      try {
        flag = this.model.movePlayer(p);
      } catch (IllegalArgumentException iae) {
        System.out.println("error: " + iae);
      }
    }

    this.view.refresh();
    return flag;
  }

  @Override
  public void pickup() {
    if (model.isPlayerAlive()
            && !model.getPlayerLocation().equals(model.getEnd())) {
      try {
        this.model.collectItems();
      } catch (IllegalArgumentException iae) {
        System.out.println("error: " + iae);
      }
    }

    this.view.refresh();
  }

  @Override
  public boolean attack(Direction d, int distance) {
    boolean flag = false;
    if (model.isPlayerAlive()
            && !model.getPlayerLocation().equals(model.getEnd())) {

      try {
        flag = this.model.usePlayerWeapon(d, distance);
      } catch (IllegalArgumentException iae) {
        throw new IllegalArgumentException(iae.getMessage());
      }

    }

    this.view.refresh();
    return flag;
  }

  @Override
  public void newGame() {
    IRandomNumberGenerator rng1 = new RandomNumberGenerator();
    int seed = rng1.getRandomNumber(0, 200);
    this.view.disposeFrame();
    this.playGame(seed);
  }

  @Override
  public void restartGame() {
    this.view.disposeFrame();
    this.playGame(this.seed);
  }

  @Override
  public void changeSettingsAndPlayGame(
          String rowNum,
          String colNum,
          boolean wrap,
          String interconnectFactor,
          String percentTreasures,
          String monsterCount) {

    int height;
    int width;
    boolean wrapOrNot;
    int interconnect;
    int treasurePercent;
    int monsters;

    try {
      height = Integer.parseInt(rowNum);
      width = Integer.parseInt(colNum);
      wrapOrNot = wrap;
      interconnect = Integer.parseInt(interconnectFactor);
      treasurePercent = Integer.parseInt(percentTreasures);
      monsters = Integer.parseInt(monsterCount);
    }

    catch (NumberFormatException nfe) {
      throw new IllegalArgumentException("Some field(s) had invalid values");
    }

    IRandomNumberGenerator rng = new PseudoRandomNumberGenerator(this.seed);

    Dungeon dungeon1;
    try {
      dungeon1 = new DungeonImpl(wrapOrNot,
              height,
              width,
              interconnect,
              treasurePercent,
              monsters,
              rng);
    }

    catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException(iae.getMessage());
    }

    this.rows = height;
    this.cols = width;
    this.wrapping = wrapOrNot;
    this.interconnectivity = interconnect;
    this.percentageTreasures = treasurePercent;
    this.numMonsters = monsters;

    this.model = dungeon1;
    this.view.disposeFrame();
    this.playGame();
  }

}
