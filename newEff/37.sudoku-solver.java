/*
 * @lc app=leetcode id=37 lang=java
 *
 * [37] Sudoku Solver
 *
 * https://leetcode.com/problems/sudoku-solver/description/
 *
 * algorithms
 * Hard (34.94%)
 * Total Accepted:    119.1K
 * Total Submissions: 333.5K
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * A sudoku solution must satisfy all of the following rules:
 * 
 * 
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3
 * sub-boxes of the grid.
 * 
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * 
 * A sudoku puzzle...
 * 
 * 
 * ...and its solution numbers marked in red.
 * 
 * Note:
 * 
 * 
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique
 * solution.
 * The given board size is always 9x9.
 * 
 * 
 */
class Solution {
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0, board.length, board[0].length);
    }
    public boolean dfs(char[][] board, int i, int j, int row, int col) {
        if (i == row) {
            return true;
        }
        int nexti = -1;
        int nextj = -1;
        if (j < col - 1) {
            nexti = i;
            nextj = j + 1;
        } else {
            nexti = i + 1;
            nextj = 0;
        }

        if (board[i][j] != '.') {
            return dfs(board, nexti, nextj, row, col);
        } else {
            for (char c = '1'; c <= '9'; c++) {
                if (isValid(board, c, i, j)) {
                    board[i][j] = c;
                    if (dfs(board, nexti, nextj, row, col)) {
                        return true;
                    }
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

    public boolean isValid(char[][] board, char c, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) {
                return false;
            }
            if (board[i][col] == c) {
                return false;
            }
        }
        int ith = row / 3;
        int jth = col / 3;

        for (int i = ith * 3; i < (ith + 1) * 3; i++) {
            for (int j = jth * 3; j < (jth + 1) * 3; j++) {
                if (board[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }
}
