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

        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        findGroup(i, j);
                        if(!lebtGroup())  {         //wenn die Gruppe nicht lebt
                                realkick();         //kick der Gruppe
                                resetGroup();       //reset der Gruppenauswahl
                        }
                        else resetGroup();  //ansonsten nur Reset der Gruppenauswahl
                }
        }
}
public void findGroup(int i, int j){ //Gruppe finden und jedem Stein die anzahl der Verbindungen zuweisen

        if(brett[i][j] != null) {
                findCon(i, j); //Anzahl der gleiche, angrenzenden Steine zählen
                brett[i][j].mark(); //jeden Stein als Teil Gruppe markieren
                boolean steinFarbe = brett[i][j].isWhite();

                //rekursiv anliegende Steine als Teil der Gruppe markieren
                if(i != 0) { //nach oben
                        if(brett[i-1][j] != null && !brett[i-1][j].group && brett[i-1][j].isWhite() == steinFarbe) findGroup(i-1, j);
                }
                if(i < n-1) { //nach unten
                        if(brett[i+1][j] != null && !brett[i+1][j].group && brett[i+1][j].isWhite() == steinFarbe) findGroup(i+1, j);
                }
                if(j != 0) { //nach links
                        if(brett[i][j-1] != null && !brett[i][j-1].group && brett[i][j-1].isWhite() == steinFarbe) findGroup(i, j-1);
                }
                if(j < n-1) { //nach rechts
                        if(brett[i][j+1] != null && !brett[i][j+1].group && brett[i][j+1].isWhite() == steinFarbe) findGroup(i, j+1);
                }
        }
}

public boolean lebtGroup(){
        int points = 0;
        int con = 0;
        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        if(brett[i][j] != null && brett[i][j].group) {
                                freiSingle(i, j);
                                points += brett[i][j].getFreiheit();
                                con += brett[i][j].getCon();
                        }
                }
        }
        points -= con;         //Freiheiten der gesamten Gruppe abzüglich deren Verbindungen

        if(points == 0) return false;
        else return true;
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

public void realkick(){ //kickt alle steine der aktuellen gruppe
        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        if(brett[i][j] != null && brett[i][j].group) {
                                if(brett[i][j].isWhite()) pointsB++;
                                else pointsW++;
                                remove(i, j);
                        }
                }
        }
}
public void findCon(int i, int j){
        boolean steinFarbe = brett[i][j].isWhite();
        int con = 0;

        if(i != 0) {         //Anzahl der Verbindungen zu anderen gleichen Steinen finden
                if(brett[i-1][j] != null && brett[i-1][j].isWhite() == steinFarbe) con++;
        }
        if(i < n-1) {
                if(brett[i+1][j] != null && brett[i+1][j].isWhite() == steinFarbe) con++;
        }
        if(j != 0) {
                if(brett[i][j-1] != null && brett[i][j-1].isWhite() == steinFarbe) con++;
        }
        if(j < n-1) {
                if(brett[i][j+1] != null && brett[i][j+1].isWhite() == steinFarbe) con++;
        }
        brett[i][j].setCon(con);
}
public void freiSingle(int i, int j) //subtrahiert Freiheiten für Kanten und anliegende gegn. Steine
{
        int freiheiten = 4;
        boolean steinFarbe = brett[i][j].isWhite();

        if(i == 0 || i == n-1) freiheiten -= 1; //Abzug fuer Brettkante
        if(j == 0 || j == n-1) freiheiten -= 1;

        if(i != 0) //nach oben
        {
                if(brett[i-1][j] != null && brett[i-1][j].isWhite() == !steinFarbe) freiheiten -= 1;
        }
        if(i != n-1) //nach unten
        {
                if(brett[i+1][j] != null && brett[i+1][j].isWhite() == !steinFarbe) freiheiten -= 1;
        }
        if(j != 0) //nach links
        {
                if(brett[i][j-1] != null && brett[i][j-1].isWhite() == !steinFarbe) freiheiten -= 1;
        }
        if(j != n-1) //nach rechts
        {
                if(brett[i][j+1] != null && brett[i][j+1].isWhite() == !steinFarbe) freiheiten -= 1;
        }
        brett[i][j].setFreiheit(freiheiten);
}

public void resetGroup(){
        for(int i = 0; i < n; i++) { //Reset der Gruppe
                for(int j = 0; j < n; j++) {
                        if(brett[i][j] != null)
                                brett[i][j].unmark();
                }
        }
}
public void remove(int i, int j){
        brett[i][j] = null;
}
public void next(){
        white = !white;
}
public void draw(){
        Out.draw(n, brett);
}
}
