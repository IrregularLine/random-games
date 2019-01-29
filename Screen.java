/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Random;

/**
 *
 * @author Diogo
 */
public class Screen {

    Random randomNumber = new Random();

    private final int positions;
    private String[][] screen;
    private String[][] screenUser;
    String[][] screenWithNumbersAndBombs;
    private int bombsAround;
    int iterator;

    public Screen(int positions) {
        iterator = 0;
        this.positions = positions;
        screen = new String[positions][positions];
        screenUser = new String[positions][positions];
    }

    public String[][] getScreen() {
        return screen;
    }

    public void setScreen(String[][] screen) {
        this.screen = screen;
    }

    public int getPositions() {
        return positions;
    }

    /**
     * Clears the screen possibly for a new game.
     *
     * @param bombs Numbers of bombs,
     */
    public void ScreenClear(int bombs) {
        int x, y;
        for (int i = 0; i < positions; i++) {
            for (int j = 0; j < positions; j++) {
                screenUser[i][j] = "??";
                screen[i][j] = "??";
            }
        }
        for (int i = 0; i < bombs; i++) {
            x = randomNumber.nextInt(positions);
            y = randomNumber.nextInt(positions);
//just wanted to be fancy and use the '?' operator
            i = screen[x][y].equalsIgnoreCase("bomb") ? --i : i;
//            if (screen[x][y].equals("bomb")) {
//                i--;
//            }
            screen[x][y] = "bomb";
        }
    }

