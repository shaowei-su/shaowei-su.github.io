/*
 * @lc app=leetcode id=361 lang=java
 *
 * [361] Bomb Enemy
 *
 * https://leetcode.com/problems/bomb-enemy/description/
 *
 * algorithms
 * Medium (42.13%)
 * Total Accepted:    30.1K
 * Total Submissions: 71.4K
 * Testcase Example:  '[["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]'
 *
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0'
 * (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted
 * point until it hits the wall since the wall is too strong to be destroyed.
 * Note: You can only put the bomb at an empty cell.
 * 
 * Example:
 * 
 * 
 * 
 * Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * Output: 3 
 * Explanation: For the given grid,
 * 
 * 0 E 0 0 
 * E 0 W E 
 * 0 E 0 0
 * 
 * Placing a bomb at (1,1) kills 3 enemies.
 * 
 * 
 */
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length; 
        int col = grid[0].length;
        int[][] count = new int[row][col];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 'E') {
                    dfs(grid, count, i, j, 0);
                    dfs(grid, count, i, j, 1);
                    dfs(grid, count, i, j, 2);
                    dfs(grid, count, i, j, 3);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                max = Math.max(max, count[i][j]);
            }
        }
        return max;
    }
    public void dfs(char[][] grid, int[][] count, int i, int j, int dir) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] == 'W') {
            return;
        }
        if (grid[i][j] == '0') {
            count[i][j]++;
        }
        switch(dir) {
            case 0:
                dfs(grid, count, i + 1, j, 0);
                break;
            case 1:
                dfs(grid, count, i - 1, j, 1);
                break;
            case 2:
                dfs(grid, count, i, j + 1, 2);
                break;
            case 3:
                dfs(grid, count, i, j - 1, 3);
                break;
            default:
                break;
        }
    }
        
}
