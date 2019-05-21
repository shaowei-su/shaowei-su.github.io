/*
 * [416] Partition Equal Subset Sum
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (39.21%)
 * Total Accepted:    67.6K
 * Total Submissions: 171.9K
 * Testcase Example:  '[1,5,11,5]'
 *
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both
 * subsets is equal.
 * 
 * 
 * Note:
 * 
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [1, 5, 11, 5]
 * 
 * Output: true
 * 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 2, 3, 5]
 * 
 * Output: false
 * 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * 
 */
class Solution {
    public boolean canPartition(int[] nums) {
          if (nums == null || nums.length == 0) {
              return false;
          }
          int sum = Arrays.stream(nums).sum();
          if (sum % 2 == 1) {
              return false;
          }
          int len = nums.length;
          boolean[] dp = new boolean[sum / 2 + 1];
          dp[0] = true;
          for (int i = 0; i < len; i++) {
              for (int j = sum / 2; j >= 0; j--) {
                  if (j - nums[i] >= 0) {
                      dp[j] = dp[j] || dp[j - nums[i]];
                  }
              }
          }
          return dp[sum / 2];




    }
    public boolean canPartition3(int[] nums) {
         if (nums == null || nums.length == 0) {
             return false;
         }
         int sum = Arrays.stream(nums).sum();
         if (sum % 2 == 1) {
             return false;
         }
         int len = nums.length;
         boolean[][] dp = new boolean[len + 1][sum / 2 + 1];
         dp[0][0] = true;
         for (int i = 1; i <= len; i++) {
             dp[i][0] = true;
         }
         for (int i = 1; i <= len; i++) {
             for (int j = 1; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
             }
         }
         return dp[len][sum / 2];
    }
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }
        int len = nums.length;
        boolean[][] dp = new boolean[len][sum / 2 + 1];
        for (int i = 0; i < len; i++) {
            if (nums[i] <= sum / 2) {
                dp[i][nums[i]] = true;
            }
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i ; j++) {
                for (int k = 0; k <= sum / 2; k++) {
                    if (k + nums[i] <= sum / 2 && dp[j][k]) {
                        dp[i][k + nums[i]] = true;
                    }
                }
            }
        }
        for (int i = 0; i < len; i++) {
            if (dp[i][sum / 2]) {
                return true;
            }
        }
        return false;
    }
}
