package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class SettController implements Initializable {
    @FXML
    private GridPane setty;
    @FXML
    private ChoiceBox openPlayer;
    @FXML
    private ChoiceBox playerXcolor;
    @FXML
    private ChoiceBox playerOcolor;
    @FXML
    private ChoiceBox board_size;
    @FXML
    private Button BackButton;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        openPlayer.setItems(FXCollections.observableArrayList("Player X", "Player O "));
        playerXcolor.setItems(FXCollections.observableArrayList("Black", "Blue", "Yellow", "Pink", "Purple", "Cyan"));
        playerOcolor.setItems(FXCollections.observableArrayList("White", "Blue", "Yellow", "Pink", "Purple", "Cyan"));
        board_size.setItems(FXCollections.observableArrayList("4x4", "6x6", "8x8", "10x10", "12x12", "14x14", "16x16",
                "18x18", "20x20"));
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
                "Player X", "Player O")
        );
}

    public void endGameEvent(MouseEvent mouseEvent) {
        exit(0);
    }


}
