/*
 * [494] Target Sum
 *
 * https://leetcode.com/problems/target-sum/description/
 *
 * algorithms
 * Medium (44.36%)
 * Total Accepted:    75.4K
 * Total Submissions: 169.9K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 
 * You are given a list of non-negative integers, a1, a2, ..., an, and a
 * target, S. Now you have 2 symbols + and -. For each integer, you should
 * choose one from + and - as its new symbol.
 * ⁠
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to
 * target S.  
 * 
 * 
 * Example 1:
 * 
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * Explanation: 
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array is positive and will not exceed 20. 
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * 
 * 
 */
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        //dp[i][j]: how many ways to add up to j, at index i
        //dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Arrays.stream(nums).sum();
        if (max < S) {
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len][max * 2 + 1];
        dp[0][nums[0] +  max] += 1;
        dp[0][max - nums[0]] += 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= 2 * max; j++) {
                if (j - nums[i] >= 0 && j - nums[i] <= 2 * max) {
                    dp[i][j] += dp[i - 1][j - nums[i]];
                }
                if (j + nums[i] >= 0 && j + nums[i] <= 2 * max) {
                    dp[i][j] += dp[i - 1][j + nums[i]];
                }
            }
        }
        return dp[len - 1][S + max];
    }
}
