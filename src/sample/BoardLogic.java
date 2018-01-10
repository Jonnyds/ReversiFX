package sample;

import java.util.ArrayList;
import static sample.DiscSymbol.E;

public class BoardLogic {

    private Board board; // the game board's object
    private Player player_turn; // The player that the turn belongs to.
    private Player player_opponent; // the opponent.
    private ArrayList<Coordinates> valid_points; // the vector holding the Coordinates of the valid moves the player can make.


    /**
     * BoardLogic object constructor.
     * @param game_board the game's board object
     * @param player the player which the turn belongs to.
     * @param opponent the opponent of the player currently in turn.
     */
    public BoardLogic(Board game_board,Player player,Player opponent){
        this.board = game_board;
        this.player_turn = player;
        this.player_opponent = opponent;
        this.valid_points = new ArrayList<Coordinates>();
    }

    /**
     * Checks the Coordinates of the moves the current player (in turn) can make.
     * @return a vector of Coordinates with all the valid moves the player can make.
     */
    public ArrayList<Coordinates> valid_moves(){
        int j, k, direction = 0;
        ArrayList<Disc> list = this.player_turn.get_disc_list();
        for (int i = 0; i < list.size(); ++i) { // runs on a player's disc list.
            for (direction = 0; direction < 8; ++direction) { // runs in each direction once.
                j = list.get(i).getLocationRow();
                k = list.get(i).getLocationColumn();
                check_direction(direction ,j ,k );
            }

        }
        check_double();
        return this.valid_points;
    }


