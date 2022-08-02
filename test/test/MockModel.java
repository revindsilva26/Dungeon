package test;

import dungeon.model.Direction;
import dungeon.model.Dungeon;
import dungeon.model.HealthStatus;
import dungeon.model.Position;
import dungeon.model.SmellStrength;
import dungeon.model.Treasure;
import dungeon.model.Weapon;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * A mock to simulate passing values to the model.
 */
public class MockModel implements Dungeon {

  private final Appendable appendable;

  /**
   * Constructor to initialize mock model.
   * @param appendable Appendable to write the
   *                   input received from controller
   */
  public MockModel(Appendable appendable) {
    this.appendable = appendable;
  }

  @Override
  public boolean movePlayer(Direction d) {
    try {
      this.appendable.append("Received input: " + d.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean movePlayer(Position p) {
    try {
      this.appendable.append("Received input: " + p.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public void collectItems() {
    try {
      this.appendable.append("Called collectItems()");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean usePlayerWeapon(Direction d, int distance) {
    try {
      this.appendable.append(String.format(
              "Received inputs: %s and %d", d.getValue(), distance));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public Position getStart() {
    try {
      this.appendable.append("Called getStart()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Position(0, 0);
  }

  @Override
  public Position getEnd() {
    try {
      this.appendable.append("Called getStart()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Position(6, 6);
  }

  @Override
  public List getDungeonGrid() {
    try {
      this.appendable.append("Called getDungeonGrid()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Object> describeLocation() {
    try {
      this.appendable.append("Called describeLocation()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Object> describePlayer() {
    try {
      this.appendable.append("Called describePlayer()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Weapon> getWeaponsAtLocation() {
    try {
      this.appendable.append("Called getWeaponsAtLocation()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Weapon> getWeaponsOfPlayer() {
    try {
      this.appendable.append("Called getWeaponsOfPlayer()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Treasure> getTreasureAtLocation() {
    try {
      this.appendable.append("Called getTreasureAtLocation");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Treasure> getTreasureOfPlayer() {
    try {
      this.appendable.append("Called getTreasureOfPlayer()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Position getPlayerLocation() {
    try {
      this.appendable.append("Called getPlayerLocation()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Position(5, 5);
  }

  @Override
  public SmellStrength describeSmellAtLocation() {
    try {
      this.appendable.append("Called describeSmellAtLocation()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return SmellStrength.SLIGHT;
  }

  @Override
  public boolean isPlayerAlive() {
    try {
      this.appendable.append("Called isPlayerAlive()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public String getPlayerName() {
    try {
      this.appendable.append("Called getPlayerName()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "Nash";
  }

  @Override
  public List<Direction> getExitsFromPlayerLocation() {
    try {
      this.appendable.append("Called getExitsFromPlayerLocation()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean isPlayerLocationCave() {
    try {
      this.appendable.append("Called isPlayerLocationCave()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public HealthStatus getMonsterHealth() {
    try {
      this.appendable.append("Called getMonsterHealth()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return HealthStatus.ALIVE;
  }

  @Override
  public boolean isThiefPresent() {
    try {
      this.appendable.append("Called isPlayerLocationCave()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public int getRows() {
    try {
      this.appendable.append("Called getRows");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 6;
  }

  @Override
  public int getColumns() {
    try {
      this.appendable.append("Called getColumns()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 6;
  }

  @Override
  public boolean isWrapping() {
    try {
      this.appendable.append("Called isWrapping()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public int getPercentageTreasures() {
    try {
      this.appendable.append("Called getPercentageTreasures()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public int getInterconnectivity() {
    try {
      this.appendable.append("Called getInterconnectivity()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public int getNumberOfMonsters() {
    try {
      this.appendable.append("Called getNumberOfMonsters()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public Set<Position> getVisitedNodes() {
    try {
      this.appendable.append("Called getNumberOfMonsters()");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
