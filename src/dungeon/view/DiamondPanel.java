package dungeon.view;

import dungeon.model.ReadOnlyDungeon;
import dungeon.model.Treasure;
import dungeon.model.Weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.JPanel;

/**
 * Package-private class to extend JPanel.
 */
class DiamondPanel extends JPanel {

  private ReadOnlyDungeon model;

  DiamondPanel(ReadOnlyDungeon model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    setBackground(Color.YELLOW);
    java.util.List<Treasure> treasures = model.getTreasureOfPlayer();
    List<Weapon> weapons = model.getWeaponsOfPlayer();
    int count = 0;

    for (Treasure t : treasures) {
      if (t == Treasure.DIAMOND) {
        count += 1;
      }
    }

    Image myPicture = Utils.getImageToDraw("img/diamond.png");
    g.drawImage(myPicture, super.getWidth() / 3,
            super.getHeight() / 3,
            null);
    g.drawString(String.format("You have %d diamonds", count),
            super.getWidth() / 5,
            super.getHeight() / 5);
  }

}
