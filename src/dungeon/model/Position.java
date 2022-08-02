package dungeon.model;

/**
 * Package-private class to denote Position of a
 * Player in the Grid as (row, column) co-ordinates.
 */
public class Position {

  /**
   * Get value of row field.
   * @return int denoting row co-ordinate
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Package-private method to set row value.
   * @param row int value
   */
  void setRow(int row) {
    this.row = row;
  }

  /**
   * Method to get the value of column co-ordinate.
   * @return column int value of co-ordinate
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * Package-private method to set column value.
   * @param column int value
   */
  void setColumn(int column) {
    this.column = column;
  }

  private int row;
  private int column;

  /**
   * Package-private constructor used to
   * instantiate Position objects.
   * @param row int value of co-ordinate
   * @param column int value of co-ordinate
   */
  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  @Override
  public String toString() {
    return String.format("(%d, %d)",
            this.getRow(),
            this.getColumn());
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Position)) {
      return false;
    }
    Position other = (Position) o;

    return this.getRow() == other.getRow()
            && this.getColumn() == other.getColumn();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(Long.hashCode(this.getRow())
                    + Long.hashCode(this.getColumn()));
  }

}
