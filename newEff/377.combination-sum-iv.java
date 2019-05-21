/*
 * [377] Combination Sum IV
 *
 * https://leetcode.com/problems/combination-sum-iv/description/
 *
 * algorithms
 * Medium (43.10%)
 * Total Accepted:    67.4K
 * Total Submissions: 156.3K
 * Testcase Example:  '[1,2,3]\n4'
 *
 * ⁠Given an integer array with all positive numbers and no duplicates, find
 * the number of possible combinations that add up to a positive integer
 * target.
 * 
 * Example:
 * 
 * nums = [1, 2, 3]
 * target = 4
 * 
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 
 * Note that different sequences are counted as different combinations.
 * 
 * Therefore the output is 7.
 * 
 * 
 * 
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers? 
 * 
 * Credits:Special thanks to @pbrother for adding this problem and creating all
 * test cases.
 */
class Solution {
    int globalCount = 0;
    public int combinationSum4(int[] nums, int target) {
        if (nums == null | nums.length == 0) {
            return 0;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
            for (int i = 0; i <= target; i++) {
        for (int num : nums) {
                if (num + i <= target) {
                    dp[num + i] += dp[i];
                }
            }
        }
        return dp[target];
    }
    public void dfs(int[] nums, int target, int curSum) {
        if (target == curSum) {
            globalCount++;
            return;
        }
        for (int num : nums) {
            if (num + curSum <= target) {
                dfs(nums, target, num + curSum);
            } else {
                return;
            }
        }
        return;
    }
}
