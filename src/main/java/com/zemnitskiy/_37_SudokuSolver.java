package com.zemnitskiy;

/*
    https://leetcode.com/problems/sudoku-solver/?source=submission-ac

    Write a program to solve a Sudoku puzzle by filling the empty cells.

    A sudoku solution must satisfy all of the following rules:

    Each of the digits 1-9 must occur exactly once in each row.
    Each of the digits 1-9 must occur exactly once in each column.
    Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.

    The '.' character indicates empty cells.
 */
public class _37_SudokuSolver {
    static char[][] input = {
            {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
            {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
            {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
            {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
            {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
            {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
            {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
    };

    static char[][] expected = {
            {'5', '1', '9', '7', '4', '8', '6', '3', '2'},
            {'7', '8', '3', '6', '5', '2', '4', '1', '9'},
            {'4', '2', '6', '1', '3', '9', '8', '7', '5'},
            {'3', '5', '7', '9', '8', '6', '2', '4', '1'},
            {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
            {'1', '9', '8', '5', '2', '4', '3', '6', '7'},
            {'9', '7', '5', '8', '6', '3', '1', '2', '4'},
            {'8', '3', '2', '4', '9', '1', '7', '5', '6'},
            {'6', '4', '1', '2', '7', '5', '9', '8', '3'}
    };

    public static void main(String[] args) {
        _37_SudokuSolver solver = new _37_SudokuSolver();
        solver.solveSudoku(input);
    }

    public void solveSudoku(char[][] board) {
        solve(board, 0);
    }

    private boolean solve(char[][] board, int index) {
        if (index == 9 * 9) {
            return true;
        }

        int row = index / 9;
        int col = index % 9;

        if (board[row][col] != '.') {
            return solve(board, index + 1);
        }

        for (char num = '1'; num <= '9'; num++) {
            if (canPlace(board, row, col, num)) {
                board[row][col] = num;
                if (solve(board, index + 1)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean canPlace(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num && i != col) {
                return false;
            }
            if (board[i][col] == num && i != row) {
                return false;
            }
        }

        int blockRow = row - row % 3;
        int blockCol = col - col % 3;
        for (int r = blockRow; r < blockRow + 3; r++) {
            for (int c = blockCol; c < blockCol + 3; c++) {
                if (board[r][c] == num && (r != row || c != col)) {
                    return false;
                }
            }
        }
        return true;
    }
}
