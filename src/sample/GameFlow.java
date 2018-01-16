/*
 * Name: Noam Itzhaki
 * ID: 315773465
 * Name: Jonathan Schwarz
 * ID: 203672910
 */

package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Math.pow;
import static java.lang.System.exit;
import static sample.DiscSymbol.O;
import static sample.DiscSymbol.X;


public class GameFlow implements Initializable {

    private Board playing_board; // The game's board object.
    private Player black; // The black player (with symbol X).
    private Player white; // The white player (with symbol O).
    private DiscSymbol turn; // Which player does the turn belong to.
    private int no_more_moves;
    private BoardLogic boardlogic;

    @FXML
    private HBox root;
    @FXML
    private VBox VBOX;

    /**
     * The gameflow object constructor.
     *
     * @param n the board's size.
     */
    public GameFlow(int n, Color c1, Color c2) {

        this.playing_board = new Board(n);
        this.white = new Player(O, c2);
        this.black = new Player(X, c1);
        this.turn = X;
        this.no_more_moves = 0;
        this.boardlogic = new BoardLogic(this.playing_board, this.black, this.white);
    }

    public GameFlow(){}

    /**
     * Initializes the board.
     */
    public void initGame() {
        this.playing_board.init(this.white, this.black);
    }

    /**
     * A function that prints a winning message according to the game's results.
     */
    public void winMassege() {
        if (this.white.get_disc_list().size() > this.black.get_disc_list().size()) {
            System.out.println("The white player is the winner");
        } else {
            if (this.white.get_disc_list().size() < this.black.get_disc_list().size()) {
                System.out.println("The black player is the winner");
            } else {
                System.out.println("It's a tie");
            }
        }
    }

    /**
     * @return true if the conditions for the game's end are met or false if not.
     */
    public boolean isGameOver() {

        int total_disc = this.white.get_disc_list().size() + black.get_disc_list().size();
        if (total_disc != pow(boardlogic.getBoard().get_size() - 1, 2) && (no_more_moves != 2)) {
            return false;
        }
        return true;
    }

    /**
     * Manages a game after the players and board are created.
     */
    public void play() {

        Disc d;

        System.out.println("It's the black player's turn \n");

        int x = 0, y = 0;
        Coordinates chose = new Coordinates(x, y);
        int total_disc = this.white.get_disc_list().size() + this.black.get_disc_list().size();
        ArrayList<Coordinates> possible_moves;

        while (!isGameOver()) {
            this.playing_board.draw();
            this.playing_board.print();

            System.out.println("The white player has: " + this.white.get_disc_list().size() + " discs on board");
            System.out.println("The black player has: " + this.black.get_disc_list().size() + " discs on board \n");

            possible_moves = boardlogic.valid_moves(); // checks the valid moves.

            if (possible_moves.isEmpty()) { // check if both players have no more moves then the game ends.

                this.no_more_moves++;
                switchTurn(true);
            } else {
                this.no_more_moves = 0;


                switch (turn) {
                    case X:
                        chose = this.black.makeMove(boardlogic);
                        d = new Disc(this.turn, chose.getCoordinatesX(), chose.getCoordinatesY());
                        this.playing_board.add_to_board(d, chose.getCoordinatesX(), chose.getCoordinatesY());
                        this.black.add_disc(d);
                        break;
                    case O:
                        chose = this.white.makeMove(boardlogic);
                        d = new Disc(this.turn, chose.getCoordinatesX(), chose.getCoordinatesY());
                        this.playing_board.add_to_board(d, chose.getCoordinatesX(), chose.getCoordinatesY());
                        this.white.add_disc(d);
                        break;
                    case E:
                        break;
                }

                this.boardlogic.flipping(chose.getCoordinatesX(), chose.getCoordinatesY()); //makes the move (changes discs on board).

                switchTurn(false);
            }
        }
        this.winMassege();
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
            System.out.println("No possible moves - switching turn");
        }
        switch (this.turn) {
            case X:
                this.boardlogic.swapPlayers();
                this.boardlogic.clearVec();
                this.turn = O;
                System.out.println("It's the white player's turn \n");
                break;
            case O:
                this.boardlogic.swapPlayers();
                this.boardlogic.clearVec();
                this.turn = X;
                if (no_moves) {

                }
                System.out.println("It's the black player's turn \n");
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
                    case "opening_player":
                        open = parts[1];
                        break;
                    case "player_1_color":
                        color1 = fromFileToColor(parts[1]);
                        break;
                    case "player_2_color":
                        color2 = fromFileToColor(parts[1]);
                        break;
                    case "board_size":
                        size_n = Integer.parseInt(parts[1]);
                        break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        GameFlow g = new GameFlow(size_n, color1, color2);
        g.getPlaying_board().setPrefWidth(400);
        g.getPlaying_board().setPrefHeight(400);
        g.initGame();
        root.getChildren().add(0, g.getPlaying_board());
        g.getPlaying_board().draw();

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            g.getPlaying_board().setPrefWidth(boardNewWidth);
            g.getPlaying_board().draw();
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            g.getPlaying_board().setPrefHeight(newValue.doubleValue());
            g.getPlaying_board().draw();
        });

        //g.play();
        //this.VBOX.getChildren().get(2).setOnMouseClicked(event -> {endGameEvent();});
        // root.getChildren().get(1).setOnMouseClicked(event -> {endGameEvent();});
    }

    public void endGameEvent() throws IOException {
        exit(0);
    }


    public Color fromFileToColor(String colstr) {
        switch (colstr) {
            case "Black":
                return Color.BLACK;
            case "White":
                return Color.WHITE;
            case "Blue":
                return Color.BLUEVIOLET;
            case "Yellow":
                return Color.YELLOW;
            case "Pink":
                return Color.PINK;
            case "Purple":
                return Color.PURPLE;
            case "Cyan":
                return Color.CYAN;
        }

        return Color.BLACK;
    }
}


