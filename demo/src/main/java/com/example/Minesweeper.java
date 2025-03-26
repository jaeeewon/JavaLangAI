package com.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    private final int rows;
    private final int cols;
    private final int totalMines;
    private final char[][] board; // Visible board for the player
    private final int[][] mineBoard; // Underlying board: -1 for mine, otherwise number of adjacent mines
    private final boolean[][] revealed;
    private final boolean[][] flagged;
    private static final int[][] NEARBY = {
            { -1, -1 }, { -1, 0 }, { -1, 1 },
            { 0, -1 }, { 0, 1 },
            { 1, -1 }, { 1, 0 }, { 1, 1 }
    };

    public Minesweeper(int size, int totalMines) {
        this.rows = size;
        this.cols = size;
        this.totalMines = totalMines;
        board = new char[size][size];
        mineBoard = new int[size][size];
        revealed = new boolean[size][size];
        flagged = new boolean[size][size];
        initializeBoard();
        placeMines();
        calculateNumbers();
    }

    private void calculateNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineBoard[i][j] == -1)
                    continue;

                for (int[] dir : NEARBY) {
                    int ni = i + dir[0], nj = j + dir[1];
                    if (ni >= 0 && nj >= 0 && ni < rows && nj < cols && mineBoard[ni][nj] == -1) {
                        mineBoard[i][j]++;
                    }
                }
            }
        }
    }

    // Randomly place mines on the board.
    private void placeMines() {
        Random rand = new Random();
        int placedMines = 0;
        while (placedMines < totalMines) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (mineBoard[r][c] != -1) {
                mineBoard[r][c] = -1;
                placedMines++;
            }
        }
    }

    // Clear the console output using ANSI escape sequences.
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Initialize the visible board with default characters.
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Display the current state of the visible board.
    private void printBoard() {
        // Print column indices.
        System.out.print("    ");
        for (int j = 0; j < cols; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println("   -------------------");
        int revealedCount = 0;
        for (int i = 0; i < rows; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
                revealedCount += (board[i][j] >= '0') ? 1 : 0;
            }
            System.out.println(Arrays.toString(mineBoard[i]));
        }
        System.out.printf("%d tiles left to win the game!\n", this.rows * this.cols - revealedCount - this.totalMines);
    }

    private void openNearby(int i, int j) {
        // 재귀적으로 오픈
        if (i < 0 || j < 0 || i >= rows || j >= cols || mineBoard[i][j] == -1 || board[i][j] != '.')
            return;

        board[i][j] = (char) (mineBoard[i][j] + '0');
        revealed[i][j] = true;

        if (mineBoard[i][j] == 0) { // 지뢰 근처 아닐 때만
            for (int[] dir : NEARBY) {
                openNearby(i + dir[0], j + dir[1]);
            }
        }
    }

    private boolean isGameEnd() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineBoard[i][j] != -1 && !revealed[i][j]) {
                    // System.out.printf("[%d][%d] is not revealed though it is 지뢰", i, j);
                    return false;
                }
            }
        }
        return true;
    }

    // Main game loop.
    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
        clearScreen();
        while (gameRunning) {
            printBoard();
            System.out.print("Enter command (r row col to reveal, f row col to flag): ");
            String command = scanner.next();
            int r = scanner.nextInt();
            int c = scanner.nextInt();

            if (r < this.rows && c < this.cols) {
                if (command.equals("r")) {
                    if (mineBoard[r][c] == -1) {
                        gameRunning = false;
                        System.out.println("YOU LOSE!");
                        continue;
                    } else {
                        openNearby(r, c);
                        if (isGameEnd()) {
                            System.out.printf("YOU WIN!");
                            break;
                        }
                    }
                } else if (command.equals("f")) {
                    if (revealed[r][c]) {
                        System.out.println("THIS POINT IS REVEALED!");
                        continue;
                    } else {
                        flagged[r][c] = true;
                        board[r][c] = 'F';
                    }
                }
            }

            clearScreen();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("The field size: ");
        int fieldSize = scanner.nextInt();
        System.out.print("The number of mines: ");
        int numMines = scanner.nextInt();
        // int fieldSize = 9; // hard-coded
        // int numMines = 10; // hard-coded
        // Initialize a 9x9 board with 10 mines.
        Minesweeper game = new Minesweeper(fieldSize, numMines);
        game.play();
        scanner.close();
    }
}
