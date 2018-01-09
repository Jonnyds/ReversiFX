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

    private final char name;

    private DiscSymbol(char s) {
        name = s;
    }
}