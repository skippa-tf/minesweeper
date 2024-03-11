
package ca.jasoncodes.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Main extends Application {
    /* Load all our images */    /* Intellij suggested to add Objects.requiredNonNull because getting the resource might be null */
    private final Image T0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t0.png")));
    private final Image T1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t1.png")));
    private final Image T2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t2.png")));
    private final Image T3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t3.png")));
    private final Image T4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t4.png")));
    private final Image T5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t5.png")));
    private final Image T6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t6.png")));
    private final Image T7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t7.png")));
    private final Image T8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t8.png")));
    private final Image T9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t9.png")));
    private final Image ZERO = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/0.png")));
    private final Image ONE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/1.png")));
    private final Image TWO = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/2.png")));
    private final Image THREE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/3.png")));
    private final Image FOUR = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/4.png")));
    private final Image FIVE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/5.png")));
    private final Image SIX = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/6.png")));
    private final Image SEVEN = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7.png")));
    private final Image EIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/8.png")));
    /* This array is used to make image grabbing easy with an index */
    private final Image[] VAL_IMAGE_ARRAY = new Image[] {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT};

    private final Image BOMB_REVEALED_BICUBIC = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bombrevealed-bicubic.png")));
    private final Image COVER = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/cover.png")));
    private final Image FACE_DEAD = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-dead.png")));
    private final Image FACE_SMILE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-smile.png")));
    private final Image FACE_WIN = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-win.png")));
    private final Image FLAG = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flag.png")));
    private final Image MINE_RED = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-red.png")));
    private final Image MINE_GREY = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-grey.png")));
    private final Image MINE_MISFLAGGED = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-misflagged.png")));

    /* Init the testing grid */
    private final boolean[][] testGrid = {
            {false, false, false, false, false},
            {false, true,  false, false, false},
            {false, false, false, true,  false},
            {false, false, false, true,  false},
            {false, false, false, false, false}
    };

    /* Various constant variables for setting up games */

        /* Beginner */
    private final int beginnerMineCount = 10;
    private final int beginnerNumRows = 8;
    private final int beginnerNumCols = 8;

        /* Intermediate */
    private final int intermediateMineCount = 40;
    private final int intermediateNumRows = 16;
    private final int intermediateNumCols = 16;

        /* Expert */
    private final int expertMineCount = 99;
    private final int expertNumRows = 16;
    private final int expertNumCols = 32;


    private int numRows = beginnerNumRows;
    private int numCols = beginnerNumCols;
    private int totalMines = beginnerMineCount;
    private int totalSquares = numRows * numCols;

    private boolean isPlaying = true;
    /* These controls get injected (and instantiated) via the FXMLLoader. */
    @FXML
    private HBox smileHBox;
    @FXML
    private Button smileBTN;
    @FXML
    private VBox vBoxForMinefield;

    private GridPane minefieldGPane;
    @Override
    public void start(Stage stage) throws IOException {
        /* Load the fxml file that builds the ui */
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ui.fxml"));
        fxmlLoader.setController(this);

        Scene scene = new Scene(fxmlLoader.load());
        stage.setWidth(320);
        stage.setHeight(450);
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();

        minefieldGPane = new GridPane();
        vBoxForMinefield.getChildren().add(minefieldGPane);


        reset();

    }

    /* Reset the game */
    private void reset() {
        setupSmileBTN();
        setupMinefield();

    }

    /* This method sets up the smileBTN with the appropriate behaviour */
    private void setupSmileBTN(){
        smileBTN.setGraphic(new ImageView(FACE_SMILE));
        smileBTN.setPadding(Insets.EMPTY);
        smileBTN.setOnMouseClicked((mouseEvent -> {
            System.out.println(mouseEvent.getSource().toString());
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                isPlaying = true;
                System.out.println("LEFT");
                reset();
            }
        }));
    }

    /* This method is responsible for randomizing bomb location */
    private boolean[][] generateRandomMinefield(int rows, int cols){
        // Generate a boolean array of totalSquares size and then shuffle the array
        int totalTiles = rows * cols;
        boolean[] mines = new boolean[totalTiles];
        for (int i = 0; i < totalTiles; i++) {
            if (i < totalMines) {
                mines[i] = true;
            } else {
                mines[i] = false;
            }
        }

        shuffle(mines);
        boolean[][] mines2D = convertTo2DArray(mines, numCols);
        displayArray(mines2D);
        return mines2D;
    }

    private void displayArray(boolean[][] arr) {
        StringBuilder out = new StringBuilder();
        out.append("-".repeat(arr[0].length + 2));
        out.append("\n");
        for (boolean[] row : arr) {
            out.append('|');
            for (boolean b : row) {
                if (b) {
                    out.append("X");
                } else {
                    out.append("o");
                }
            }
            out.append("|\n");
        }
        out.append("-".repeat(arr[0].length + 2));
        System.out.println(out);
    }

    /* Uses a Fisher-Yates shuffle to ensure the contents of the array are properly and evenly shuffled. */
    /* https://www.youtube.com/watch?v=4zx5bM2OcvA helped a lot with this + my amazing prof Jim Nastos :) */
    private <T> void shuffle(T[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int r = rand.nextInt(i,arr.length);
            T temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }
    }

    private void shuffle(boolean[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int r = rand.nextInt(i,arr.length);
            boolean temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }
    }

    /* Converts a one dimensional array to a 2D dimensional array with the specified # of values per row */
    private boolean[][] convertTo2DArray(boolean[] arr, int numCols) {
        boolean[][] temp = new boolean[Math.ceilDiv(arr.length, numCols)][numCols];
        for (int i = 0; i < arr.length; i++) {
            temp[i/numCols][i%numCols] = arr[i];
        }
        return temp;
    }

    /* This method is responsible for setting up the minefield and its grid pane; */
    private void setupMinefield(){
        Tile.reset();
        boolean[][] minefield = generateRandomMinefield(numRows, numCols);

        /* Set up the grid of tiles and add them to the GridPane */
        Tile[][] grid = new Tile[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Tile tile = makeTile(col, row, grid, minefield);
                tile.setPadding(Insets.EMPTY);
                minefieldGPane.add(tile, col, row);
                grid[row][col] = tile;
            }
        }
        /* Once the grid is instantiated, set the vals for each tile */
        for (Tile[] tRow : grid)
            for (Tile t : tRow) {
                t.setVal(grid);
            }
    }

    /* This is a helper method for creating tiles to be added to the grid pane */
    private Tile makeTile(int col, int row, Tile[][] grid, boolean[][] minefield) {
        Tile tile = new Tile(col, row, minefield[row][col], grid);
        tile.setMaxHeight(32);
        tile.setMaxWidth(32);
        tile.setGraphic(new ImageView(COVER));
        tile.setOnMouseClicked((e) ->{
            System.out.println(e.getSource().toString());
            if (isPlaying) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    System.out.println("LEFT");
                    if (tile.isMine()) {
                        tile.setGraphic(new ImageView(MINE_RED));
                        smileBTN.setGraphic(new ImageView(FACE_DEAD));
                        isPlaying = false;
                    } else if (!tile.isRevealed()) {
                        tile.reveal(VAL_IMAGE_ARRAY);
                        int bombCount = Tile.getBombCount();
                        int tileCount = Tile.getTileCount();
                        int revealedTileCount = Tile.getRevealedTileCount();
                        if (tileCount - bombCount == revealedTileCount) {
                            smileBTN.setGraphic(new ImageView(FACE_WIN));
                            isPlaying = false;
                        }
                    }
                } else if (e.getButton() == MouseButton.SECONDARY) {
                    System.out.println("RIGHT");
                    tile.toggleFlag();
                }
            }
        });
        return tile;
    }


    public static void main(String[] args) {
        launch();
    }


    /* Mostly adopted from lecture code */
    class Tile extends Button {
        private static int tileCount;
        private static int revealedTileCount;
        private static int bombCount;
        private final int col;
        private final int row;
        private int val;
        private final boolean mine;
        private boolean revealed;
        private boolean flagged;

        public int getCol() { return col; }
        public int getRow() { return row; }
        public int getVal() { return val; }
        public boolean isRevealed() { return revealed; }
        public boolean isFlagged() { return flagged; }
        public boolean isMine() { return mine; }
        public static int getTileCount() { return tileCount; }
        public static int getRevealedTileCount() { return revealedTileCount; }
        public static int getBombCount() { return bombCount; }


        public Tile(int col, int row, boolean mine, Tile[][] grid) {
            this.col = col;
            this.row = row;
            this.mine = mine;
            if (mine)
                bombCount++;
            tileCount++;
            setPadding(Insets.EMPTY);
            //System.out.println("Tile count: " + tileCount + "\nBomb count: " + bombCount);
        }


        /* This method checks the tiles around this tile and sets its value to the bomb count */
        public void setVal(Tile[][] grid) {
            int row = this.getRow();
            int col = this.getCol();
            int sum = 0;
            int numRows = grid.length;
            int numCols = grid[0].length;
            /* Check the tiles around the current tile for bombs and sum the bomb count */
            // Top left
            if (col > 0 && row > 0 && grid[row-1][col-1].isMine()) { sum++; }
            // Top middle
            if (row > 0 && grid[row-1][col].isMine()) { sum++; }
            // Top right
            if (col < numCols - 1 && row > 0 && grid[row-1][col+1].isMine()) { sum++; }
            // Right
            if (col < numCols - 1 && grid[row][col+1].isMine()) { sum++; }
            // Bottom right
            if (col < numCols - 1 && row < numRows - 1 && grid[row+1][col+1].isMine()) { sum++; }
            // Bottom middle
            if (row < numRows - 1 && grid[row+1][col].isMine()) { sum++; }
            // Bottom left
            if (col > 0 && row < numRows - 1 && grid[row+1][col-1].isMine()) { sum++; }
            // Left
            if (col > 0 && grid[row][col-1].isMine()) { sum++; }

            this.val = sum;
        }


        /* This method "reveals" the tile and sets the image to the correct value */
        public void reveal(Image[] imgArr){
            if (!isFlagged()) {
                revealedTileCount++;
                //System.out.println("Revealed Tile Count: " + revealedTileCount);
                revealed = true;
                this.setGraphic(new ImageView(imgArr[this.getVal()]));
            }
        }

        public void toggleFlag() {
            if (!isRevealed()) {
                if (!isFlagged()) {
                    flagged = true;
                    this.setGraphic(new ImageView(FLAG));
                } else {
                    flagged = false;
                    this.setGraphic(new ImageView(COVER));
                }
            }
        }


        public static void reset() {
            bombCount = 0;
            tileCount = 0;
            revealedTileCount = 0;

        }

        public String toString() {
            return "(" + getCol() + ", " + getRow() + "): " + super.toString();
        }
    }
}

class PlayAreaGP extends GridPane {

}


