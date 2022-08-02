package dungeon.model;

/**
 * Dungeon is a maze of caves and tunnels where
 * a player must begin at a cave marked as the start
 * and end at the cave marked as end, where start and
 * end are randomly chosen. The maze for the Dungeon is
 * created using Kruskal's algorithm.
 */
public interface Dungeon extends ReadOnlyDungeon {

  /**
   * Method to move player from one point in the
   * Dungeon to the next in a direction specified by
   * the user.
   * @param d enum value denoting the direction
   *          of movement of the player
   * @return boolean value indicating if
   *         move was successful
   */
  boolean movePlayer(Direction d);

  /**
   * Method to move player from one point in the
   * Dungeon to a specified position if the move is
   * possible from current location.
   * @param p Position object indicating the
   *
   * @return boolean value indicating if
   *         move was successful
   */
  boolean movePlayer(Position p);

  /**
   * Collect the treasures and weapons in the cave
   * if either exists. They will be added to treasure
   * list and weapons list of the Player.
   */
  void collectItems();

  /**
   * One of the player's weapons is used, which
   * can wound Monsters in the Dungeon.
   */
  boolean usePlayerWeapon(Direction d, int distance);
}
