/*
 * [311] Sparse Matrix Multiplication
 *
 * https://leetcode.com/problems/sparse-matrix-multiplication/description/
 *
 * algorithms
 * Medium (53.57%)
 * Total Accepted:    52.4K
 * Total Submissions: 97.9K
 * Testcase Example:  '[[1,0,0],[-1,0,3]]\n[[7,0,0],[0,0,0],[0,0,1]]'
 *
 * Given two sparse matrices A and B, return the result of AB.
 * 
 * You may assume that A's column number is equal to B's row number.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * A = [
 * ⁠ [ 1, 0, 0],
 * ⁠ [-1, 0, 3]
 * ]
 * 
 * B = [
 * ⁠ [ 7, 0, 0 ],
 * ⁠ [ 0, 0, 0 ],
 * ⁠ [ 0, 0, 1 ]
 * ]
 * 
 * Output:
 * 
 * ⁠    |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 * ⁠                 | 0 0 1 |
 * 
 * 
 */
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int aRow = A.length;
        int aCol = A[0].length;
        int bRow = B.length;
        int bCol = B[0].length;

        int[][] res = new int[aRow][bCol];
        Map<Integer, Map<Integer, Integer>> matA = new HashMap<>(); // key row
        Map<Integer, Map<Integer, Integer>> matB = new HashMap<>(); // key col
        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < aCol; j++) {
                if (A[i][j] != 0) {

                Map<Integer, Integer> rowMap = matA.computeIfAbsent(i, m -> new HashMap<Integer, Integer>());
                rowMap.put(j, A[i][j]);
            
                }
            }
        }
        for (int j = 0; j < bCol; j++) {
            for (int i = 0; i < bRow; i++) {
                if (B[i][j] != 0) {
                    Map<Integer, Integer> colMap = matB.computeIfAbsent(j, m -> new HashMap<Integer, Integer>());
                    colMap.put(i, B[i][j]);
                }
            }
        }

        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < bCol; j++) {
                if (matA.get(i) == null || matB.get(j) == null) {
                    continue;
                }
                for (Integer cur : matA.get(i).keySet()) {
                    if (matB.get(j).get(cur) != null) {

                        res[i][j] += matA.get(i).get(cur) * matB.get(j).get(cur);
                
                    }
                }
            }
        }
        
        return res;
    }
}