    public void ScreenShow() {
        System.out.printf("%7s", "");
        for (int j = 0; j < positions; j++) {
            System.out.printf("%s%d%s", "Col ", j + 1, " ");
        }
        System.out.println();
        for (int i = 0; i < positions; i++) {
            System.out.printf("%s%d%s", "Row ", i + 1, "->");
            for (int j = 0; j < positions; j++) {
                System.out.printf("%6s", screenUser[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean IsItABomb(int row, int column) {
        return screen[row][column].equalsIgnoreCase("bomb");
    }

    public void ScreenUpdate(int row, int column) {
        iterator++;
        if (screen[row][column].equals("bomb")) {
            screenUser[row][column] = "bomb";
        } else {
            bombsAround = 0;
            if (row - 1 >= 0 && column - 1 >= 0 && screen[row - 1][column - 1].equals("bomb")) {
                bombsAround++;
            }
            if (row - 1 >= 0 && column >= 0 && screen[row - 1][column].equals("bomb")) {
                bombsAround++;
            }
            if (row - 1 >= 0 && column + 1 < positions && screen[row - 1][column + 1].equals("bomb")) {
                bombsAround++;
            }
            if (row >= 0 && column - 1 >= 0 && screen[row][column - 1].equals("bomb")) {
                bombsAround++;
            }
            if (row >= 0 && column + 1 < positions && screen[row][column + 1].equals("bomb")) {
                bombsAround++;
            }
            if (row + 1 < positions && column - 1 >= 0 && screen[row + 1][column - 1].equals("bomb")) {
                bombsAround++;
            }

            if (row + 1 < positions && column + 1 < positions && screen[row + 1][column + 1].equals("bomb")) {
                bombsAround++;
            }
            if (row + 1 < positions && column >= 0 && screen[row + 1][column].equals("bomb")) {
                bombsAround++;
            }
            screenUser[row][column] = String.valueOf(bombsAround);
        }
    }

    /**
     * After the player picks a place, clears white spaces if the player picked
     * a position with no bombs around that space
     *
     * @param row
     * @param column
     */
    void ClearSafeSpaces(int row, int column) {
        screenUser[row][column] = "0";

        if (row - 1 >= 0 && column - 1 >= 0) {
            iterator++;
            if (screenWithNumbersAndBombs[row - 1][column - 1].equals("0") && !screenWithNumbersAndBombs[row - 1][column - 1].equals(screenUser[row - 1][column - 1])) {
                ClearSafeSpaces(row - 1, column - 1);
            } else {
                screenUser[row - 1][column - 1] = screenWithNumbersAndBombs[row - 1][column - 1];
            }
        }
        if (row - 1 >= 0 && column >= 0) {
            iterator++;
            if (screenWithNumbersAndBombs[row - 1][column].equals("0") && !screenWithNumbersAndBombs[row - 1][column].equals(screenUser[row - 1][column])) {
                ClearSafeSpaces(row - 1, column);
            } else {
                screenUser[row - 1][column] = screenWithNumbersAndBombs[row - 1][column];
            }
        }
        if (row - 1 >= 0 && column + 1 < positions) {
            iterator++;
            if (screenWithNumbersAndBombs[row - 1][column + 1].equals("0") && !screenWithNumbersAndBombs[row - 1][column + 1].equals(screenUser[row - 1][column + 1])) {
                ClearSafeSpaces(row - 1, column + 1);
            } else {
                screenUser[row - 1][column + 1] = screenWithNumbersAndBombs[row - 1][column + 1];
            }
        }
        if (row >= 0 && column - 1 >= 0) {
            iterator++;
            if (screenWithNumbersAndBombs[row][column - 1].equals("0") && !screenWithNumbersAndBombs[row][column - 1].equals(screenUser[row][column - 1])) {
                ClearSafeSpaces(row, column - 1);
            } else {
                screenUser[row][column - 1] = screenWithNumbersAndBombs[row][column - 1];
            }
        }
        if (row >= 0 && column + 1 < positions) {
            iterator++;
            if (screenWithNumbersAndBombs[row][column + 1].equals("0") && !screenWithNumbersAndBombs[row][column + 1].equals(screenUser[row][column + 1])) {
                ClearSafeSpaces(row, column + 1);
            } else {
                screenUser[row][column + 1] = screenWithNumbersAndBombs[row][column + 1];
            }
        }
        if (row + 1 < positions && column - 1 >= 0) {
            iterator++;
            if (screenWithNumbersAndBombs[row + 1][column - 1].equals("0") && !screenWithNumbersAndBombs[row + 1][column - 1].equals(screenUser[row + 1][column - 1])) {
                ClearSafeSpaces(row + 1, column - 1);
            } else {
                screenUser[row + 1][column - 1] = screenWithNumbersAndBombs[row + 1][column - 1];
            }
        }

        if (row + 1 < positions && column + 1 < positions) {
            iterator++;
            if (screenWithNumbersAndBombs[row + 1][column + 1].equals("0") && !screenWithNumbersAndBombs[row + 1][column + 1].equals(screenUser[row + 1][column + 1])) {
                ClearSafeSpaces(row + 1, column + 1);
            } else {
                screenUser[row + 1][column + 1] = screenWithNumbersAndBombs[row + 1][column + 1];
            }
        }
        if (row + 1 < positions && column >= 0) {
            iterator++;
            if (screenWithNumbersAndBombs[row + 1][column].equals("0") && !screenWithNumbersAndBombs[row + 1][column].equals(screenUser[row + 1][column])) {
                ClearSafeSpaces(row + 1, column);
            } else {
                screenUser[row + 1][column] = screenWithNumbersAndBombs[row + 1][column];
            }
        }

    }

    public void ScreenWithBombsAndNumbersStart(int row, int column) {
        if (screen[row][column].equals("bomb")) {
            screenWithNumbersAndBombs[row][column] = "bomb";
        } else {
            bombsAround = 0;
            if (row - 1 >= 0 && column - 1 >= 0 && screen[row - 1][column - 1].equals("bomb")) {
                bombsAround++;
            }
            if (row - 1 >= 0 && column >= 0 && screen[row - 1][column].equals("bomb")) {
                bombsAround++;
            }
            if (row - 1 >= 0 && column + 1 < positions && screen[row - 1][column + 1].equals("bomb")) {
                bombsAround++;
            }
            if (row >= 0 && column - 1 >= 0 && screen[row][column - 1].equals("bomb")) {
                bombsAround++;
            }
            if (row >= 0 && column + 1 < positions && screen[row][column + 1].equals("bomb")) {
                bombsAround++;
            }
            if (row + 1 < positions && column - 1 >= 0 && screen[row + 1][column - 1].equals("bomb")) {
                bombsAround++;
            }

            if (row + 1 < positions && column + 1 < positions && screen[row + 1][column + 1].equals("bomb")) {
                bombsAround++;
            }
            if (row + 1 < positions && column >= 0 && screen[row + 1][column].equals("bomb")) {
                bombsAround++;
            }
            screenWithNumbersAndBombs[row][column] = String.valueOf(bombsAround);
        }
    }
}
