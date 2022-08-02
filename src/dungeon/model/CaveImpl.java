package dungeon.model;

import java.util.ArrayList;
import java.util.List;

/**
 * CaveImpl class is used to denote a node in
 * the Dungeon class. When a Cave has only
 * 2 exits, it is considered to be a Tunnel
 * and hence, will not be considered for start
 * or end position in Dungeon nor will it contain
 * any treasures. Made package-private since all accesses
 * happen from within the Dungeon class.
 */
class CaveImpl implements Cave {

  private final int nodeNum;
  private final List<Treasure> treasures;
  private final List<List<Object>> possibleExits;
  private List<Weapon> weapons;
  private final String type;
  private Monster monster;
  private Monster thief;

  /**
   * Constructor for CaveImpl class.
   * Made package-private since all accesses
   * happen from within the Dungeon class.
   */
  CaveImpl(int nodeNum, List<List<Object>> possibleExits) {

    if (possibleExits == null) {
      throw new IllegalArgumentException("Exits list is null");
    }

    this.nodeNum = nodeNum;
    this.treasures = new ArrayList<>();
    this.possibleExits = possibleExits;
    if (this.possibleExits.size() == 2) {
      this.type = "Tunnel";
    }
    else {
      this.type = "Cave";
    }
    this.weapons = new ArrayList<>();
    this.monster = new Otyugh(HealthStatus.DEAD);
    this.thief = new Thief(HealthStatus.DEAD);
  }

  /**
   * Copy constructor for CaveImpl class.
   * Made package private since all
   * accesses to happen from Dungeon class.
   */
  CaveImpl(Cave cave) {
    CaveImpl cave1 = (CaveImpl) cave;
    this.nodeNum = cave1.getNodeNum();
    this.type = cave1.getType();
    this.possibleExits = cave1.getExits();
    this.treasures = cave1.getTreasure();
    this.weapons = cave1.getWeapons();
    this.monster = cave1.getMonster();
    this.thief = cave1.getThief();
  }

  @Override
  public List<Treasure> getTreasure() {
    List<Treasure> arr = new ArrayList<>(this.treasures.size());
    arr.addAll(this.treasures);
    return arr;
  }

  @Override
  public List<List<Object>> getExits() {
    List<List<Object>> arr = new ArrayList<>(this.possibleExits.size());
    for (List<Object> a : this.possibleExits) {
      List<Object> a1 = new ArrayList<>(a.size());
      a1.addAll(a);
      arr.add(a1);
    }
    return arr;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public boolean liveMonsterPresent() {
    return this.monster.getHealthStatus() != HealthStatus.DEAD;
  }

  @Override
  public boolean liveThiefPresent() {
    return this.thief.getHealthStatus() == HealthStatus.ALIVE;
  }

  @Override
  public Monster getMonster() {
    return new Otyugh(this.monster.getHealthStatus());
  }

  @Override
  public Monster getThief() {
    return new Thief(this.thief.getHealthStatus());
  }

  @Override
  public List<Weapon> getWeapons() {
    List<Weapon> arr = new ArrayList<>(this.weapons.size());
    arr.addAll(this.weapons);
    return arr;
  }

  @Override
  public int getNodeNum() {
    return this.nodeNum;
  }

  /**
   * Package-private method removing treasure from
   * a Cave when player visits.
   */
  void removeTreasure(Treasure t) {
    this.treasures.remove(t);
  }

  /**
   * Package-private method removing weapon from
   * a Cave when player visits.
   */
  void removeWeapon(Weapon w) {
    this.weapons.remove(w);
  }

  /**
   * Package-private method to add treasure list.
   */
  void addTreasure(List<Treasure> t) {
    this.treasures.addAll(t);
  }

  /**
   * Package-private method to add weapon list.
   */
  void addWeaponList(List<Weapon> w) {
    this.weapons.addAll(w);
  }

  /**
   * Package-private method to set monster health.
   */
  void setMonsterHealth(HealthStatus hs) {
    ((Otyugh) (this.monster)).setHealthStatus(hs);
  }

  /**
   * Package-private method to set thief health.
   */
  void setThiefHealth(HealthStatus hs) {
    ((Thief) (this.thief)).setHealthStatus(hs);
  }

  @Override
  public String toString() {
    return String.format("%s, #%d, "
                    + "T: %s, E: %s, "
                    + "W: %s, M: %s",
            this.type,
            this.nodeNum,
            this.treasures.toString(),
            this.possibleExits.toString(),
            this.weapons.toString(),
            this.monster.toString());
  }
}
