package sample;

import javafx.fxml.FXML;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import static sample.DiscSymbol.*;


public class Board extends GridPane{

    private Disc[][] board; // A double pointer variable used to create a matrix (board) in the constructor.
    private int size;
    @FXML
    public GridPane root;
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

    public void draw(Color c1, Color c2) {

        //size of the board
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();

        //size of a single cell on the board
        int cellHeight = height / this.size;
        int cellWidth = width / this.size;

        for (int i = 1; i < this.size; i++) {
            for (int j = 1; j < this.size; j++) {

                Rectangle boardRec = new Rectangle(cellWidth, cellHeight, Color.AZURE);
                boardRec.setStroke(Color.BLACK);
                boardRec.setStrokeType(StrokeType.INSIDE);
                Light.Point rec = new Light.Point();
                rec.setX(80);
                rec.setY(80);
                rec.setZ(400);

                Lighting l = new Lighting();
                l.setLight(rec);
                l.setSurfaceScale(3.0);
                boardRec.setEffect(l);

                if (board[i][j].get_sym() == X) {
                    Circle c = new Circle(cellHeight / 3, c1);
                    c.setStroke(Color.TRANSPARENT);
                    c.setStrokeType(StrokeType.OUTSIDE);
                    c.setStrokeWidth(cellHeight/6);
                    Light.Point cx = new Light.Point();
                    cx.setX(80);
                    cx.setY(80);
                    cx.setZ(150);

                    Lighting lightingX = new Lighting();
                    lightingX.setLight(cx);
                    lightingX.setSurfaceScale(6.0);
                    c.setEffect(lightingX);
                    this.add(boardRec,j,i);
                   this.add(c, j, i);
                    continue;
                }

                if (board[i][j].get_sym() == O) {
                    Circle cc = new Circle(cellHeight / 3, c2);
                    cc.setStroke(Color.TRANSPARENT);
                    cc.setStrokeType(StrokeType.OUTSIDE);
                    cc.setStrokeWidth(cellHeight/6);
                    Light.Point cx = new Light.Point();
                    cx.setX(80);
                    cx.setY(80);
                    cx.setZ(150);

                    Lighting lightingX = new Lighting();
                    lightingX.setLight(cx);
                    lightingX.setSurfaceScale(6.0);
                    cc.setEffect(lightingX);
                    this.add(boardRec,j,i);
                    this.add(cc, j, i);
                    continue;
                }
                this.add(boardRec,j,i);
            }
        }
    }

}
