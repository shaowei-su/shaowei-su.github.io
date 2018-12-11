/*
 * [498] Diagonal Traverse
 *
 * https://leetcode.com/problems/diagonal-traverse/description/
 *
 * algorithms
 * Medium (44.27%)
 * Total Accepted:    32.2K
 * Total Submissions: 72.7K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a matrix of M x N elements (M rows, N columns), return all elements of
 * the matrix in diagonal order as shown in the below image.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * 
 * Output:  [1,2,4,7,5,3,6,8,9]
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The total number of elements of the given matrix will not exceed 10,000.
 * 
 */
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
       if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
           return new int[0];
       }
       int r = matrix.length;
       int c = matrix[0].length;
       int[] res = new int[r * c];
       boolean right = true;
       int pos = 0;
       int i = 0, j = 0;
       while (pos < r * c) {
        res[pos++] = matrix[i][j];
        if (right) {
            if (i > 0 && j < c - 1) {
                i--;
                j++;
            } else if (j == c - 1) {
                i++;
                right = !right;
            } else if (i == 0) {
                j++;
                right = !right;
            }
        } else {
            if (i < r - 1 && j > 0) {
                i++;
                j--;
            } else if (i == r - 1) {
                j++;
                right = !right;
            } else if (j == 0) {
                i++;
                right = !right;
            }
        }
    }
    return res;
    }
}
