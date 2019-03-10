/*
 * [73] Set Matrix Zeroes
 *
 * https://leetcode.com/problems/set-matrix-zeroes/description/
 *
 * algorithms
 * Medium (37.76%)
 * Total Accepted:    165.6K
 * Total Submissions: 438.1K
 * Testcase Example:  '[[1,1,1],[1,0,1],[1,1,1]]'
 *
 * Given a m x n matrix, if an element is 0, set its entire row and column to
 * 0. Do it in-place.
 * 
 * Example 1:
 * 
 * 
 * Input: 
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * Output: 
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * Output: 
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 * 
 * 
 * Follow up:
 * 
 * 
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best
 * solution.
 * Could you devise a constant space solution?
 * 
 * 
 */
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        boolean fr = false;
        boolean fc = false;
        for (int i = 0; i <  row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if (i == 0) fr = true;
                    if (j == 0) fc = true;
                }
            }
        }
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < col; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < row; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (fr) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (fc) {
            for (int i = 0; i < row; i++) {
                matrix[i][0]= 0;
            }
        }


    }
    public void setZeroes2(int[][] matrix) {
       if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
           return;
       }
       int row = matrix.length;
       int col = matrix[0].length;
       for (int i = 0; i <  row; i++) {
           for (int j = 0; j < col; j++) {
               if (matrix[i][j] == 0) {
                   expand(matrix, i, j, row, col);
               }
           }
       }
       for (int i = 0; i < row; i++) {
           for (int j = 0; j < col; j++) {
               if (matrix[i][j] == Integer.MIN_VALUE) {
                   matrix[i][j] = 0;
               }
           }
       }
       return;
    }

    public void expand(int[][] matrix, int i, int j, int row, int col) {
        for (int m = 0; m < row; m++) {
            if (matrix[m][j] > 0) {
                matrix[m][j] = Integer.MIN_VALUE;
            }
        }
        for (int n = 0; n < col; n++) {
            if (matrix[i][n] > 0) {
                matrix[i][n] = Integer.MIN_VALUE;
            }
        }
    }
        
}
