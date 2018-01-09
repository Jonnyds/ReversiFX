/*
 * Name: Noam Itzhaki
 * ID: 315773465
 * Name: Jonathan Schwarz
 * ID: 203672910
 */

package sample;

import java.util.ArrayList;

public class GameFlow {

    private Board playing_board; // The game's board object.
    private Player black; // The black player (with symbol X).
    private Player white; // The white player (with symbol O).
    private DiscSymbol turn; // Which player does the turn belong to.
    private int no_more_moves;
    private BoardLogic boardlogic;

    /**
     * The gameflow object constructor.
     * @param n the board's size.
     */
    public GameFlow(int n) {

        this.playing_board = new Board(n);
        this.white = Player(O);
        this.black = Player(X);
        this.turn = X;
        this.no_more_moves = 0;
        this.boardlogic = new BoardLogic();
    }

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
            System.out.println ("The white player is the winner");
        } else {
            if (this.white.get_disc_list().size() < this.black.get_disc_list().size()) {
                System.out.println ("The black player is the winner");
            } else {
                System.out.println ("It's a tie");
            }
        }
    }

    /**
     * @return true if the conditions for the game's end are met or false if not.
     */
    public boolean isGameOver() {

        int total_disc = this.white.get_disc_list().size() + black->get_disc_list().size();
        if (total_disc != pow(boardlogic->getBoard()->get_size() - 1, 2) && (no_more_moves != 2)) {
            return false;
        }
        return true;
    }

    /**
     * Manages a game after the players and board are created.
     */
    public void play() {
        int i;
        Disc d;
        Coordinates chose;

        System.out.println ("It's the black player's turn \n");

        int x = 0, y = 0;
        int total_disc = this.white.get_disc_list().size() + this.black.get_disc_list().size();
        ArrayList<Coordinates> possible_moves;

        while (!isGameOver()) {

            this.playing_board.print();
            System.out.println ("The white player has: " + this.white.get_disc_list().size() + " discs on board");
            System.out.println ("The black player has: " + this.black.get_disc_list().size() + " discs on board \n");

            possible_moves = boardlogic.valid_moves(); // checks the valid moves.

            if (possible_moves.isEmpty()) { // check if both players have no more moves then the game ends.

                this.no_more_moves++;
                switchTurn(true);
            } else {
                this.no_more_moves = 0;
            }

            switch (this.turn.X) {
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

    /**
     * A function that switches the turns.
     * @param no_moves accepts true if the player does not have any moves to make or false if he does,
     * according to this param it prints (or doesnt) a message.
     */
   public void switchTurn(boolean no_moves) {
        // switch turn.
        if(no_moves) {
            System.out.println ("No possible moves - switching turn");
        }
        switch (this.turn) {
            case X:
                this.boardlogic.swapPlayers();
                this.boardlogic.clearVec();
                this.turn = O;
                System.out.println ("It's the white player's turn \n");
                break;
            case O:
                this.boardlogic.swapPlayers();
                this.boardlogic.clearVec();
                this.turn = X;
                if(no_moves) {

                }
                System.out.println ("It's the black player's turn \n");
                break;
            case E:
                break;
        }
    }
            }






