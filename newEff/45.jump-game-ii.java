/*
 * [45] Jump Game II
 *
 * https://leetcode.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Hard (26.58%)
 * Total Accepted:    140.2K
 * Total Submissions: 527.1K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * Example:
 * 
 * 
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * ⁠   Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Note:
 * 
 * You can assume that you can always reach the last index.
 * 
 */
class Solution {
    public int jump(int[] nums) {

    int n = nums.length, step = 0, start = 0, end = 0;
    while (end < n - 1) {
        step++;
        int maxend = end + 1;
        for (int i = start; i <= end; i++) {
            if (i + nums[i] >= n - 1) {
                return step;
            }
            maxend = Math.max(maxend, i + nums[i]);
        }
        start = end + 1;
        end = maxend;
    }
    
    return step;



    }
    public int jump2(int[] nums) {
        if (nums == null || nums.length < 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
               if (nums[j] >= (i - j)) {
                   dp[i] = Math.min(dp[i], dp[j] + 1);
               }
            }
        }
        return dp[len - 1];
    }
}
