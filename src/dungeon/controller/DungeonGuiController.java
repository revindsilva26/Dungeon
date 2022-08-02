package dungeon.controller;

import dungeon.model.Direction;
import dungeon.model.Position;

/**
 * Represents a Controller for Dungeon game: handle user
 * actions by executing them using the model;
 * convey action outcomes to the user in some form.
 */
public interface DungeonGuiController {

  /**
   * Execute a single game of the Dungeon.
   */
  void playGame();

  /**
   * Execute a single game of the Dungeon where
   * the seed is provided by the calling method.
   */
  void playGame(int seed);

  /**
   * Listen for keypad presses from the view
   * to Move the player and return a boolean
   * value indicating a successful move.
   */
  boolean move(Direction d);

  /**
   * Listen for mouse clicks to move a player
   * to a specified position in grid and return
   * a boolean value indicating a successful move.
   * @param p Position object
   */
  boolean move(Position p);

  /**
   * Listen for keypad presses from the view
   * to pickup items player can see in current
   * location of dungeon.
   */
  void pickup();

  /**
   * Listen for keypad presses from the view
   * to shoot arrows in some direction at some
   * distance. If the arrow strikes the Monster,
   * then boolean true returned, else false returned.
   */
  boolean attack(Direction d, int distance);

  /**
   * Execute a new game with a different random seed.
   */
  void newGame();

  /**
   * Execute a new game with the same
   * seed as used previously.
   */
  void restartGame();

  /**
   * Change gae settings and fire a new game.
   */
  void changeSettingsAndPlayGame(String rowNum,
                                 String colNum,
                                 boolean wrap,
                                 String interconnectFactor,
                                 String percentTreasures,
                                 String monsterCount);

}
