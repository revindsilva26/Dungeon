package dungeon.model;

import java.util.List;

/**
 * Player class is used to create objects of
 * Player type. Player objects will be placed in
 * the Dungeon at the start node and will move through the
 * dungeon until end node is reached. Player can
 * move, pickup, shoot when inside the Dungeon.
 * Made package private since all
 * accesses are from within Dungeon.
 */
interface Player {

  /**
   * Describes the current state of the Player
   * mainly to elaborate on the Treasures
   * collected.
   * @return List of state variables
   */
  List<Object> describeState();

  /**
   * Return the Player's name.
   */
  String getName();

  /**
   * Return the weapons a
   * player has collected.
   */
  List<Weapon> getPlayerWeapons();

  /**
   * Return the Treasures
   * a player has collected.
   */
  List<Treasure> getPlayerTreasures();

  /**
   * Return true if the player
   * is alive, else return false.
   */
  boolean isAlive();
}
