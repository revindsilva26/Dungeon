package dungeon.randomizer;

import java.util.Random;

/**
 * Class for RandomNumberGenerator, which generates
 * numbers at random from a specified range of values.
 */
public class RandomNumberGenerator implements IRandomNumberGenerator {

  @Override
  public int getRandomNumber(
          int low, int high) {
    return new Random().nextInt(high - low + 1) + low;
  }

}
