/*
 * [689] Maximum Sum of 3 Non-Overlapping Subarrays
 *
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 *
 * algorithms
 * Hard (42.67%)
 * Total Accepted:    19.6K
 * Total Submissions: 46K
 * Testcase Example:  '[1,2,1,2,6,7,5,1]\n2'
 *
 * 
 * In a given array nums of positive integers, find three non-overlapping
 * subarrays with maximum sum.
 * 
 * 
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k
 * entries.
 * 
 * 
 * Return the result as a list of indices representing the starting position of
 * each interval (0-indexed).  If there are multiple answers, return the
 * lexicographically smallest one.
 * 
 * Example:
 * 
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting
 * indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be
 * lexicographically larger.
 * 
 * 
 * 
 * Note:
 * nums.length will be between 1 and 20000.
 * nums[i] will be between 1 and 65535.
 * k will be between 1 and floor(nums.length / 3).
 * 
 */
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // dp[i][n] up to index i, with n selection: dp[i][n] = max{dp[i - 1][n], dp[j][n - 1] + sum(i,... i + k), j = {2k, ..., i - 1}}
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] res = new int[3];
        int len = nums.length;
        int[][] dp = new int[len + 1][4];

        int[] presum = new int[len + 1];
        int curSum = 0;
        for (int i = 0; i < len; i++) {
            curSum += nums[i];
            presum[i] = curSum;
        }
        int[][] id = new int[len + 1][4];

        for (int i = 1; i < 4; i++) { // how many combinations
            for (int j = k - 1; j < len; j++) { // whats the max sum at j
                int tmpmax = j - k < 0 ? presum[j] : presum[j] - presum[j - k] + dp[j - k][i - 1];
                if (j > 0) {
                    dp[j][i] = dp[j - 1][i];
                    id[j][i] = id[j - 1][i];
                }
                if (j > 0 && tmpmax > dp[j - 1][i]) {
                    dp[j][i] = tmpmax;
                    id[j][i] = j - k + 1;
                }
            }
        }
        res[2] = id[len - 1][3];
        res[1] = id[res[2] - 1][2];
        res[0] = id[res[1] - 1][1];
        return res;

        
    }
}
