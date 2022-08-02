package dungeon;

import dungeon.controller.DungeonController;
import dungeon.controller.DungeonControllerImpl;
import dungeon.controller.DungeonGuiController;
import dungeon.controller.DungeonGuiControllerImpl;
import dungeon.model.Dungeon;
import dungeon.model.DungeonImpl;
import dungeon.randomizer.IRandomNumberGenerator;
import dungeon.randomizer.PseudoRandomNumberGenerator;
import dungeon.randomizer.RandomNumberGenerator;

import java.io.InputStreamReader;

/**
 * Driver class to see output of Model
 * manipulations and debug accordingly.
 */
public class Driver {

  /**
   * Main function where the dungeon class
   * is created and used.
   * @param args arguments from command line
   * @throws Exception for invalid cases
   */
  public static void main(String[] args) throws IllegalArgumentException {

    // Command line Dungeon game
    if (args.length == 6) {

      int rows;
      int columns;
      boolean wrapping;
      int interconnectivity;
      int percentage;
      int numMonsters;

      try {
        rows = Integer.parseInt(args[0]);
        columns = Integer.parseInt(args[1]);
        interconnectivity = Integer.parseInt(args[2]);
        wrapping = Boolean.parseBoolean(args[3]);
        percentage = Integer.parseInt(args[4]);
        numMonsters = Integer.parseInt(args[5]);
      }

      catch (NumberFormatException nfe) {
        throw new IllegalArgumentException(nfe.getMessage());
      }

      IRandomNumberGenerator rng1 = new RandomNumberGenerator();
      IRandomNumberGenerator rng = new PseudoRandomNumberGenerator();

      Dungeon dungeonWrap;
      Dungeon dungeonNotWrap;
      try {
        dungeonWrap = new DungeonImpl(wrapping,
                rows,
                columns,
                interconnectivity,
                percentage,
                numMonsters,
                rng1);
        dungeonNotWrap = new DungeonImpl(!wrapping,
                rows,
                columns,
                interconnectivity,
                percentage,
                numMonsters,
                rng1);
      }

      catch (IllegalArgumentException iae) {
        throw new IllegalArgumentException(iae.getMessage());
      }

      Readable input = new InputStreamReader(System.in);
      DungeonController ctrl = new DungeonControllerImpl(input, System.out);

      try {
        ctrl.playGame(dungeonNotWrap);
      }

      catch (IllegalArgumentException iae) {
        throw new IllegalArgumentException(iae.getMessage());
      }
    }

    // GUI Dungeon game
    else {
      DungeonGuiController guiController = new DungeonGuiControllerImpl();
      guiController.playGame();
    }
  }
}
