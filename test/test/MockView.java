package test;

import dungeon.controller.DungeonGuiController;
import dungeon.model.Dungeon;
import dungeon.view.DungeonGuiView;

import java.io.IOException;

/**
 * Mock view class to test for correct interaction with
 * the controller.
 */
public class MockView implements DungeonGuiView {

  private DungeonGuiController listener;
  private Dungeon model;
  private Appendable appendable;

  /**
   * Constructor for mock view to test for
   * proper interaction with controller.
   * @param appendable Log to check for correct interactions
   */
  public MockView(Appendable appendable) {
    this.appendable = appendable;
  }

  @Override
  public void addKeypadListener(DungeonGuiController listener) {
    try {
      this.appendable.append("Called addKeypadListener()");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addMouseClickListener(DungeonGuiController listener) {
    try {
      this.appendable.append("Called addMouseClickListener()");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void refresh() {
    try {
      this.appendable.append("Called refresh()");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void makeVisible() {
    try {
      this.appendable.append("Called makeVisible()");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void disposeFrame() {
    try {
      this.appendable.append("Called disposeFrame()");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