    /**
     * Checks for moves in a specific direction from a player's disc on board.
     * @param i defines the direction which the Coordinates advance in.
     * @param j the row index of the disc we are currently checking its surrounding
     * (changes within the funcation).
     * @param k the column index of the disc we are currently checking its surrounding
     * (changes within the funcation).
     */
    public void check_direction(int i, int j, int k) {
        int count = 0;
        int another_symbol_found = 0;
        Coordinates coor;

        while (!this.is_board_end(j, k)) {
            if (is_empty(j, k) && count == 0) {
                break;
            }

            if (is_opponent(j, k)) {
                count++;
            }
            if(this.player_turn.get_symbol() == this.board.get_board()[j][k].get_sym( )) {
                another_symbol_found++;
                if (another_symbol_found == 2) {
                    break;
                }
            }

            if (is_empty(j, k) && count > 0) {
                coor = new Coordinates(j,k);
                this.valid_points.add(coor);
                break;
            }
            switch (i) { // checks the directions.
                case 0:
                    j++;
                    break;
                case 1:
                    j++;
                    k++;
                    break;
                case 2:
                    k++;
                    break;
                case 3:
                    j--;
                    k++;
                    break;
                case 4: j--;
                    break;
                case 5:
                    j--;
                    k--;
                    break;
                case 6: k--;
                    break;
                case 7:
                    j++;
                    k--;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Perform the changes needed on board after adding a new disc to it.
     * @param j the row index of the new disc added by the player (changes within the funcation).
     * @param k the column index of the new disc added by the player (changes within the funcation).
     */
    public void flipping(int j, int k) {
        int count = 0;
        int jsave = j;
        int ksave = k;
        for (int i = 0; i < 8; ++i) { // for each direction.
            count = 0;
            j = jsave;
            k = ksave;
            while (!is_board_end(j, k)) {

                if (is_empty(j, k) && count == 0) {
                    break;
                }

                if (is_opponent(j, k)) {
                    count++;
                }

                if (this.board.get_board()[j][k].get_sym() == this.player_turn.get_symbol() && count > 0) {
                    for (int l = 0; l < count; ++l) { //runs back in each direction and changes the discs to the player's type discs.
                        switch (i) {
                            case 0:
                                j--;
                                add_to_board(j, k);
                                break;
                            case 1:
                                j--;
                                k--;
                                add_to_board(j, k);
                                break;
                            case 2:
                                k--;
                                add_to_board(j, k);
                                break;
                            case 3:
                                j++;
                                k--;
                                add_to_board(j, k);
                                break;
                            case 4:
                                j++;
                                add_to_board(j, k);
                                break;
                            case 5:
                                j++;
                                k++;
                                add_to_board(j, k);
                                break;
                            case 6:
                                k++;
                                add_to_board(j, k);
                                break;
                            case 7:
                                j--;
                                k++;
                                add_to_board(j, k);
                                break;
                            default:
                                break;
                        }

                    }
                    break;
                }

                switch (i) { // checks the directions.
                    case 0:
                        j++;
                        break;
                    case 1:
                        j++;
                        k++;
                        break;
                    case 2:
                        k++;
                        break;
                    case 3:
                        j--;
                        k++;
                        break;
                    case 4:
                        j--;
                        break;
                    case 5:
                        j--;
                        k--;
                        break;
                    case 6:
                        k--;
                        break;
                    case 7:
                        j++;
                        k--;
                        break;
                    default:
                        break;
                }
            }

        }
    }

    /**
     * Changes the discs which need to be flipped to the current player's turn symbol.
     * updating the board and player's lists
     * @param i the row index of the disc we would like to change.
     * @param j the column index of the disc we would like to change.
     */
    public void add_to_board(int i, int j){
        Disc d;
        d = new Disc(this.player_turn.get_symbol(),i,j);
        this.board.add_to_board(d ,i ,j );
        this.player_opponent.remove_disc(d);
        this.player_turn.add_disc(d);
    }

    /**
     * Checks if the indexes i of j stepped out the the board's bounds.
     * @param i the row index needed to be checked.
     * @param j the column index needed to be checked.
     * @return if the indexes stepped out of the board's bounds.
     */
    public boolean is_board_end(int i, int j) {
        return (1 > i || i > this.board.get_size() - 1) || (1 > j || j > this.board.get_size() - 1);
    }

    /**
     * Checks if the cell in board with the i and j indexes is empty.
     * @param i the row index.
     * @param j the column index.
     * @return if the cell is empty (had an empty disc).
     */
    public boolean is_empty(int i, int j) {
        return this.board.get_board()[i][j].get_sym() == E;
    }

    /**
     * Checks if the cell in board with the i and j indexes belongs to the opponent.
     * @param i the row index.
     * @param j the column index.
     * @return if the cell belongs to the opponents.
     */
    public boolean is_opponent(int i, int j) {
        return this.board.get_board()[i][j].get_sym() == this.player_opponent.get_symbol();
    }

    /**
     * Prints the valid moves vector.
     */
    public void print_vec(){
        for (int i = 0; i < this.valid_points.size(); ++i) {
            System.out.print('(' + "" + this.valid_points.get(i).getCoordinatesX() + "" + ','
                  + "" + this.valid_points.get(i).getCoordinatesY() + "" + ") ");
        }
        System.out.println();
    }

    /**
     * Checks and deletes double appearance of Coordinates of valid moves vector.
     */
    public void check_double() {
        for (int j = 0; j < this.valid_points.size(); ++j) {

            for (int k = 0; k < this.valid_points.size(); ++k) {

                if ((this.valid_points.get(j).getCoordinatesY() == this.valid_points.get(k).getCoordinatesY()) &&
                        (this.valid_points.get(j).getCoordinatesX() == this.valid_points.get(k).getCoordinatesX()) && (j != k)) {
                    this.valid_points.remove(k);
                }
            }
        }
    }


    /**
     * @return the a vector of valid moves the playre can make;
     */
    public ArrayList<Coordinates> getValidMoves() {
        return this.valid_points;
    }

    /**
     * @return the player that is currently playing (the turn belongs to that player).
     */
    public Player getPlayerTurn() {
        return this.player_turn;
    }

    /**
     * @return the opponent player.
     */
    public Player getPlayerOpponent() {
        return this.player_opponent;
    }

    /**
     * @return the game's playing board.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * swaps between the player_turn and player_opponent when the turn is switched.
     */
    public void swapPlayers() {
        Player temp = this.player_turn;
        this.player_turn = this.player_opponent;
        this.player_opponent = temp;
    }

    /**
     * Clears the valid_points vector for reuse.
     */
    void clearVec() {
        this.valid_points.clear();
    }
    
}
