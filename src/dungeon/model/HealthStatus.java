package dungeon.model;

/**
 * Enum to denote the possible health
 * statuses of a Monster type.
 */
public enum HealthStatus {
  ALIVE("Alive"),
  WOUNDED("Wounded"),
  DEAD("Dead");

  private final String value;

  /**
   * Constructor for HealthStatus enum class.
   *
   * @param value enum type
   */
  HealthStatus(String value) {
    this.value = value;
  }

  /**
   * Method to return value of the enum type.
   *
   * @return String of the enum type HealthStatus
   */
  public String getValue() {
    return this.value;
  }
}
