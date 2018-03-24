public class Teritory {
static int n;
static boolean[][] egroup; //Als Gruppenmarkierung der Teritory Felder
boolean[][] used; //Bereits gezählte Felder

public Teritory(int n, Stone[][] brett){
        this.egroup = new boolean[n][n];
        this.used = new boolean[n][n];
}

public void markZone(int n, int i, int j){   //Gruppe der leeren Steine finden und markieren
        if(brett[i][j] == null) {
                egroup[i][j] = true;   //jedes Teritory Feld markieren was der umschlossenen Gruppe ist

                //rekursiv anliegende Felder als Teil der Gruppe markieren
                if(i != 0) {   //nach oben
                        if(brett[i-1][j] == null && !egroup[i-1][j]) Teritory(i-1, j);
                }
                if(i < n-1) {   //nach unten
                        if(brett[i+1][j] == null && !egroup[i+1][j]) Teritory(i+1, j);
                }
                if(j != 0) {   //nach links
                        if(brett[i][j-1] == null && !egroup[i][j-1]) Teritory(i, j-1);
                }
                if(j < n-1) {   //nach rechts
                        if(brett[i][j+1] != null && !egroup[i][j+1]) Teritory(i, j+1);
                }
        }
}
}
