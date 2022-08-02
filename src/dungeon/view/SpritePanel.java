package dungeon.view;

import dungeon.model.ReadOnlyDungeon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * Package-private class to extend JPanel.
 */
class SpritePanel extends JPanel {

  private ReadOnlyDungeon model;

  SpritePanel(ReadOnlyDungeon model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Image myPicture = Utils.getImageToDraw("img/player.png");
    g.drawImage(myPicture, super.getWidth() / 5,
            super.getHeight() / 4,
            null);

    if (model.isPlayerAlive() && model.getPlayerLocation().equals(model.getEnd())) {
      setBackground(Color.pink);
      g.drawString("You reached the exit !!!",
              super.getWidth() / 5,
              super.getHeight() / 5);
      g.drawString(String.format("Exit found at %s in location (%d, %d)",
                      (model.isPlayerLocationCave() ? "Cave" : "Tunnel"),
                      model.getEnd().getRow() + 1, model.getEnd().getColumn() + 1),
              (super.getWidth() / 5) - 20,
              super.getHeight() * 4 / 5);
    }

    else if (model.isPlayerAlive() && !model.getPlayerLocation().equals(model.getEnd())) {
      setBackground(Color.GREEN);
      g.drawString(String.format("You are %s", model.getPlayerName()),
              super.getWidth() / 5,
              super.getHeight() / 5);
      g.drawString(String.format("In a %s",
                      (model.isPlayerLocationCave() ? "Cave" : "Tunnel")),
              (super.getWidth() / 5) + 10,
              super.getHeight() * 4 / 5);
    }

    else {
      setBackground(Color.RED);
      g.drawString("You are dead",
              super.getWidth() / 5,
              super.getHeight() / 5);
      g.drawString(String.format("Otyugh found in %s, it ate you",
                      (model.isPlayerLocationCave() ? "Cave" : "Tunnel")),
              (super.getWidth() / 5) - 20,
              super.getHeight() * 4 / 5);
    }
  }
}
