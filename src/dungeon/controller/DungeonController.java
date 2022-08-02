package dungeon.controller;

import dungeon.model.Dungeon;

/**
 * Represents a Controller for Dungeon game: handle user
 * actions by executing them using the model;
 * convey action outcomes to the user in some form.
 */
public interface DungeonController {

  /**
   * Execute a single game of Dungeon.
   * When the game is over, the playGame
   * method ends.
   *
   * @param m a non-null Dungeon Model
   */
  void playGame(Dungeon m);

}
