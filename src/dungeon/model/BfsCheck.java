package dungeon.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class with methods to check
 * distances between Dungeon nodes.
 * Package-private since it is a class
 * of utility and implementation specific
 * methods.
 */
class BfsCheck {

  private final int length;
  private List<List<Integer>> adj;

  /**
   * Constructor for BfsCheck class.
   */
  BfsCheck(List<List<Integer>> edgesList, int source, int dest, int v) {

    // Adjacency list for storing which vertices are connected
    List<List<Integer>> adj = new ArrayList<>(v);
    for (int i = 0; i < v; i++) {
      adj.add(new ArrayList<>());
    }
    this.adj = adj;

    // Creating graph given in the above diagram.
    // add_edge function takes adjacency list, source
    // and destination vertex as argument and forms
    // an edge between them.
    for (List<Integer> li : edgesList) {
      addEdge(adj, li.get(0), li.get(1));
      addEdge(adj, li.get(1), li.get(0));
    }

    this.length = printShortestDistance(adj, source, dest, v);
  }

  // get length between start and end
  public int getLength() {
    return this.length;
  }

  // get neighbours of a node at a distance specified
  public List<Integer> getNeighboursSpecifiedDistanceAway(
          int source,
          int v,
          int distance) {

    List<Integer> arr1 = new ArrayList<>();
    for (int i = 0; i < v; i++) {
      int len = printShortestDistance(this.adj, source, i, v);
      if (len == distance) {
        arr1.add(i);
      }
    }

    return arr1;
  }

  // function to form edge between two vertices
  // source and dest
  private static void addEdge(List<List<Integer>> adj, int i, int j) {
    adj.get(i).add(j);
    adj.get(j).add(i);
  }

  // function to print the shortest distance and path
  // between source vertex and destination vertex
  private static int printShortestDistance(
          List<List<Integer>> adj,
          int s, int dest, int v) {
    // predecessor[i] array stores predecessor of
    // i and distance array stores distance of i
    // from s
    int[] pred = new int[v];
    int[] dist = new int[v];

    if (!bfs(adj, s, dest, v, pred, dist)) {
      // System.out.println("start and end not connected");
      return 0;
    }

    // LinkedList to store path
    LinkedList<Integer> path = new LinkedList<>();
    int crawl = dest;
    path.add(crawl);
    while (pred[crawl] != -1) {
      path.add(pred[crawl]);
      crawl = pred[crawl];
    }

    // System.out.println(path);

    return dist[dest];
  }

  // a modified version of BFS that stores predecessor
  // of each vertex in array pred
  // and its distance from source in array dist
  private static boolean bfs(List<List<Integer>> adj, int src,
                             int dest, int v, int[] pred, int[] dist) {

    // boolean array visited[] which stores the
    // information whether ith vertex is reached
    // at least once in the Breadth first search
    boolean[] visited = new boolean[v];

    // initially all vertices are unvisited
    // so v[i] for all i is false
    // and as no path is yet constructed
    // dist[i] for all i set to infinity
    for (int i = 0; i < v; i++) {
      visited[i] = false;
      dist[i] = Integer.MAX_VALUE;
      pred[i] = -1;
    }

    // a queue to maintain queue of vertices whose
    // adjacency list is to be scanned as per normal
    // BFS algorithm using LinkedList of Integer type
    LinkedList<Integer> queue = new LinkedList<>();

    // now source is first to be visited and
    // distance from source to itself should be 0
    visited[src] = true;
    dist[src] = 0;
    queue.add(src);

    // bfs Algorithm
    while (!queue.isEmpty()) {
      int u = queue.remove();
      for (int i = 0; i < adj.get(u).size(); i++) {
        if (!visited[adj.get(u).get(i)]) {
          visited[adj.get(u).get(i)] = true;
          dist[adj.get(u).get(i)] = dist[u] + 1;
          pred[adj.get(u).get(i)] = u;
          queue.add(adj.get(u).get(i));

          // stopping condition (when we find
          // our destination)
          if (adj.get(u).get(i) == dest) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
