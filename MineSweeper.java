/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Scanner;

/**
 *
 * @author Diogo
 */
public class MineSweeper {

    final static int MaxPositions = 9;
    final static int MaxBombs = 30;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int positions = 0, bombs = 0, row, column;
        Scanner in = new Scanner(System.in);
        System.out.println("How many rows/columns do you want to have on your game? (Maximum is 9)");
        while (positions <= 1 || positions > MaxPositions) {
            try {
                positions = in.nextInt();
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Rows/columns should be a number: " + e);
            }
        }
        System.out.println("How many bombs do you want? (Maximum bombs is 30 and bombs can't be equal or greater than rows*columns) ");
        while (bombs <= 0 || bombs > MaxBombs || bombs >= positions * positions) {
            try {
                bombs = in.nextInt();
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Bombs should be a number: " + e);
            }
        }

        Screen screen = new Screen(positions);
        screen.ScreenClear(bombs);
        boolean loseGame = false;
        screen.screenWithNumbersAndBombs = new String[positions][positions];
        for (int i = 0; i < positions; i++) {
            for (int j = 0; j < positions; j++) {
                screen.ScreenWithBombsAndNumbersStart(i, j);
            }
        }
        while (!loseGame && (screen.iterator + bombs != positions * positions)) {
            row = -1;
            column = -1;
            screen.ScreenShow();
            while (row < 0 || row > positions) {
                System.out.print("Chose a valid row: ");
                try {
                    row = in.nextInt() - 1;
                } catch (Exception e) {
                    in.nextLine();
                    System.out.println("Expected a number: " + e);
                }
            }
            while (column < 0 || column > positions) {
                System.out.print("Chose a valid column: ");
                try {
                    column = in.nextInt() - 1;
                } catch (Exception e) {
                    in.nextLine();
                    System.out.println("Expected a number: " + e);
                }
            }

            loseGame = screen.IsItABomb(row, column);

            Utils.Clear();
            screen.ScreenUpdate(row, column);
            if (screen.screenWithNumbersAndBombs[row][column].equals("0")) {
                screen.ClearSafeSpaces(row, column);
            }
        }
        screen.ScreenShow();
        if (loseGame) {
            System.out.println("\nYou lost! :(");
        } else {
            System.out.println("You win! This time...");
        }

    }

}
