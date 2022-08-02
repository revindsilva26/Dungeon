package dungeon.model;

import java.util.ArrayList;
import java.util.List;

/**
 * PlayerImpl class is used to implement
 * functions outlined by the Player objects.
 * We create objects of type Player using
 * PlayerImpl class. Made package-private since all
 * accesses happen from within the Dungeon class.
 */
class PlayerImpl implements Player {

  private String name;
  private List<Treasure> treasureCollected;
  private List<Weapon> weaponsAvailable;
  private boolean isAlive;

  /**
   * PlayerImpl class constructor that is
   * used to instantiate PlayerImpl objects.
   * Made package-private since all accesses
   * happen from within the Dungeon class.
   * @param name String value for name
   */
  PlayerImpl(String name) {
    this.name = name;
    this.treasureCollected = new ArrayList<>();
    this.weaponsAvailable = new ArrayList<>();
    // Player gets to start with 3 Crooked arrows
    for (int i = 0; i < 3; i++) {
      this.weaponsAvailable.add(Weapon.CROOKEDARROW);
    }
    this.isAlive = true;
  }

  @Override
  public List<Object> describeState() {
    List<Object> arr = new ArrayList<>();
    arr.add(new String(name));
    List<Treasure> treasureColl = new ArrayList<>(this.treasureCollected);
    List<Weapon> weaponColl = new ArrayList<>(this.weaponsAvailable);
    arr.add(treasureColl);
    arr.add(weaponColl);
    return arr;
  }

  @Override
  public String getName() {
    return new String(this.name);
  }

  @Override
  public List<Weapon> getPlayerWeapons() {
    return new ArrayList<>(this.weaponsAvailable);
  }

  @Override
  public List<Treasure> getPlayerTreasures() {
    return new ArrayList<>(this.treasureCollected);
  }

  @Override
  public boolean isAlive() {
    return this.isAlive;
  }

  /**
   * Package-private method to set a player
   * as being dead if he encounters Monsters
   * in the Dungeon.
   */
  void setDead() {
    this.isAlive = false;
  }

  /**
   * Package-Private method to add treasure from
   * the cave that the Player is in.
   */
  void addTreasure(Treasure t) {
    this.treasureCollected.add(t);
  }

  /**
   * Package-Private method to reset treasure to empty list.
   */
  void resetTreasure() {
    this.treasureCollected = new ArrayList<>();
  }

  /**
   * Package-Private method to add weapon from
   * the cave that the Player is in.
   */
  void addWeapon(Weapon w) {
    this.weaponsAvailable.add(w);
  }

  /**
   * Package-Private method to remove weapon
   * that Player uses in the Dungeon.
   */
  void removeWeapon(Weapon w) {
    for (Weapon weapon : this.weaponsAvailable) {
      if (weapon.getValue().equals(w.getValue())) {
        this.weaponsAvailable.remove(weapon);
        return;
      }
    }
  }
}
