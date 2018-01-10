/*
 * Name: Jonathan Schwarz
 * ID: 203672910
 * Name: Noam Itzhaki
 * ID: 315773465
 */

package sample;


public class Disc {

    private DiscSymbol symbol; // The disc's symbol.
    private Coordinates  coordinates_on_board; // The disc's coordinates.

    /**
     * Constructor
     * @param sym- the symbol Disc
     * @param x- location row
     * @param y- location column
     */
    public Disc(DiscSymbol sym, int x, int y) {

        this.symbol = sym;
        this.coordinates_on_board = new Coordinates(x, y);
    }

    /**
     * @return the row of the disc on the board.
     */
    public int getLocationRow() {
        return this.coordinates_on_board.getCoordinatesX();
    }

    /**
     *
     * @return the column of the disc on the board.
     */
    public int getLocationColumn() {
        return this.coordinates_on_board.getCoordinatesY();
    }

    /**
     * This function compare between two Disc's location on the board.
     * @param d- checks if the coordinates of d match this objects coordinates.
     * @return true of false (if the coordinates match).
     */
    public boolean check_coordinates(Disc d) {
        return (this.coordinates_on_board.getCoordinatesX() == d.getLocationRow()
                && coordinates_on_board.getCoordinatesY() == d.getLocationColumn());
    }

    /**
     * @return the symbol of the disc
     */
    public DiscSymbol get_sym() {
        return symbol;
    }
}