import java.util.*;
import java.lang.*;
import java.io.*;
public class Board
{
Stone[][] brett; //Spielbrett
boolean[][] egroup; //Als Gruppenmarkierung der Teritory Felder
boolean[][] used; //Bereits gezählte Felder
boolean white = false; //Aktueller Spieler
int n; //Spielbrett width
int pointsW = 0; //Punkte white
int pointsB = 0; //Punkte black
Scanner sc; //Zum Einlesen


public Board(int n) //Übergabe der Spielbrettgröße und erzeugen des Boards
{
        this.n = n;
        this.brett = new Stone[n][n];
        this.egroup = new boolean[n][n];
        this.used = new boolean[n][n];
}

public void move(Scanner sc)
{
        this.sc = sc;
        if(white) System.out.println("Weiß ist am Zug");
        else System.out.println("Schwarz ist am Zug");

        int[] zs = InCheck.getZeileUndSpalte(sc, n, brett);
        int zeile = zs[0];
        int spalte = zs[1];

        brett[zeile][spalte] = new Stone(white); //Stelle markieren
}

public void kick(){
        Kick bla = new Kick(n, brett);
        int[] pointsWB = Kick.andReturnPoints();
        pointsW += pointsWB[0];
        pointsB += pointsWB[1];

}

public void Teritory(int i, int j){ //Gruppe der leeren Steine finden und markieren

        if(brett[i][j] == null) {
                egroup[i][j] = true; //jedes Teritory Feld markieren was der umschlossenen Gruppe ist

                //rekursiv anliegende Felder als Teil der Gruppe markieren
                if(i != 0) { //nach oben
                        if(brett[i-1][j] == null && !egroup[i-1][j]) Teritory(i-1, j);
                }
                if(i < n-1) { //nach unten
                        if(brett[i+1][j] == null && !egroup[i+1][j]) Teritory(i+1, j);
                }
                if(j != 0) { //nach links
                        if(brett[i][j-1] == null && !egroup[i][j-1]) Teritory(i, j-1);
                }
                if(j < n-1) { //nach rechts
                        if(brett[i][j+1] != null && !egroup[i][j+1]) Teritory(i, j+1);
                }
        }
}

public void next(){
        white = !white;
}
public void draw(){
        Out.draw(n, brett);
}
}
