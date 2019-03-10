/*
 * @lc app=leetcode id=48 lang=java
 *
 * [48] Rotate Image
 *
 * https://leetcode.com/problems/rotate-image/description/
 *
 * algorithms
 * Medium (45.88%)
 * Total Accepted:    228.1K
 * Total Submissions: 485.7K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Note:
 * 
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly. DO NOT allocate another 2D matrix and do the
 * rotation.
 * 
 * Example 1:
 * 
 * 
 * Given input matrix = 
 * [
 * ⁠ [1,2,3],
 * ⁠ [4,5,6],
 * ⁠ [7,8,9]
 * ],
 * 
 * rotate the input matrix in-place such that it becomes:
 * [
 * ⁠ [7,4,1],
 * ⁠ [8,5,2],
 * ⁠ [9,6,3]
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Given input matrix =
 * [
 * ⁠ [ 5, 1, 9,11],
 * ⁠ [ 2, 4, 8,10],
 * ⁠ [13, 3, 6, 7],
 * ⁠ [15,14,12,16]
 * ], 
 * 
 * rotate the input matrix in-place such that it becomes:
 * [
 * ⁠ [15,13, 2, 5],
 * ⁠ [14, 3, 4, 1],
 * ⁠ [12, 6, 8, 9],
 * ⁠ [16, 7,10,11]
 * ]
 * 
 * 
 */
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int rowEnds = row % 2 == 0 ? row / 2 : row / 2 + 1;

        for (int i = 0; i < rowEnds; i++) {
            for (int j = i; j < (col - i - 1); j++) {
                int cur = matrix[i][j];

                // right
                int iR = j;
                int jR = row - 1 - i;
                int tmp = matrix[iR][jR];
                matrix[iR][jR] = cur;
                cur = tmp;

                // bottom
                int iB = row - 1 - i;
                int jB = row - 1 - j;
                tmp = matrix[iB][jB];
                matrix[iB][jB] = cur;
                cur = tmp;

                // left
                int iL = row - 1 - j;
                int jL = i;
                tmp = matrix[iL][jL];
                matrix[iL][jL] = cur;
                cur = tmp;

                // up
                matrix[i][j] = cur;
            }
        }

        return;

    }
}
