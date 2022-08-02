package dungeon.model;

/**
 * Enum to denote all directions of
 * movement possible in the Dungeon.
 */
public enum Direction {
  NORTH("North"),
  SOUTH("South"),
  EAST("East"),
  WEST("West");

  private final String value;

  /**
   * Constructor for Direction enum class.
   *
   * @param value enum type
   */
  Direction(String value) {
    this.value = value;
  }

  /**
   * Method to return value of the enum type.
   *
   * @return String of the enum type Direction
   */
  public String getValue() {
    return this.value;
  }
}