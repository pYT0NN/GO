import java.util.*;
import java.lang.*;
import java.io.*;
public class InCheck {

public static int[] getZeileUndSpalte(Scanner sc, int n, Stone[][] brett){
        System.out.println("Zeile?");
        int zeile = sc.nextInt();
        zeile = checkZeile(sc, n, brett, zeile); //Überprüft auf Array out of Bound exception
        System.out.println("Spalte?");
        int spalte = sc.nextInt();
        spalte = checkSpalte(sc, n, brett, spalte); //Überprüft auf Array out of Bound exception
        int[] zs = empty(sc, n, zeile, spalte, brett); //Check ob dort ein Stein liegt
        System.out.println();

        return zs;

}
public static int[] empty(Scanner sc, int n, int zeile, int spalte, Stone brett[][]){
        int[] out = new int[2];
        while(brett[zeile][spalte] != null)   //Check ob dort bereits ein Stein liegt
        {
                System.out.println("Ungültiger Move");
                System.out.println("Zeile?");
                zeile = sc.nextInt();
                zeile = checkZeile(sc, n, brett, zeile);


                System.out.println("Ungültiger Move");
                System.out.println("Spalte?");
                spalte = sc.nextInt();
                spalte = checkSpalte(sc, n, brett, spalte);
        }
        out[0] = zeile;
        out[1] = spalte;
        return out;

}
public static int checkZeile(Scanner sc, int n, Stone brett[][], int zeile){
        while(zeile >= n || zeile < 0)
        {
                System.out.println("Ungültiger Move");
                System.out.println("Zeile?");
                zeile = sc.nextInt();

        }
        return zeile;
}

public static int checkSpalte(Scanner sc, int n, Stone brett[][], int spalte){
        while(spalte >= n || spalte < 0)
        {
                System.out.println("Ungültiger Move");
                System.out.println("Spalte?");
                spalte = sc.nextInt();

        }
        return spalte;
}
}
