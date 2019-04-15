/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 *
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (41.01%)
 * Total Accepted:    261.1K
 * Total Submissions: 621.1K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 * 
 * Example:
 * 
 * 
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * 
 */
class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int res = 0;
        int iter = 0;
        while (iter < height.length) {
            if (stack.isEmpty() || height[iter] <= height[stack.peek()]) {
                stack.push(iter);
                iter++;
            } else {
                Integer lastLow = stack.pop();
                if (!stack.isEmpty()) {
                    res += (Math.min(height[iter], height[stack.peek()]) - height[lastLow]) * (iter - stack.peek() - 1);
                }
            }
        }
        return res;
    }

    public int trap3(int[] height) {

        Deque<Integer> stack = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i);
            } else {
                Integer prev = stack.pop();
                if (stack.size() > 0) {
                    int minh = Math.min(height[i], height[stack.peek()]);
                    res += (minh - height[prev]) * (i - stack.peek() - 1);
                }
                i--;
            }
        }

        return res;
    }
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = height[0];
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        right[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += (Math.min(left[i], right[i]) - height[i]);
        }
        return res;
    }
}
