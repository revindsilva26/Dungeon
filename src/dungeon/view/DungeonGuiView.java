package dungeon.view;

import dungeon.controller.DungeonGuiController;

/**
 * A view for Dungeon: display the Dungeon maze
 * and provide visual interface for users.
 */
public interface DungeonGuiView {

  /**
   * Set up the controller to handle keypad
   * events in this view.
   *
   * @param listener the controller
   */
  void addKeypadListener(DungeonGuiController listener);

  /**
   * Set up the controller as the listener in this view
   * for mouse clicks.
   *
   * @param listener the controller
   */
  void addMouseClickListener(DungeonGuiController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Destroy base frame.
   */
  void disposeFrame();
}
