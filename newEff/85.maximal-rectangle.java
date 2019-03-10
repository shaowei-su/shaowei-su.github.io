/*
 * @lc app=leetcode id=85 lang=java
 *
 * [85] Maximal Rectangle
 *
 * https://leetcode.com/problems/maximal-rectangle/description/
 *
 * algorithms
 * Hard (31.93%)
 * Total Accepted:    111.8K
 * Total Submissions: 345.2K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * ⁠ ["1","0","1","0","0"],
 * ⁠ ["1","0","1","1","1"],
 * ⁠ ["1","1","1","1","1"],
 * ⁠ ["1","0","0","1","0"]
 * ]
 * Output: 6
 * 
 * 
 */
class Solution {

    public int maxArea(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (stack.size() == 0 || nums[i] > nums[stack.peek()]) {
                stack.push(i);
            } else {
                int lastInd = stack.pop();
                max = Math.max(max, nums[lastInd] * (stack.size() == 0 ? i : (i - stack.peek() - 1)));
                i--;
            }
        }
        while (stack.size() > 0) {
            int lastInd = stack.pop();
            max = Math.max(max, nums[lastInd] * (stack.size() == 0 ? nums.length : (nums.length - stack.peek() - 1)));
        }
        return max;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[] heights = new int[col];
        int curMax = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            curMax = Math.max(curMax, maxArea(heights));
        }
        return curMax;

    }
}
