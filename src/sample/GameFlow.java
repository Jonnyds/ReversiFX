/*
 * Name: Noam Itzhaki
 * ID: 315773465
 * Name: Jonathan Schwarz
 * ID: 203672910
 */
package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Math.pow;
import static sample.DiscSymbol.O;
import static sample.DiscSymbol.X;

public class GameFlow implements Initializable {

    private Board playing_board; // The game's board object.
    private Player player1; // The black player (with symbol X).
    private String nameP1;
    private Player player2; // The white player (with symbol O).
    private String nameP2;
    private DiscSymbol turn; // Which player does the turn belong to.
    private int no_more_moves;
    private BoardLogic boardlogic;
    private GameFlow  controller;

    @FXML
    private HBox root;
    @FXML
    private Text score1;
    @FXML
    private Text score2;
    @FXML
    private Text nameplayer1;
    @FXML
    private Text nameplayer2;
    @FXML
    private Text nomoremoves;
    @FXML
    private Text currentplayer;

    /**
     * The gameflow object constructor.
     *
     * @param n the board's size.
     */
    public GameFlow(int n, Color c1, Color c2, String opening, GameFlow control) {
        this.controller = control;
        this.playing_board = new Board(n);
        this.player2 = new Player(O, c2);
        this.player1 = new Player(X, c1);
        this.nameP1 = control.nameP1;
        this.nameP2 = control.nameP2;
        if (opening.compareTo("Player 1") == 0) {
            this.boardlogic = new BoardLogic(this.playing_board, this.player1, this.player2);
            this.controller.currentplayer.setText(this.nameP1);
            this.turn = X;
        } if (opening.compareTo("Player 2") == 0) {
            this.boardlogic = new BoardLogic(this.playing_board, this.player2, this.player1);
            this.controller.currentplayer.setText(this.nameP2);
            this.turn = O;
        }
        this.no_more_moves = 0;

    }



    public GameFlow() {
    }

    /**
     * Initializes the board.
     */
    public void initGame() {
        this.playing_board.init(this.player2, this.player1);
    }


