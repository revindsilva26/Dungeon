package dungeon.view;

import dungeon.controller.DungeonGuiController;
import dungeon.model.Direction;
import dungeon.model.HealthStatus;
import dungeon.model.Position;
import dungeon.model.ReadOnlyDungeon;
import dungeon.model.SmellStrength;
import dungeon.model.Treasure;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class NodePanel extends JPanel {

  private int rowNum;
  private int colNum;
  private ReadOnlyDungeon model;
  private DungeonGuiController listener;
  private Image imgPath = null;
  private Image imgCaveOnly = null;

  NodePanel(int rowNum, int colNum, ReadOnlyDungeon model) {
    this.rowNum = rowNum;
    this.colNum = colNum;
    this.model = model;

    setLayout(new BorderLayout());
    setSize(150, 150);
  }

  Position getRowColPosition() {
    return new Position(this.rowNum, this.colNum);
  }

  private Image getImageToDraw(String path) {
    Image img = null;
    try {
      img = scaleImage(ImageIO.read(new File(path)),
              this.getWidth(),
              this.getHeight());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return img;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Image pic = getImageToDraw("img/black.png");
    g.drawImage(pic, 0, 0, null);

    // Now, check if player is in this Dungeon node and
    // display the cave image accordingly
    if ((model.getPlayerLocation().getRow() == this.rowNum
            && model.getPlayerLocation().getColumn() == this.colNum)) {

      //  To make the game auto-scroll
      //  this.requestFocusInWindow();
      //  Rectangle rec = new Rectangle(NodePanel.super.getWidth(), NodePanel.super.getHeight());
      //  NodePanel.super.scrollRectToVisible(rec);
      //  NodePanel.super.transferFocusUpCycle();

      StringBuilder dirImagePath = new StringBuilder();

      for (Direction d : model.getExitsFromPlayerLocation()) {
        if (d == Direction.EAST) {
          dirImagePath.append("E");
        }

        else if (d == Direction.NORTH) {
          dirImagePath.append("N");
        }

        else if (d == Direction.SOUTH) {
          dirImagePath.append("S");
        }

        else if (d == Direction.WEST) {
          dirImagePath.append("W");
        }
      }

      String imgPath = dirImagePath.toString().chars()
              .sorted()
              .collect(StringBuilder::new,
                      StringBuilder::appendCodePoint,
                      StringBuilder::append)
              .toString();

      Image imgCave = getImageToDraw(
              String.format("img/%s.png", imgPath));
      this.imgCaveOnly = imgCave;
      Image imgPlayer = getImageToDraw(
              String.format("img/%s.png", "dead-player-2"));
      if (model.isPlayerAlive()) {
        imgPlayer = getImageToDraw(
                String.format("img/%s.png", "player"));
      }
      Image imgDiamond = getImageToDraw(
              String.format("img/%s.png", "diamond"));
      Image imgRuby = getImageToDraw(
              String.format("img/%s.png", "ruby"));
      Image imgSapphire = getImageToDraw(
              String.format("img/%s.png", "sapphire"));
      Image imgWeapon = getImageToDraw(
              String.format("img/%s.png", "arrow"));
      Image imgMonster = getImageToDraw(
              String.format("img/%s.png", "otyugh"));
      Image imgThief = getImageToDraw(
              String.format("img/%s.png", "thief"));

      this.imgPath = imgCave;

      Image finalImg = null;
      try {

        if (!model.isPlayerAlive()) {

          // Overlay monster
          finalImg = this.overlayImage(
                  getBufferedImage(imgCave),
                  getBufferedImage(imgMonster),
                  0,
                  0,
                  1);

          // Overlay player sprite
          finalImg = this.overlayImage(
                  getBufferedImage(finalImg),
                  getBufferedImage(imgPlayer),
                  (this.getWidth() / 2) + 10,
                  (this.getHeight() / 2) - 55,
                  3);
        }

        else if (model.isPlayerAlive()
                && model.getPlayerLocation() == model.getEnd()) {

          if (model.getMonsterHealth() == HealthStatus.WOUNDED) {
            // Overlay monster
            finalImg = this.overlayImage(
                    getBufferedImage(imgCave),
                    getBufferedImage(imgMonster),
                    0,
                    0,
                    3);

            // Overlay player sprite
            finalImg = this.overlayImage(
                    getBufferedImage(finalImg),
                    getBufferedImage(imgPlayer),
                    (this.getWidth() / 2) - 30,
                    (this.getHeight() / 2) - 70,
                    3);
          }

          else {
            // Overlay player sprite
            finalImg = this.overlayImage(
                    getBufferedImage(imgCave),
                    getBufferedImage(imgPlayer),
                    (this.getWidth() / 2) - 30,
                    (this.getHeight() / 2) - 70,
                    3);
          }
        }

        else {

          if (model.getMonsterHealth() == HealthStatus.WOUNDED) {
            // Overlay monster
            finalImg = this.overlayImage(
                    getBufferedImage(imgCave),
                    getBufferedImage(imgMonster),
                    0,
                    0,
                    3);

            // Overlay player sprite
            finalImg = this.overlayImage(
                    getBufferedImage(finalImg),
                    getBufferedImage(imgPlayer),
                    (this.getWidth() / 2) - 30,
                    (this.getHeight() / 2) - 70,
                    3);
          }

          else {
            // Overlay player sprite
            finalImg = this.overlayImage(
                    getBufferedImage(imgCave),
                    getBufferedImage(imgPlayer),
                    (this.getWidth() / 2) - 30,
                    (this.getHeight() / 2) - 70,
                    3);
          }

          // Overlay thief if present
          if (model.isThiefPresent()) {
            finalImg = this.overlayImage(
                    getBufferedImage(finalImg),
                    getBufferedImage(imgThief),
                    (this.getWidth() / 8),
                    (this.getHeight() / 10),
                    3);
          }

          // Overlay rubies if present
          if (model.getTreasureAtLocation().contains(Treasure.RUBY)) {
            finalImg = this.overlayImage(
                    getBufferedImage(finalImg),
                    getBufferedImage(imgRuby),
                    this.getWidth() * 2 / 3,
                    (this.getHeight() / 3) + 15,
                    10);
          }

          // Overlay diamonds if present
          if (model.getTreasureAtLocation().contains(Treasure.DIAMOND)) {
            finalImg = this.overlayImage(
                    getBufferedImage(finalImg),
                    getBufferedImage(imgDiamond),
                    this.getWidth() * 2 / 3,
                    (this.getHeight() / 3) + 30,
                    10);
          }

          // Overlay Sapphires if present
          if (model.getTreasureAtLocation().contains(Treasure.SAPPHIRE)) {
            finalImg = this.overlayImage(
                    getBufferedImage(finalImg),
                    getBufferedImage(imgSapphire),
                    this.getWidth() * 2 / 3,
                    (this.getHeight() / 3) + 45,
                    10);
          }

          // Overlay arrows if present
          if (model.getWeaponsAtLocation().size() > 0) {
            finalImg = this.overlayImage(
                    getBufferedImage(finalImg),
                    getBufferedImage(imgWeapon),
                    (this.getWidth() / 3) + 10,
                    (this.getHeight() / 2) - 20,
                    3);
          }

          // Overlay stench if present
          SmellStrength stench = model.describeSmellAtLocation();
          if (stench != SmellStrength.NONE) {
            if (stench == SmellStrength.SLIGHT) {
              Image imgSlightStench = getImageToDraw(
                      String.format("img/%s.png", "stench01"));
              finalImg = this.overlayImage(
                      getBufferedImage(finalImg),
                      getBufferedImage(imgSlightStench),
                      0,
                      0,
                      1);
            }
            else {
              Image imgDeepStench = getImageToDraw(
                      String.format("img/%s.png", "stench02"));
              finalImg = this.overlayImage(
                      getBufferedImage(finalImg),
                      getBufferedImage(imgDeepStench),
                      0,
                      0,
                      1);
            }
          }
        }

      } catch (IOException e) {
        System.out.println("error: " + e);
      }
      g.drawImage(finalImg, 0, 0, null);
    }


    else {

      for (Position p : model.getVisitedNodes()) {
        if (p.getRow() == rowNum && p.getColumn() == colNum) {
          this.requestFocusInWindow();
          this.repaint();
          super.transferFocusUpCycle();
          g.drawImage(imgCaveOnly, 0, 0, null);
        }

        else if (this.imgPath != null) {
          g.drawImage(imgPath, 0, 0, null);
        }
      }

    }
  }

  private Image overlayImage(BufferedImage starting,
                             BufferedImage overlay,
                             int offsetX,
                             int offsetY,
                             int scaleFactor)
          throws IOException {
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    Image combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    Image overlay1 = scaleImage(overlay,
            this.getWidth() / scaleFactor,
            this.getHeight() / scaleFactor);
    g.drawImage(getBufferedImage(overlay1), offsetX, offsetY, null);
    return combined;
  }

  private BufferedImage getBufferedImage(Image img) {
    if (img instanceof BufferedImage) {
      return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(
            img.getWidth(null),
            img.getHeight(null),
            BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
  }

  private Image scaleImage(Image img, int width, int height) {
    return img.getScaledInstance(
              width,
              height,
              Image.SCALE_DEFAULT);
  }

}
