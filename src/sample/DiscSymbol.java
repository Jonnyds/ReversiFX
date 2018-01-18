/*
 * Name: Jonathan Schwarz
 * ID: 203672910
 * Name: Noam Itzhaki
 * ID: 315773465
 */

package sample;

/**
 * Enum object for placing the right symbols on the board.
 */
public enum DiscSymbol {
    X('X'),
    O('O'),
    E(' '),
    P('|'),
    M('-');

    public final char name;

    DiscSymbol(char s) {
        this.name = s;
    }
    public char asChar() {
        return this.name;
    }
}