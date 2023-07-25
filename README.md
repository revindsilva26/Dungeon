# Dungeon
A dungeon game where a player must traverse through the maze, designed using Kruskal's algorithm, to reach the exit in order to win. The obstacles in your way? Monsters and Thieves of course!

## About
- You are in a dungeon of caves (having 1, 3 or 4 exits) and tunnels (having 2 exits).
- You can find arrows and treasures (rubies, diamonds, sapphires) scattered randomly in the dungeon.
- The player starts in a cave and to win, must reach the end cave.
- If you see a faint green mist at your location, an Otyugh is 2 caves away from you.
- If you see a dense green mist at your location, an Otyugh is 1 cave away from you.
- To kill Otyughs, you can shoot crooked arrows to a distance of 5 caves (Tunnels don't count as distance travelled).
- You will also find Thieves at random locations that will rob you of all the treasures you have collected.

## Controls
- Move: Use arrow keys or mouse clicks on valid locations.
- Shoot: Hold 'z' key, an arrow key to indicate direction of shot, and a number key (1-5) and simultaneously release all the keys.
- Pickup items: Press 'p' key.
- 'New Game' option in the Menu will start a game with same settings but game objects and their respective positions randomized.
- 'Restart Game' option in the Menu will start a game with same settings and same positions for game objects.
- 'Change Settings' option in the Menu will allow user to set new game settings, such as number of rows & columns in the dungeon, dunegon node interconnectivity etc.

## How to play?
- Run this command on your Terminal: ```git clone https://github.com/revindsilva26/dungeon```
- From the root directory, run the command: ```java -jar dungeon.jar```

## Concepts used
- Kruskal's algorithm: For maze construction.
- BFS (Breadth First Search): To check and ensure that the randomly chosen start and end nodes for a game are atleast 5-hop distance away from each other.
- MVC (Model-View-Controller): Design pattern followed to structure code modules.

## Screenshots
![Screenshot-1](./res/github-screenshots/img-1.png)
![Screenshot-2](./res/github-screenshots/img-2.png)
![Screenshot-3](./res/github-screenshots/img-3.png)
![Screenshot-4](./res/github-screenshots/img-4.png)

## Bibliography
Few links that were particularly helpful during the development of this project:
- [Testing random number generator](https://softwareengineering.stackexchange.com/questions/356456/testing-a-function-that-uses-random-number-generator)
- [Finding shortest path in unweighted graph](https://www.geeksforgeeks.org/shortest-path-unweighted-graph/)
- [Simple Kruskal algorithm implementation](https://www.geeksforgeeks.org/kruskals-algorithm-simple-implementation-for-adjacency-matrix/)
- [GridLayout for Maze UI](https://stackoverflow.com/questions/28035085/using-gridlayout-how-to-set-jpanel-size)
- [Setting mouse click listener in GridLayout](https://stackoverflow.com/questions/8127418/gridlayout-mouse-listener)
- [Making internal JPanel scrollable](https://stackoverflow.com/questions/3279991/add-a-scrollable-jpanel-to-a-gridlayout)
- [Java Swing Menubar](https://www.geeksforgeeks.org/java-swing-jmenubar/)
