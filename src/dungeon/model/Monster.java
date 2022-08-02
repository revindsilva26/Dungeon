package dungeon.model;

/**
 * Interface to instantiate objects of type
 * Monster. Monsters like Otyugh may exist
 * in caves of the Dungeon, while some Monsters
 * like Thief can exist anywhere in the dungeon.
 * Package-private since accesses happen inside
 * Dungeon only.
 */
interface Monster {

  /**
   * Method to obtain the health status of
   * a Monster which helps determine if it
   * can affect a Player.
   * @return enum HealthStatus
   */
  HealthStatus getHealthStatus();
}
