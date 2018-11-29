/*
 * [673] Number of Longest Increasing Subsequence
 *
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (32.58%)
 * Total Accepted:    23.8K
 * Total Submissions: 73K
 * Testcase Example:  '[1,3,5,4,7]'
 *
 * 
 * Given an unsorted array of integers, find the number of longest increasing
 * subsequence.
 * 
 * 
 * Example 1:
 * 
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1,
 * 3, 5, 7].
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1,
 * and there are 5 subsequences' length is 1, so output 5.
 * 
 * 
 * 
 * Note:
 * Length of the given array will be not exceed 2000 and the answer is
 * guaranteed to be fit in 32-bit signed int.
 * 
 */
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i] = max(dp[j], dp[j] + 1 if nums[i] > nums[j])
        // res[i] = count of longest
        int len = nums.length;
        int[] dp = new int[len];
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            res[i] = 1;
        }
        for (int i = 1; i < len; i++) {
            int curLong = 0;
            int maxLong = 0;
            int maxCount = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    curLong = dp[j] + 1;
                } else {
                    curLong = dp[j];
                }
                if (curLong == maxLong) {
                    maxCount++;
                } else if (curLong > maxLong) {
                    maxCount = 1;
                }
            }
            res[i] = maxCount;
        }
        return res[len - 1];
    }
}
