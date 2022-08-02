package dungeon.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Package-private class for utility functions.
 */
class Utils {

  static Image getImageToDraw(String path) {
    Image myPicture = null;
    try {
      myPicture = ImageIO.read(new File(path));
    } catch (IOException e) {
      System.out.println("error");
    }
    return myPicture;
  }
}
