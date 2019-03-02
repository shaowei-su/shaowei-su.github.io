/*
 * @lc app=leetcode id=130 lang=java
 *
 * [130] Surrounded Regions
 *
 * https://leetcode.com/problems/surrounded-regions/description/
 *
 * algorithms
 * Medium (21.70%)
 * Total Accepted:    133.6K
 * Total Submissions: 605.2K
 * Testcase Example:  '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions
 * surrounded by 'X'.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded
 * region.
 * 
 * Example:
 * 
 * 
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * 
 * After running your function, the board should be:
 * 
 * 
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * 
 * Explanation:
 * 
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on
 * the border of the board are not flipped to 'X'. Any 'O' that is not on the
 * border and it is not connected to an 'O' on the border will be flipped to
 * 'X'. Two cells are connected if they are adjacent cells connected
 * horizontally or vertically.
 * 
 */
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return ;
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O' && isSur(board, i, j, row, col, new boolean[row][col])) {
                    flip(board, i, j, row, col, new boolean[row][col]);
                }
            }
        }
        return;
    }

    int[][] dirs = {{-1, 0}, {1 ,0}, {0, 1}, {0, -1}};

    public boolean isSur(char[][] board, int i, int j, int row, int col, boolean[][] visited) {
        if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
            return false;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (board[x][y] != 'O') {
                continue;
            }
            if (visited[x][y]) {
                continue;
            }
            if (!isSur(board, x, y, row, col, visited)) {
                return false;
            }
        }
        return true;
    }

    public void flip(char[][] board, int i, int j, int row, int col, boolean[][] visited) {
        visited[i][j] = true;
        board[i][j] = 'X';
        for (int[] dir : dirs) {
             int x = i + dir[0];
             int y = j + dir[1];
             if (x < 0 || x >= row || y < 0 || y >= col) {
                 continue;
             }
             if (board[x][y] != 'O') {
                 continue;
             }
             if (visited[x][y]) {
                 continue;
             }
             flip(board, x, y, row, col, visited);
         }
    }

}
