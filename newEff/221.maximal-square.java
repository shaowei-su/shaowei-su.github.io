import java.util.*;

/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 *
 * https://leetcode.com/problems/maximal-square/description/
 *
 * algorithms
 * Medium (31.89%)
 * Total Accepted:    117.1K
 * Total Submissions: 364.4K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * 
 * Input: 
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * Output: 4
 * 
 */
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row][col];
        int res = 0;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                res = 1;
            }
        }
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                res = 1;
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] != '1') {
                    continue;
                }
                dp[i][j] = 1;
                res = Math.max(res, 1);
                
                if (dp[i - 1][j - 1] != 0) {
                    int cur = dp[i - 1][j - 1];
                    boolean invalid = false;
                    int len = 0;
                    for (int m = i - 1; m >= 0 && m >= i - cur; m--) {
                        if (matrix[m][j] != '1') {
                            invalid = true;
                            len  = i - m;
                            break;
                        }
                    }
                    if (!invalid) {
                        len = cur + 1;
                    }
                    for (int n = j - 1; n >= 0 && n >= j - cur; n--) {
                        if (matrix[i][n] != '1') {
                            invalid = true;
                            len = Math.min(len, j - n);
                            break;
                        }
                    }
                    if (!invalid) {
                        dp[i][j] = cur + 1;
                        res = Math.max(res, (cur + 1) * (cur + 1));
                    } else {
                        dp[i][j] = Math.max(1, len);
                        res = Math.max(res, dp[i][j] * dp[i][j]);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.maximalSquare(new char[][] {{'1','1','1','0','0'},{'1','1','1','0','0'},{'1','1','1','1','1'},{'0','1','1','1','1'},{'0','1','1','1','1'},{'0','1','1','1','1'}});
    }
}
