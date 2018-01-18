package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    /**
     * the start application function setting the stage of the menu
     */
    public void start(Stage primaryStage) {
        try
        {
            VBox root = (VBox) FXMLLoader.load(getClass().getResource("../Controllers/Menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi game");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch
                (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * the main function that launches the program
     * @param args param
     */
    public static void main(String[] args) {
        launch(args);
    }
}
