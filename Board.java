import java.util.*;
import java.lang.*;
import java.io.*;
public class Board
{
Stone[][] brett; //Spielbrett
boolean[][] used; //Bereits gezählte Felder
boolean white = false; //Aktueller Spieler
int n; //Spielbrett width
int pointsW = 0; //Punkte white
int pointsB = 0; //Punkte black
Scanner sc; //Zum Einlesen


public Board(Scanner sc, int n) //Übergabe der Spielbrettgröße und Erzeugen des Boards
{
        this.sc = sc;
        this.n = n;
        this.brett = new Stone[n][n];
        this.used = new boolean[n][n];
}

public void move()
{

        if(white) System.out.println("Weiß ist am Zug"); //Zugbeginn
        else System.out.println("Schwarz ist am Zug");

        InCheck read = new InCheck(sc, n, brett);
        int[] zs = read.getZeileUndSpalte(); //Überprüft die Eingabe und gibt Zeile x Spalte in einem Array wieder
        int zeile = zs[0];
        int spalte = zs[1];

        brett[zeile][spalte] = new Stone(white); //Stelle markieren
}

public void kick(){
        Kick temp = new Kick(n, brett);
        int[] pointsWB = Kick.andReturnPoints(); //Überprüft of alle Steine leben und kickt Steine ohne Freiheiten

        pointsW += pointsWB[0]; //Gibt Weiß Punkte für geschlagene Steine
        pointsB += pointsWB[1]; //Gibt Schwarz Punkte für geschlagene Steine
}

public int[][] count(){ //Zählt Teritorium Punkte
        Teritory counter = new Teritory(n, brett, used);
        int[] pointsWB = Teritory.pointsWB();
        pointsW += pointsWB[0];
        pointsB += pointsWB[1];
}

public void draw(){ //Ausgabe des Spielbretts
        Out.draw(n, brett);
}

public void next(){ //Nächste Spieler
        white = !white;
}
}
