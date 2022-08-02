package dungeon.model;

import java.util.List;

/**
 * Cave represents a node in the Dungeon that
 * a Player can exist inside. A cave will have 1,
 * 3 or 4 directions in which a Player can move.
 * A special type of cave is a Tunnel, where a Player
 * can move in 2 directions from that tunnel. Made
 * package-private since all accesses to happen from
 * within the Dungeon.
 */
interface Cave {

  /**
   * Method to get all the treasures that are
   * present in a cave.
   * @return List of treasures
   */
  List<Treasure> getTreasure();

  /**
   * Method that returns all the exit
   * routes a Player can take from a given
   * node of the Dungeon.
   * @return List of exits
   */
  List<List<Object>> getExits();

  /**
   * Method to return the node index number
   * assigned to the particular cave.
   * @return int value denoting node number.
   */
  int getNodeNum();

  /**
   * Method to return whether this is cave
   * or a tunnel type, depending on the
   * number of possible exits.
   * @return String value denoting type.
   */
  String getType();

  /**
   * Method to return if a cave has a monster.
   * @return boolean value denoting presence of monster.
   */
  boolean liveMonsterPresent();

  /**
   * Method to return if a cave has a thief.
   * @return boolean value denoting presence of thief.
   */
  boolean liveThiefPresent();

  /**
   * Method to return the Monster in the
   * cave if it exists.
   * @return object of Monster type
   */
  Monster getMonster();

  /**
   * Method to return special Monster
   * type Thief if it exists.
   * @return object of Monster type
   */
  Monster getThief();

  /**
   * Method to get all the weapons that are
   * present in a cave.
   * @return List of weapons
   */
  List<Weapon> getWeapons();
}
