import java.util.*;
import java.lang.*;
import java.io.*;
public class InCheck {


public static int[] empty(int n, int zeile, int spalte, Stone brett[][], Scanner sc){
        int[] out = new int[2];
        while(brett[zeile][spalte] != null)   //Check ob dort bereits ein Stein liegt
        {
                System.out.println("Ungültiger Move");
                System.out.println("Zeile?");
                zeile = sc.nextInt();
                zeile = checkZeile(n, zeile, brett, sc);


                System.out.println("Ungültiger Move");
                System.out.println("Spalte?");
                spalte = sc.nextInt();
                spalte = checkSpalte(n, spalte, brett, sc);
        }
        out[0] = zeile;
        out[1] = spalte;
        return out;

}
public static int checkZeile(int n, int zeile, Stone brett[][], Scanner sc){
        while(zeile >= n || zeile < 0)
        {
                System.out.println("Ungültiger Move");
                System.out.println("Zeile?");
                zeile = sc.nextInt();

        }
        return zeile;
}

public static int checkSpalte(int n, int spalte, Stone brett[][], Scanner sc){
        while(spalte >= n || spalte < 0)
        {
                System.out.println("Ungültiger Move");
                System.out.println("Spalte?");
                spalte = sc.nextInt();

        }
        return spalte;
}
}
