import java.util.*;
import java.lang.*;
import java.io.*;
public class Teritory {

static int n;
static Stone[][] brett; //Spielbrett
static boolean[][] egroup; //Als Gruppenmarkierung der Teritory Felder
static boolean[][] used; //Bereits gezählte Felder

public Teritory(int n, Stone[][] brett, boolean[][] used){
        this.n = n;
        this.brett = brett;
        this.used = used;
        this.egroup = new boolean[n][n];
}

public static int[] pointsWB(){
        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        markZone(i, j);
                }
        }
}

public static void markZone(int i, int j){   //Gruppe der leeren Steine finden und markieren
        if(brett[i][j] == null) {
                egroup[i][j] = true;   //jedes betretene Teritory Feld markieren was umschlossen ist

                //rekursiv anliegende Felder als Teil der Gruppe markieren
                if(i != 0) {   //nach oben
                        if(brett[i-1][j] == null && !egroup[i-1][j]) markZone(i-1, j);
                }
                if(i < n-1) {   //nach unten
                        if(brett[i+1][j] == null && !egroup[i+1][j]) markZone(i+1, j);
                }
                if(j != 0) {   //nach links
                        if(brett[i][j-1] == null && !egroup[i][j-1]) markZone(i, j-1);
                }
                if(j < n-1) {   //nach rechts
                        if(brett[i][j+1] != null && !egroup[i][j+1]) markZone(i, j+1);
                }
        }
}

public static boolean isZoneWhite(int i, int j){
        boolean white;
        while(egroup[i][j] && i < n) {         //Zu einem Feld der markierten Gruppe gehen
                while(egroup[i][j] && j < n) {
                        if(!egroup[i][j]) {

                        }
                        j++;
                }

                i++;
        }

        if(i != 0) {                         //nach oben
                white = brett[i-1][j].isWhite();
        }
        else if(i < n-1) {                         //nach unten
                white = brett[i+1][j].isWhite();
        }
        else if(j != 0) {                         //nach links
                white = brett[i][j-1].isWhite();
        }
        else if(j < n-1) {                         //nach rechts
                white = brett[i][j+1].isWhite();
        }
}
}