    /**
     * A function that prints a winning message according to the game's results.
     */
    public void winMassege() throws IOException {

        Label endPlayerLabel = new Label();
        endPlayerLabel.setId("endPlayerLabel");
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        endPlayerLabel.setFont(Font.font("Broadway", FontWeight.BOLD, 30));
        endPlayerLabel.setTextFill(Color.BLACK);

        if (this.player2.get_disc_list().size() > this.player1.get_disc_list().size()) {
            endPlayerLabel.setText("\n    Game over!\n\n   The Winner Is: \n\n\t" + this.nameP2);
        } else {
            if (this.player2.get_disc_list().size() < this.player1.get_disc_list().size()) {
                endPlayerLabel.setText("\n    Game over!\n\n   The Winner Is: \n\n\t" + this.nameP1);
            } else {
                endPlayerLabel.setText("\n  Game over!\n\n   It's A TIE");
            }
        }

        hBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.getChildren().addAll(endPlayerLabel);

        Scene scene = new Scene(hBox, 400, 300);

        Stage seconderyStage = new Stage();
        seconderyStage.setTitle("Game Over");
        seconderyStage.setScene(scene);
        Button endBack = new Button("Back to Menu");
        endBack.setId("endBack");
        scene.setRoot(hBox);
        hBox.getChildren().add(endBack);
        endBack.setAlignment(Pos.BOTTOM_CENTER);
        scene.getStylesheets().add(getClass().getResource("Alert.css").toExternalForm());

        endBack.setOnMouseClicked(mouseEvent -> {
            try {
           Stage stage = (Stage) this.controller.root.getScene().getWindow();
                VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));

                Scene s = new Scene(root, 520,400);
                s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                stage.setScene(s);
                stage.show();
                seconderyStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        seconderyStage.show();
    }

    /**
     * @return true if the conditions for the game's end are met or false if not.
     */
    public boolean isGameOver() {

        int total_disc = this.player2.get_disc_list().size() + this.player1.get_disc_list().size();
        if (total_disc != pow(boardlogic.getBoard().get_size() - 1, 2) && (no_more_moves != 2)) {
            return false;
        }
        return true;
    }

    /**
     * Manages a game after the players and board are created.
     */
    public void play(double x, double y) throws IOException {

        this.boardlogic.clearVec();
        ArrayList<Coordinates> possible_moves = boardlogic.valid_moves();

        Coordinates coor = pressTurnCoor(x, y);

        if (checkMove(possible_moves, coor)) {
            Disc d = new Disc(this.turn, coor.getCoordinatesX(), coor.getCoordinatesY());
            this.playing_board.add_to_board(d, coor.getCoordinatesX(), coor.getCoordinatesY());


            switch (turn) {
                case X:
                    this.player1.add_disc(d);
                    break;
                case O:
                    this.player2.add_disc(d);
                    break;

            }

            this.boardlogic.flipping(coor.getCoordinatesX(), coor.getCoordinatesY());//makes the move (changes discs on board).
            this.playing_board.draw(player1.getColor(), player2.getColor());

            if (isGameOver()) {
                winMassege();
            }

            switchTurn(false);


            // checks the next 2 moves a player can make
            ArrayList<Coordinates>  possible_moves1 = boardlogic.valid_moves();
            if (possible_moves1.isEmpty()) {
                this.no_more_moves++;
                switchTurn(true);

                ArrayList<Coordinates>possible_moves2 = boardlogic.valid_moves();
                if (possible_moves2.isEmpty()) {
                    this.no_more_moves++;
                    if (isGameOver()) {
                        winMassege();
                    }
                } else {
                    this.no_more_moves = 0;
                }
            }
        }
    }

    /**
     * A function that switches the turns.
     *
     * @param no_moves accepts true if the player does not have any moves to make or false if he does,
     *                 according to this param it prints (or doesnt) a message.
     */
    public void switchTurn(boolean no_moves) {
        // switch turn.
        if (no_moves) {
            controller.nomoremoves.setText("no moves switching turn");
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1500),
                    ae -> controller.nomoremoves.setText("")));
            timeline.play();

        }
        switch (this.turn) {
            case X:
                this.boardlogic.swapPlayers();
                this.boardlogic.clearVec();
                this.turn = O;
                this.controller.currentplayer.setText(this.nameP1);
                break;
            case O:
                this.boardlogic.swapPlayers();
                this.boardlogic.clearVec();
                this.turn = X;
                this.controller.currentplayer.setText(this.nameP2);
                break;
            case E:
                break;
        }
    }

    /**
     * @return the current playing board.
     */
    public Board getPlaying_board() {
        return this.playing_board;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // FileReader reads text files in the default encoding.

        String open = "", line = "";
        Color color1 = Color.BLACK, color2 = Color.WHITE;
        int size_n = 0;

        try {

            FileReader fileR = new FileReader("settings_file");

            BufferedReader bufferedReader = new BufferedReader(fileR);

            while ((line = bufferedReader.readLine()) != null) {
                String parts[] = line.split(":");
                String set = parts[0];
                switch (set) {
                    case "player1_name":
                        this.nameP1 = parts[1];
                        break;
                    case "player2_name":
                        this.nameP2 = parts[1];
                        break;
                    case "opening_player":
                        open = parts[1];
                        break;
                    case "player_1_color":
                        color1 = Color.valueOf(parts[1]);
                        break;
                    case "player_2_color":
                        color2 = Color.valueOf(parts[1]);
                        break;
                    case "board_size":
                        size_n = Integer.parseInt(parts[1]);
                        break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        GameFlow g = new GameFlow(size_n, color1, color2, open, this);
        g.getPlaying_board().setPrefWidth(400);
        g.getPlaying_board().setPrefHeight(400);
        g.getPlaying_board().setId("boardy");
        g.initGame();
        root.getChildren().add(0, g.getPlaying_board());
        root.getChildren().get(0).setTranslateX(20);
        root.getChildren().get(0).setTranslateY(20);
        this.nameplayer1.setText(nameP1 + "'s score is:");
        this.nameplayer2.setText(nameP2 + "'s score is:");
        g.getPlaying_board().setOnMouseClicked(event -> {
            try {
                g.play(event.getX(), event.getY());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.score1.setText(Integer.toString(g.player1.get_disc_list().size()));
            this.score2.setText(Integer.toString(g.player2.get_disc_list().size()));
        });

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 220;
            g.getPlaying_board().setPrefWidth(boardNewWidth);
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            g.getPlaying_board().setPrefHeight(newValue.doubleValue());
        });
        g.getPlaying_board().draw(color1,color2);
    }

    public void endGameEvent() throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        VBox root = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene s = new Scene(root, 520,400);
        s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(s);
        stage.show();
    }


    public Coordinates pressTurnCoor(double x, double y) {

        int height = (int) this.playing_board.getPrefHeight();
        int width = (int) this.playing_board.getPrefWidth();
        double cellHeight = (double) (y / height) * playing_board.get_size();
        double cellWidth = (double) (x / width) * playing_board.get_size();
        return new Coordinates((int) cellHeight + 1, (int) cellWidth + 1);
    }

    public Boolean checkMove(ArrayList<Coordinates> possible, Coordinates c) {
        Boolean contain = false;
        for (int i = 0; i < possible.size(); i++) {
            if ((possible.get(i).getCoordinatesY() == c.getCoordinatesY()) &&
                    (possible.get(i).getCoordinatesX() == c.getCoordinatesX())) {
                contain = true;
                break;
            }
        }
        return contain;
    }
}