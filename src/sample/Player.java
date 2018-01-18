/*
 * Name: Noam Itzhaki
 * ID: 315773465
 * Name: Jonathan Schwarz
 * ID: 203672910
 */

package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {

    private DiscSymbol symbol; //The player's symbol.
    private ArrayList<Disc> dlist; //The player's disc list.
    private int counter; //The number of discs the player has of board.
    private Color color;

    /**
     * A player's constructor.
     * @param sym the player's symbol.
     */
    public Player(DiscSymbol sym,Color c) {
        this.symbol = sym;
        this.dlist = new ArrayList<Disc>();
        this.counter = 0;
        this.color = c;
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
            if (this.dlist.get(i).check_coordinates(d)) {
                this.dlist.remove(i);
                this.counter--;
            }
        }
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

    /**
     * @return the player's color
     */
    public Color getColor(){ return this.color; }

}
