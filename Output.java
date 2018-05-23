package planer;

import java.lang.*;

class Output {
    static void draw(int n, Stone[][] brett) {
        System.out.print(" ");
        for (int i = 0; i < n; i++) { //Nummerierung der Spalten
            System.out.print(" " + i);
        }
        System.out.println();
        System.out.print(" ");
        for (int i = 0; i < n; i++) {
            System.out.print(" _");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(i); //Nummerierung der Zeilen
            for (int j = 0; j < n; j++) {
                if (brett[i][j] == null) System.out.print("| ");
                if (brett[i][j] != null && !brett[i][j].isWhite()) System.out.print("|o"); // o für Schwarz
                if (brett[i][j] != null && brett[i][j].isWhite()) System.out.print("|*"); // * für Weiß
                if (j + 1 == n) System.out.print("|");
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 0; i < n; i++) {
            System.out.print(" ¯");
        }
        System.out.println();
    }
    static void printCurrentPlayer(boolean isWhite) {
        if (isWhite) System.out.println("Weiß ist am Zug"); //Zugbeginn
        else System.out.println("Schwarz ist am Zug");
    }
}
