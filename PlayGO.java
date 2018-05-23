package planer;

import java.util.*;
import java.lang.*;

public class PlayGO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Input input = new Input(scanner);
        Board spiel = new Board(input);

        spiel.draw();
        spiel.play();
        spiel.count();
        spiel.printPoints();

    }

}
