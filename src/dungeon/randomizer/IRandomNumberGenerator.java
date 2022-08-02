package dungeon.randomizer;

/**
 * Interface for instantiating the Random Number
 * Generator that is used to generate random numbers
 * for choosing edges in dungeon.Kruskal algorithm.
 */
public interface IRandomNumberGenerator {

  /**
   * Method to generate a random number
   * in the range of low to high
   * that is provided by user.
   * @param low int value denoting lower range
   * @param high int value denoting higher range
   * @return int value between low and
   *         high, inclusive
   */
  public int getRandomNumber(int low, int high);
}

