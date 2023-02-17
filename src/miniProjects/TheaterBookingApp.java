package miniProjects;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class TheaterBookingApp {
   public static Scanner in = new Scanner(System.in);

    final static Path path = Paths.get("D:/tmp/log-theater.txt");
    public static boolean[][] sits = new boolean[12][31];

    public static void main(String[] args) {
        boolean quit = false;
        String s;
        do {
            menu();
            s = getChoice();
            if (s.matches("[Qq]")) quit = true;
            else {
                try {
                    handleChoice(s);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        } while (!quit);
    }

    public static void menu() {
        System.out.println("1. Book as sit");
        System.out.println("2. Cansel a booking");
        System.out.println("3. View available sits");
        System.out.println("q. Exit");
    }

    public static String getChoice() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please insert your choice");
        return in.nextLine().trim();
    }

    public static void handleChoice(String s) {
        try {
            int choice = Integer.parseInt(s);
            if (!isValidChoice(choice)) throw new IllegalArgumentException("Error - choice between 1-3");

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Choice must be between A and L capital");
                        char row = getRow();
                        int column = getColumn();
                        if ((column > 30) || (column < 0) || (!isValidRow(row))) { throw new IllegalArgumentException("This sit does not exist");}
                        bookService(row, column);
                        System.out.println("Your booking was successful");
                    } catch (IllegalArgumentException e) {
                        log(e,"Error in booking");
                        throw e;
                    } catch (InputMismatchException e) {
                        log(e);
                        throw e;
                    }
                    break;
                case 2:
                    try {
                        char row = getRow();
                        int column = getColumn();
                        if ((column > 30) || (column < 0) || (!isValidRow(row))) throw new IllegalArgumentException("This sit does not exist");
                        canselService (row, column);
                        System.out.println("Your cancellation was successful");

                    } catch (IllegalArgumentException e) {
                        log(e, "Error in cancellation");
                        throw e;
                    } catch (InputMismatchException e) {
                        log(e,"Wrong column input");
                    }
                    break;
                case 3:
                    printSits();
                    break;
                default:
                    System.out.println("Invalid choice");

            }
        } catch ( IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static boolean isValidChoice (int choice) {
        return ((choice >=1 ) && (choice <= 3));
    }

    public static boolean isValidRow (char row) {
        return ((row >= 'A' ) && (row <= 'L'));
    }

    public static char getRow() {
        try {
            System.out.println("Please insert a row");
            char inputRow = in.next().charAt(0);
            return inputRow;
        } catch (InputMismatchException e) {
            log(e);
            throw e;
        }
    }

    public static int getColumn() {
        try {
            System.out.println("Please insert a column");
            int inputColumn = in.nextInt();
            return inputColumn;
        } catch (InputMismatchException e) {
            log(e);
            throw e;
        }
    }

    public static void bookService (char row, int column) {
        try {
            if (!book(row, column)) {
                throw new IllegalArgumentException("Your booking was not successful");
            }
        } catch (IllegalArgumentException e){
            log(e);
            throw e;
        }

    }

    public static  void canselService(char row, int column) {
        try {
            if (!cancel(row, column)) {
                throw new IllegalArgumentException ("Your cancellation was not successful");
            }
        } catch (IllegalArgumentException e){
            log(e);
            throw e;
        }
    }

    public static boolean book (char row, int column) {
        int i = 0;
        boolean done = false;

        if (row == 'A') i = 0;
        if (row == 'B') i = 1;
        if (row == 'C') i = 2;
        if (row == 'D') i = 3;
        if (row == 'E') i = 4;
        if (row == 'F') i = 5;
        if (row == 'G') i = 6;
        if (row == 'H') i = 7;
        if (row == 'I') i = 8;
        if (row == 'J') i = 9;
        if (row == 'K') i = 10;
        if (row == 'L') i = 11;

        if (sits[i][column]) {
            System.out.println("This sit is not available");
        } else {
            sits[i][column] = true;
            done = true;
            System.out.println("You booked it");
        }
        return done;
    }

    public static boolean cancel(char row, int column) {
        int i = 0;
        boolean done = false;

        if (row == 'A') i = 0;
        if (row == 'B') i = 1;
        if (row == 'C') i = 2;
        if (row == 'D') i = 3;
        if (row == 'E') i = 4;
        if (row == 'F') i = 5;
        if (row == 'G') i = 6;
        if (row == 'H') i = 7;
        if (row == 'I') i = 8;
        if (row == 'J') i = 9;
        if (row == 'K') i = 10;
        if (row == 'L') i = 11;

        if (sits[i][column]) {
            sits[i][column] = false;
            done = true;
        } else {
            System.out.println("This sit is not booked");
        }
        return done;
    }

    public static void printSits() {
        for (int i = 0; i < sits.length; i++) {
            for(int j = 0; j < sits[0].length; j++) {
                if (sits[i][j] == false) {
                    System.out.print("Av ");
                } else {
                    System.out.print("Occ ");
                }
            }
            System.out.println();
        }
    }

    public static void log(Exception e, String ... messages) {
        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))){
            ps.print(LocalDateTime.now() + "\n" + e + "\n");
            ps.printf("%s", (messages.length == 1) ? messages[0] : "");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
