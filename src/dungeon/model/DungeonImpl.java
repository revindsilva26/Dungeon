package dungeon.model;

import dungeon.randomizer.IRandomNumberGenerator;
import dungeon.randomizer.PseudoRandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DungeonImpl class is used to create
 * Dungeon objects with all its associated
 * functionalities. It implements Dungeon
 * interface. It handles the functionality for
 * getting information about the dungeon itself,
 * as well as, actions like move, pickup, shoot.
 */
public class DungeonImpl implements Dungeon {

  private final int rows;
  private final int columns;
  private final int numNodes;
  private final boolean wrapping;
  private final int interconnectivity;
  private final int initialMonsters;
  private int startNode;
  private int endNode;
  private Position start;
  private Position end;
  private List<List<Cave>> grid;
  private final List<List<Integer>> edgesList;
  private final Player player;
  private Position playerLocation;
  private final IRandomNumberGenerator rng;
  private final int[][] adjacencyMatrix;
  private final int percentageOfTreasures;
  private final Set<Position> visited;

  /**
   * Constructor for DungeonImpl class.
   */
  public DungeonImpl(boolean wrapping,
                 int rows,
                 int columns,
                 int interconnectivity,
                     int percentageOfTreasures,
                     int numMonsters,
                     IRandomNumberGenerator rng)
          throws IllegalArgumentException {

    if (rows < 6 || columns < 6) {
      throw new IllegalArgumentException(
              "Rows and Columns values should be >= 6");
    }

    if (rng == null) {
      throw new IllegalArgumentException("Null not allowed");
    }

    if (percentageOfTreasures < 0
            || percentageOfTreasures > 100) {
      throw new IllegalArgumentException("Percentage must be between 0-100");
    }

    if (interconnectivity < 0) {
      throw new IllegalArgumentException("Interconnectivity can't be less than 0");
    }

    if (numMonsters < 1) {
      throw new IllegalArgumentException("Can't have no. of monsters < 1");
    }

    this.rows = rows;
    this.columns = columns;
    this.rng = rng;
    this.numNodes = rows * columns;
    this.interconnectivity = interconnectivity;
    this.percentageOfTreasures = percentageOfTreasures;
    this.wrapping = wrapping;
    Kruskal k = new Kruskal(wrapping, rows, columns, interconnectivity);
    k.kruskalGrid(rng);

    // get list of edges that are connected
    this.edgesList = k.getEdgeList();

    //print for reference
    //System.out.println(this.edgesList);
    this.adjacencyMatrix = new int[this.numNodes][this.numNodes];
    this.generateAdjacencyMatrix();

    // this.playerNodeLocation = -1;
    this.startNode = -1;
    this.endNode = -1;
    this.start = new Position(-1, -1);
    this.end = new Position(-1, -1);
    this.playerLocation = new Position(-1, -1);

    this.grid = new ArrayList<>();
    this.visited = new HashSet<>();

    this.initialMonsters = numMonsters;

    // generate grid
    this.generateGrid();
    //System.out.println(this.grid);

    if (this.initialMonsters > this.numCaves()) {
      throw new IllegalArgumentException(
              "Monsters count should not exceed Cave count "
                      + this.numCaves());
    }

    if (this.initialMonsters == 0) {
      throw new IllegalArgumentException(
              "Monsters count should be greater than 0.");
    }

    // create player and add him to the
    // dungeon, update all locations
    this.player = new PlayerImpl("LaraCroft");
    this.setStartEndAndPlayerLoc();
    this.addMonstersToCaves();
    this.visited.add(this.getPlayerLocation());
    this.addThievesToCaves();
  }

