package com.example;

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
        // calculateNumbers();
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
        System.out.print("   ");
        for (int j = 0; j < cols; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println("  -----------------");
        for (int i = 0; i < rows; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
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
        // Initialize a 9x9 board with 10 mines.
        Minesweeper game = new Minesweeper(fieldSize, numMines);
        game.play();
        scanner.close();
    }
}
