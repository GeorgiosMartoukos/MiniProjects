package miniProjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CombinationsOfSix {


    public static void main(String[] args)   {
        do {
            Scanner sc = new Scanner(System.in);
            String s;
            System.out.println("Press any key to start the program, except q or Q");
            System.out.println("press q or Q to quit");
            s = sc.nextLine().trim();
            if (s.matches("[qQ]") ) break;

            try {
                getCombinations();
            } catch (FileNotFoundException e) {

            }

        } while(true);



    }

    public static void getCombinations() throws FileNotFoundException {
        try {
            File inFile = new File("D:/tmp/combinationOfSixProject/numbers.txt");
            File outFile = new File("D:/tmp/combinationOfSixProject/combinations.txt");
            Scanner in = new Scanner(inFile);
            PrintStream ps = new PrintStream(outFile);
            final int N = 6;
            int[] row = new int[6];
            ArrayList <Integer> numbers = new ArrayList<>();

            while (in.hasNextInt()){
                numbers.add(in.nextInt());
            }

            for (int i = 0; i <numbers.size() - N ; i ++) {
                for (int j = i + 1; j < numbers.size() - N + 1; j++) {
                    for(int k = j + 1; k < numbers.size() - N + 2; k++) {
                        for (int m = k + 1; m < numbers.size() - N + 3; m++) {
                            for (int l = m + 1; l < numbers.size() - N + 4; l++) {
                                for (int w = l + 1; w < numbers.size() ; w++) {
                                    row[0] = numbers.get(i);
                                    row[1] = numbers.get(j);
                                    row[2] = numbers.get(k);
                                    row[3] = numbers.get(m);
                                    row[4] = numbers.get(l);
                                    row[5] = numbers.get(w);

                                    if ((isEven(row)) && (isSameEnding(row)) && (isOdd(row)) && (isContiguous(row))) {
                                        System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", row[0], row[1], row[2], row[3], row[4], row[5]);
                                        ps.printf("%d\t%d\t%d\t%d\t%d\t%d\n", row[0], row[1], row[2], row[3], row[4], row[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("It was done");
        } catch (FileNotFoundException ex) {
            throw ex;
        }

    }

    public static boolean isEven(int[] row) {
        int counter = 0;

        for (int i = 0 ; i < row.length; i++) {
            if (row[i] % 2 ==0) {
                counter++;
            }
        }

        return (counter <= 4);
    }

    public static boolean isOdd(int[] row) {
        int counter = 0;

        for (int i = 0 ; i < row.length; i++) {
            if(row[i] % 2 != 0) {
                counter++;
            }
        }
        return (counter <= 4);
    }

    public static boolean  isContiguous(int[] row) {
        int counter = 0;
        for (int i = 0 ; i < row.length - 1; i++) {
            if (row[i] == row[i + 1] + 1 ) {
                counter++;
            }
        }
        return counter <= 2;
    }

    public static boolean isSameEnding(int[] row) {
        int[] counter = new int[10];
        int[] alfa = new int[6];

        for (int i = 0 ; i < row.length ; i++) {
            alfa[i] = row[i] % 10;
        }

        for(int i = 0; i < alfa.length; i++) {
            counter[alfa[i]]++;
            if (counter[alfa[i]] > 3) {
                return false;
            }
        }
        return true;
    }
/*
    public static boolean isSame10th(int[] row) {

    }*/



}
