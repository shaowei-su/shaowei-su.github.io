/*
 * @lc app=leetcode id=741 lang=java
 *
 * [741] Cherry Pickup
 *
 * https://leetcode.com/problems/cherry-pickup/description/
 *
 * algorithms
 * Hard (27.00%)
 * Total Accepted:    7.3K
 * Total Submissions: 25.9K
 * Testcase Example:  '[[0,1,-1],[1,0,-1],[1,1,1]]'
 *
 * In a N x N grid representing a field of cherries, each cell is one of three
 * possible integers.
 * 
 * 
 * 
 * 
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass
 * through;
 * -1 means the cell contains a thorn that blocks your way.
 * 
 * 
 * 
 * 
 * Your task is to collect maximum number of cherries possible by following the
 * rules below:
 * 
 * 
 * 
 * 
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or
 * down through valid path cells (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through
 * valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the
 * cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries
 * can be collected.
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid =
 * [[0, 1, -1],
 * ⁠[1, 0, -1],
 * ⁠[1, 1,  1]]
 * Output: 5
 * Explanation: 
 * The player started at (0, 0) and went down, down, right right to reach (2,
 * 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes
 * [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more
 * cherry.
 * The total number of cherries picked up is 5, and this is the maximum
 * possible.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * grid is an N by N 2D array, with 1 <= N <= 50.
 * Each grid[i][j] is an integer in the set {-1, 0, 1}.
 * It is guaranteed that grid[0][0] and grid[N-1][N-1] are not
 * -1.
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int cherryPickup(int[][] grid) {
        // first pass
        //
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row + 1][col + 1];
         for (int i = 1; i <= row; i++) {
             for (int j = 1; j <= col; j++) {
                 if (grid[i - 1][j - 1] == -1) {
                     dp[i][j] = -1;
                 } else {
                     if (i == 1 && j == 1) {
                         dp[i][j] = grid[i - 1][j - 1];
                     } else if (dp[i - 1][j] == -1 && dp[i][j - 1] == -1) {
                         dp[i][j] = -1;
                     } else {
                         dp[i][j] = grid[i - 1][j - 1] + Math.max(dp[i - 1][j], dp[i][j - 1]);
                     }
                 }
             }
         }
        int res = dp[row][col];
        if (res == -1) {
            return 0;
        }

        // reset
        //
        int curI = row;
        int curJ = col;
        while (curI > 1 || curJ > 1) {
            grid[curI - 1][curJ - 1] = 0;
            if (curI == 1) {
                curJ--;
            } else if (curJ == 1) {
                curI--;
            } else {
                if (dp[curI - 1][curJ] == dp[curI][curJ] - 1) {
                    curI--;
                } else if (dp[curI][curJ - 1] == dp[curI][curJ] - 1) {
                    curJ--;
                }
            }
        }
        grid[0][0] = 0;

        // second pass
        int[][] dp2 = new int[row + 1][col + 1];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (grid[i][j] == -1) {
                    dp2[i][j] = 0;
                } else {
                    dp2[i][j] = grid[i][j] + Math.max(dp2[i + 1][j], dp2[i][j + 1]);
                }
            }
        }

        res += dp2[0][0];

        return res;
    }
}
