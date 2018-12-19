/*
 * [463] Island Perimeter
 *
 * https://leetcode.com/problems/island-perimeter/description/
 *
 * algorithms
 * Easy (59.42%)
 * Total Accepted:    111K
 * Total Submissions: 186.9K
 * Testcase Example:  '[[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]'
 *
 * You are given a map in form of a two-dimensional integer grid where 1
 * represents land and 0 represents water.
 * 
 * Grid cells are connected horizontally/vertically (not diagonally). The grid
 * is completely surrounded by water, and there is exactly one island (i.e.,
 * one or more connected land cells).
 * 
 * The island doesn't have "lakes" (water inside that isn't connected to the
 * water around the island). One cell is a square with side length 1. The grid
 * is rectangular, width and height don't exceed 100. Determine the perimeter
 * of the island.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input:
 * [[0,1,0,0],
 * ⁠[1,1,1,0],
 * ⁠[0,1,0,0],
 * ⁠[1,1,0,0]]
 * 
 * Output: 16
 * 
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 * 
 * 
 * 
 * 
 */
class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) continue;
                if (i == 0) {
                    count++;
                } else {
                    if (grid[i - 1][j] == 0) {
                        count++;
                    }
                }
                if (i == r - 1) {
                    count++;
                } else {
                    if (grid[i + 1][j] == 0) {
                        count++;
                    }
                }
                if (j == 0) {
                    count++;
                } else {
                    if (grid[i][j - 1] == 0) {
                        count++;
                    }
                }
                if (j == c - 1) {
                    count++;
                } else {
                    if (grid[i][j + 1] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
