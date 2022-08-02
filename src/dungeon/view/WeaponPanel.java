package dungeon.view;

import dungeon.model.ReadOnlyDungeon;
import dungeon.model.Weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.JPanel;

/**
 * Package-private class to extend JPanel.
 */
class WeaponPanel extends JPanel {

  private ReadOnlyDungeon model;

  WeaponPanel(ReadOnlyDungeon model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    setBackground(Color.ORANGE);
    List<Weapon> weapons = model.getWeaponsOfPlayer();
    int count = weapons.size();

    Image myPicture = Utils.getImageToDraw("img/arrow-black.png");
    g.drawImage(myPicture, super.getWidth() / 3,
            super.getHeight() / 3,
            null);
    g.drawString(String.format("You have %d arrows", count),
            super.getWidth() / 5,
            super.getHeight() / 5);
  }

}
