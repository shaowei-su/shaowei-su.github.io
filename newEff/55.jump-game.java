/*
 * @lc app=leetcode id=55 lang=java
 *
 * [55] Jump Game
 *
 * https://leetcode.com/problems/jump-game/description/
 *
 * algorithms
 * Medium (30.87%)
 * Total Accepted:    242.4K
 * Total Submissions: 770.6K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * Example 1:
 * 
 * 
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last
 * index.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its
 * maximum
 * jump length is 0, which makes it impossible to reach the last index.
 * 
 * 
 */
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;


    }
    public boolean canJump3(int[] nums) {
        int len = nums.length, start = 0, end = nums[0];
        while (end < len - 1) {
            int preEnd = end;
            for (int i = start; i <= preEnd; i++) {
                if (nums[i] + i >= len - 1) {
                    return true;
                }
                end = Math.max(end, nums[i] + i);
            }
            if (preEnd == end) {
                return false;
            }
            start = preEnd;
        }
        return true;


    }
    public boolean canJump2(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len - 1];
    }
}
