## To Do:
- Add recursive search on zero-valued tiles.
- Add flagging feature:
    - Right-click on a tile flags it.
    - Left-clicks do not affect flagged tiles.
    - Right-click again to un-flag the tile.
- Update the mine counter in the header:
    - Decrease the count of mines left whenever a covered tile is flagged.
- Implement the game logic for clicking on covered spots:
    - If a mine is uncovered, display it in red.
    - Reveal all remaining uncovered mines as grey mines.
    - Keep the correctly flagged mines flagged.
- Implement other difficulties: maybe the menu selection sets an int to a difficulty value and then calls the resetGame() method. The resetGame() might call setDifficulty(int difficulty) that sets up the difficulties.
  - Intermediate level: 16 x 16 board with 40 mines.
  - Expert level: 32 x 16 board with 99 mines.

- Bind components to the right width properties so that things get resized correctly.



- Prepare for submission:
    - Ensure all requirements are met.
    - Double-check the functionality and address any remaining issues.
    - Package the project files for submission.
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

- Randomly generate the boards with the correct number of mines, and then correctly calculate the numbers for the rest of the spots.
- Implement the functionality for the face icon button:
    - When clicked, regardless of its current state, start a new game.

