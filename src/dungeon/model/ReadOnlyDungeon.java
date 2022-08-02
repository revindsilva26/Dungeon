package dungeon.model;

import java.util.List;
import java.util.Set;

/**
 * Dungeon is a maze of caves and tunnels where
 * a player must begin at a cave marked as the start
 * and end at the cave marked as end, where start and
 * end are randomly chosen. The maze for the Dungeon is
 * created using Kruskal's algorithm.
 */
public interface ReadOnlyDungeon {

  /**
   * Method to return the value randomly chosen
   * where the player will begin in the maze.
   * @return Position object denoting start node coordinates.
   */
  Position getStart();

  /**
   * Method to return the value randomly chosen
   * where the player must end in the maze.
   * @return Position object denoting end node coordinates.
   */
  Position getEnd();

  /**
   * Method to return the maze of caves and
   * tunnels known as the Dungeon. This method
   * returns a list of the grid layout of the
   * dungeon.
   * @return 2D List of caves/tunnels.
   */
  List<List<Cave>> getDungeonGrid();


  /**
   * Describes the location the Player is
   * currently in, in the Dungeon.
   * @return List of state variables
   */
  List<Object> describeLocation();

  /**
   * Describes the information relevant to the
   * Player, such as Treasure collected etc.
   * @return List of state variables
   */
  List<Object> describePlayer();

  /**
   * Get all the weapons at a location
   * in the Dungeon.
   */
  List<Weapon> getWeaponsAtLocation();

  /**
   * Get all the weapons held by a player
   * at any instant when he is in the Dungeon.
   */
  List<Weapon> getWeaponsOfPlayer();

  /**
   * Get all the treasures at a location
   * in the Dungeon.
   */
  List<Treasure> getTreasureAtLocation();

  /**
   * Get treasures held by a Player
   * at any instant.
   */
  List<Treasure> getTreasureOfPlayer();

  /**
   * Get player location at any instant.
   */
  Position getPlayerLocation();

  /**
   * Describe if Player can detect smell of
   * Monster at any given location.
   */
  SmellStrength describeSmellAtLocation();

  /**
   * Check if Player is alive, if so, end the
   * game immediately.
   */
  boolean isPlayerAlive();

  /**
   * Return player's name as String.
   */
  String getPlayerName();

  /**
   * Get all possible exits from a Dungeon location.
   */
  List<Direction> getExitsFromPlayerLocation();

  /**
   * Get a boolean value to indicate if current
   * player location is a cave or not.
   */
  boolean isPlayerLocationCave();

  /**
   * Get whether Monster is present at a location
   * using its status.
   */
  HealthStatus getMonsterHealth();

  /**
   * Check whether Thief is present at a location
   * same as Player.
   */
  boolean isThiefPresent();

  /**
   * Get the total rows in dungeon grid.
   */
  int getRows();

  /**
   * Get the total columns in dungeon grid.
   */
  int getColumns();

  /**
   * Check if Dungeon is wrapping or not.
   */
  boolean isWrapping();

  /**
   * Get the percentage of dungeon nodes with treasures.
   */
  int getPercentageTreasures();

  /**
   * Get the interconnectivity of dungeon grid.
   */
  int getInterconnectivity();

  /**
   * Get the number of monsters present in the dungeon.
   */
  int getNumberOfMonsters();

  /**
   * Get the set of all visited nodes in the dungeon.
   */
  Set<Position> getVisitedNodes();
}
