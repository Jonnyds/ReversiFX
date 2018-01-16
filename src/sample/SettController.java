package sample;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.event.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SettController implements Initializable {
    @FXML
    private GridPane setty;
    @FXML
    private ChoiceBox openPlayer;
    @FXML
    private ColorPicker player1color;
    @FXML
    private ColorPicker player2color;
    @FXML
    private ChoiceBox board_size;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ColorPicker colorPicker1 = new ColorPicker();
        colorPicker1.setValue(Color.BLACK);
        ColorPicker colorPicker2 = new ColorPicker();
        colorPicker2.setValue(Color.WHITE);

        openPlayer.setItems(FXCollections.observableArrayList("Player 1", "Player 2"));
        openPlayer.getSelectionModel().selectFirst();
      /*  player1color.setItems(FXCollections.observableArrayList("Black","White", "Blue", "Yellow", "Pink",
                "Purple", "Cyan"));*/

        player1color.setValue(colorPicker1.getValue());
        player2color.setValue(colorPicker2.getValue());
        /*player2color.setItems(FXCollections.observableArrayList("Black","White", "Blue", "Yellow", "Pink",
                "Purple", "Cyan"));
        player2color.getSelectionModel().select(1);*/
        board_size.setItems(FXCollections.observableArrayList("4x4", "6x6", "8x8", "10x10", "12x12", "14x14", "16x16",
                "18x18", "20x20"));
        board_size.getSelectionModel().select(2);
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
                "Player X", "Player O")
        );


}

    public void backMenu(MouseEvent mouseEvent) throws IOException {
        try {
            FileWriter fileW = new FileWriter("settings_file");
            BufferedWriter bufferedW = new BufferedWriter(fileW);



        String open,color1,color2,size_b,size[];

        open = openPlayer.getSelectionModel().getSelectedItem().toString();
        color1 = player1color.getValue().toString();
        color2 = player2color.getValue().toString();
        size_b = board_size.getSelectionModel().getSelectedItem().toString();
        size = size_b.split("x");



        bufferedW.write("opening_player:" + open);
        bufferedW.newLine();
        bufferedW.write("player_1_color:" + color1);
        bufferedW.newLine();
        bufferedW.write("player_2_color:" + color2);
        bufferedW.newLine();
        bufferedW.write("board_size:" + size[0]);

        bufferedW.close();

                fileW.close();

        } catch (Exception e) {
            System.out.println("not opening");
        }



        Stage stage = (Stage) this.setty.getScene().getWindow();
        VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene s = new Scene(root, 520,400);
        stage.setScene(s);
        stage.show();
    }


}
