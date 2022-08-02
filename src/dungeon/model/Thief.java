package dungeon.model;

/**
 * Thief is a special type of monster who only
 * steals treasure from the Player but does not
 * affect the player in any other way. Package-private
 * since it is only accessible by the DungeonImpl class.
 */
class Thief implements Monster {

  private HealthStatus healthStatus;

  Thief(HealthStatus hs) {
    this.healthStatus = hs;
  }

  /**
   * Package-private method to set the
   * health status of a Thief.
   */
  void setHealthStatus(HealthStatus hs) {
    this.healthStatus = hs;
  }

  @Override
  public HealthStatus getHealthStatus() {
    return this.healthStatus;
  }

  @Override
  public String toString() {
    return String.format("Thief - %s",
            this.getHealthStatus().getValue());
  }
}
