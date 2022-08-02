package dungeon.model;

/**
 * A Monster of type Otyugh, that is smelly
 * enough for a Player to detect its presence
 * before encountering it. The Otyugh requires
 * 2 hits to be killed and therefore, we must
 * note its health status if we do encounter it.
 * Made package-private since accesses all happen
 * inside the Dungeon only.
 */
class Otyugh implements Monster {

  private HealthStatus status;

  Otyugh(HealthStatus hs) {
    this.status = hs;
  }

  /**
   * Package-private method to set the
   * health status of an Otyugh.
   */
  void setHealthStatus(HealthStatus hs) {
    this.status = hs;
  }

  @Override
  public HealthStatus getHealthStatus() {
    return this.status;
  }

  @Override
  public String toString() {
    return String.format("Otyugh - %s",
            this.getHealthStatus().getValue());
  }
}
