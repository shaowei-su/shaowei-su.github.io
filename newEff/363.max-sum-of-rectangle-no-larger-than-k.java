/*
 * @lc app=leetcode id=363 lang=java
 *
 * [363] Max Sum of Rectangle No Larger Than K
 *
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/description/
 *
 * algorithms
 * Hard (34.60%)
 * Total Accepted:    24.6K
 * Total Submissions: 70.9K
 * Testcase Example:  '[[1,0,1],[0,-2,3]]\n2'
 *
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a
 * rectangle in the matrix such that its sum is no larger than k.
 * 
 * Example:
 * 
 * 
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2 
 * Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is
 * 2,
 * and 2 is the max number no larger than k (k = 2).
 * 
 * Note:
 * 
 * 
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?
 * 
 */
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            int[] levelSum = new int[col];
            for (int j = i; j < matrix.length; j++) {
                for (int m = 0; m < col; m++) {
                    levelSum[m] += matrix[j][m];
                }

                    int temp = findMax(levelSum, k);
                    max = Math.max(max, temp);
            }
        }
        return max;
    }


    public int findMax(int[] levelSum, int k) {
        int agg = 0;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0); //!!!
        int optimal = Integer.MIN_VALUE;
        for (int i = 0; i < levelSum.length; i++) {
            agg += levelSum[i];
            Integer prev = set.ceiling(agg - k);
            if (prev != null) {
                optimal = Math.max(optimal, agg - prev);
            }
            set.add(agg);
        }
        return optimal;
    }


    public int maxSumSubmatrix2(int[][] matrix, int k) {
        Map<Integer, int[]> rowPre = new HashMap<>();
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            int[] pre = new int[col + 1];
            int sum = 0;
            pre[0] = 0;
            for (int j = 0; j < col; j++) {
                sum += matrix[i][j];
                pre[j + 1] = sum;
            }
            rowPre.put(i, pre);
        }
        int max = Integer.MIN_VALUE;
        for (int m = 0; m < col; m++) {
            for (int n = m + 1; n <= col; n++) {
                for (int i = 0; i < row; i++) {
                    int curSum = 0;
                    for (int j = i; j < row; j++) {
                    curSum += (rowPre.get(j)[n] - rowPre.get(j)[m]);
                    if (curSum > max && curSum <= k) {
                        max = curSum;
                    }
                    }
                }
            }
        }
        return max;
    }
}
