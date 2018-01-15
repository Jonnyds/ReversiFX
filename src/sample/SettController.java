package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
                "Player X", "Player O")
        );
}

    public void endGameEvent(MouseEvent mouseEvent) {
        exit(0);
    }


}
