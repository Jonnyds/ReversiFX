package sample;

import java.util.ArrayList;

public class Player {

    private DiscSymbol symbol; //The player's symbol.
    private ArrayList<Disc> dlist; //The player's disc list.
    private int counter; //The number of discs the player has of board.

    /**
     * A player's constructor.
     * @param sym the player's symbol.
     */
    public Player(DiscSymbol sym) {
        this.symbol = sym;
        this.dlist = new ArrayList<Disc>();
        this.counter = 0;
    }

    /**
     * Adds a disc to a player's list.
     * @param d the disc we want to add to the player's list.
     */
    public void add_disc(Disc d){
        this.dlist.add(d);
        counter++;
    }

    /**
     * Deletes a disc to a player's list.
     * @param d the disc we want to delete from the player's list.
     */
    public void remove_disc(Disc d) {

        for (int i = 0; i < counter; ++i) {
            if (this.dlist.get(i).check_Coordinates(d)) {
                this.dlist.remove(i);
                this.counter--;
            }
        }
    }

    public Coordinates makeMove(BoardLogic bl) {
        boolean move_found = false;
        Coordinates coor;
        int x = 0, y = 0;
        System.out.println("possible moves:");
        bl.print_vec();


        while (!move_found) { // This part checks of the input is valid.

            System.out.println("please enter the Coordinates of the move you would like to do:");
             ;
            cin >> y;

            for (int move = 0; move < bl.getValidMoves().size(); move++) {
                if (x == bl.getValidMoves()[move].x && y == bl.getValidMoves()[move].y) {
                    move_found = true;
                    System.out.println("Your move is (" + x + ',' + y + ')');
                }
            }

            if(!move_found) {
                System.out.println("Your input is not valid ");
            }
        }

        coor = new Coordinates(x,y);
        return coor;

        // adds the chosen location disc to board
    }


    /**
     * @return the player's disc list.
     */
    public ArrayList<Disc>  get_disc_list(){
        return  this.dlist;
    }

    /**
     * @return The player's symbol.
     */
    public DiscSymbol get_symbol() {
        return this.symbol;
    }
}
