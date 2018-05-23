package planer;

class Kick {

    private int boardHeight;
    private Stone[][] gameBoard;
    private int pointsWhite = 0;
    private int pointsBlack = 0;


    Kick(int boardHeight, Stone[][] gameBoard) {
        this.boardHeight = boardHeight;
        this.gameBoard = gameBoard;
    }

    int[] andReturnPoints() {

        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                findGroup(i, j);

                if (!isGroupAlive()) {        //if group is not alive
                    realKick();           //kick group
                    resetGroup();        //reset the Group
                } else resetGroup();    //else just reset the group
            }
        }
        int[] pointsWhiteBlack = new int[2];
        pointsWhiteBlack[0] = pointsWhite;
        pointsWhiteBlack[1] = pointsBlack;

        return pointsWhiteBlack;
    }

    private void findGroup(int zeile, int spalte) {
        /* find and mark group and number of connections with other stones */
        /* call itself recursively to find and mark the group */

        if (gameBoard[zeile][spalte] != null) {
            countConnection(zeile, spalte);   //count allied neighbor stones
            gameBoard[zeile][spalte].mark();  //mark them as part of the group
            boolean steinFarbe = gameBoard[zeile][spalte].isWhite();

            //up
            try {
                if (gameBoard[zeile - 1][spalte] != null && !gameBoard[zeile - 1][spalte].group &&
                        gameBoard[zeile - 1][spalte].isWhite() == steinFarbe) findGroup(zeile - 1, spalte);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }

            //down
            try {
                if (gameBoard[zeile + 1][spalte] != null && !gameBoard[zeile + 1][spalte].group &&
                        gameBoard[zeile + 1][spalte].isWhite() == steinFarbe) findGroup(zeile + 1, spalte);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }

            //left
            try {   //nach links
                if (gameBoard[zeile][spalte - 1] != null && !gameBoard[zeile][spalte - 1].group &&
                        gameBoard[zeile][spalte - 1].isWhite() == steinFarbe) findGroup(zeile, spalte - 1);
            }
            catch(ArrayIndexOutOfBoundsException ignored){

            }

            //right
            try {
                if (gameBoard[zeile][spalte + 1] != null && !gameBoard[zeile][spalte + 1].group &&
                        gameBoard[zeile][spalte + 1].isWhite() == steinFarbe) findGroup(zeile, spalte + 1);
            }
            catch(ArrayIndexOutOfBoundsException ignored){

            }
        }
    }

    private boolean isGroupAlive() {
        int points = 0;
        int con = 0;
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (gameBoard[i][j] != null && gameBoard[i][j].group) {
                    freiSingle(i, j);
                    points += gameBoard[i][j].getFreiheit();
                    con += gameBoard[i][j].getCon();
                }
            }
        }
        points -= con;           //Freiheiten der gesamten Gruppe abzüglich deren Verbindungen

        return points != 0;
    }

    private void realKick() { //kickt alle Steine der aktuellen Gruppe
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (gameBoard[i][j] != null && gameBoard[i][j].group) {
                    if (gameBoard[i][j].isWhite()) pointsBlack++;
                    else pointsWhite++;
                    remove(i, j);
                }
            }
        }
    }

    private void countConnection(int i, int j) {
        boolean steinFarbe = gameBoard[i][j].isWhite();
        int con = 0;

        if (i != 0) {         //Anzahl der Verbindungen zu anderen gleichen Steinen finden
            if (gameBoard[i - 1][j] != null && gameBoard[i - 1][j].isWhite() == steinFarbe) con++;
        }
        if (i < boardHeight - 1) {
            if (gameBoard[i + 1][j] != null && gameBoard[i + 1][j].isWhite() == steinFarbe) con++;
        }
        if (j != 0) {
            if (gameBoard[i][j - 1] != null && gameBoard[i][j - 1].isWhite() == steinFarbe) con++;
        }
        if (j < boardHeight - 1) {
            if (gameBoard[i][j + 1] != null && gameBoard[i][j + 1].isWhite() == steinFarbe) con++;
        }
        gameBoard[i][j].setCon(con);
    }

    private void freiSingle(int i, int j)
//subtrahiert Freiheiten für Kanten und anliegende gegn. Steine
    {
        int freiheiten = 4;
        boolean steinFarbe = gameBoard[i][j].isWhite();

        if (i == 0 || i == boardHeight - 1) freiheiten -= 1; //Abzug fuer Brettkante
        if (j == 0 || j == boardHeight - 1) freiheiten -= 1;

        if (i != 0) //nach oben
        {
            if (gameBoard[i - 1][j] != null && gameBoard[i - 1][j].isWhite() == !steinFarbe) freiheiten -= 1;
        }
        if (i != boardHeight - 1) //nach unten
        {
            if (gameBoard[i + 1][j] != null && gameBoard[i + 1][j].isWhite() == !steinFarbe) freiheiten -= 1;
        }
        if (j != 0) //nach links
        {
            if (gameBoard[i][j - 1] != null && gameBoard[i][j - 1].isWhite() == !steinFarbe) freiheiten -= 1;
        }
        if (j != boardHeight - 1) //nach rechts
        {
            if (gameBoard[i][j + 1] != null && gameBoard[i][j + 1].isWhite() == !steinFarbe) freiheiten -= 1;
        }
        gameBoard[i][j].setFreiheit(freiheiten);
    }

    private void resetGroup() {
        for (int i = 0; i < boardHeight; i++) { //Reset der Gruppe
            for (int j = 0; j < boardHeight; j++) {
                if (gameBoard[i][j] != null)
                    gameBoard[i][j].unmark();
            }
        }
    }

    private void remove(int i, int j) {
        gameBoard[i][j] = null;
    }
}
