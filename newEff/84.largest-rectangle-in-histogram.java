/*
 * @lc app=leetcode id=84 lang=java
 *
 * [84] Largest Rectangle in Histogram
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (29.77%)
 * Total Accepted:    158.8K
 * Total Submissions: 524.7K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * 
 * 
 * 
 * Above is a histogram where width of each bar is 1, given height =
 * [2,1,5,6,2,3].
 * 
 * 
 * 
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10
 * unit.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: [2,1,5,6,2,3]
 * Output: 10
 * 
 * 
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();

        int maxRes = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            if (stack.size() == 0 || heights[i] > heights[stack.peek()]) {
                stack.push(i);
            } else {
                int lastInd = stack.pop();
                maxRes = Math.max(maxRes, heights[lastInd] * (stack.size() == 0 ? i : (i - stack.peek() - 1)));
                i--;
            }
        }
        while (stack.size() > 0) {
            int lastInd = stack.pop();
            maxRes = Math.max(maxRes, heights[lastInd] * (stack.size() == 0 ? heights.length : heights.length - stack.peek() - 1));
        }

        return maxRes;
        
    }
}
