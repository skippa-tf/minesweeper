## To Do:

- Implement other difficulties: maybe the menu selection sets an int to a difficulty value and then calls the resetGame() method. The resetGame() might call setDifficulty(int difficulty) that sets up the difficulties.
  - Intermediate level: 16 x 16 board with 40 mines.
  - Expert level: 32 x 16 board with 99 mines.

- The first click of every game is always on a spot with 0 mines around it. The surrounding spots
  with no mines around are also opened automatically. This is usually done using recursion.
- When the game is over, after a win or loss, the only clickable button is the face button.
- 
- Bind components to the right width properties so that things get resized correctly.
- Refactor code that passes row, col, and a grid to just pass a tile + give tiles a reference to the grid.
** double check every minesweeper requirement!**
--------
## If Time Permits:
- Create a play area class that manages the minefield gridpane and minefield logic.
- Document the codebase:
    - Add comments explaining the purpose and functionality of each section.
    - Provide clear instructions for future developers or collaborators.
- Optimize and refactor the code:
    - Ensure code readability and maintainability.
    - Identify and address any performance bottlenecks.
----------
## Done
- Chording: A left click on an uncovered number tile that already has the correct number of flags around it
  will uncover all the remaining covered tiles in it’s group of 8.
- Win condition does not work correctly. [Tiles where revealing multiple times, causing a miscount of revealed tiles.]
- Update the mine counter in the header:
  - Decrease the count of mines left whenever a covered tile is flagged.
- Add flagging feature:
  - Right-click on a tile flags it.
  - Left-clicks do not affect flagged tiles.
  - Right-click again to un-flag the tile.
- Add recursive search on zero-valued tiles.
- Randomly generate the boards with the correct number of mines, and then correctly calculate the numbers for the rest of the spots.
- Implement the functionality for the face icon button:
    - When clicked, regardless of its current state, start a new game.

