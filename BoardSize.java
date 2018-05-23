package planer;

import java.util.Scanner;

 class BoardSize {
    private Scanner scanner;

    BoardSize(Scanner scanner) {
        this.scanner = scanner;
    }

    int getSize() {
        System.out.println("Spielfeldgröße?");
        int n = scanner.nextInt();
        while (n <= 0) {
            System.out.println("Ungültige Größe");
            n = scanner.nextInt();
        }
        scanner.nextLine();
        System.out.println();
        return n;
    }
}
