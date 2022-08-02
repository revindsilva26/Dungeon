package dungeon.view;

import dungeon.model.ReadOnlyDungeon;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * Package private class for implementing
 * the middle maze panel in the GUI.
 */
class MazePanel extends JPanel {

  MazePanel(ReadOnlyDungeon model) {

    int rows = model.getDungeonGrid().size();
    int cols = model.getDungeonGrid().get(0).size();
    setLayout(new GridLayout(
            rows,
            cols, -1, -1));

    NodePanel[][] grid = new NodePanel[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        grid[i][j] = new NodePanel(i, j, model);
        add(grid[i][j]);
      }
    }
  }
}
