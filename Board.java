import java.util.*;
import java.lang.*;
import java.io.*;
public class Board
{
int[][] brett; //Spielbret
boolean[][] group; //Poisitionen der Steine einer Gruppe
boolean play = false; //Aktueller Spieler
int n; //Spielbrett width
int con; //Anzahl an connections

public Board(int n)
{
        this.n = n;
        this.brett = new int[n][n];
        this.group = new boolean[n][n];
}

public void move(Scanner sc)
{
        if(play) System.out.println("Weiß ist am Zug"); //true = weiß
        else System.out.println("Schwarz ist am Zug"); //false = schwarz
        System.out.println("Zeile?");
        int zeile = sc.nextInt();
        while(zeile >= n || zeile < 0) //Eingabe check
        {
                System.out.println("Ungültiger Move");
                System.out.println("Zeile?");
                zeile = sc.nextInt();

        }
        System.out.println("Spalte?");
        int spalte = sc.nextInt();
        while(spalte >= n || spalte < 0)
        {
                System.out.println("Ungültiger Move");
                System.out.println("Spalte?");
                spalte = sc.nextInt();

        }
        while(brett[zeile][spalte] != 0) //check ob dort bereits ein stein liegt
        {
                System.out.println("Ungültiger Move");
                System.out.println("Zeile?");
                zeile = sc.nextInt();

                while(zeile >= n || zeile < 0)
                {
                        System.out.println("Ungültiger Move");
                        System.out.println("Zeile?");
                        zeile = sc.nextInt();

                }
                System.out.println("Ungültiger Move");
                System.out.println("Spalte?");
                spalte = sc.nextInt();
                while(spalte >= n || spalte < 0)
                {
                        System.out.println("Ungültiger Move");
                        System.out.println("Spalte?");
                        spalte = sc.nextInt();

                }

        }
        System.out.println();


        if(play) brett[zeile][spalte] = -1; //position zuweisen
        else brett[zeile][spalte] = 1;
}

public void draw()
{
        for(int i = 0; i < n; i++)
        {
                for(int j = 0; j < n; j++)
                {

                        if(brett[i][j] == 0) System.out.print("| ");
                        if(brett[i][j] == 1) System.out.print("|o");
                        if(brett[i][j] == -1) System.out.print("|*");
                        if(j+1 == n) System.out.print("|");
                }
                System.out.println();
        }
        System.out.println();
}

public int freiSingle(int zeile, int spalte) //zieht freiheiten durch gegn. Steine oder kanten ab
{
        int freiheiten = 4;
        int x = 0;

        if(play) x = -1; //abhaengig davon wer dran ist
        else x = 1;
        int y = x * (-1);

        if(zeile == 0 || zeile == 8) freiheiten -= 1; //Abzug fuer Brettkante
        if(spalte == 0 || spalte == 8) freiheiten -= 1;

        if(brett[zeile][spalte] == x) //abzug fuer umliegende gegn. Steine
        {
                if(zeile != 0)
                {
                        if(brett[zeile-1][spalte] == y) freiheiten -= 1; //nach oben
                }
                if(zeile != n-1)
                {
                        if(brett[zeile+1][spalte] == y) freiheiten -= 1; //nach unten
                }
                if(spalte != 0)
                {
                        if(brett[zeile][spalte-1] == y) freiheiten -= 1; //nach links
                }
                if(spalte != n-1)
                {
                        if(brett[zeile][spalte+1] == y) freiheiten -= 1; //nach rechts
                }
        }

        return freiheiten;
}
public void kick(){

        for(int t = 0; t < 2; t++) { //fuer jeden spieler einmal
                int x = 0;
                if(play) x = -1; //in abhaengigkeit davon wer dran ist
                else x = 1;
                int y = x * (-1);

                for(int i = 0; i < n; i++) {
                        for(int j = 0; j < n; j++) {
                                findGroup(i, j);
                                if(liberties(i, j) == 0)  { //wenn die stelle keine freiheiten hat
                                        realkick(); //kick
                                        reset();  //reset der gruppenauswahl
                                }
                                else reset();
                        }
                }
                next();
        }
}
public void findGroup(int i, int j){
        int key = brett[i][j];
        group[i][j] = true; //jedes feld rekusriv auf group markieren was teil der gruppe ist

        if(i != 0) {
                if(brett[i-1][j] == key) con++;
        }
        if(i > n-1) {
                if(brett[i+1][j] == key) con++;
        }
        if(j != 0) {
                if(brett[i][j-1] == key) con++;
        }
        if(j < n-1) {
                if(brett[i][j+1] == key) con++;
        }

        if(i != 0) {
                if(!group[i-1][j]) findGroup(i-1, j); //nach oben
        }
        if(i > n-1) {
                if(!group[i+1][j]) findGroup(i+1, j); //nach unten
        }
        if(j != 0) {
                if(!group[i][j-1]) findGroup(i, j-1); //nach links
        }
        if(j < n-1) {
                if(!group[i][j+1]) findGroup(i, j+1); //nach rechts
        }

}
public int liberties(int zeile, int spalte){
        int points = 0;
        points += freiSingle(zeile, spalte);
        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        if(i == zeile && j == spalte) break;
                        if(group[i][j]) {
                                points += freiSingle(i, j);
                        }
                }
        }
        points -= con;
        con = 0;
        return points;
}
public void realkick(){ //kickt alle steine der gruppe
        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        if(group[i][j]) {
                                remove(i, j);
                        }
                }
        }
}

public void reset(){
        for(int i = 0; i < n; i++) { //Reset des bool board
                for(int j = 0; j < n; j++) {
                        group[i][j] = false;
                }
        }
}
public void remove(int i, int j){
        brett[i][j] = 0;
}
public void next(){
        play = !play;
}
}
