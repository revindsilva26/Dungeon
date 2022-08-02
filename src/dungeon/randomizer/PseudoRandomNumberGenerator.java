package dungeon.randomizer;

import java.util.Random;

/**
 * A class denoting a Pseudo Random Number Generator
 * that will be useful to generate a pre-set sequence
 * for testing.
 */
public class PseudoRandomNumberGenerator implements IRandomNumberGenerator {

  private Random randNum;

  /**
   * Constructor for dungeon.RandomNumberGenerator class.
   */
  public PseudoRandomNumberGenerator() {
    randNum = new Random();
    randNum.setSeed(123);
  }

  /**
   * Constructor for dungeon.RandomNumberGenerator class.
   * where we can pass the seed value as an argument.
   */
  public PseudoRandomNumberGenerator(int seed) {
    randNum = new Random();
    randNum.setSeed(seed);
  }

  @Override
  public int getRandomNumber(int low, int high) {
    return randNum.nextInt(high - low + 1) + low;
  }

}