  /**
   * Helper method to add special monster
   * Thief to caves.
   */
  private void addThievesToCaves() {
    IRandomNumberGenerator rng1 = new PseudoRandomNumberGenerator();

    List<Integer> nodeList = new ArrayList<>(this.getDungeonGrid().size());
    for (int i = 0; i < this.getRows() * this.getColumns(); i++) {
      nodeList.add(i);
    }

    // remove start and end node from consideration
    nodeList.remove(Integer.valueOf(this.startNode));
    nodeList.remove(Integer.valueOf(this.endNode));

    // Choosing which caves to place thieves in
    Set<Integer> choices = new HashSet<>();

    while (choices.size() != this.initialMonsters / 2) {
      int choice1 = rng1.getRandomNumber(0, nodeList.size() - 2);
      choices.add(nodeList.get(choice1));
      nodeList.remove(Integer.valueOf(nodeList.get(choice1)));
    }

    // Place thieves after choosing
    // which nodes to place them in
    // However, if an otyugh is in same cave
    // then thief will not be placed.
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (choices.contains(this.grid.get(i).get(j).getNodeNum())) {
          if (!((CaveImpl) this.grid.get(i).get(j)).liveMonsterPresent()) {
            ((CaveImpl) this.grid.get(i).get(j)).setThiefHealth(HealthStatus.ALIVE);
          }
        }
      }
    }
  }

  /**
   * Helper method to add monsters to caves.
   */
  private void addMonstersToCaves() {
    // Choosing which caves to place Monsters in
    Set<Integer> choices = new HashSet<>();
    choices.add(this.endNode);

    List<Integer> caveList = this.getCaveList();

    // remove start node from consideration
    caveList.remove(Integer.valueOf(this.startNode));

    while (choices.size() != this.initialMonsters) {
      int choice1 = rng.getRandomNumber(0, caveList.size() - 1);
      choices.add(caveList.get(choice1));
      caveList.remove(Integer.valueOf(caveList.get(choice1)));
    }

    // Place monsters after choosing
    // which nodes to place them in
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (choices.contains(this.grid.get(i).get(j).getNodeNum())) {
          ((CaveImpl) this.grid.get(i).get(j)).setMonsterHealth(HealthStatus.ALIVE);
        }
      }
    }
  }

  /**
   * Helper method to convert node number
   * to row, column co-ordinates in Position
   * class.
   * @param node int value denoting the node
   * @return Position object of row, column coordinates
   */
  private Position convertNodeToCoordinate(int node) {
    int r = 0;
    int c = 0;

    r = node / this.columns;
    c = node % this.columns;

    return new Position(r, c);
  }

  /**
   * Helper method to convert row, column
   * coordinates to node number.
   * @param p Position object of row, column coordinates
   * @return int value indicating node number
   */
  private int convertCoordinateToNode(Position p) {
    int r = p.getRow();
    int c = p.getColumn();

    return (r * this.columns) + c;
  }

  /**
   * Helper method to initialize the Start and End
   * co-ordinates as well as Player location
   * co-ordinates when Dungeon grid is just generated.
   */
  private void setStartEndAndPlayerLoc()
          throws IllegalArgumentException {

    int starting = 0;
    int ending = 0;
    boolean invalid = true;
    do {
      starting = this.rng.getRandomNumber(0,
              this.numNodes - 1);
      ending = this.rng.getRandomNumber(0,
              this.numNodes - 1);
      int length = new BfsCheck(
              this.edgesList,
              starting,
              ending,
              this.numNodes)
              .getLength();
      if ((length >= 5)
              && (starting >= 0)
              && (starting <= this.numNodes - 1)
              && (ending >= 0)
              && (ending <= this.numNodes - 1)
              && (isCave(starting))
              && (isCave(ending))) {
        invalid = false;
      }
    }
    while (invalid);

    this.startNode = starting;
    this.endNode = ending;

    this.start = convertNodeToCoordinate(this.startNode);
    this.end = convertNodeToCoordinate(this.endNode);
    //this.playerNodeLocation = this.startNode;
    this.playerLocation = new Position(this.start.getRow(),
            this.start.getColumn());
  }

  /**
   * Helper method to generate adjacency
   * matrix of Dungeon.
   */
  private void generateAdjacencyMatrix() {
    // initialize with 0s
    for (int i = 0; i < this.numNodes; i++) {
      for (int j = 0; j < this.numNodes; j++) {
        this.adjacencyMatrix[i][j] = 0;
      }
    }
    // change to 1 if in edgesList
    for (List<Integer> edge : this.edgesList) {
      this.adjacencyMatrix[edge.get(0)][edge.get(1)] = 1;
      this.adjacencyMatrix[edge.get(1)][edge.get(0)] = 1;
    }
  }

  /**
   * Helper method to check if a node is
   * a cave or not.
   * @param nodeNum the node index number
   * @return boolean indicating true if it is a cave
   */
  private boolean isCave(int nodeNum) {
    int count = 0;
    for (int i = 0; i < this.numNodes; i++) {
      if (this.adjacencyMatrix[nodeNum][i] == 1) {
        count += 1;
      }
    }
    return count != 2;
  }

  /**
   * Helper method to count number of caves
   * in the Dungeon.
   */
  private int numCaves() {
    int caveCount = 0;
    for (int i = 0; i < this.numNodes; i++) {
      if (isCave(i)) {
        caveCount += 1;
      }
    }
    return caveCount;
  }

  /**
   * Helper method to generate Dungeon grid.
   */
  private void generateGrid() {
    int caveCount = this.numCaves();

    //System.out.println("CC:" + caveCount);
    int cavesWithTreasures = (int) Math.round(((double)
            this.percentageOfTreasures / 100) * caveCount);


    //System.out.println("CWT:" + cavesWithTreasures);
    int cwtCopy = cavesWithTreasures;
    int nodeIndex = 0;

    //final grid of dungeon
    List<List<Cave>> gridDungeon = new ArrayList<>();
    for (int i = 0; i < this.rows; i++) {
      List<Cave> caveArr = new ArrayList<>();
      while (caveArr.size() != this.columns) {
        List<List<Object>> possibleExits = new ArrayList<>();
        // create possible exits list
        for (int k = 0; k < this.numNodes; k++) {
          if (this.adjacencyMatrix[nodeIndex][k] == 1) {
            List<Object> li = new ArrayList<>();
            li.add(k);
            if ((((nodeIndex + 1) == k))
                    || ((((nodeIndex + 1) % this.columns) == 0)
                    && ((nodeIndex + 1 - this.columns) == k))) {
              li.add(Direction.EAST);
            } else if (((nodeIndex + this.columns) == k)
                    || ((nodeIndex - (this.columns * (this.rows - 1)) == k)
                    && (((nodeIndex + this.columns) >= this.numNodes)))) {
              li.add(Direction.SOUTH);
            } else if (((nodeIndex - this.columns) == k)
                    || (((nodeIndex + (this.columns * (this.rows - 1))) == k))
                    && (((nodeIndex - this.columns) < 0))) {
              li.add(Direction.NORTH);
            } else {
              li.add(Direction.WEST);
            }

            possibleExits.add(li);
          }
        }

        Cave cave = new CaveImpl(nodeIndex, possibleExits);
        caveArr.add(cave);
        nodeIndex += 1;
      }
      gridDungeon.add(caveArr);
    }

    // HERE: We add treasures according to percentage
    //
    List<Integer> caveList = new ArrayList<>();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (isCave(gridDungeon.get(i).get(j).getNodeNum())) {
          caveList.add(gridDungeon.get(i).get(j).getNodeNum());
        }
      }
    }
    //System.out.println(caveList);

    // Choosing which caves to place treasures in
    Set<Integer> choices = new HashSet<>();
    while (choices.size() != cavesWithTreasures) {
      int choice1 = rng.getRandomNumber(0, caveList.size() - 1);
      choices.add(choice1);
    }

    int cavesWithWeapons = (int) Math.round(((double)
            this.percentageOfTreasures / 100) * (rows * columns));

    // Choosing which nodes to place weapons in
    Set<Integer> choicesForWeapons = new HashSet<>();
    while (choicesForWeapons.size() != cavesWithWeapons) {
      int choice1 = rng.getRandomNumber(0, (rows * columns) - 1);
      choicesForWeapons.add(choice1);
    }

    // Place weapons after choosing
    // which nodes to place them in
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        List<Weapon> ww = new ArrayList<>();
        if (choicesForWeapons.contains(gridDungeon.get(i).get(j).getNodeNum())) {
          int numberOfWeapons = rng.getRandomNumber(1, 3);
          for (int k = 0; k < numberOfWeapons; k++) {
            ww.add(Weapon.CROOKEDARROW);
          }
          ((CaveImpl) gridDungeon.get(i).get(j)).addWeaponList(ww);
        }
      }
    }


    // Place treasures after choosing
    // which nodes to place them in
    //System.out.println(choices);
    List<Integer> caveChoices = new ArrayList<>(choices.size());
    for (Integer choice : choices) {
      caveChoices.add(caveList.get(choice));
    }
    //System.out.println(caveChoices);
    Treasure[] t = {Treasure.RUBY, Treasure.DIAMOND, Treasure.SAPPHIRE};
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        List<Treasure> tt = new ArrayList<>();
        if (caveChoices.contains(gridDungeon.get(i).get(j).getNodeNum())) {
          int numberOfTreasures = rng.getRandomNumber(1, 3);
          for (int k = 0; k < numberOfTreasures; k++) {
            tt.add(t[rng.getRandomNumber(0, 2)]);
          }
          ((CaveImpl) gridDungeon.get(i).get(j)).addTreasure(tt);
        }
      }
    }
    this.grid = gridDungeon;
  }

  /**
   * Helper method to get the list of
   * all caves in the Dungeon.
   * @return List
   */
  private List<Integer> getCaveList() {
    List<Integer> caveList = new ArrayList<>();
    List<List<Cave>> gridDungeon = this.getDungeonGrid();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (isCave(gridDungeon.get(i).get(j).getNodeNum())) {
          caveList.add(gridDungeon.get(i).get(j).getNodeNum());
        }
      }
    }
    return caveList;
  }

  @Override
  public Position getStart() {
    return new Position(
            this.start.getRow(),
            this.start.getColumn());
  }

  @Override
  public Position getEnd() {
    return new Position(
            this.end.getRow(),
            this.end.getColumn());
  }

  @Override
  public List<List<Cave>> getDungeonGrid() {
    List<List<Cave>> arr = new ArrayList<>(this.grid.size());
    for (List<Cave> a : this.grid) {
      List<Cave> a1 = new ArrayList<>(a.size());
      for (Cave c: a) {
        Cave c1 = new CaveImpl(c);
        a1.add(c1);
      }
      arr.add(a1);
    }
    return arr;
  }

  @Override
  public boolean movePlayer(Direction d)
          throws IllegalArgumentException {
    if (d == null) {
      throw new IllegalArgumentException(
              "Null values not allowed");
    }

    this.visited.add(this.getPlayerLocation());

    int r = this.playerLocation.getRow();
    int c = this.playerLocation.getColumn();
    Cave cave = new CaveImpl(this.getDungeonGrid().get(r).get(c));
    List<List<Object>> exits = cave.getExits();

    switch (d) {
      case EAST:
        for (List<Object> exit : exits) {
          if (exit.get(1) == Direction.EAST) {
            this.playerLocation = convertNodeToCoordinate((Integer) exit.get(0));
            checkForMonster(this.playerLocation);
            checkForThieves(this.playerLocation);
            this.visited.add(this.getPlayerLocation());
            return true;
          }
        }
        break;
      case WEST:
        for (List<Object> exit : exits) {
          if (exit.get(1) == Direction.WEST) {
            this.playerLocation = convertNodeToCoordinate((Integer) exit.get(0));
            checkForMonster(this.playerLocation);
            checkForThieves(this.playerLocation);
            this.visited.add(this.getPlayerLocation());
            return true;
          }
        }
        break;
      case NORTH:
        for (List<Object> exit : exits) {
          if (exit.get(1) == Direction.NORTH) {
            this.playerLocation = convertNodeToCoordinate((Integer) exit.get(0));
            checkForMonster(this.playerLocation);
            checkForThieves(this.playerLocation);
            this.visited.add(this.getPlayerLocation());
            return true;
          }
        }
        break;
      case SOUTH:
        for (List<Object> exit : exits) {
          if (exit.get(1) == Direction.SOUTH) {
            this.playerLocation = convertNodeToCoordinate((Integer) exit.get(0));
            checkForMonster(this.playerLocation);
            checkForThieves(this.playerLocation);
            this.visited.add(this.getPlayerLocation());
            return true;
          }
        }
        break;
      default:
        return false;
    }

    return false;
  }

  @Override
  public boolean movePlayer(Position p) {
    if (p == null) {
      throw new IllegalArgumentException("Can't move to that position.");
    }

    this.visited.add(this.getPlayerLocation());

    int nodeNum = convertCoordinateToNode(p);
    Direction dirToMove = null;

    // Check if it is a valid move
    List<List<Object>> exits = this.getDungeonGrid()
            .get(this.playerLocation.getRow())
            .get(this.playerLocation.getColumn())
            .getExits();

    for (List<Object> o : exits) {
      int newNodeNum = (Integer) o.get(0);
      if (newNodeNum == nodeNum) {
        dirToMove = (Direction) o.get(1);
      }
    }

    if (dirToMove != null) {
      switch (dirToMove) {
        case EAST:
          for (List<Object> exit : exits) {
            if (exit.get(1) == Direction.EAST) {
              this.playerLocation = convertNodeToCoordinate((Integer) exit.get(0));
              checkForMonster(this.playerLocation);
              checkForThieves(this.playerLocation);
              this.visited.add(this.getPlayerLocation());
              return true;
            }
          }
          break;
        case WEST:
          for (List<Object> exit : exits) {
            if (exit.get(1) == Direction.WEST) {
              this.playerLocation = convertNodeToCoordinate((Integer) exit.get(0));
              checkForMonster(this.playerLocation);
              checkForThieves(this.playerLocation);
              this.visited.add(this.getPlayerLocation());
              return true;
            }
          }
          break;
        case NORTH:
          for (List<Object> exit : exits) {
            if (exit.get(1) == Direction.NORTH) {
              this.playerLocation = convertNodeToCoordinate((Integer) exit.get(0));
              checkForMonster(this.playerLocation);
              checkForThieves(this.playerLocation);
              this.visited.add(this.getPlayerLocation());
              return true;
            }
          }
          break;
        case SOUTH:
          for (List<Object> exit : exits) {
            if (exit.get(1) == Direction.SOUTH) {
              this.playerLocation = convertNodeToCoordinate((Integer) exit.get(0));
              checkForMonster(this.playerLocation);
              checkForThieves(this.playerLocation);
              this.visited.add(this.getPlayerLocation());
              return true;
            }
          }
          break;
        default:
          return false;
      }
    }

    return false;
  }

  /**
   * Helper method to change player state if
   * he encounters a Monster upon moving to a
   * Dungeon node.
   */
  private void checkForMonster(Position playerLocation) {

    if (!this.isPlayerAlive()) {
      return;
    }

    if (this.getDungeonGrid()
            .get(playerLocation
                    .getRow())
            .get(playerLocation
                    .getColumn())
            .getMonster()
            .getHealthStatus() == HealthStatus.ALIVE) {
      ((PlayerImpl) this.player).setDead();
    }
    else if (this.getDungeonGrid()
            .get(playerLocation
                    .getRow())
            .get(playerLocation
                    .getColumn())
            .getMonster()
            .getHealthStatus() == HealthStatus.WOUNDED) {
      if (rng.getRandomNumber(0, 1) == 1) {
        ((PlayerImpl) this.player).setDead();
      }
    }
  }

  /**
   * Helper method to change player state if
   * he encounters a Monster upon moving to a
   * Dungeon node.
   */
  private void checkForThieves(Position playerLocation) {
    if (this.getDungeonGrid()
            .get(playerLocation
                    .getRow())
            .get(playerLocation
                    .getColumn())
            .liveThiefPresent()) {
      ((PlayerImpl) this.player).resetTreasure();
    }
  }

  @Override
  public void collectItems() {

    if (!this.isPlayerAlive()) {
      return;
    }

    this.collectTreasure();
    this.collectWeapons();

  }

  /**
   * Helper method to collect treasures at a location.
   */
  private void collectTreasure() {
    int r = this.playerLocation.getRow();
    int c = this.playerLocation.getColumn();
    // List<Treasure> result = new ArrayList<>();
    List<Treasure> treasures = (this.getDungeonGrid()
            .get(r)
            .get(c)
            .getTreasure());
    if (treasures.size() == 0) {
      return;
    }
    for (Treasure treasure : treasures) {
      ((PlayerImpl) this.player).addTreasure(treasure);
      //result.add(Treasure.valueOf(treasure.getValue()));
      ((CaveImpl) this.grid.get(r).get(c)).removeTreasure(treasure);
    }
  }

  /**
   * Helper method to collect treasures at a location.
   */
  private void collectWeapons() {
    int r = this.playerLocation.getRow();
    int c = this.playerLocation.getColumn();
    // List<Treasure> result = new ArrayList<>();
    List<Weapon> weapons = (this.getDungeonGrid()
            .get(r)
            .get(c)
            .getWeapons());
    if (weapons.size() == 0) {
      return;
    }
    for (Weapon weapon : weapons) {
      ((PlayerImpl) this.player).addWeapon(weapon);
      //result.add(Treasure.valueOf(treasure.getValue()));
      ((CaveImpl) this.grid.get(r).get(c)).removeWeapon(weapon);
    }
  }

  @Override
  public List<Object> describeLocation() {
    List<Object> objects = new ArrayList<>();
    objects.add(this.playerLocation);
    Cave c1 = this.getDungeonGrid()
            .get(this.playerLocation.getRow())
            .get(this.playerLocation.getColumn());
    objects.add(c1);
    return objects;
  }

  @Override
  public List<Object> describePlayer() {
    List<Object> objects = this.player.describeState();
    return objects;
  }

  @Override
  public boolean usePlayerWeapon(Direction dir, int distance) {
    if (dir == null) {
      throw new IllegalArgumentException("Null values not allowed");
    }

    if (this.player.getPlayerWeapons().size() == 0) {
      throw new IllegalArgumentException("No weapons available");
    }

    if (distance < 1) {
      throw new IllegalArgumentException("Min. distance is 1");
    }

    if (!this.isPlayerAlive()) {
      throw new IllegalArgumentException("Player is dead!");
    }

    Position src = new Position(
            this.playerLocation.getRow(),
            this.playerLocation.getColumn());

    Cave curCave = this.getDungeonGrid()
            .get(this.getPlayerLocation().getRow())
            .get(this.getPlayerLocation().getColumn());
    Position curLocation = convertNodeToCoordinate(curCave.getNodeNum());

    int turn = 0;
    int distanceCopy = distance;

    while (distanceCopy != 0 || !isCave(this.getDungeonGrid()
            .get(curLocation.getRow())
            .get(curLocation.getColumn())
            .getNodeNum())) {
      //System.out.println("---> starting at " + curLocation);
      //System.out.println("---> distance left " + distanceCopy);

      List<List<Object>> possibleExits = curCave.getExits();

      List<Direction> dirs = new ArrayList<>(possibleExits.size());
      for (List<Object> exit : possibleExits) {
        dirs.add((Direction) exit.get(1));
      }

      if (!dirs.contains(dir)) {
        throw new IllegalArgumentException("Can't shoot in that direction");
      }

      boolean flag = false;

      if (turn == 0) {
        if (isCave(curCave.getNodeNum())) {
          for (List<Object> exit : possibleExits) {
            if (exit.get(1) == dir) {
              flag = true;
              src = curLocation;
              curLocation = convertNodeToCoordinate((Integer) exit.get(0));
              break;
            }
          }

          if (!flag) {
            throw new IllegalArgumentException("Choose only from exits available");
          }

          ((PlayerImpl) this.player).removeWeapon(Weapon.CROOKEDARROW);
          distanceCopy -= 1;
        } else {
          for (List<Object> exit : possibleExits) {
            if (exit.get(1) == dir) {
              flag = true;
              src = curLocation;
              curLocation = convertNodeToCoordinate((Integer) exit.get(0));
              break;
            }
          }

          if (!flag) {
            throw new IllegalArgumentException("Choose only from exits available");
          }

          ((PlayerImpl) this.player).removeWeapon(Weapon.CROOKEDARROW);
        }
        turn += 1;
      } else {

        possibleExits = this.getDungeonGrid()
                .get(curLocation.getRow())
                .get(curLocation.getColumn())
                .getExits();
        flag = false;

        if (isCave(this.getDungeonGrid()
                .get(curLocation.getRow())
                .get(curLocation.getColumn())
                .getNodeNum())) {

          for (List<Object> exit : possibleExits) {
            if (exit.get(1) == dir
                    && (((Integer) exit.get(0))
                    != (this.getDungeonGrid()
                                    .get(src.getRow())
                                    .get(src.getColumn())
                                    .getNodeNum()))) {
              flag = true;
              src = curLocation;
              curLocation = convertNodeToCoordinate((Integer) exit.get(0));
              break;
            }
          }

          if (!flag) {
            return false;
          }

          distanceCopy -= 1;
          turn += 1;
        } else {
          Position temp = null;
          Direction newDir = null;
          for (List<Object> exit : possibleExits) {
            //System.out.println("s r c ... " + src);
            //System.out.println("exit ... " + exit);
            if ((Integer) (exit.get(0))
                    != (this.getDungeonGrid()
                            .get(src.getRow())
                            .get(src.getColumn())
                            .getNodeNum())) {
              temp = curLocation;
              newDir = (Direction) exit.get(1);
              curLocation = convertNodeToCoordinate((Integer) exit.get(0));
              break;
            }
          }
          flag = true;
          turn += 1;
          if (temp == null || newDir == null) {
            throw new IllegalArgumentException("Must not be possible");
          }
          src = temp;
          dir = newDir;
        }
      }
    }

    //System.out.println("starting at " + curLocation);
    //System.out.println("distance left " + distanceCopy);
    Cave finalCave = this.grid
            .get(curLocation.getRow())
            .get(curLocation.getColumn());

    if (finalCave.liveMonsterPresent()) {
      if (finalCave.getMonster().getHealthStatus() == HealthStatus.ALIVE) {
        ((CaveImpl) finalCave).setMonsterHealth(HealthStatus.WOUNDED);
        return true;
      }

      if (finalCave.getMonster().getHealthStatus() == HealthStatus.WOUNDED) {
        ((CaveImpl) finalCave).setMonsterHealth(HealthStatus.DEAD);
        return true;
      }
    }

    return false;

  }

  @Override
  public int getRows() {
    return this.rows;
  }

  @Override
  public int getColumns() {
    return this.columns;
  }

  @Override
  public boolean isWrapping() {
    return this.wrapping;
  }

  @Override
  public int getPercentageTreasures() {
    return this.percentageOfTreasures;
  }

  @Override
  public int getInterconnectivity() {
    return this.interconnectivity;
  }

  @Override
  public int getNumberOfMonsters() {
    return this.initialMonsters;
  }

  @Override
  public Set<Position> getVisitedNodes() {
    return this.visited;
  }

  @Override
  public List<Weapon> getWeaponsAtLocation() {
    int r = this.playerLocation.getRow();
    int c = this.playerLocation.getColumn();
    // List<Treasure> result = new ArrayList<>();
    List<Weapon> weapons = (this.getDungeonGrid()
            .get(r)
            .get(c)
            .getWeapons());

    List<Weapon> arr = new ArrayList<Weapon>(weapons.size());
    arr.addAll(weapons);
    return arr;
  }

  @Override
  public List<Weapon> getWeaponsOfPlayer() {
    return new ArrayList<>(this.player.getPlayerWeapons());
  }

  @Override
  public List<Treasure> getTreasureAtLocation() {
    int r = this.playerLocation.getRow();
    int c = this.playerLocation.getColumn();
    // List<Treasure> result = new ArrayList<>();
    List<Treasure> treasures = (this.getDungeonGrid()
            .get(r)
            .get(c)
            .getTreasure());

    List<Treasure> arr = new ArrayList<Treasure>(treasures.size());
    arr.addAll(treasures);
    return arr;
  }

  @Override
  public List<Treasure> getTreasureOfPlayer() {
    return new ArrayList<>(this.player.getPlayerTreasures());
  }

  @Override
  public Position getPlayerLocation() {
    return new Position(
            this.playerLocation.getRow(),
            this.playerLocation.getColumn());
  }

  @Override
  public SmellStrength describeSmellAtLocation() {
    BfsCheck b = new BfsCheck(
            this.edgesList,
            this.startNode,
            this.endNode,
            this.numNodes);

    List<Integer> firstNeighbours =
            b.getNeighboursSpecifiedDistanceAway(
                    this.getDungeonGrid()
                            .get(this.getPlayerLocation()
                                    .getRow())
                            .get(this.getPlayerLocation()
                                    .getColumn())
                            .getNodeNum(),
                    this.numNodes,
                    1);

    List<Integer> secondNeighbours =
            b.getNeighboursSpecifiedDistanceAway(
                    this.getDungeonGrid()
                            .get(this.getPlayerLocation()
                                    .getRow())
                            .get(this.getPlayerLocation()
                                    .getColumn())
                            .getNodeNum(),
                    this.numNodes,
                    2);

    int count1 = 0;
    for (Integer neighbour1 : firstNeighbours) {
      Position p = convertNodeToCoordinate(neighbour1);
      Cave c = this.getDungeonGrid().get(p.getRow()).get(p.getColumn());
      if (c.liveMonsterPresent()) {
        count1 += 1;
      }
    }
    //System.out.println("First neighbours: " + firstNeighbours);
    //System.out.println(count1);

    int count2 = 0;
    for (Integer neighbour2 : secondNeighbours) {
      Position p = convertNodeToCoordinate(neighbour2);
      Cave c = this.getDungeonGrid().get(p.getRow()).get(p.getColumn());
      if (c.liveMonsterPresent()) {
        count2 += 1;
      }
    }
    //System.out.println("Second neighbours: " + secondNeighbours);
    //System.out.println(count2);

    if (count1 >= 1 || count2 > 1) {
      return SmellStrength.STRONG;
    }

    else if (count2 == 1) {
      return SmellStrength.SLIGHT;
    }

    else {
      return SmellStrength.NONE;
    }

  }

  @Override
  public boolean isPlayerAlive() {
    return this.player.isAlive();
  }

  @Override
  public String getPlayerName() {
    return this.player.getName();
  }

  @Override
  public List<Direction> getExitsFromPlayerLocation() {
    List<List<Object>> exits = this.getDungeonGrid()
            .get(this.playerLocation.getRow())
            .get(this.playerLocation.getColumn()).getExits();
    List<Direction> dir = new ArrayList<>();
    for (List<Object> exit : exits) {
      dir.add((Direction) exit.get(1));
    }
    return dir;
  }

  @Override
  public boolean isPlayerLocationCave() {
    return isCave(this.getDungeonGrid()
            .get(this.playerLocation.getRow())
            .get(this.playerLocation.getColumn())
            .getNodeNum());
  }

  @Override
  public HealthStatus getMonsterHealth() {
    return this.getDungeonGrid()
            .get(this.playerLocation.getRow())
            .get(this.playerLocation.getColumn())
            .getMonster()
            .getHealthStatus();
  }

  @Override
  public boolean isThiefPresent() {
    return this.getDungeonGrid()
            .get(this.playerLocation.getRow())
            .get(this.playerLocation.getColumn())
            .liveThiefPresent();
  }
}
