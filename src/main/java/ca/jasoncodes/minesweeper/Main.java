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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    /* Load all our images */
    private final Image t0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t0.png")));
    private final Image t1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t1.png")));
    private final Image t2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t2.png")));
    private final Image t3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t3.png")));
    private final Image t4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t4.png")));
    private final Image t5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t5.png")));
    private final Image t6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t6.png")));
    private final Image t7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t7.png")));
    private final Image t8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t8.png")));
    private final Image t9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/t9.png")));
    private final Image zero = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/0.png")));
    private final Image one = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/1.png")));
    private final Image two = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/2.png")));
    private final Image three = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/3.png")));
    private final Image four = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/4.png")));
    private final Image five = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/5.png")));
    private final Image six = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/6.png")));
    private final Image seven = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7.png")));
    private final Image eight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/8.png")));
    private final Image[] valImageArray = new Image[] {zero, one, two, three, four, five, six, seven, eight};
    private final Image bombRevealedBicubic = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bombrevealed-bicubic.png")));
    private final Image cover = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/cover.png")));
    private final Image faceDead = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-dead.png")));
    private final Image faceSmile = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-smile.png")));
    private final Image faceWin = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-win.png")));
    private final Image flag = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flag.png")));
    private final Image mineGrey = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-grey.png")));
    private final Image mineMisflagged = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-misflagged.png")));
    private final Image mineRed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-red.png")));

    /* Init the testing grid */
    private final boolean[][] testGrid = {
            {false, false, false, false, false},
            {false, true,  false, false, false},
            {false, false, false, true,  false},
            {false, false, false, true,  false},
            {false, false, false, false, false}
    };
    /* Only works with a 5x5 for now as makeTile() uses the testGrid which is 5x5 */
    private int numRows = 5;
    private int numCols = 5;

    /* These controls get injected from the ui.fxml file */
    @FXML
    private HBox smileHBox;
    @FXML
    private GridPane minefieldGPane;
    @FXML
    private Button smileBTN;

    @Override
    public void start(Stage stage) throws IOException {
        /* Load the fxml file that builds the ui */
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ui.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();

        /* Setup the smileBTN */
        setupSmileBTN();
        setupMinefield();

    }

    /* This method sets up the smileBTN with the appropriate behaviour */
    private void setupSmileBTN(){
        smileBTN.setGraphic(new ImageView(faceSmile));
        smileBTN.setPadding(Insets.EMPTY);
        smileBTN.setOnMouseClicked((mouseEvent -> {
            System.out.println(mouseEvent.getSource().toString());
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                System.out.println("LEFT");
                setupSmileBTN();
                setupMinefield();
            }
        }));
    }

    /* This method is responsible for setting up the minefield and its grid pane; */
    private void setupMinefield(){
        minefieldGPane.getChildren().clear();
        Tile.reset();
        /* Set up the grid of tiles and add them to the GridPane */
        Tile[][] grid = new Tile[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Tile tile = makeTile(col, row, grid);
                minefieldGPane.add(tile, col, row);
                grid[row][col] = tile;
            }
        }
        /* Once the grid is instantiated, set the vals for each tile */
        for (Tile[] tRow : grid)
            for (Tile t : tRow)
                t.setVal(grid);
    }

    /* This is a helper method for creating tiles in the grid pane. It returns a new tile. */
    private Tile makeTile(int col, int row, Tile[][] grid) {
        Tile tile = new Tile(col, row, testGrid[row][col], grid);
        tile.setMaxHeight(32);
        tile.setMaxWidth(32);
        tile.setGraphic(new ImageView(cover));
        tile.setOnMouseClicked((e) ->{
            System.out.println(e.getSource().toString());
            if (e.getButton() == MouseButton.PRIMARY) {
                System.out.println("LEFT");
                if (tile.hasMine()) {
                    tile.setGraphic(new ImageView(mineRed));
                    smileBTN.setGraphic(new ImageView(faceDead));
                } else if (!tile.isRevealed()){
                    tile.reveal(valImageArray);
                    int bombCount = Tile.getBombCount();
                    int tileCount = Tile.getTileCount();
                    int revealedTileCount = Tile.getRevealedTileCount();
                    if (tileCount - bombCount == revealedTileCount)
                        smileBTN.setGraphic(new ImageView(faceWin));
                }

            }
        });
        return tile;
    }


    public static void main(String[] args) {
        launch();
    }
}

/* Mostly adopted from lecture code */
class Tile extends Button{
    private static int tileCount;
    private static int revealedTileCount;
    private static int bombCount;
    private final int col;
    private final int row;
    private int val;
    private final boolean hasMine;
    private boolean isRevealed;

    public int getCol() { return col; }
    public int getRow() { return row; }
    public int getVal() { return val; }
    public boolean isRevealed() { return isRevealed; }
    public boolean hasMine() { return hasMine; }
    public static int getTileCount() { return tileCount; }
    public static int getRevealedTileCount() { return revealedTileCount; }
    public static int getBombCount() { return bombCount; }

    public Tile(int col, int row, boolean hasMine, Tile[][] grid) {
        this.col = col;
        this.row = row;
        this.hasMine = hasMine;
        if (hasMine)
            bombCount++;
        tileCount++;
        setPadding(Insets.EMPTY);
        //System.out.println("Tile count: " + tileCount + "\nBomb count: " + bombCount);
    }

    /* This method instantiates the val to the number of bombs around the tile */
    /**
    private void setVal(int col, int row, Tile[][] grid) {
        if (col <= 0 || col >= grid[0].length - 1)
            return;
        if (row <= 0 || row >= grid.length - 1)
            return;

        if (!grid[col-1][row-1].hasMine()) {

        }
     **/
    public void setVal(Tile[][] grid) {
        int row = this.getRow();
        int col = this.getCol();
        int sum = 0;
        int numRows = grid.length;
        int numCols = grid[0].length;
        // Top left
        if (col > 0 && row > 0 && grid[row-1][col-1].hasMine())
            sum++;
        // Top middle
        if (row > 0 && grid[row-1][col].hasMine())
            sum++;
        // Top right
        if (col < numCols - 1 && row > 0 && grid[row-1][col+1].hasMine())
            sum++;
        // Right
        if (col < numCols - 1 && grid[row][col+1].hasMine())
            sum++;
        // Bottom right
        if (col < numCols - 1 && row < numRows - 1 && grid[row+1][col+1].hasMine())
            sum++;
        // Bottom middle
        if (row < numRows - 1 && grid[row+1][col].hasMine())
            sum++;
        // Bottom left
        if (col > 0 && row < numRows - 1 && grid[row+1][col-1].hasMine())
            sum++;
        // Left
        if (col > 0 && grid[row][col-1].hasMine())
            sum++;

        this.val = sum;
    }

    /* This method "reveals" the tile and sets the image to the correct value */
    public void reveal(Image[] imgArr){
        revealedTileCount++;
        //System.out.println("Revealed Tile Count: " + revealedTileCount);
        isRevealed = true;
        this.setGraphic(new ImageView(imgArr[this.getVal()]));
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
