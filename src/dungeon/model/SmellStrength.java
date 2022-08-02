package dungeon.model;

/**
 * Enum to denote a smell indicating
 * positions an Otyugh may be present in
 * from current player location.
 */
public enum SmellStrength {
  STRONG("Strong Pungent"),
  SLIGHT("Slight Pungent"),
  NONE("No Smell");

  private final String value;

  /**
   * Constructor for SmellStrength enum class.
   *
   * @param value enum type
   */
  SmellStrength(String value) {
    this.value = value;
  }

  /**
   * Method to return value of the enum type.
   *
   * @return String of the enum type SmellStrength
   */
  public String getValue() {
    return this.value;
  }
}