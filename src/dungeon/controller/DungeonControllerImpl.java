package dungeon.controller;

import dungeon.model.Direction;
import dungeon.model.Dungeon;
import dungeon.model.Treasure;
import dungeon.model.Weapon;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 * This class implements the DungeonController
 * interface to play the Dungeon game and flesh out
 * the functionalities like accepting the user input,
 * perform action in Dungeon, display result to the
 * user in some fashion.
 */
public class DungeonControllerImpl implements DungeonController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller, where
   * Readable and Appendable type objects
   * are provided as arguments.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonControllerImpl(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(Dungeon m) {
    if (m == null) {
      throw new IllegalArgumentException("Pass a valid model object");
    }

    try {

      out.append(String.format("You, %s, have entered Dungeon.\n", m.getPlayerName()));
      out.append(String.format("You are starting at %s \n", m.getStart()));
      out.append("Beware of what creatures may lurk in your way...\n");

      while (m.isPlayerAlive()
              && !(m.getPlayerLocation().equals(m.getEnd()))) {

        out.append("\nYou have:\n");
        List<Weapon> weapons = m.getWeaponsOfPlayer();
        out.append("Weapons:\n");
        if (weapons.size() > 0) {
          out.append(String.format("%s x%d\n",
                  weapons.get(0).getValue(),
                  weapons.size()));
        }
        out.append("Treasures:\n");
        List<Treasure> treasures = m.getTreasureOfPlayer();
        if (treasures.size() > 0) {
          for (Treasure t : treasures) {
            out.append(String.format("%s ", t.getValue()));
          }
        }
        out.append("\n");
        if (m.isPlayerLocationCave()) {
          out.append("You are in a Cave.\n");
        }
        else {
          out.append("You are in a Tunnel.\n");
        }
        out.append("Exits can be seen to the \n");
        for (Direction d : m.getExitsFromPlayerLocation()) {
          out.append(String.format("%s ", d.getValue()));
        }
        out.append("\n");
        out.append(String.format("Is there a pungent smell in the air? %s\n",
                m.describeSmellAtLocation()
                        .getValue()));
        out.append("Now !!!\n");
        out.append("Time to decide! Move - M, Pickup items - P, Shoot weapon - S\n");

        String in = scan.next();
        try {
          switch (in) {
            case "q":
            case "Q":
            case "quit":
            case "Quit":
            case "QUIT":
              out.append("Quitting Dungeon game...");
              return;
            case "m":
            case "M":
              out.append("Which direction?\n");
              String in2 = scan.next();
              switch (in2) {
                case "q":
                case "Q":
                case "quit":
                case "Quit":
                case "QUIT":
                  out.append("Quitting Dungeon game...");
                  return;
                case "North":
                case "n":
                case "N":
                case "north":
                case "NORTH":
                  if (m.movePlayer(Direction.NORTH)) {
                    out.append("Moved North!\n");
                  } else {
                    out.append("Invalid move!\n");
                  }
                  break;

                case "South":
                case "s":
                case "S":
                case "south":
                case "SOUTH":
                  if (m.movePlayer(Direction.SOUTH)) {
                    out.append("Moved South!\n");
                  } else {
                    out.append("Invalid move!\n");
                  }
                  break;

                case "East":
                case "e":
                case "E":
                case "east":
                case "EAST":
                  if (m.movePlayer(Direction.EAST)) {
                    out.append("Moved EAST!\n");
                  } else {
                    out.append("Invalid move!\n");
                  }
                  break;

                case "WEST":
                case "w":
                case "W":
                case "west":
                case "West":
                  if (m.movePlayer(Direction.WEST)) {
                    out.append("Moved West!\n");
                  } else {
                    out.append("Invalid move!\n");
                  }
                  break;

                default:
                  out.append(String.format("Invalid input %s\n", in2));
                  break;
              }
              break;

            case "p":
            case "P":
              out.append("Collecting these weapons:\n");
              for (Weapon w : m.getWeaponsAtLocation()) {
                out.append(String.format("%s ", w.getValue()));
              }
              out.append("\n");
              out.append("Collecting these treasures:\n");
              for (Treasure w : m.getTreasureAtLocation()) {
                out.append(String.format("%s ", w.getValue()));
              }
              out.append("\n");
              m.collectItems();
              break;

            case "s":
            case "S":
              Direction dir = Direction.NORTH;
              int dist = 0;
              out.append("Which direction?\n");
              String in3 = scan.next();

              switch (in3) {
                case "q":
                case "Q":
                case "quit":
                case "Quit":
                case "QUIT":
                  out.append("Quitting Dungeon game...");
                  return;
                case "North":
                case "n":
                case "N":
                case "north":
                case "NORTH":
                  dir = Direction.NORTH;
                  break;

                case "South":
                case "s":
                case "S":
                case "south":
                case "SOUTH":
                  dir = Direction.SOUTH;
                  break;

                case "East":
                case "e":
                case "E":
                case "east":
                case "EAST":
                  dir = Direction.EAST;
                  break;

                case "WEST":
                case "w":
                case "W":
                case "west":
                case "West":
                  dir = Direction.WEST;
                  break;

                default:
                  out.append(String.format("Invalid input %s\n", in3));
                  continue;
              }


              out.append("Enter distance to shoot (>= 1)\n");
              String in4 = scan.next();

              if (in4.equals("q")
                      || in4.equals("Q")
                      || in4.equals("quit")
                      || in4.equals("Quit")
                      || in4.equals("QUIT")) {
                out.append("Quitting Dungeon game...");
                return;
              }

              try {
                dist = Integer.parseInt(in4);
              } catch (NumberFormatException nfe) {
                out.append("Invalid input\n");
                continue;
              }

              boolean hit = false;

              try {
                hit = m.usePlayerWeapon(dir, dist);
              } catch (IllegalArgumentException iae) {
                out.append(iae.getMessage());
                continue;
              }


              if (hit) {
                out.append("ROAR! Loud wail heard! You hit something!\n");
              }
              else {
                out.append("... No sound, weapon gone with the wind\n");
              }
              break;

            default:
              out.append("Invalid input\n");
              break;
          }
        } catch (InputMismatchException ime) {
          out.append("Input mismatch: ")
                  .append(ime.getMessage())
                  .append("\n");
        }
      }


      if (m.isPlayerAlive() && m.getPlayerLocation().equals(m.getEnd())) {
        out.append(String.format("Your Location: %s ; YES! Exit found at %s\n",
                m.getPlayerLocation(),
                m.getEnd()));
        out.append(String.format("YOU WIN!!! Reached exit %s\n", m.getEnd()));
      }

      else if (!m.isPlayerAlive()) {
        out.append("DEAD! You encountered a monster and didn't survive\n");
      }
    }
    catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed", ioe);
    }
  }
}
