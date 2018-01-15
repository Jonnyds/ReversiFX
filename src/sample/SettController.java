package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    public void endGameEvent(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) this.setty.getScene().getWindow();
        VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene s = new Scene(root, 520,400);
        stage.setScene(s);
        stage.show();
    }


}
