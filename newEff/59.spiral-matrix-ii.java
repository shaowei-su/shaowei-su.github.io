/*
 * @lc app=leetcode id=59 lang=java
 *
 * [59] Spiral Matrix II
 *
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 *
 * algorithms
 * Medium (44.63%)
 * Total Accepted:    129.2K
 * Total Submissions: 283.6K
 * Testcase Example:  '3'
 *
 * Given a positive integer n, generate a square matrix filled with elements
 * from 1 to n^2 in spiral order.
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 8, 9, 4 ],
 * ⁠[ 7, 6, 5 ]
 * ]
 * 
 * 
 */
class Solution {
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return null;
        }
        int[][] res = new int[n][n];
        int curVal = 1;

        int iu = 0;
        int ib = n - 1;
        int jl = 0;
        int jr = n - 1;

        while (iu < ib && jr > jl) {
            for (int j = jl; j < jr; j++) {
                res[iu][j] = curVal++;
            }
            for (int i = iu; i < ib; i++) {
                res[i][jr] = curVal++;
            }
            for (int j = jr; j > jl; j--) {
                res[ib][j] = curVal++;
            }
            for (int i = ib; i > iu; i--) {
                res[i][jl] = curVal++;
            }
            iu++;
            ib--;
            jl++;
            jr--;
        }
        if (jl == jr && iu == ib) {
            res[iu][jl] = curVal;
        } else if (jl == jr) {
            for (int i = iu; i <= ib; i++) {
                res[i][jl] = curVal++;
            }
        } else if (iu == ib) {
            for (int j = jl; j <= jr; j++) {
                res[iu][j] = curVal++;
            }
        }
        return res;
    }
}
