package planer;

import java.util.*;
import java.lang.*;

class Input {
    private Scanner scanner;
    int boardHeight;
    Stone[][] gameBoard;

    Input(Scanner scanner) {
        this.scanner = scanner;

        BoardSize boardSize = new BoardSize(scanner);
        this.boardHeight = boardSize.getSize();

        createGameBoard();

    }

    private void createGameBoard() {
        this.gameBoard = new Stone[boardHeight][boardHeight];
    }

    int[] getRowAndColumn() {
        return isMoveValid(getRow(), getColumn());
    }

    private int getRow() {
        System.out.println("Row??");
        int row = scanner.nextInt();
        row = checkZeile(row);
        return row;
    }

    private int getColumn() {
        System.out.println("Column?");
        int column = scanner.nextInt();
        column = checkSpalte(column);
        return column;
    }

    private int[] isMoveValid(int zeile, int spalte) {
        int[] out = new int[2];
        while (gameBoard[zeile][spalte] != null)   //Solange kein freies Feld ausgewählt wurde
        {
            System.out.println("Invalid Move");
            zeile = getRow();

            System.out.println("Invalid Move");
            spalte = getColumn();
        }

        out[0] = zeile;
        out[1] = spalte;
        return out;

    }

    private int checkZeile(int zeile) {
        while (zeile >= boardHeight || zeile < 0) {
            System.out.println("Ungültiger Move");
            System.out.println("Zeile?");
            zeile = scanner.nextInt();

        }
        return zeile;
    }

    private int checkSpalte(int spalte) {
        while (spalte >= boardHeight || spalte < 0) {
            System.out.println("Ungültiger Move");
            System.out.println("Spalte?");
            spalte = scanner.nextInt();

        }
        return spalte;
    }
}
