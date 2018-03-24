import java.util.*;
import java.lang.*;
import java.io.*;
public class Kick {

static int n;
static Stone[][] brett;
static int pointsW = 0;
static int pointsB = 0;


public Kick(int n, Stone[][] brett){
        this.n = n;
        this.brett = brett;
}
public static int[] andReturnPoints(){

        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        findGroup(i, j);
                        if(!lebtGroup())  {           //wenn die Gruppe nicht lebt
                                realkick();           //kick der Gruppe
                                resetGroup();         //reset der Gruppenauswahl
                        }
                        else resetGroup();    //ansonsten nur Reset der Gruppenauswahl
                }
        }
        int[] pointsWB = new int[2];
        pointsWB[0] = pointsW;
        pointsWB[1] = pointsB;

        return pointsWB;
}

public static void findGroup(int i, int j){   //Gruppe finden und jedem Stein die anzahl der Verbindungen zuweisen

        if(brett[i][j] != null) {
                findCon(i, j);   //Anzahl der gleiche, angrenzenden Steine zählen
                brett[i][j].mark();   //jeden Stein als Teil Gruppe markieren
                boolean steinFarbe = brett[i][j].isWhite();

                //rekursiv anliegende Steine als Teil der Gruppe markieren
                if(i != 0) {   //nach oben
                        if(brett[i-1][j] != null && !brett[i-1][j].group && brett[i-1][j].isWhite() == steinFarbe) findGroup(i-1, j);
                }
                if(i < n-1) {   //nach unten
                        if(brett[i+1][j] != null && !brett[i+1][j].group && brett[i+1][j].isWhite() == steinFarbe) findGroup(i+1, j);
                }
                if(j != 0) {   //nach links
                        if(brett[i][j-1] != null && !brett[i][j-1].group && brett[i][j-1].isWhite() == steinFarbe) findGroup(i, j-1);
                }
                if(j < n-1) {   //nach rechts
                        if(brett[i][j+1] != null && !brett[i][j+1].group && brett[i][j+1].isWhite() == steinFarbe) findGroup(i, j+1);
                }
        }
}

public static boolean lebtGroup(){
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
        points -= con;           //Freiheiten der gesamten Gruppe abzüglich deren Verbindungen

        if(points == 0) return false;
        else return true;
}

public static void realkick(){ //kickt alle Steine der aktuellen Gruppe
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
public static void findCon(int i, int j){
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
public static void freiSingle(int i, int j) //subtrahiert Freiheiten für Kanten und anliegende gegn. Steine
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

public static void resetGroup(){
        for(int i = 0; i < n; i++) { //Reset der Gruppe
                for(int j = 0; j < n; j++) {
                        if(brett[i][j] != null)
                                brett[i][j].unmark();
                }
        }
}
public static void remove(int i, int j){
        brett[i][j] = null;
}
}
