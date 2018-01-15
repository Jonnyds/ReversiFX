package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try
        {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("Settings.fxml"));
            Scene scene = new Scene(root, 520, 400);
            // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi game");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch
                (Exception e) {
            e.printStackTrace();
        }
    }
    public static void
    main(String[] args) {
        launch(args);
        GameFlow g = new GameFlow();
        g.initGame();
        g.play();
    }
}
