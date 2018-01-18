/*
 * Name: Noam Itzhaki
 * ID: 315773465
 * Name: Jonathan Schwarz
 * ID: 203672910
 */

package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class Menu implements Initializable {

    @FXML
    private VBox VBOX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.VBOX.getChildren().get(0).setTranslateX(105);
        this.VBOX.getChildren().get(0).setTranslateY(15);
        this.VBOX.getChildren().get(1).setTranslateX(185);
        this.VBOX.getChildren().get(1).setTranslateY(70);
        this.VBOX.getChildren().get(2).setTranslateX(185);
        this.VBOX.getChildren().get(2).setTranslateY(90);
        this.VBOX.getChildren().get(3).setTranslateX(185);
        this.VBOX.getChildren().get(3).setTranslateY(110);

    }

    public void endGameEvent(MouseEvent mouseEvent) {
        exit(0);
    }

    public void startgame(MouseEvent mouseEven) throws IOException {
        HBox root = (HBox) FXMLLoader.load(getClass().getResource("Reversi.fxml"));
        Stage stage = (Stage) this.VBOX.getScene().getWindow();
        Scene s = new Scene(root, 620,400);
        s.getStylesheets().add(getClass().getResource("board.css").toExternalForm());
        stage.setScene(s);
        stage.show();

    }

    public void movetosettings(MouseEvent mouseEvent) throws IOException {
        GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = (Stage) this.VBOX.getScene().getWindow();
        Scene s = new Scene(root, 525,525);
        s.getStylesheets().add(getClass().getResource("settings.css").toExternalForm());
        stage.setScene(s);
        stage.show();
    }
}
