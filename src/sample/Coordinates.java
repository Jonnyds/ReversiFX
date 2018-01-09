/*
 * Name: Jonathan Schwarz
 * ID: 203672910
 * Name: Noam Itzhaki
 * ID: 315773465
 */

package sample;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getCoordinatesX() {

        return this.x;
    }

    public int getCoordinatesY() {

        return this.y;
    }

    public void setCoordinatesX(int newX) {

        this.x = newX;
    }

    public void setCoordinatesY(int newY) {

        this.y = newY;
    }

}