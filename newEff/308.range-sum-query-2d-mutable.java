/*
 * @lc app=leetcode id=308 lang=java
 *
 * [308] Range Sum Query 2D - Mutable
 *
 * https://leetcode.com/problems/range-sum-query-2d-mutable/description/
 *
 * algorithms
 * Hard (30.27%)
 * Total Accepted:    32.8K
 * Total Submissions: 107.2K
 * Testcase Example:  '["NumMatrix","sumRegion","update","sumRegion"]\n[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[3,2,2],[2,1,4,3]]'
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner (row2,
 * col2).
 * 
 * 
 * 
 * The above rectangle (with the red border) is defined by (row1, col1) = (2,
 * 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * 
 * 
 * Example:
 * 
 * Given matrix = [
 * ⁠ [3, 0, 1, 4, 2],
 * ⁠ [5, 6, 3, 2, 1],
 * ⁠ [1, 2, 0, 1, 5],
 * ⁠ [4, 1, 0, 1, 7],
 * ⁠ [1, 0, 3, 0, 5]
 * ]
 * 
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * 
 * 
 * 
 * Note:
 * 
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is
 * distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 * 
 * 
 */
class NumMatrix {
    class STree {
        STree left;
        STree right;
        int r1;
        int c1;
        int r2;
        int c2;
        int sum;
        public STree(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
    }
    STree root = null;

    public NumMatrix(int[][] matrix) {
        root = buildTree(matrix, 0, 0, matrix.length, matrix[0].length); 
    }

    public STree buildTree(int[][] matrix, int r1, int c1, int r2, int c2) {
        if (r1 > r2 || c1 > c2) {
            return null;
        } else {
            STree res = new STree(r1, c1, r2, c2);
            int m1 = r1 + (r2 - r1) / 2;
            int m2 = c1 + (c2 - c1) / 2;

    
    public void update(int row, int col, int val) {
        
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
