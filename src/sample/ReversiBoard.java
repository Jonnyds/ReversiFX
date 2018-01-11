package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


import java.io.IOException;

import static sample.DiscSymbol.O;
import static sample.DiscSymbol.P;
import static sample.DiscSymbol.X;

/*
Board controller
 */
public class ReversiBoard extends GridPane {

    private DiscSymbol[][] board;

    /**
     * The controller Board
     * @param board the current board game we want to show.
     */
    public ReversiBoard(DiscSymbol[][] board) {
        this.board = board;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(" ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
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

                if (board[i][j] == P)
                    this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);

                if (board[i][j] == X) {
                    Circle c = new Circle(cellHeight / 3);
                    c.setCenterX(cellWidth / 2);
                    c.setCenterY(cellHeight / 2);
                    c.setFill(Color.BLACK);
                    c.setStroke(Color.BLACK);
                    this.add(c, j, i);
                }

                if (board[i][j] == O) {
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

