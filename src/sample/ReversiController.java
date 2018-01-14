package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;
import static sample.DiscSymbol.O;
import static sample.DiscSymbol.X;

public class ReversiController implements Initializable {

    public Button start;
    @FXML
    private HBox root;

   public void startbutton(){
       Board reversiBoard = new Board();

       reversiBoard.setPrefWidth(400);
       reversiBoard.setPrefHeight(400);
       root.getChildren().add(0, reversiBoard);
       reversiBoard.draw();
       root.widthProperty().addListener((observable, oldValue, newValue) -> {
           double boardNewWidth = newValue.doubleValue() - 120;
           reversiBoard.setPrefWidth(boardNewWidth);
           reversiBoard.draw();
       });
       root.heightProperty().addListener((observable, oldValue, newValue) -> {
           reversiBoard.setPrefHeight(newValue.doubleValue());
           reversiBoard.draw();
       });
////////////////////////////////////////////////////////////
      // root.getChildren().get(1).setOnMouseClicked(event -> {endGameEvent();});
   }//System.out.println("noam is great");

    private void endGameEvent(){
            exit(0);
        }


    @Override
        public void initialize(URL location, ResourceBundle resource) {
            Board reversiBoard = new Board();
            reversiBoard.init(new Player(X), new Player(O));
            reversiBoard.setPrefWidth(400);
            reversiBoard.setPrefHeight(400);
            root.getChildren().add(0, reversiBoard);
            reversiBoard.draw();
            root.widthProperty().addListener((observable, oldValue, newValue) -> {
                double boardNewWidth = newValue.doubleValue() - 120;
                reversiBoard.setPrefWidth(boardNewWidth);
                reversiBoard.draw();
            });
            root.heightProperty().addListener((observable, oldValue, newValue) -> {
                reversiBoard.setPrefHeight(newValue.doubleValue());
                reversiBoard.draw();
            });
////////////////////////////////////////////////////////////
            root.getChildren().get(1).setOnMouseClicked(event -> {endGameEvent();});
        }
    }


