package dungeon.model;

/**
 * Enum to denote a Weapon found
 * by a Player in the Dungeon. Currently,
 * only Crooked arrows supported, can
 * be extended to other weapons in future versions.
 */
public enum Weapon {
  CROOKEDARROW("Crooked Arrow");

  private final String value;

  /**
   * Constructor for Weapon enum class.
   *
   * @param value enum type
   */
  Weapon(String value) {
    this.value = value;
  }

  /**
   * Method to return value of the enum type.
   *
   * @return String of the enum type Weapon
   */
  public String getValue() {
    return this.value;
  }
}
