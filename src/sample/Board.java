package sample;

public class Board {

    private Disc[][] board; // A double pointer variable used to create a matrix (board) in the constructor.
    private int size;
    /**
     * Board object constructor.
     * creates a board according to the size requested.
     * @param n holds the size of the board.
     *
     */
    public Board(int n){
        this.board = new Disc[n][n];
        this.size = n;
    }
    /**
     * Initializes the board with the first 4 disc objects in the middle of the matrix (the board).
     * @param white recieves the white player signed 'O'.
     * @param black recieves the black player signed 'X'.
     */
    public void init(Player white, Player black){
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
                    System.out.print(j + '|');
                } else {
                    if (j == 0 && i > 0) {
                        System.out.print(i + '|');
                    } else {
                        System.out.print((char) this.board[i][j].get_sym() + '|');
                    }
                }

            }
            System.out.println();
            for (int j = 0; j < this.size; ++j) {
                System.out.print("- ");
            }
            System.out.println();
        }
    }

    /**
     * @return the size of the matrix.
     */
    public int get_size() {
        return size;
    }

    /**
     * @return returns the game board.
     */
    public Disc[][] get_board() {
        return board;
    }

}
