package miniProjects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TicTacToeApp {
    static char[][] ticTacToe = new char[3][3];
    static Path path = Paths.get("D:/tmp/TicTacToe.txt");
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("Press anything to start the game");
            String start = in.nextLine();
            multiplayer();
            System.out.println("Press q to exit oherewise yoou will start a new game");
            start = in.nextLine();
            if (start.equals("q")) break;
        } while (true);

    }

    public static void multiplayer() {
        boolean exit = false;
        String player = "A";
        newGame();

        do {
            try {
                applyPlayersMoveService(player);
                exit = hasWon();
                if (exit) {
                    System.out.println("Player " + player + " won!!");
                }
                if (draw()) {
                    exit = true;
                    System.out.println("Draw!!");
                }
                if ((player.equals("A"))){
                    player = "B";
                }else {
                    player = "A";
                }
            } catch (IllegalArgumentException e) {
                System.out.println( e.getMessage());
            }


        } while (!exit);
    }

    public static void applyPlayersMoveService(String player) {
        int row;
        int column;

        try {
            System.out.println("Its " + player + " turn");
            row = Integer.parseInt(getPlayerMoveRow());
            if (!hasRightMove(row)) throw new IllegalArgumentException("This position does not ");

            column = Integer.parseInt(getPlayerMoveColumn());
            if (!hasRightMove(column)) throw new IllegalArgumentException("This position does not exists");

            if ((ticTacToe[row - 1][column - 1] == 'O' ) ||  (ticTacToe[row - 1][column - 1] == 'X' )) throw new IllegalArgumentException("Move not available");

            switch (player) {
                case "A":
                    applyPlayersMove(row, column, "A");
                    break;
                case "B":
                    applyPlayersMove(row, column, "B");
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            printGame();

        } catch (IllegalArgumentException e) {
            log(e,"this position does not exists");
            throw e;
        }

    }


    public static String getPlayer() {
        System.out.println("Please choose who is playing (A OR B)");
        return in.nextLine().trim();
    }

    public static  String getPlayerMoveRow() {
        System.out.println("Please insert row");
        return in.nextLine().trim();
    }

    public static String getPlayerMoveColumn() {
        System.out.println("Please insert column");
        return in.nextLine().trim();
    }


/*-------------------------------------------------------------------------------------------*/
    /**
     *
     */


    /**
     * Applies the move to the Tic Tac Toe game.
     * it uses i and j to  place  user's input in the tictactoe array
     *
     * @param i         The row
     * @param j         the column
     * @param player    It takes the (A or B )
     * @return          return true if the move happened
     */
    public static boolean applyPlayersMove(int i, int j, String player) {
        boolean applied = false;
        i = i - 1;
        j = j -1;
        if (ticTacToe[i][j] != '-') return false;
        if(player.equals("A")) {
            ticTacToe[i][j] = 'X';
            applied = true;
        } else if (player.equals("B")) {
            ticTacToe[i][j] = 'O';
            applied = true;
        }
        return false;
    }

    public static boolean hasRightPlayer(String player) {
        if (player.equals("Α")) return true;
        if (player.equals("B")) return true;
        return false;
    }

    public static boolean hasRightMove (int move) {
        return (move >= 1 ) && (move <= 3);
    }

    public static boolean draw() {
        for(int i = 0; i < ticTacToe.length; i++) {
            for(int j = 0; j < ticTacToe[0].length; j++) {
                 if ((ticTacToe[i][j] == '-')) return false;
            }
        }
        return true;
    }

    public static boolean hasWon() {

        for (int i = 0; i < ticTacToe.length; i++) {
            if (ticTacToe[i][0] == '-') continue;
            if ((ticTacToe[i][0] == ticTacToe[i][1]) && (ticTacToe[i][0] == ticTacToe[i][2])) {
                return true;
            }
        }

        for (int j = 0; j< ticTacToe[0].length; j++) {
            if (ticTacToe[0][j] == '-') continue;
            if ((ticTacToe[0][j] == ticTacToe[1][j]) && (ticTacToe[0][j] == ticTacToe[2][j])) {
                return true;
            }
        }

        int count = 0;
        for(int i = 0; i < ticTacToe.length - 1; i++) {
            if (ticTacToe[i][i] == '-') continue;
            if ((ticTacToe[i][i] == ticTacToe[i + 1][i + 1])) {
                count++;
            }
            if (count == 2) return true;
        }

        count = 0;
        int j = 2;
        for (int i = 0; i < ticTacToe.length - 1; i++) {
            if (ticTacToe[i][j] == '-') continue;
            if ((ticTacToe[i][j] == ticTacToe[i + 1][j - 1])) {
                count++;
            }
            if (count == 2) return true;
            j--;
        }
        return false;
    }

    public static void newGame() {
        char a = '-';
        for(int i = 0; i < ticTacToe.length; i++) {
            for(int j = 0; j < ticTacToe[0].length; j++) {
                ticTacToe[i][j] = a;
            }
        }
    }

    public static void printGame() {
        for(int i = 0; i < ticTacToe.length; i++) {
            for(int j = 0; j < ticTacToe[0].length; j++) {
                System.out.print( ticTacToe[i][j] + " " );
            }
            System.out.println();
        }
        System.out.println();
    }

    /*-------------------------------------------------------------------------------*/

    /**
     * Custom logger
     *
     * @param e
     * @param messages
     */
    public static void log(Exception e, String ... messages) {
        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))){
            ps.print(LocalDateTime.now() + "\n" + e + "\n");
            ps.printf("%s", (messages.length == 1) ? messages[0] : "");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
