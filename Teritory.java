package planer;

public class Teritory {

static int boardHeight;
static Stone[][] brett; //Spielbrett
static boolean[][] tGroup; //Gruppenmarkierung der Teritoriums Felder
static boolean[][] used; //Bereits gezählte Felder
static boolean[][] grenze; //Markierung der äußeren Grenze des Teritoriums

public Teritory(int boardHeight, Stone[][] brett, boolean[][] used){
        this.boardHeight = boardHeight;
        this.brett = brett;
        this.used = used;
        this.tGroup = new boolean[boardHeight][boardHeight];
        this.grenze = new boolean[boardHeight][boardHeight];
}

public static int[] pointsWB(){

        int owner = 0;
        int pointsW = 0;
        int pointsB = 0;
        int count = 0;
        int[] punkteWB = new int[2];

        for(int i = 0; i < boardHeight; i++) {
                for(int j = 0; j < boardHeight; j++) {
                        markZone(i, j); //Von aktuellem Feld ausgehend eine Gruppe von leeren Feldern finden
                        owner = isZoneWhite();
                        count = count();


                        if(owner == 1) pointsW += count;
                        else if(owner == -1) pointsB += count;
                        else count = 0;
                        reset();
                        count = 0;
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
                        else if(brett[i-1][j] != null) grenze[i-1][j] = true;
                }
                if(i < boardHeight -1) {   //nach unten
                        if(brett[i+1][j] == null && !tGroup[i+1][j]) markZone(i+1, j);
                        else if(brett[i+1][j] != null) grenze[i+1][j] = true;
                }
                if(j != 0) {   //nach links
                        if(brett[i][j-1] == null && !tGroup[i][j-1]) markZone(i, j-1);
                        else if(brett[i][j-1] != null) grenze[i][j-1] = true;
                }
                if(j < boardHeight -1) {   //nach rechts
                        if(brett[i][j+1] != null && !tGroup[i][j+1]) markZone(i, j+1);
                        else if(brett[i][j+1] != null) grenze[i][j+1] = true;
                }
        }
}

public static int isZoneWhite(){
        // Returns
        // 0 für neutrale Gruppe
        // 1 für weiße Gruppe
        //-1 für schwarze Gruppe

        int temp = 1337;
        for(int i = 0; i < boardHeight; i++) {
                for(int j = 0; j < boardHeight; j++) {
                        if(grenze[i][j]) { //Zu einem Feld der Grenze gehen
                                if(brett[i][j].isWhite()) temp = 1; //Ersten Grenzstein als Farbe markieren
                                else if(!brett[i][j].isWhite()) temp = 0;
                        }
                        if(temp != 1337) break; //Abbruch da ein Stein gefunden wurde
                }
                if(temp != 1337) break;
        }
        boolean white = false;
        if(temp == 1) white = true; //Besitzer der Gruppe feststellen
        else if(temp == 0) white = false;

        for(int i = 0; i < boardHeight; i++) {
                for(int j = 0; j < boardHeight; j++) {
                        if(grenze[i][j]) {
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
        for(int i = 0; i < boardHeight; i++) {
                for(int j = 0; j < boardHeight; j++) {
                        if(tGroup[i][j]) count++;
                }
        }
        return count;
}
public static void reset(){
        for(int i = 0; i < boardHeight; i++) {
                for(int j = 0; j < boardHeight; j++) {
                        tGroup[i][j] = false;
                }
        }
}
public static boolean isUsed(int i, int j){
        return used[i][j];
}
}
