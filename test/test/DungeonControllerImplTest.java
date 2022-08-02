package test;

import static org.junit.Assert.assertEquals;

import dungeon.controller.DungeonController;
import dungeon.controller.DungeonControllerImpl;
import dungeon.model.Dungeon;
import dungeon.model.DungeonImpl;
import dungeon.randomizer.IRandomNumberGenerator;
import dungeon.randomizer.PseudoRandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

/**
 * Test cases for the Dungeon controller,
 * in some cases, using mocks for readable
 * and appendable.
 */
public class DungeonControllerImplTest {

  private Dungeon dungeonNotWrap;

  /**
   * Set up objects for testing.
   */
  @Before
  public void setUp() throws Exception {
    IRandomNumberGenerator rng = new PseudoRandomNumberGenerator();
    Dungeon dungeonWrap = new DungeonImpl(true,
            7,
            6,
            11,
            30,
            10,
            rng);
    dungeonNotWrap = new DungeonImpl(false,
            7,
            6,
            10,
            50,
            10,
            rng);
  }

  // ----------------------------------------
  // tests for controller with model
  // ----------------------------------------
  /**
   * Test for when invalid distance is supplied.
   */
  @Test
  public void testInvalidDistance() {
    StringReader input1 = new StringReader("S E 0 q");
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    Appendable out1 = new PrintStream(bytes1);
    DungeonController c1 = new DungeonControllerImpl(input1, out1);
    c1.playGame(dungeonNotWrap);
    assertEquals("You, LaraCroft, have entered Dungeon.\n" +
            "You are starting at (5, 3) \n" +
            "Beware of what creatures may lurk in your way...\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "Min. distance is 1\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Quitting Dungeon game...", bytes1.toString());
  }

  /**
   * Test if Player picks up treasures and
   * weapons as they move through the Dungeon.
   */
  @Test
  public void testPickupItems() {
    StringReader input1 = new StringReader("P M E P M S P M W P M N " +
            "S N 1 S N 1 M N M N P QUIT");
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    Appendable out1 = new PrintStream(bytes1);
    DungeonController c1 = new DungeonControllerImpl(input1, out1);
    c1.playGame(dungeonNotWrap);
    assertEquals("You, LaraCroft, have entered Dungeon.\n" +
            "You are starting at (5, 3) \n" +
            "Beware of what creatures may lurk in your way...\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "\n" +
            "Collecting these treasures:\n" +
            "Diamond \n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved EAST!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "\n" +
            "Collecting these treasures:\n" +
            "\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved South!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North West \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "Crooked Arrow \n" +
            "Collecting these treasures:\n" +
            "\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North West \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved West!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North East \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "Crooked Arrow \n" +
            "Collecting these treasures:\n" +
            "\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x5\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North East \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x5\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "Crooked Arrow Crooked Arrow Crooked Arrow \n" +
            "Collecting these treasures:\n" +
            "Sapphire Ruby Ruby \n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x6\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Quitting Dungeon game...", bytes1.toString());
  }

  /**
   * Test output when no weapons left
   * and player tries to shoot.
   */
  @Test
  public void testNoWeaponsLeft() {
    StringReader input1 = new StringReader("S N 1 S N 1 S N 1 S N 1 q");
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    Appendable out1 = new PrintStream(bytes1);
    DungeonController c1 = new DungeonControllerImpl(input1, out1);
    c1.playGame(dungeonNotWrap);
    assertEquals("You, LaraCroft, have entered Dungeon.\n" +
            "You are starting at (5, 3) \n" +
            "Beware of what creatures may lurk in your way...\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x2\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "... No sound, weapon gone with the wind\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "No weapons available\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Quitting Dungeon game...", bytes1.toString());
  }

  /**
   * Test behaviour when attempting to exit
   * in a direction without exit being available.
   */
  @Test
  public void testUnavailableExit() {
    StringReader input1 = new StringReader("M W quit");
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    Appendable out1 = new PrintStream(bytes1);
    DungeonController c1 = new DungeonControllerImpl(input1, out1);
    c1.playGame(dungeonNotWrap);
    assertEquals("You, LaraCroft, have entered Dungeon.\n" +
            "You are starting at (5, 3) \n" +
            "Beware of what creatures may lurk in your way...\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Invalid move!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Quitting Dungeon game...", bytes1.toString());
  }

  /**
   * Test if game continues after invalid input.
   */
  @Test
  public void testContinueAfterInvalidInput() {
    StringReader input1 = new StringReader("9 S N 1 M 3 S N one S easst S N 1 " +
            "pdp P M norrth M N P M N P " +
            "M N M N M N S E 1 S E 1 S E 1 M E");
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    Appendable out1 = new PrintStream(bytes1);
    DungeonController c1 = new DungeonControllerImpl(input1, out1);
    c1.playGame(dungeonNotWrap);
    assertEquals("You, LaraCroft, have entered Dungeon.\n" +
            "You are starting at (5, 3) \n" +
            "Beware of what creatures may lurk in your way...\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Invalid input\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x2\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Invalid input 3\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x2\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "Invalid input\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x2\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Invalid input easst\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x2\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Invalid input\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "\n" +
            "Collecting these treasures:\n" +
            "Diamond \n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Invalid input norrth\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "\n" +
            "Collecting these treasures:\n" +
            "\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "Crooked Arrow Crooked Arrow Crooked Arrow \n" +
            "Collecting these treasures:\n" +
            "Sapphire Ruby Ruby \n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "West East South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "West East South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x2\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "West East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "... No sound, weapon gone with the wind\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "West East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved EAST!\n" +
            "Your Location: (0, 4) ; YES! Exit found at (0, 4)\n" +
            "YOU WIN!!! Reached exit (0, 4)\n", bytes1.toString());
  }

  /**
   * Test if Player encounters Monster and dies.
   */
  @Test
  public void testDieByMonster() {
    StringReader input1 = new StringReader("M N P M N");
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    Appendable out1 = new PrintStream(bytes1);
    DungeonController c1 = new DungeonControllerImpl(input1, out1);
    c1.playGame(dungeonNotWrap);
    assertEquals("You, LaraCroft, have entered Dungeon.\n" +
            "You are starting at (5, 3) \n" +
            "Beware of what creatures may lurk in your way...\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "\n" +
            "Collecting these treasures:\n" +
            "\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "DEAD! You encountered a monster and didn't survive\n", bytes1.toString());
  }


  /**
   * Test if Player wins the game by reaching end.
   */
  @Test
  public void testReachEnd() {
    StringReader input1 = new StringReader("S N 1 S N 1 P M N P M N P " +
            "M N M N M N S E 1 S E 1 S E 1 M E");
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    Appendable out1 = new PrintStream(bytes1);
    DungeonController c1 = new DungeonControllerImpl(input1, out1);
    c1.playGame(dungeonNotWrap);
    assertEquals("You, LaraCroft, have entered Dungeon.\n" +
            "You are starting at (5, 3) \n" +
            "Beware of what creatures may lurk in your way...\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x2\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "\n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "\n" +
            "Collecting these treasures:\n" +
            "Diamond \n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North East South \n" +
            "Is there a pungent smell in the air? No Smell\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "\n" +
            "Collecting these treasures:\n" +
            "\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Collecting these weapons:\n" +
            "Crooked Arrow Crooked Arrow Crooked Arrow \n" +
            "Collecting these treasures:\n" +
            "Sapphire Ruby Ruby \n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Tunnel.\n" +
            "Exits can be seen to the \n" +
            "North South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "North West South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved North!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x4\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "West East South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x3\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "West East South \n" +
            "Is there a pungent smell in the air? Strong Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "ROAR! Loud wail heard! You hit something!\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x2\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "West East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Enter distance to shoot (>= 1)\n" +
            "... No sound, weapon gone with the wind\n" +
            "\n" +
            "You have:\n" +
            "Weapons:\n" +
            "Crooked Arrow x1\n" +
            "Treasures:\n" +
            "Diamond Sapphire Ruby Ruby \n" +
            "You are in a Cave.\n" +
            "Exits can be seen to the \n" +
            "West East South \n" +
            "Is there a pungent smell in the air? Slight Pungent\n" +
            "Now !!!\n" +
            "Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n" +
            "Which direction?\n" +
            "Moved EAST!\n" +
            "Your Location: (0, 4) ; YES! Exit found at (0, 4)\n" +
            "YOU WIN!!! Reached exit (0, 4)\n", bytes1.toString());
  }

  // ----------------------------------------
  // tests for controller
  // ----------------------------------------
  /**
   * Test if an invalid Appendable is passed
   * to playGame() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAppendable() {
    IRandomNumberGenerator rng = new PseudoRandomNumberGenerator();
    Dungeon m = new DungeonImpl(true,
            10,
            10,
            15,
            10,
            10,
            rng);
    StringReader input = null;
    Appendable gameLog = System.out;
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(m);
  }

  /**
   * Test if an invalid Readable is passed
   * to playGame() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReadable() {
    IRandomNumberGenerator rng = new PseudoRandomNumberGenerator();
    Dungeon m = new DungeonImpl(true,
            10,
            10,
            15,
            10,
            10,
            rng);
    StringReader input = new StringReader("M N M N P M N M N P");
    Appendable gameLog = null;
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(m);
  }

  /**
   * Test if an invalid model is passed
   * to playGame() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    Dungeon m = null;
    StringReader input = new StringReader("M N M N P M N M N P");
    Appendable gameLog = System.out;
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(m);
  }

  /**
   * Test for failing appendable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    StringReader input = new StringReader("M N M N M N M N M N M N");
    Appendable gameLog = new FailingAppendable();
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dungeonNotWrap);
  }
}