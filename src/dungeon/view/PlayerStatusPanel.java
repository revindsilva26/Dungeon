package dungeon.view;

import dungeon.model.ReadOnlyDungeon;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Package private class for implementing
 * the top maze panel in the GUI.
 */
class PlayerStatusPanel extends JPanel {

  private Image getImageToDraw(String path) {
    Image myPicture = null;
    try {
      myPicture = ImageIO.read(new File(path));
    } catch (IOException e) {
      System.out.println("error");
    }
    return myPicture;
  }

  PlayerStatusPanel(ReadOnlyDungeon model) {

    setLayout(new GridLayout(1, 6));

    add(new SpritePanel(model));
    add(new DiamondPanel(model));
    add(new RubyPanel(model));
    add(new SapphirePanel(model));
    add(new WeaponPanel(model));
  }

}


