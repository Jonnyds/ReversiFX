package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.DiscSymbol.O;
import static sample.DiscSymbol.X;

public class ReversiController implements Initializable {

    public Button start;
    @FXML
    private HBox root;
    @FXML
    private VBox VBOX;

   public void startbutton(){
       Board reversiBoard = new Board(4);

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
       //this.VBOX.getChildren().get(2).setOnMouseClicked(event -> {endGameEvent();});
      // root.getChildren().get(1).setOnMouseClicked(event -> {endGameEvent();});
   }

    public void endGameEvent() throws IOException {
       HBox H = new HBox(10);
        Button b = new Button("starting");
        b.setPrefHeight(200);
        b.setPrefWidth(200);
        Stage stage = (Stage) this.root.getScene().getWindow();
        Scene s = new Scene(H, 400,400);
        H.getChildren().add(b);
        stage.setScene(s);
        stage.show();
        }


    @Override
        public void initialize(URL location, ResourceBundle resource) {
            Board reversiBoard = new Board();
            GameFlow g =  new GameFlow(8);
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
            //this.VBOX.getChildren().get(2).setOnMouseClicked(event -> {endGameEvent();});
           // root.getChildren().get(1).setOnMouseClicked(event -> {endGameEvent();});
        }
    }


