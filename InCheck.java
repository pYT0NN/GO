import java.util.*;
import java.lang.*;
import java.io.*;
public class InCheck {
static Scanner sc;
static int n;
static Stone[][] brett;

public InCheck(Scanner sc, int n, Stone[][] brett){
        this.sc = sc;
        this.n = n;
        this.brett = brett;

}

public static int[] getZeileUndSpalte(){
        System.out.println("Zeile?");
        int zeile = sc.nextInt();
        zeile = checkZeile(zeile); //Überprüft auf Array out of Bound exception
        System.out.println("Spalte?");
        int spalte = sc.nextInt();
        spalte = checkSpalte(spalte); //Überprüft auf Array out of Bound exception
        int[] zs = empty(zeile, spalte); //Check ob dort ein Stein liegt
        System.out.println();

        return zs;

}
public static int[] empty(int zeile, int spalte){
        int[] out = new int[2];
        while(brett[zeile][spalte] != null)   //Check ob dort bereits ein Stein liegt
        {
                System.out.println("Ungültiger Move");
                System.out.println("Zeile?");
                zeile = sc.nextInt();
                zeile = checkZeile(zeile);


                System.out.println("Ungültiger Move");
                System.out.println("Spalte?");
                spalte = sc.nextInt();
                spalte = checkSpalte(spalte);
        }
        out[0] = zeile;
        out[1] = spalte;
        return out;

}
public static int checkZeile(int zeile){
        while(zeile >= n || zeile < 0)
        {
                System.out.println("Ungültiger Move");
                System.out.println("Zeile?");
                zeile = sc.nextInt();

        }
        return zeile;
}

public static int checkSpalte(int spalte){
        while(spalte >= n || spalte < 0)
        {
                System.out.println("Ungültiger Move");
                System.out.println("Spalte?");
                spalte = sc.nextInt();

        }
        return spalte;
}
}
