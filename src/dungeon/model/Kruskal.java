package dungeon.model;

import dungeon.randomizer.IRandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to generate paths between caves
 * using the Kruskal's algorithm with minor
 * adjustments to account for interconnectivity.
 * Made package-private since all accesses happen
 * from within DungeonImpl class.
 */
class Kruskal {

  private int v;
  private int[] parent;
  private int interconnectivity;
  private int[][] adjacencyMatrix;
  private static int inf = Integer.MAX_VALUE;
  private List<List<Integer>> edgeList;

  /**
   * Constructor for Kruskal class that takes following
   * parameters that are used to generate the list of edges
   * between caves in the dungeon.
   * @param wrapping boolean value to indicate a
   *                 wrapping dungeon if true
   * @param rows number of rows in dungeon grid
   * @param columns number of columns in dungeon grid
   * @param interconnectivity extra connections between caves in
   *                          the dungeon
   */
  Kruskal(boolean wrapping,
          int rows,
          int columns,
          int interconnectivity) {
    this.v = rows * columns;
    this.parent = new int[this.v];
    // this.wrapping = wrapping;
    this.interconnectivity = interconnectivity;
    this.adjacencyMatrix = new int[this.v][this.v];
    this.adjacencyMatrix = buildAdjacencyMatrix(rows, columns, wrapping);
    this.edgeList = new ArrayList<>();
  }

  // Build Adjacency matrix
  static int[][] buildAdjacencyMatrix(int rows,
                                      int columns,
                                      boolean wrapping) {
    int totalNodes = rows * columns;
    int[][] mat = new int[totalNodes][totalNodes];
    // System.out.println(totalNodes);

    for (int i = 0; i < totalNodes; i++) {
      for (int j = 0; j < totalNodes; j++) {
        if (i == j) {
          mat[i][j] = inf;
        }

        // check east movement
        else if (j == (i + 1)) {
          if (((i + 1) % columns) == 0) {
            mat[i][j] = inf;
          } else {
            // System.out.println("East: " + i + "->" + j);
            mat[i][j] = 1;
          }
        }

        // check south movement
        else if (j == (i + columns)) {
          if ((i + columns) >= totalNodes) {
            mat[i][j] = inf;
          } else {
            // System.out.println("South: " + i + "->" + j);
            mat[i][j] = 1;
          }
        }

        // check west movement
        else if (j == (i - 1)) {
          if (((i - 1) % columns) == (columns - 1)) {
            mat[i][j] = inf;
          } else {
            // System.out.println("West: " + i + "->" + j);
            mat[i][j] = 1;
          }
        }

        // check north movement
        else if (j == (i - columns)) {
          if ((i - columns) < 0) {
            mat[i][j] = inf;
          } else {
            // System.out.println("North: " + i + "->" + j);
            mat[i][j] = 1;
          }
        }

        // can't reach
        else {
          mat[i][j] = inf;
        }
      }
    }

    if (wrapping) {
      for (int i = 0; i < totalNodes; i++) {
        // North move wrap
        if ((i - columns) < 0) {
          mat[i][i + (columns * (rows - 1))] = 1;
        }

        // East move wrap
        if (((i + 1) % columns) == 0) {
          mat[i][i + 1 - columns] = 1;
        }

        // South move wrap
        if ((i + columns) >= totalNodes) {
          mat[i][i - (columns * (rows - 1))] = 1;
        }

        // West move wrap
        if ((((i - 1) % columns) == (columns - 1)) || ((i - 1) < 0)) {
          mat[i][i - 1 + columns] = 1;
        }
      }
    }

    return mat;
  }

  // Find set of vertex i
  int find(int i) {
    while (parent[i] != i) {
      i = parent[i];
    }
    return i;
  }

  // Does union of i and j. It returns
  // false if i and j are already in same
  // set.
  void union1(int i, int j) {
    int a = find(i);
    int b = find(j);
    parent[a] = b;
  }

  // Finds MST using dungeon.model.Kruskal's algorithm
  void kruskalGrid(IRandomNumberGenerator rng)
          throws IllegalArgumentException {
    if (rng == null) {
      throw new IllegalArgumentException("Pass non-null arguments");
    }
    List<List<Integer>> leftover = new ArrayList<>();
    List<List<Integer>> edgeList = new ArrayList<>();

    // Initialize sets of disjoint sets.
    for (int i = 0; i < this.v; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < this.v; i++) {
      for (int j = 0; j < this.v; j++) {
        if (this.adjacencyMatrix[i][j] == 1) {
          if (find(i) != find(j)) {
            union1(i, j);
            List<Integer> arr = new ArrayList<>();
            arr.add(i);
            arr.add(j);
            edgeList.add(arr);
          }
          else {
            List<Integer> arr = new ArrayList<>();
            List<Integer> arr1 = new ArrayList<>();
            arr.add(i);
            arr.add(j);
            arr1.add(j);
            arr1.add(i);
            if (!(leftover.contains(arr1)) && !(edgeList.contains(arr1))) {
              leftover.add(arr);
            }
          }
        }
      }
    }

    if (leftover.size() < interconnectivity) {
      throw new IllegalArgumentException(
              "Interconnectivity is too high.");
    }

    int count = 0;
    while (count != this.interconnectivity) {
      int choice = rng.getRandomNumber(0, leftover.size() - 1);
      edgeList.add(leftover.get(choice));
      leftover.remove(choice);
      count += 1;
    }

    this.edgeList = edgeList;
  }

  // retrieve list of edges
  List<List<Integer>> getEdgeList() {
    List<List<Integer>> arr = new ArrayList<>(this.edgeList.size());
    for (List<Integer> a : this.edgeList) {
      List<Integer> a1 = new ArrayList<>(a.size());
      a1.addAll(a);
      arr.add(a1);
    }
    return arr;
  }

}