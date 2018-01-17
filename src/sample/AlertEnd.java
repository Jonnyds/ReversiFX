package sample;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.awt.*;


public class AlertEnd {

    public static void alertWindows (String title, String message) {

       /* Stage alert = new Stage();
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setMinWidth(100);*/


        Label label = new Label();
        label.setText(message);
        Scene s = new Scene(label, 520,400);
        Button endBack = new Button("Back to Menu");
       // VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
       // Scene s = new Scene(root, 520,400);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, endBack);
        layout.setAlignment(Pos.CENTER);

        Scene sc = new Scene(layout);
      //  alert.setScene(sc);
       // alert.showAndWait();
    }


}
