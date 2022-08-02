package dungeon.model;

/**
 * Enum to denote all treasures
 * found in the Dungeon.
 */
public enum Treasure {
  RUBY("Ruby"),
  SAPPHIRE("Sapphire"),
  DIAMOND("Diamond");

  private final String value;

  /**
   * Constructor for Treasure enum class.
   *
   * @param value enum type
   */
  Treasure(String value) {
    this.value = value;
  }

  /**
   * Method to return value of the enum type.
   *
   * @return String of the enum type Treasure
   */
  public String getValue() {
    return this.value;
  }
}