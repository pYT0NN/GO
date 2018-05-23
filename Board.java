package planer;

import java.lang.*;

class Board {
    private Stone[][] gameBoard; //Spielbrett
    private boolean[][] isFieldCounted; //Bereits gezählte Felder
    private boolean isWhite = false; //Aktueller Spieler
    private int boardHeight; //Spielbrett width
    private int pointsWhite = 0; //Punkte isWhite
    private int pointsBlack = 0; //Punkte black
    private Input input;


    Board(Input input) //Übergabe der Spielbrettgröße und Erzeugen des Boards
    {
        this.input = input;
        this.boardHeight = input.boardHeight;
        this.gameBoard = input.gameBoard;
        this.isFieldCounted = new boolean[boardHeight][boardHeight]; //Ist es Teil der Gruppe?
    }

    void play() {

        for (int i = 0; i < 100; i++) {
            this.move();
            this.kick();
            this.draw();
            this.next();
        }
    }

    private void move() {
        Output.printCurrentPlayer(isWhite);
        setStone(input.getRowAndColumn());
    }

    private void kick() {
        Kick kickStone = new Kick(boardHeight, gameBoard);
        int[] pointsWB = kickStone.andReturnPoints(); //Überprüft of alle Steine leben und kickt Steine ohne Freiheiten

        pointsWhite += pointsWB[0]; //Gibt Weiß Punkte für geschlagene Steine
        pointsBlack += pointsWB[1]; //Gibt Schwarz Punkte für geschlagene Steine
    }

    void count() { //Zählt Teritorium Punkte
        Teritory counter = new Teritory(boardHeight, gameBoard, isFieldCounted);
        int[] pointsWB = Teritory.pointsWB();
        pointsWhite += pointsWB[0];
        pointsBlack += pointsWB[1];
    }

    void printPoints() {
        System.out.println("Weiß Punkte: " + pointsWhite);
        System.out.println("Schwarz Punkte: " + pointsBlack);
    }

    void draw() { //Ausgabe des Spielbretts
        Output.draw(boardHeight, gameBoard);
    }

    private void next() { //Nächste Spieler
        isWhite = !isWhite;
    }


    private void setStone(int[] zeileUndSpalte) {
        gameBoard[zeileUndSpalte[0]][zeileUndSpalte[1]] = new Stone(isWhite); //Stelle markieren
    }
}
