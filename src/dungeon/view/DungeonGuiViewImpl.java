package dungeon.view;

import dungeon.controller.DungeonGuiController;
import dungeon.model.Direction;
import dungeon.model.ReadOnlyDungeon;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Implementation of the view that adds functionality
 * required from the GUI for Dungeon game.
 */
public class DungeonGuiViewImpl extends JFrame
        implements DungeonGuiView {

  private MazePanel mazePanel;
  private ReadOnlyDungeon model;
  private DungeonGuiController listener;

  /**
   * Constructor for GUI View instance.
   * @param model Read only instance of model
   */
  public DungeonGuiViewImpl(ReadOnlyDungeon model) {
    super("Dungeon");
    pack();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

    this.model = model;

    this.mazePanel = new MazePanel(model);
    PlayerStatusPanel playerStatusPanel = new PlayerStatusPanel(model);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    playerStatusPanel.setPreferredSize(
            new Dimension(screenSize.width, screenSize.height / 5));

    setLayout(new BorderLayout());

    JMenuItem newGame = new JMenuItem("New Game");
    JMenuItem restartGame = new JMenuItem("Restart Game");
    JMenuItem viewControls = new JMenuItem("View Controls");
    JMenuItem viewSettings = new JMenuItem("View Game Settings");
    JMenuItem changeSettings = new JMenuItem("Change Settings");
    JMenuItem quitGame = new JMenuItem("Quit");

    JMenu menu = new JMenu("Menu");
    menu.add(newGame);
    menu.add(restartGame);
    menu.add(viewControls);
    menu.add(viewSettings);
    menu.add(changeSettings);
    menu.add(quitGame);
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menu);

    newGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.newGame();
      }
    });

    restartGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.restartGame();
      }
    });

    viewControls.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                "TO MOVE: Hit the arrow keys (or) \n"
                        + "click with mouse on valid location\n\n"
                        + "TO PICKUP ITEMS: Hit the 'p' key on keyboard\n\n"
                        + "TO SHOOT: Hold 'z' key, then hold the arrow key\n"
                        + "indicating direction, then press keys 1-5 to\n"
                        + "indicate distance, in that exact order.\n");
      }
    });

    viewSettings.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                String.format("Dungeon size - Rows: %d Columns: %d"
                                + "\nWrapping? %s"
                                + "\nInterconnectivity: %d"
                                + "\nPercentage of caves with treasures: %d"
                                + "\nNumber of monsters in Dungeon: %d",
                        model.getRows(),
                        model.getColumns(),
                        model.isWrapping(),
                        model.getInterconnectivity(),
                        model.getPercentageTreasures(),
                        model.getNumberOfMonsters()));
      }
    });

    changeSettings.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        JTextField rows = new JTextField(5);
        JTextField cols = new JTextField(5);
        JCheckBox wrapping = new JCheckBox();
        JTextField interconnectivity = new JTextField(5);
        JTextField percentTreasures = new JTextField(5);
        JTextField numMonsters = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, 1));
        myPanel.add(new JLabel("Rows: "));
        myPanel.add(rows);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Columns: "));
        myPanel.add(cols);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Wrapping: "));
        myPanel.add(wrapping);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Interconnectivity: "));
        myPanel.add(interconnectivity);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Percentage of caves with treasures: "));
        myPanel.add(percentTreasures);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Number of monsters: "));
        myPanel.add(numMonsters);
        myPanel.add(Box.createVerticalStrut(15));

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Enter values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          try {
            listener.changeSettingsAndPlayGame(rows.getText(),
                    cols.getText(),
                    wrapping.isSelected(),
                    interconnectivity.getText(),
                    percentTreasures.getText(),
                    numMonsters.getText());
          }
          catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(null, iae.getMessage());
          }
        }

      }
    });

    quitGame.addActionListener(e -> {
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    });

    setJMenuBar(menuBar);

    add(playerStatusPanel, BorderLayout.PAGE_START);

    mazePanel.setPreferredSize(
            new Dimension(
                    model.getDungeonGrid().get(0).size() * 250,
                    model.getDungeonGrid().size() * 250));

    JScrollPane scrollPaneMaze = new JScrollPane(mazePanel);

    add(scrollPaneMaze, BorderLayout.CENTER);

  }



  @Override
  public void addKeypadListener(DungeonGuiController listener) {

    this.listener = listener;

    KeyListener kl  = new KeyListener() {

      List<Integer> pressedKeys = new ArrayList<>();

      @Override
      public void keyTyped(KeyEvent e) {
        // ignored
      }

      @Override
      public void keyPressed(KeyEvent e) {

        if (model.isPlayerAlive()
                && !model.getPlayerLocation().equals(model.getEnd())) {
          String hitMessage = "ROAR heard. You hit something...";
          String missMessage = "You shot into the darkness and heard nothing...";
          String wrongDirMessage = "Can't shoot in that direction...";

          Map<Integer, Direction> directionMap = new HashMap<>();
          directionMap.put(KeyEvent.VK_UP, Direction.NORTH);
          directionMap.put(KeyEvent.VK_DOWN, Direction.SOUTH);
          directionMap.put(KeyEvent.VK_LEFT, Direction.WEST);
          directionMap.put(KeyEvent.VK_RIGHT, Direction.EAST);

          Map<Integer, Integer> distanceMap = new HashMap<>();
          distanceMap.put(KeyEvent.VK_1, 1);
          distanceMap.put(KeyEvent.VK_2, 2);
          distanceMap.put(KeyEvent.VK_3, 3);
          distanceMap.put(KeyEvent.VK_4, 4);
          distanceMap.put(KeyEvent.VK_5, 5);

          if (!pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.add(e.getKeyCode());
          }

          if (pressedKeys.get(0) == KeyEvent.VK_Z) {

            if (model.getWeaponsOfPlayer().size() > 0) {
              try {
                if (directionMap.containsKey(pressedKeys.get(1))
                        && distanceMap.containsKey(pressedKeys.get(2))) {
                  boolean success = false;
                  boolean shotInWrongDirection = false;
                  try {
                    success = listener.attack(directionMap.get(pressedKeys.get(1)),
                            distanceMap.get(pressedKeys.get(2)));
                  } catch (IllegalArgumentException iae) {
                    shotInWrongDirection = true;
                  }

                  if (success) {
                    JOptionPane.showMessageDialog(null, hitMessage);
                  } else if (shotInWrongDirection) {
                    JOptionPane.showMessageDialog(null, wrongDirMessage);
                  } else {
                    JOptionPane.showMessageDialog(null, missMessage);
                  }

                  pressedKeys = new ArrayList<>();
                }
              } catch (IndexOutOfBoundsException ignored) {
              }
            } else {
              JOptionPane.showMessageDialog(null,
                      "You don't have arrows to shoot");
            }
          }
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (pressedKeys.contains(e.getKeyCode())) {
          if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (listener.move(Direction.NORTH)) {
              if (model.isThiefPresent()) {
                JOptionPane.showMessageDialog(null,
                        "Prepare for trouble! THIEF alert!");
              }
            }
          }
          else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (listener.move(Direction.SOUTH)) {
              if (model.isThiefPresent()) {
                JOptionPane.showMessageDialog(null,
                        "Prepare for trouble! THIEF alert!");
              }
            }
          }
          if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (listener.move(Direction.EAST)) {
              if (model.isThiefPresent()) {
                JOptionPane.showMessageDialog(null,
                        "Prepare for trouble! THIEF alert!");
              }
            }
          }
          if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (listener.move(Direction.WEST)) {
              if (model.isThiefPresent()) {
                JOptionPane.showMessageDialog(null,
                        "Prepare for trouble! THIEF alert!");
              }
            }
          }
          if (e.getKeyCode() == KeyEvent.VK_P) {
            listener.pickup();
          }
        }

        pressedKeys = new ArrayList<>();
      }
    };
    this.addKeyListener(kl);
  }

  @Override
  public void addMouseClickListener(DungeonGuiController listener) {

    this.mazePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        try {
          NodePanel panel =
                  (NodePanel) SwingUtilities
                          .getDeepestComponentAt(
                                  e.getComponent(),
                                  e.getX(),
                                  e.getY());
          if (listener.move(panel.getRowColPosition())) {
            if (model.isThiefPresent()) {
              JOptionPane.showMessageDialog(null,
                      "Prepare for trouble! THIEF alert!");
            }
          }
        }
        catch (IllegalArgumentException iae) {
          System.out.println(iae.getMessage());
        }
      }
    });

  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void disposeFrame() {
    setVisible(false);
    dispose();
  }

}
