import java.util.*;
import java.lang.*;
import java.io.*;
public class PlayGO
{
public static void main(String[] args)
{
        Scanner sc = new Scanner(System.in);
        System.out.println("Spielfeldgröße?");
        int n = sc.nextInt();
        while(n <= 0)
        {
                System.out.println("Ungültige Größe");
                n = sc.nextInt();
        }
        sc.nextLine();
        Board spiel = new Board(n);
        System.out.println();


        for(int i = 0; i < 100; i++)
        {
                spiel.move(sc);
                System.out.println();
                spiel.kick();
                spiel.draw();
                spiel.next();
        }
}
}
