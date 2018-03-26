import java.util.*;
import java.lang.*;
import java.io.*;
public class Teritory {

static int n;
static Stone[][] brett; //Spielbrett
static boolean[][] tGroup; //Gruppenmarkierung der Teritoriums Felder
static boolean[][] used; //Bereits gezählte Felder
static boolean[][] grenze; //Markierung der äußeren Grenze des Teritoriums

public Teritory(int n, Stone[][] brett, boolean[][] used){
        this.n = n;
        this.brett = brett;
        this.used = used;
        this.tGroup = new boolean[n][n];
        this.grenze = new boolean[n][n];
}

public static int[] pointsWB(){

        int owner = 0;
        int pointsW = 0;
        int pointsB = 0;
        int count = 0;
        int[] punkteWB = new int[2];

        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        markZone(i, j); //Von aktuellem Feld ausgehend eine Gruppe von leeren Feldern finden
                        owner = isZoneWhite();
                        count = count();
                        reset();

                        if(owner == 1) pointsW += count;
                        else if(owner == -1) pointsB += count;
                }
        }
        punkteWB[0] = pointsW;
        punkteWB[1] = pointsB;
        return punkteWB;
}

public static void markZone(int i, int j){   //Gruppe des Teritoriums und seiner Grenze finden und markieren
        if(brett[i][j] == null && !isUsed(i, j)) { //Check ob das Feld leer ist und noch nicht gezählt wurde

                tGroup[i][j] = true;   //leeres Feld als Teil der zu zählenden Gruppe markieren
                used[i][j] = true; //Das Feld als gezählt markieren

                //rekursiv anliegende Felder als Teil der Gruppe markieren
                //anliegende Felder, die nicht leer sind als Teil der Grenze markieren

                if(i != 0) {   //nach oben
                        if(brett[i-1][j] == null && !tGroup[i-1][j]) markZone(i-1, j);
                        else if(brett[i-1][j] != null && !tGroup[i-1][j]) grenze[i-1][j] = true;
                }
                if(i < n-1) {   //nach unten
                        if(brett[i+1][j] == null && !tGroup[i+1][j]) markZone(i+1, j);
                        else if(brett[i-1][j] != null && !tGroup[i-1][j]) grenze[i+1][j] = true;
                }
                if(j != 0) {   //nach links
                        if(brett[i][j-1] == null && !tGroup[i][j-1]) markZone(i, j-1);
                        else if(brett[i-1][j] != null && !tGroup[i-1][j]) grenze[i][j-1] = true;
                }
                if(j < n-1) {   //nach rechts
                        if(brett[i][j+1] != null && !tGroup[i][j+1]) markZone(i, j+1);
                        else if(brett[i-1][j] != null && !tGroup[i-1][j]) grenze[i][j+1] = true;
                }
        }
}

public static int isZoneWhite(){

        boolean white = false; //Besitzer der Gruppe feststellen
        //0 für neutrale Gruppe
        //1 für weiße Gruppe
        //-1 für schwarze Gruppe

        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        if(grenze[i][j]) { //Zu einem Feld der Grenze gehen
                                white = brett[i][j].isWhite(); //Ersten Grenzstein als Farbe markieren
                                if(brett[i][j].isWhite() != white) return 0;
                                //Wenn keine einheitliche Grenze werte Zone als neutral
                        }
                }

        }
        if(white) return 1; //Wenn mit weiß durchgelaufen
        else return -1; //Wenn mit schwarz durchgelaufen

}
public static int count(){
        int count = 0;
        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        if(tGroup[i][j]) count++;
                }
        }
        return count;
}
public static void reset(){
        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        tGroup[i][j] = false;
                }
        }
}
public static boolean isUsed(int i, int j){
        return used[i][j];
}
}
