package ca.jasoncodes.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private HBox smileHBox;
    private Button smileBTN;

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
    private final Image bombRevealedBicubic = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bombrevealed-bicubic.png")));
    private final Image cover = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/cover.png")));
    private final Image faceDead = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-dead.png")));
    private final Image faceSmile = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-smile.png")));
    private final Image faceWin = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/face-win.png")));
    private final Image flag = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flag.png")));
    private final Image mineGrey = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-grey.png")));
    private final Image mineMisflagged = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-misflagged.png")));
    private final Image mineRed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mine-red.png")));

    @Override
    public void start(Stage stage) throws IOException {
        /* Load the fxml file that builds the ui */
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();

        smileBTN = new Button();
        smileBTN.setGraphic(new ImageView(t0));
        smileHBox = new HBox();
        smileHBox.getChildren().add(smileBTN);

    }

    public static void main(String[] args) {
        launch();
    }
}
