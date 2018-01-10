/*
 * Name: Jonathan Schwarz
 * ID: 203672910
 * Name: Noam Itzhaki
 * ID: 315773465
 */

package sample;

public enum DiscSymbol {
    X('X'),
    O('O'),
    E(' ');

    private char name;

    private DiscSymbol(char s) {
        this.name = s;
    }
    public char asChar() {
        return this.name;
    }
}