package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static sample.DiscSymbol.E;
import static sample.DiscSymbol.O;
import static sample.DiscSymbol.X;
import static sample.DiscSymbol.P;
import static sample.DiscSymbol.M;


public class Board extends GridPane {

    private Disc[][] board; // A double pointer variable used to create a matrix (board) in the constructor.
    private int size;
    final int defaultSize = 8;
    /**
     * Board object constructor.
     * creates a board according to the size requested.
     * @param n holds the size of the board.
     *
     */
    public Board(int n){
        this.size = n + 1;
        this.board = new Disc[size][size];
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml" ));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * A default constructor.
     * creates a 8X8 board.
     */
    public Board(){
        this.size = defaultSize + 1;
        this.board = new Disc[size][size];
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml" ));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    /**
     * Initializes the board with the first 4 disc objects in the middle of the matrix (the board).
     * @param white recieves the white player signed 'O'.
     * @param black recieves the black player signed 'X'.
     */
    public void init(Player white, Player black){

        Disc d;
        for (int i = 0; i < this.size; i++) {
            for(int j = 0; j <this.size; j++) {
                d= new Disc(E,i,j);
                this.add_to_board(d,i,j);
            }
        }

        Disc odisc44 = new Disc(O,this.size / 2,this.size / 2);
        Disc xdisc45 = new Disc(X,this.size / 2,this.size / 2 + 1);
        Disc odisc55 = new Disc(O,this.size / 2 + 1,this.size / 2 + 1);
        Disc xdisc54 = new Disc(X,this.size / 2 + 1,this.size / 2);

        /**
         * Adding the first four discs to the board.
         */
        this.board[size / 2][size / 2] = odisc44;
        this.board[size / 2][size / 2 + 1] = xdisc45;
        this.board[size / 2 + 1][size / 2 + 1] = odisc55;
        this.board[size / 2 + 1][size / 2] = xdisc54;

        /**
         * adding the first four discs to the player's lists.
         */
        white.add_disc(odisc44);
        white.add_disc(odisc55);
        black.add_disc(xdisc45);
        black.add_disc(xdisc54);


    }

    /**
     * Adds a disc object to the board.
     * @param d the disc we would like to add to the board.
     * @param i the row index on the matrix we would like to add the disc to.
     * @param j the column index on the matrix we would like to add the disc to.
     */
    public void add_to_board(Disc d, int i, int j) {
        this.board[i][j] = d;
    }

    /**
     * Prints the game board.
     */
    public void print() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                if (i == 0 && j > 0) {
                    System.out.print(j + "" + P.asChar());
                } else {
                    if (j == 0 && i > 0) {
                        System.out.print(i + "" + P.asChar());
                    } else {
                        System.out.print(this.board[i][j].get_sym().asChar() + "" + P.asChar());
                    }
                }

            }
            System.out.println();
            for (int j = 0; j < this.size; ++j) {
                System.out.print(M.asChar() + " ");
            }
            System.out.println();
        }
    }

    /**
     * @return the size of the matrix.
     */
    public int get_size() {
        return this.size;
    }

    /**
     * @return returns the game board.
     */
    public Disc[][] get_board() {
        return this.board;
    }

    public void draw() {

        this.getChildren().clear();

        //size of the board
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();

        //size of a single cell on the board
        int cellHeight = height / board.length;
        int cellWidth = width / board[0].length;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                Rectangle boardRec = new Rectangle(width, height, Color.AQUA);
                this.add(boardRec, j, i);

                if (board[i][j].get_sym() == P)
                    this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);

                if (board[i][j].get_sym() == X) {
                    Circle c = new Circle(cellHeight / 3);
                    c.setCenterX(cellWidth / 2);
                    c.setCenterY(cellHeight / 2);
                    c.setFill(Color.BLACK);
                    c.setStroke(Color.BLACK);
                    this.add(c, j, i);
                }

                if (board[i][j].get_sym() == O) {
                    Circle c = new Circle(cellHeight / 3);
                    c.setCenterX(cellWidth / 2);
                    c.setCenterY(cellHeight / 2);
                    c.setFill(Color.WHITE);
                    c.setStroke(Color.WHITE);
                    this.add(c, j, i);
                }
            }
        }
    }

}
