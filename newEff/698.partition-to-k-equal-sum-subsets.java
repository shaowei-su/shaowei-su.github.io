/*
 * @lc app=leetcode id=698 lang=java
 *
 * [698] Partition to K Equal Sum Subsets
 *
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
 *
 * algorithms
 * Medium (40.36%)
 * Total Accepted:    36.7K
 * Total Submissions: 88.3K
 * Testcase Example:  '[4,3,2,3,5,2,1]\n4'
 *
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into k non-empty subsets whose sums are all
 * equal.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3),
 * (2,3) with equal sums.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 * 
 * 
 */
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % k != 0) {
            return false;
        }
        int cap = sum / k;
        return dfs(nums, 0, k, 0, new boolean[nums.length], cap);
    }

    public boolean dfs(int[] nums, int startInd, int k, int curSum, boolean[] visited, int capacity) {
        if (k == 1) {
            return true;
        }
        if (curSum == capacity) {
            return dfs(nums, 0, k - 1, 0, visited, capacity);
        }
        for (int i = startInd; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (dfs(nums, i + 1, k, curSum + nums[i], visited, capacity)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
