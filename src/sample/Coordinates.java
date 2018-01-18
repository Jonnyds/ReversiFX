/*
 * Name: Jonathan Schwarz
 * ID: 203672910
 * Name: Noam Itzhaki
 * ID: 315773465
 */

package sample;

/**
 * a point object for placing a disc in the right location on the board
 */
public class Coordinates {

    private int x;
    private int y;

    /**
     * constructor
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public int getCoordinatesX() {

        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public int getCoordinatesY() {

        return this.y;
    }

    /**
     * set a neww x coordinate
     * @param newX the new x coordinate
     */
    public void setCoordinatesX(int newX) {

        this.x = newX;
    }
    /**
     * set a neww y coordinate
     * @param newY the new x coordinate
     */
    public void setCoordinatesY(int newY) {

        this.y = newY;
    }

}