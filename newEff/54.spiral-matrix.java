/*
 * @lc app=leetcode id=54 lang=java
 *
 * [54] Spiral Matrix
 *
 * https://leetcode.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (29.15%)
 * Total Accepted:    211.1K
 * Total Submissions: 712.1K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * 
 * 
 * Example 2:
 * 
 * Input:
 * [
 * ⁠ [1, 2, 3, 4],
 * ⁠ [5, 6, 7, 8],
 * ⁠ [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int iu = 0, ib = row - 1, jl = 0, jr = col - 1;

        while (iu < ib && jr > jl) {
            for (int j = jl; j < jr; j++) {
                res.add(matrix[iu][j]);
            }
            for (int i = iu; i < ib; i++) {
                res.add(matrix[i][jr]);
            }
            for (int j = jr; j > jl; j--) {
                res.add(matrix[ib][j]);
            }
            for (int i = ib; i > iu; i--) {
                res.add(matrix[i][jl]);
            }
            iu++;
            jr--;
            ib--;
            jl++;
        }
        if (iu == ib && jr == jl) {
            res.add(matrix[iu][jl]);
        } else if (iu == ib) {
            for (int j = jl; j <= jr; j++) {
                res.add(matrix[iu][j]);
            }
        } else if (jl == jr) {
            for (int i = iu; i <= ib; i++) {
                res.add(matrix[i][jl]);
            }
        }
        return res;
    }
}
